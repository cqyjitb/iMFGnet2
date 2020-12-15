package com.hand.hap.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;
import yj.kanb.viewdataschemaline.service.IViewdataschemalineService;
import yj.mes.oee.dto.OeeLineData;
import yj.mes.oee.service.IOeeLineDataService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//MES 看板
public class KanbGetDataJobV3 extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJobV3.class);
    @Autowired
    private IViewdataschemalineService viewdataschemalineService;
    @Autowired
    private IVblinegroupheaderService vblinegroupheaderService;
    @Autowired
    private IOeeLineDataService oeeLineDataService;


    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {

        List<Vblinegroupheader> listvbgh = new ArrayList<>();
        listvbgh = vblinegroupheaderService.selectAllGroup();
        List<Viewdataschemaline> datalist = new ArrayList<>();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (listvbgh.size() > 0){
            for (int i=0;i<listvbgh.size();i++){
                OeeLineData param = new OeeLineData();
                param.setCompanyCode(listvbgh.get(i).getWerks());
                param.setWarehouseCode(listvbgh.get(i).getWorkshopId());
                param.setLineCode(listvbgh.get(i).getLineId());
                List<OeeLineData> list = oeeLineDataService.queryOeeDataByLineCode(param);
                if (list.size() > 0){
                    for (int j = 0;j<list.size();j++){
                        String starts = list.get(j).getShiftBeginTime();
                        String ends = list.get(j).getShiftEndTime();
                        Date star = sdf2.parse(starts);
                        Date end = sdf2.parse(ends);
                        Date curdate = new Date();
                        Viewdataschemaline viewdatatmp = new Viewdataschemaline();
                        viewdatatmp = viewdataschemalineService.selectforKanb2(listvbgh.get(i).getGroupId(),listvbgh.get(i).getWerks());
                        if (list.get(j).getTotalQty().equals("") || list.get(j).getTotalQty() == null ){
                            list.get(j).setTotalQty(0);
                        }

                        if (list.get(j).getQualifiedQty().equals("") || list.get(j).getQualifiedQty() == null){
                            list.get(j).setTotalQty(0);
                        }

                        if (list.get(j).getPlanQty().equals("") || list.get(j).getPlanQty() == null){
                            list.get(j).setPlanQty(0);
                        }
                        if (viewdatatmp != null){
                            viewdatatmp.setBukrs(listvbgh.get(i).getBukrs());
                            viewdatatmp.setWerks(listvbgh.get(i).getWerks());
                            viewdatatmp.setGroupId(listvbgh.get(i).getGroupId());
                            viewdatatmp.setWorkshopId(listvbgh.get(i).getWorkshopId());
                            viewdatatmp.setWorkshopName(list.get(j).getWarehouseName());
                            viewdatatmp.setProduct(list.get(j).getMaterialCode());
                            viewdatatmp.setMaktx(list.get(j).getMaterialName());
                            viewdatatmp.setMatnr(list.get(j).getMaterialCode());
                            viewdatatmp.setPlanqty(list.get(j).getPlanQty().doubleValue());
                            viewdatatmp.setActqty(list.get(j).getTotalQty().doubleValue());//当前进度
                            //viewdatatmp.setInsufqty(list.get(j).getPlanQty().doubleValue() - list.get(j).getQualifiedQty().doubleValue());//差缺数
                            viewdatatmp.setShifttimebegin(list.get(j).getShiftBeginTime());
                            viewdatatmp.setShifttimeend(list.get(j).getShiftEndTime());
                            viewdatatmp.setClassgrp(list.get(j).getWorkTeam());
                            if (list.get(j).getShiftName().contains("早")){
                                viewdatatmp.setShift("1");
                            }else if(list.get(j).getShiftName().contains("中")){
                                viewdatatmp.setShift("2");
                            }else if(list.get(j).getShiftName().contains("晚")){
                                viewdatatmp.setShift("3");
                            }
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            String erdatstr = df.format(new Date());
                            Date erdat = df.parse(erdatstr);
                            viewdatatmp.setErdat(erdat);
                            viewdatatmp.setLineLeader(list.get(j).getUserCnname());
                            viewdatatmp.setLineLeaderEn(list.get(j).getUserEnname());
                            viewdatatmp.setLeaderPhone(list.get(j).getTelphoneNo());
                            viewdatatmp.setCycletime(list.get(j).getCycleTimes().doubleValue() / list.get(j).getSeqCount());

                            if (list.get(j).getTotalQty() == 0){
                                viewdatatmp.setQcRate(0D);
                            }else{
                                viewdatatmp.setQcRate(list.get(j).getQualifiedQty().doubleValue() / list.get(j).getTotalQty().doubleValue() * 100 );//合格率
                            }
                            Long    sjtim = 0L;
                            if (!viewdatatmp.getShifttimebegin().equals("1900-01-01 00:00:00")){
                                sjtim = ( curdate.getTime() - star.getTime() ) / 1000;
                            }

                            viewdatatmp.setJdcqqty(list.get(j).getQualifiedQty() - Math.rint(sjtim / list.get(j).getCycleTimes() / list.get(j).getSeqCount() ));//进度差缺数量  合格数-时间差/节拍
                            //viewdatatmp.setOeeRate(list.get(j).getQualifiedQty().doubleValue() * list.get(j).getCycleTimes().doubleValue() / sjtim * 100 ) ;
                            if ((list.get(j).getTotalQty() - viewdatatmp.getJdcqqty()) != 0){
                                viewdatatmp.setInsufqty(list.get(j).getTotalQty().doubleValue() / ( list.get(j).getTotalQty().doubleValue() - viewdatatmp.getJdcqqty()) * 100 );
                            }else{
                                viewdatatmp.setInsufqty(0D);
                            }
                            viewdatatmp.setOeeRate(viewdatatmp.getInsufqty() * viewdatatmp.getQcRate() / 100);
                            viewdatatmp.setLastUpdateDate(new Date());
                            viewdatatmp.setLastUpdatedBy(10001L);
                            viewdataschemalineService.updateforKanb(viewdatatmp);
                        }else{
                            Viewdataschemaline viewdataschemaline = new Viewdataschemaline();
                            viewdataschemaline.setBukrs(listvbgh.get(i).getBukrs());
                            viewdataschemaline.setWerks(listvbgh.get(i).getWerks());
                            viewdataschemaline.setGroupId(listvbgh.get(i).getGroupId());
                            viewdataschemaline.setWorkshopId(listvbgh.get(i).getWorkshopId());
                            viewdataschemaline.setWorkshopName(list.get(j).getWarehouseName());
                            viewdataschemaline.setProduct(list.get(j).getMaterialCode());
                            viewdataschemaline.setMaktx(list.get(j).getMaterialName());
                            viewdataschemaline.setMatnr(list.get(j).getMaterialCode());
                            viewdataschemaline.setPlanqty(list.get(j).getPlanQty().doubleValue());
                            viewdataschemaline.setActqty(list.get(j).getTotalQty().doubleValue());//当前进度
                            //viewdataschemaline.setInsufqty(list.get(j).getPlanQty().doubleValue() - list.get(j).getQualifiedQty().doubleValue());//差缺数
                            viewdataschemaline.setShifttimebegin(list.get(j).getShiftBeginTime());
                            viewdataschemaline.setShifttimeend(list.get(j).getShiftEndTime());
                            viewdataschemaline.setClassgrp(list.get(j).getWorkTeam());
                            if (list.get(j).getShiftName().contains("早")){
                                viewdataschemaline.setShift("1");
                            }else if(list.get(j).getShiftName().contains("中")){
                                viewdataschemaline.setShift("2");
                            }else if(list.get(j).getShiftName().contains("晚")){
                                viewdataschemaline.setShift("3");
                            }
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            String erdatstr = df.format(new Date());
                            Date erdat = df.parse(erdatstr);
                            viewdataschemaline.setErdat(erdat);
                            viewdataschemaline.setLineLeader(list.get(j).getUserCnname());
                            viewdataschemaline.setLineLeaderEn(list.get(j).getUserEnname());
                            viewdataschemaline.setLeaderPhone(list.get(j).getTelphoneNo());
                            viewdataschemaline.setCycletime(list.get(j).getCycleTimes().doubleValue() / list.get(j).getSeqCount());

                            if (list.get(j).getTotalQty() == 0){
                                viewdataschemaline.setQcRate(0D);
                            }else{
                                viewdataschemaline.setQcRate(list.get(j).getQualifiedQty().doubleValue() / list.get(j).getTotalQty().doubleValue() * 100 );//合格率
                            }
                            Long    sjtim = 1L;
                            if (!viewdataschemaline.getShifttimebegin().equals("1900-01-01 00:00:00")){
                                sjtim = ( curdate.getTime() - star.getTime() ) / 1000;
                            }

                            viewdataschemaline.setJdcqqty(list.get(j).getQualifiedQty() - Math.rint(sjtim / list.get(j).getCycleTimes() / list.get(j).getSeqCount() ));//进度差缺数量
                            //viewdataschemaline.setOeeRate(list.get(j).getQualifiedQty().doubleValue() * list.get(j).getCycleTimes().doubleValue() / sjtim * 100 ) ;
                            if ((list.get(j).getTotalQty() - viewdataschemaline.getJdcqqty()) != 0){
                                viewdataschemaline.setInsufqty(list.get(j).getTotalQty() / ( list.get(j).getTotalQty() - viewdataschemaline.getJdcqqty()) * 100 );
                            }else{
                                viewdataschemaline.setInsufqty(0D);
                            }
                            viewdataschemaline.setOeeRate(viewdataschemaline.getInsufqty() * viewdataschemaline.getQcRate() / 100);
                            viewdataschemaline.setCreatedBy(10001L);
                            viewdataschemaline.setCreationDate(new Date());
                            viewdataschemalineService.insertforKanb(viewdataschemaline);
                        }
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
        return false;
    }
}
