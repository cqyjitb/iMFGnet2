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
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class KanbgetDateV2 extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IVblinegroupheaderService vblinegroupheaderService;
    @Autowired
    private ICgroupPlanService cgroupPlanService;
    @Autowired
    private IViewdataschemalineService viewdataschemalineService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICustService custService;
    public KanbgetDateV2(){

    }

    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        String msg = "KanbGetDataJob Test<insertNewData>! - . jobKey:" + key + ", triggerKey:" + triggerKey + ", execTime:" + new Date();
        log.info(msg);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
        NumberFormat ddf1 = NumberFormat.getNumberInstance() ;
        ddf1.setMaximumFractionDigits(2);

        //获取当前日期
        Date curdate = new Date();
        String erdat = sdf.format(curdate).substring(0,10);

        //根据当前日期 取所有当前流转卡数据
        List<Curlzk> listcurlzk = new ArrayList<>();
        listcurlzk = curlzkService.selectAllLinesForKanbByErdat(erdat);

        //取所有看板设备与产线组配置数据
        List<Vblinegroupheader> listvbgh = new ArrayList<>();
        listvbgh = vblinegroupheaderService.selectAllGroup();

        //取所有的产线组看板计划数据
        List<CgroupPlan> listplan = new ArrayList<>();
        listplan = cgroupPlanService.selectByErdat(erdat);

        if (listcurlzk.size() > 0 && listvbgh.size() > 0 && listplan.size() > 0){

            for (int i=0;i<listvbgh.size();i++){
                String l_get = "";
                Viewdataschemaline viewdata = new Viewdataschemaline();
                Double actnum = 0D;//装箱汇总
                Double outnum = 0D;//取件汇总
                Double jdcq = 0D;//进度差缺汇总
                Double lncq = 0D;//理论产出汇总
                Long    lntim = 0L;
                Long    sjtim = 0L;
                for (int j=0;j<listplan.size();j++){
                    //根据产线组code来匹配产线组计划，如果存在产线组计划 判断当前时间是否在计划时间范围之内，如果在则根据时间范围取产线组对应产线的生产数据统计后显示看板
                    //如果不存在产线组计划或者当前时间不在计划时间范围之类，则删除原有产线看板数据，不显示任何内容。
                    if (listplan.get(j).getCgroup().equals(listvbgh.get(i).getLineId())){

                        String planstar = sdf.format(listplan.get(j).getPlandate()) + " " + sdf3.format(listplan.get(j).getPlantimestart());
                        String planend = sdf.format(listplan.get(j).getPlandate()) +  " " + sdf3.format(listplan.get(j).getPlantimeend());
                        Date star = sdf2.parse(planstar);
                        Date end = sdf2.parse(planend);
                        lntim = end.getTime() - star.getTime();
                        sjtim = curdate.getTime() - star.getTime();
                        if (curdate.getTime() >= sdf2.parse(planstar).getTime() && curdate.getTime() <= sdf2.parse(planend).getTime()){
                            l_get = "X";
                            viewdata.setShift(listplan.get(j).getShift());//班次
                            viewdata.setShifttimebegin(sdf2.format(star));//生产开始时间
                            viewdata.setShifttimeend(sdf2.format(end));//生产结束时间
                            viewdata.setClassgrp(listplan.get(j).getClassgrp());//生产班次
                            viewdata.setPlanqty(listplan.get(j).getPlanqty());//计划数量
                            viewdata.setGroupId(listvbgh.get(i).getGroupId());
                            viewdata.setBukrs(listvbgh.get(i).getBukrs());
                            viewdata.setWerks(listvbgh.get(i).getWerks());
                            viewdata.setWorkshopId(listvbgh.get(i).getWorkshopId());
                            viewdata.setErdat(curdate);
                        }
                    }
                }

                if (l_get.equals("X")){
                    //开始根据产线组code取生产数据
                     for (int k=0;k<listcurlzk.size();k++){
                         if (listcurlzk.get(k).getCgroup().equals(listvbgh.get(i).getLineId())){
                             //取产线数据
                             Lines lines = new Lines();
                             lines = linesService.selectById(Long.parseLong(listcurlzk.get(k).getLineId()));

                            //取装箱数据
                             ServerSetting serverSetting = new ServerSetting();
                             serverSetting = serverSettingService.selectByLineId(listvbgh.get(i).getWerks(),lines.getLineId().toString());
                             WebServerHelp webServerHelp = new WebServerHelp();
                             OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                             String sqlzx = "select a.main_id from "+serverSetting.getDbUsername()+".wip_pallet_sn_rel  a"
                                     +" inner join  "+serverSetting.getDbUsername()+".wip_main_data b on a.main_id = b.main_id";
                             String where = " where a.zremade != '1' and a.line_id = " + "'" + listcurlzk.get(k).getLineId() + "' and  a.status = '0' and b.ENABLE_FLAG = '1' ";
                             where = where + "and b.item_code = " + "'" + listvbgh.get(i).getProduct() + "' " ;
                             where = where + " and a.creation_date >= to_date('"+viewdata.getShifttimebegin()+"','yyyy-mm-dd hh24:mi:ss')";
                             where = where + " and a.creation_date <= to_date('"+viewdata.getShifttimeend()+"','yyyy-mm-dd hh24:mi:ss')";
                             String sql = sqlzx + where;
                             System.out.println("查询装箱数据sql:"+sql);
                             List<Map<String, Object>> listresult = new ArrayList<Map<String,Object>>();
                             try {
                                 listresult = oracleConn.select(sql);
                                 actnum = actnum + listresult.size();
                                 System.out.println("获取到产线："+listcurlzk.get(k).getLineId()+"装箱数据:"+actnum+"条");
                             } catch (Exception e) {
                                 e.printStackTrace();
                             }

                             //3.1 取件数量
                             InOutRecord inOutRecord = new InOutRecord();
                             List<InOutRecord> listio = inOutRecordService.selectforKanb(listvbgh.get(i).getWerks(),listcurlzk.get(k).getLineId(),listvbgh.get(i).getProduct(),viewdata.getShifttimebegin(),viewdata.getShifttimeend());
                             outnum = outnum + listio.size();

                             //理论产出
                             lncq = lncq + (curdate.getTime() - sdf2.parse(viewdata.getShifttimebegin()).getTime()) / 1000 / lines.getTaktTime();
                         }
                     }
                     viewdata.setActqty(actnum);//实际装箱数量
                    if (viewdata.getPlanqty() < viewdata.getActqty()){
                        viewdata.setInsufqty(0D);
                    }else {
                        viewdata.setInsufqty(Math.abs(viewdata.getPlanqty() - viewdata.getActqty()));//差缺数量
                    }


                     Double qcrate = 0D;
                     if (viewdata.getActqty() > 0D){
                         qcrate  =  viewdata.getActqty() / ( viewdata.getActqty() + outnum )  * 100;
                         String qcs = ddf1.format(qcrate);
                         viewdata.setQcRate(Double.parseDouble(qcs));
                     }else{
                         viewdata.setQcRate(qcrate);
                     }

//                     //进度差缺
//                    if (Math.abs(Math.rint(viewdata.getActqty() - lncq)) >= viewdata.getPlanqty()){
//                        viewdata.setJdcqqty( 0 - viewdata.getPlanqty());//进度差缺
//                    }else{
//                        viewdata.setJdcqqty(Math.rint(viewdata.getActqty() - lncq ));//进度差缺
//                    }
                    viewdata.setJdcqqty(viewdata.getActqty() - Math.rint(sjtim / (lntim / viewdata.getPlanqty())));



                    //综合利用率
                    String oeestr = ddf1.format( viewdata.getActqty() / lncq * 100);
                    viewdata.setOeeRate(Double.parseDouble(oeestr));

                    if (listcurlzk.get(0) != null){
                        Lines linestmp = new Lines();
                        linestmp = linesService.selectById(Long.parseLong(listcurlzk.get(0).getLineId()));
                        viewdata.setCycletime(Double.valueOf(linestmp.getTaktTime()));
                        viewdata.setLineLeader(linestmp.getLineHeader());
                        viewdata.setLineLeaderEn(linestmp.getLineHeaderEn());
                        viewdata.setLeaderPhone(linestmp.getHeaderPhone());
                        Cardh cardh = new Cardh();
                        cardh = cardhService.selectByBarcode(listcurlzk.get(0).getZpgdbar());
                        viewdata.setProduct(cardh.getMatnr());
                        //2：获取产品信息
                        Marc marc= new Marc();
                        marc = marcService.selectByMatnr(cardh.getMatnr());
                        viewdata.setMatnr(marc.getMatnr());
                        viewdata.setMaktx(marc.getMaktx());

                        //根据产品 取客户信息
                        ProductsCfg productsCfg = productsCfgService.selectByLineidAndPMatnr(listcurlzk.get(0).getLineId(),listcurlzk.get(0).getMatnrjj());
                        //根据客户编码取客户描述 名称
                        Cust cust = custService.selectByKunnr(productsCfg.getKunnr());
                        viewdata.setKunnr(cust.getKunnr());
                        viewdata.setName1(cust.getName1());
                        viewdata.setSortl(cust.getSortl());

                        if (viewdata.getShift().equals("1")){
                            viewdata.setShiftdes("白班");
                        }else if(viewdata.getShift().equals("2")){
                            viewdata.setShiftdes("中班");
                        }else if(viewdata.getShift().equals("3")){
                            viewdata.setShiftdes("夜班");
                        }

                    }
                    Viewdataschemaline viewdatatmp = viewdataschemalineService.selectforKanb2(listvbgh.get(i).getGroupId(),listvbgh.get(i).getWerks());
                    if (viewdatatmp != null){
                        viewdata.setLastUpdateDate(new Date());
                        viewdata.setLastUpdatedBy(10001L);
                        viewdataschemalineService.updateforKanb(viewdata);
                    }else{
                        viewdata.setCreatedBy(10001L);
                        viewdata.setCreationDate(new Date());
                        viewdataschemalineService.insertforKanb(viewdata);
                    }


                }else{
                    //没有符合条件的看板计划数据记录
                    //删除看板数据
                    viewdataschemalineService.deleteKanb(listvbgh.get(i).getGroupId());
                }

            }
        }


    }

    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return true;
    }
}
