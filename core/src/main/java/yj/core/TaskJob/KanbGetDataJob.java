package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.OracleConn.OracleConn;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cust.dto.Cust;
import yj.core.cust.service.ICustService;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;
import yj.kanb.kbtest.service.IKbtestService;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;
import yj.kanb.viewdataschemaline.service.IViewdataschemalineService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  看板后台取数任务
 */
public class KanbGetDataJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
    @Autowired
    private IKbtestService kbtestService;
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IVblinegroupheaderService vblinegroupheaderService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IViewdataschemalineService viewdataschemalineService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICustService custService;
    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return false;
    }

    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        String msg = "KanbGetDataJob Test<insertNewData>! - . jobKey:" + key + ", triggerKey:" + triggerKey + ", execTime:" + new Date();
        log.info(msg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curdate = new Date();
        String erdat = sdf.format(curdate).substring(0,10);
        List<Curlzk> listcurlzk = new ArrayList<>();
        listcurlzk = curlzkService.selectAllLinesForKanbByErdat(erdat);

        List<Vblinegroupheader> listvbgh = new ArrayList<>();
        listvbgh = vblinegroupheaderService.selectAllGroup();

        if (listvbgh.size() > 0 && listcurlzk.size() > 0){


            for (int i=0;i<listvbgh.size();i++){
                Viewdataschemaline viewdata = new Viewdataschemaline();
                Double outnum = 0D;
                Double actnum = 0D;
                Double plqty = 0D;
                Double takt_time = 0D;
                String l_error= "E";

                for (int j=0;j<listcurlzk.size();j++){

                    if (listcurlzk.get(j).getDeptId().equals(listvbgh.get(i).getWorkshopId()) && listcurlzk.get(j).getMatnrjj().equals(listvbgh.get(i).getProduct())){
                        //根据产品 取客户信息
                        ProductsCfg productsCfg = productsCfgService.selectByLineidAndPMatnr(listcurlzk.get(j).getLineId(),listcurlzk.get(j).getMatnrjj());

                        //根据客户编码取客户描述 名称
                        Cust cust = custService.selectByKunnr(productsCfg.getKunnr());

                        viewdata.setKunnr(cust.getKunnr());
                        viewdata.setName1(cust.getName1());
                        viewdata.setSortl(cust.getSortl());

                        viewdata.setClassgrp(listcurlzk.get(j).getShift());//班组
                        //1:根据生产线当前流转卡号 获取流转卡数据
                        Cardh cardh = new Cardh();
                        String zpgdbar = listcurlzk.get(j).getZpgdbar();
                        cardh = cardhService.selectByBarcode(zpgdbar);

                        if (cardh == null){
                            System.out.println("没有获取到当前流转卡信息 跳出循环**********************************");
                            continue;//如果没有获取到当前流转卡信息 跳出循环
                        }else{
                            plqty = plqty + cardh.getPlqty();
                        }
                        //2：获取产品信息
                        Marc marc= new Marc();
                        marc = marcService.selectByMatnr(cardh.getMatnr());
                        viewdata.setMatnr(marc.getMatnr());
                        viewdata.setMaktx(marc.getMaktx());
                        System.out.println("获取物料信息**********************************");

                        //2.1 计算班次时间    白班 08:00:00-15:59:59   中班 16:00-23:59:59 夜班 00:00:00-07:59:59
                        System.out.println("计算班次 及班次开始日期**********************************");
                        String lastupdatestr = listcurlzk.get(j).getLastUpdateDateStr().substring(0,19);//当前流转卡切换的时间
                        String ds1 = lastupdatestr.substring(0,10) + " 08:00:00";
                        Date d1 = null;
                        try {
                            d1 = sdf2.parse(ds1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String ds2 = lastupdatestr.substring(0,10) + " 15:59:59";
                        Date d2 = null;
                        try {
                            d2 = sdf2.parse(ds2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String ds3 = lastupdatestr.substring(0,10) + " 16:00:00";
                        Date d3 = null;
                        try {
                            d3 = sdf2.parse(ds3);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String ds4 = lastupdatestr.substring(0,10) + " 23:59:59";
                        Date d4 = null;
                        try {
                            d4 = sdf2.parse(ds4);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String ds5 = lastupdatestr.substring(0,10) + " 00:00:00";
                        Date d5 = null;
                        try {
                            d5 = sdf2.parse(ds5);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String ds6 = lastupdatestr.substring(0,10) + " 07:59:59";
                        Date d6 = null;
                        try {
                            d6 = sdf2.parse(ds6);
                        } catch (ParseException e){
                            e.printStackTrace();
                        }

                        Date lastupdate = sdf2.parse(lastupdatestr);

                        if (lastupdate.getTime() >= d1.getTime() && lastupdate.getTime() <= d2.getTime()){
                            viewdata.setShift("1");
                            viewdata.setShiftdes("白班");
                            viewdata.setShifttimebegin(ds1);
                        }

                        if (lastupdate.getTime() >= d3.getTime() && lastupdate.getTime() <= d4.getTime()){
                            viewdata.setShift("2");
                            viewdata.setShiftdes("中班");
                            viewdata.setShifttimebegin(ds1);
                        }

                        if (lastupdate.getTime() >= d5.getTime() && lastupdate.getTime() <= d6.getTime()){
                            viewdata.setShift("2");
                            viewdata.setShiftdes("夜班");
                            viewdata.setShifttimebegin(ds1);
                        }

                        //4:取产线配置数据
                        Lines lines = new Lines();
                        lines = linesService.selectById(Long.parseLong(listcurlzk.get(j).getLineId()));
                        takt_time = takt_time + lines.getTaktTime();

                        Long lineId = 0L;
                        if (lines.getPlineId() != null){
                            lineId = lines.getPlineId();
                        }else{
                            lineId = Long.parseLong(listcurlzk.get(j).getLineId());
                        }

                        //3：去装箱数据
                        ServerSetting serverSetting = new ServerSetting();
                        serverSetting = serverSettingService.selectByLineId(listvbgh.get(i).getWorks(),lineId.toString());
                        WebServerHelp webServerHelp = new WebServerHelp();
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                        String sqlzx = "select a.main_id from "+serverSetting.getDbUsername()+".wip_pallet_sn_rel  a"
                                +" inner join  "+serverSetting.getDbUsername()+".wip_main_data b on a.main_id = b.main_id";
                        String where = " where a.line_id = " + "'" + listcurlzk.get(j).getLineId() + "' and  a.status = '0' and b.ENABLE_FLAG = '1' ";
                        where = where + "and b.item_code = " + "'" + listvbgh.get(i).getProduct() + "' " ;


                        if (viewdata.getShift().equals("1")){
                            where = where + " and a.creation_date >= to_date('"+ds1+"','yyyy-mm-dd hh24:mi:ss')";
                            where = where + " and a.creation_date <= to_date('"+ds2+"','yyyy-mm-dd hh24:mi:ss')";
                        }

                        if (viewdata.getShift().equals("2")){
                            where = where + " and a.creation_date >= to_date('"+ds3+"','yyyy-mm-dd hh24:mi:ss')";
                            where = where + " and a.creation_date <= to_date('"+ds4+"','yyyy-mm-dd hh24:mi:ss')";
                        }

                        if (viewdata.getShift().equals("3")){
                            where = where + " and a.creation_date >= to_date('"+ds5+"','yyyy-mm-dd hh24:mi:ss')";
                            where = where + " and a.creation_date <= to_date('"+ds6+"','yyyy-mm-dd hh24:mi:ss')";
                        }

                        String sql = sqlzx + where;
                        List<Map<String, Object>> listresult = new ArrayList<Map<String,Object>>();

                        try {
                            listresult = oracleConn.select(sql);
                            actnum = actnum + listresult.size();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //3.1 取件数量
                        InOutRecord inOutRecord = new InOutRecord();
                        String start = "";
                        String end = "";
                        if (viewdata.getShift().equals("1")){
                            start = ds1;
                            end = ds2;
                        }

                        if (viewdata.getShift().equals("2")){
                            start = ds3;
                            end = ds4;
                        }

                        if (viewdata.getShift().equals("3")){
                            start = ds5;
                            end = ds6;
                        }

                        List<InOutRecord> listio = inOutRecordService.selectforKanb(listvbgh.get(i).getWorks(),listcurlzk.get(j).getLineId(),listvbgh.get(i).getProduct(),start,end);
                        outnum = outnum + listio.size();

                        l_error = "";

                    }
                }

                if (l_error == ""){
                    takt_time = takt_time / listcurlzk.size();//平均生产节拍
                    viewdata.setPlanqty(plqty);//计划产量
                    viewdata.setActqty(actnum);//实际产量
                    viewdata.setCycletime(takt_time);//平均节拍
                    viewdata.setGroupId(listvbgh.get(i).getGroupId());//产线组ID
                    viewdata.setWorks(listvbgh.get(i).getWorks());
                    viewdata.setBukrs(listvbgh.get(i).getBukrs());
                    viewdata.setProduct(listvbgh.get(i).getProduct());
                    viewdata.setWorkshopId(listvbgh.get(i).getWorkshopId());
                    viewdata.setErdat(curdate);
                    try {
                        Date startDate = sdf2.parse(viewdata.getShifttimebegin());
                        Long times = new Double(viewdata.getPlanqty() * viewdata.getCycletime() * 1000).longValue();
                        times = startDate.getTime() + times;
                        viewdata.setShifttimeend(sdf2.format(new Date(times)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //viewdata.setShifttimeend();
                    viewdata.setInsufqty(viewdata.getActqty() - viewdata.getPlanqty());//差缺数量
                    Double qcrate = 0D;
                    Double oeerate = 0D;
                    if (viewdata.getActqty() > 0D){

                        qcrate = ( viewdata.getActqty() / ( viewdata.getActqty() + outnum ) ) * 100;
                        oeerate = ( viewdata.getActqty() / ( viewdata.getActqty() + outnum ) ) * 100;
                    }

                    viewdata.setOeeRate(oeerate);//oee
                    viewdata.setQcRate(qcrate);//合格率

                    try {
                        viewdata.setJdcqqty(viewdata.getActqty() - (new Date().getTime() - sdf2.parse(viewdata.getShifttimebegin()).getTime()) / ( viewdata.getCycletime() * 1000 ));//进度差缺
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Viewdataschemaline viewdatatmp = viewdataschemalineService.selectforKanb(listvbgh.get(i).getGroupId(),
                            listvbgh.get(i).getProduct(),listvbgh.get(i).getWorkshopId(),listvbgh.get(i).getBukrs(),listvbgh.get(i).getWorks());
                    if (viewdatatmp != null){
                        viewdata.setLastUpdateDate(new Date());
                        viewdata.setLastUpdatedBy(10001L);
                        viewdataschemalineService.updateforKanb(viewdata);
                    }else{
                        viewdata.setCreatedBy(10001L);
                        viewdata.setCreationDate(new Date());
                        viewdataschemalineService.insertforKanb(viewdata);
                    }
                }
            }
        }

    }

}
