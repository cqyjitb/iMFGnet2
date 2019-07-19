package com.hand.hap.job;

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
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;
import yj.kanb.viewdataschemaline.service.IViewdataschemalineService;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  看板后台取数任务
 */
public class KanbGetDataJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
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
    public KanbGetDataJob(){

    }
    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return true;
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
        Date now = new Date();
        if (listvbgh.size() > 0 && listcurlzk.size() > 0){


            for (int i=0;i<listvbgh.size();i++){
                Viewdataschemaline viewdata = new Viewdataschemaline();
                int num = 0;//计数器
                Double outnum = 0D;
                Double actnum = 0D;
                Double plqty = 0D;
                Double lunqty = 0D;//理论产出
                Double jdcq = 0D;
                Double takt_time = 0D;
                float oee = 0f;
                String l_error= "E";

                for (int j=0;j<listcurlzk.size();j++){
                    double actnumtmp = 0D;
                    double outnumtmp = 0D;
                    if (listcurlzk.get(j).getDeptId().equals(listvbgh.get(i).getWorkshopId()) && listcurlzk.get(j).getCgroup().equals(listvbgh.get(i).getLineId().toString())){

                        num = num + 1;
                        if (num == 1){
                            //该产线的流转卡切换时间最小
                            viewdata.setShifttimebegin(listcurlzk.get(j).getLastUpdateDateStr());
                        }
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
                            listvbgh.get(i).setProduct(cardh.getMatnr());
                            //反写物料编码到产线组配置。
                            vblinegroupheaderService.updateMatnr(listvbgh.get(i));
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


                        if (now.getTime() >= d1.getTime() && now.getTime() <= d2.getTime()){
                            viewdata.setShift("1");
                            viewdata.setShiftdes("白班");
                            //viewdata.setShifttimebegin(ds1);
                        }

                        if (now.getTime() >= d3.getTime() && now.getTime() <= d4.getTime()){
                            viewdata.setShift("2");
                            viewdata.setShiftdes("中班");
                            //viewdata.setShifttimebegin(ds1);
                        }

                        if (now.getTime() >= d5.getTime() && now.getTime() <= d6.getTime()){
                            viewdata.setShift("3");
                            viewdata.setShiftdes("夜班");
                            //viewdata.setShifttimebegin(ds1);
                        }

                        //4:取产线配置数据
                        Lines lines = new Lines();
                        lines = linesService.selectById(Long.parseLong(listcurlzk.get(j).getLineId()));
                        viewdata.setLeaderPhone(lines.getHeaderPhone());
                        viewdata.setLineLeader(lines.getLineHeader());
                        viewdata.setLineLeaderEn(lines.getLineHeaderEn());
                        takt_time =  lines.getTaktTime().doubleValue();
                        if (lines.getOeerate() != null){
                            oee = lines.getOeerate();
                        }else{
                            oee = oee + 1;
                        }
                        Long lineId = 0L;
                        if (lines.getPlineId() != null){
                            lineId = lines.getPlineId();
                        }else{
                            lineId = Long.parseLong(listcurlzk.get(j).getLineId());
                        }

                        String beginstr = listcurlzk.get(j).getLastUpdateDateStr();
                        String nowstr = sdf2.format(now);
                        //3：去装箱数据
                        ServerSetting serverSetting = new ServerSetting();
                        serverSetting = serverSettingService.selectByLineId(listvbgh.get(i).getWerks(),lineId.toString());
                        WebServerHelp webServerHelp = new WebServerHelp();
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                        String sqlzx = "select a.main_id from "+serverSetting.getDbUsername()+".wip_pallet_sn_rel  a"
                                +" inner join  "+serverSetting.getDbUsername()+".wip_main_data b on a.main_id = b.main_id";
                        String where = " where a.zremade != '1' and a.line_id = " + "'" + listcurlzk.get(j).getLineId() + "' and  a.status = '0' and b.ENABLE_FLAG = '1' ";
                        where = where + "and b.item_code = " + "'" + listvbgh.get(i).getProduct() + "' " ;

                        where = where + " and a.creation_date >= to_date('"+beginstr.substring(0,19)+"','yyyy-mm-dd hh24:mi:ss')";
                        where = where + " and a.creation_date <= to_date('"+nowstr+"','yyyy-mm-dd hh24:mi:ss')";

                        String sql = sqlzx + where;
                        System.out.println("查询装箱数据sql:"+sql);
                        List<Map<String, Object>> listresult = new ArrayList<Map<String,Object>>();

                        try {
                            listresult = oracleConn.select(sql);
                            actnumtmp = listresult.size();
                            actnum = actnum + listresult.size();
                            System.out.println("获取到产线："+listcurlzk.get(j).getLineId()+"装箱数据:"+actnum+"条");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //3.1 取件数量
                        InOutRecord inOutRecord = new InOutRecord();
                        List<InOutRecord> listio = inOutRecordService.selectforKanb(listvbgh.get(i).getWerks(),listcurlzk.get(j).getLineId(),listvbgh.get(i).getProduct(),beginstr,nowstr);
                        outnum = outnum + listio.size();

                        l_error = "";

                        //计算该班次的实际生产用时
                        Long begin = sdf.parse(listcurlzk.get(j).getLastUpdateDateStr().substring(0,19)).getTime() / 1000;
                        Long end = now.getTime() /1000;
                        lunqty = lunqty + ( end - begin ) / takt_time;//理论产出汇总

                        //取最小节拍
                        if (viewdata.getCycletime() != null){
                            if (viewdata.getCycletime() > takt_time){
                                viewdata.setCycletime(takt_time);
                                Long yongshi = new Double(cardh.getPlqty() * takt_time * 1000).longValue() + ( begin * 1000 );
                                viewdata.setShifttimeend(sdf2.format(new Date(yongshi)));

                            }
                        }else{
                            viewdata.setCycletime(takt_time);
                            Long yongshi = new Double(cardh.getPlqty() * takt_time * 1000).longValue() + ( begin * 1000 );
                            viewdata.setShifttimeend(sdf2.format(new Date(yongshi)));
                        }




                        //单线进度差缺
                        double a = ( new Date().getTime() - sdf2.parse(listcurlzk.get(j).getLastUpdateDateStr().substring(0,19)).getTime() ) / 1000 / takt_time * oee;
                        jdcq = jdcq + a;

                    }
                }

                if (l_error == ""){

                    viewdata.setPlanqty(plqty);//计划产量
                    viewdata.setActqty(actnum);//实际产量
                    viewdata.setGroupId(listvbgh.get(i).getGroupId());//产线组ID
                    viewdata.setWerks(listvbgh.get(i).getWerks());
                    viewdata.setBukrs(listvbgh.get(i).getBukrs());
                    viewdata.setProduct(listvbgh.get(i).getProduct());
                    viewdata.setWorkshopId(listvbgh.get(i).getWorkshopId());
                    viewdata.setErdat(curdate);
                    double b = viewdata.getActqty() - viewdata.getPlanqty();
                    if (b > 0){
                        b = 0;
                    }else{
                        b = Math.abs(b);
                    }
                    viewdata.setInsufqty(b);//差缺数量

                    if (Math.abs(Math.rint(viewdata.getActqty() - jdcq)) >= viewdata.getPlanqty()){
                        viewdata.setJdcqqty( 0 - viewdata.getPlanqty());//进度差缺
                    }else{
                        viewdata.setJdcqqty(Math.rint(viewdata.getActqty() - jdcq ));//进度差缺
                    }
                    Double qcrate = 0D;
                    Double oeerate = 0D;
                    NumberFormat ddf1 = NumberFormat.getNumberInstance() ;
                    ddf1.setMaximumFractionDigits(2);
                    if (viewdata.getActqty() > 0D){

                        double qcratetmp =  viewdata.getActqty() / ( viewdata.getActqty() + outnum )  * 100;

                        double oeetmp =  viewdata.getActqty() / lunqty  * 100;
                        String s = ddf1.format(oeetmp) ;
                        oeerate = Double.parseDouble(s);

                        String qc = ddf1.format(qcratetmp);
                        qcrate = Double.parseDouble(qc);

                    }

                    viewdata.setOeeRate(oeerate);//oee
                    viewdata.setQcRate(qcrate);//合格率

                    Viewdataschemaline viewdatatmp = viewdataschemalineService.selectforKanb(listvbgh.get(i).getGroupId(),
                            listvbgh.get(i).getProduct(),listvbgh.get(i).getWorkshopId(),listvbgh.get(i).getBukrs(),listvbgh.get(i).getWerks());
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
