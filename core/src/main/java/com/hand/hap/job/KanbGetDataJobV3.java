package com.hand.hap.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;
import yj.kanb.viewdataschemaline.service.IViewdataschemalineService;
import yj.kanb.viewdatawarn.dto.ViewDataWarn;
import yj.kanb.viewdatawarn.dto.ViewDataWarnUser;
import yj.kanb.viewdatawarn.service.IViewDataWarnService;
import yj.kanb.viewdatawarn.service.IViewDataWarnUserService;
import yj.mes.oee.dto.OeeLineData;
import yj.mes.oee.service.IOeeLineDataService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//MES 看板
public class KanbGetDataJobV3 extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJobV3.class);
    @Autowired
    private IViewdataschemalineService viewdataschemalineService;
    @Autowired
    private IVblinegroupheaderService vblinegroupheaderService;
    @Autowired
    private IOeeLineDataService oeeLineDataService;
    @Autowired
    private IViewDataWarnService viewDataWarnService;
    @Autowired
    private IViewDataWarnUserService viewDataWarnUserService;

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
                            viewdatatmp.setCycletime(list.get(j).getCycleTimes().doubleValue());

                            if (list.get(j).getTotalQty() == 0){
                                viewdatatmp.setQcRate(0D);
                            }else{
                                viewdatatmp.setQcRate(list.get(j).getQualifiedQty().doubleValue() / list.get(j).getTotalQty().doubleValue() * 100 );//合格率
                            }
                            Long    sjtim = 0L;
                            if (!viewdatatmp.getShifttimebegin().equals("1900-01-01 00:00:00")){
                                sjtim = ( curdate.getTime() - star.getTime() ) / 1000;
                            }
                            Double jdcq = list.get(j).getTotalQty() - Math.rint(sjtim / list.get(j).getCycleTimes() / list.get(j).getSeqCount())  ;
                            viewdatatmp.setJdcqqty(jdcq);

                            if ((list.get(j).getTotalQty() - viewdatatmp.getJdcqqty()) != 0){
                                viewdatatmp.setInsufqty(list.get(j).getTotalQty().doubleValue() / ( list.get(j).getTotalQty().doubleValue() - viewdatatmp.getJdcqqty()) * 100 );
                            }else{
                                viewdatatmp.setInsufqty(0D);
                            }
                            Double oeeRate = 0D;
                            oeeRate = viewdatatmp.getInsufqty() * viewdatatmp.getQcRate() / 100;
                            if (oeeRate > 100D)
                            {
                                oeeRate = 100D;
                            }
                            viewdatatmp.setOeeRate(oeeRate);
                            viewdatatmp.setLastUpdateDate(new Date());
                            viewdatatmp.setLastUpdatedBy(10001L);
                            viewdataschemalineService.updateforKanb(viewdatatmp);
                            createViewDataWarnData(viewdatatmp,listvbgh.get(i).getGroupName());
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
                            viewdataschemaline.setCycletime(list.get(j).getCycleTimes().doubleValue());

                            if (list.get(j).getTotalQty() == 0){
                                viewdataschemaline.setQcRate(0D);
                            }else{
                                viewdataschemaline.setQcRate(list.get(j).getQualifiedQty().doubleValue() / list.get(j).getTotalQty().doubleValue() * 100 );//合格率
                            }
                            Long    sjtim = 1L;
                            if (!viewdataschemaline.getShifttimebegin().equals("1900-01-01 00:00:00")){
                                sjtim = ( curdate.getTime() - star.getTime() ) / 1000;
                            }

                            Double jdcq = list.get(j).getTotalQty() - Math.rint(sjtim / list.get(j).getCycleTimes() / list.get(j).getSeqCount());
                            viewdataschemaline.setJdcqqty(jdcq);
                            if ((list.get(j).getTotalQty() - viewdataschemaline.getJdcqqty()) != 0){
                                viewdataschemaline.setInsufqty(list.get(j).getTotalQty() / ( list.get(j).getTotalQty() - viewdataschemaline.getJdcqqty()) * 100 );
                            }else{
                                viewdataschemaline.setInsufqty(0D);
                            }
                            Double oeeRate = 0D;
                            oeeRate = viewdataschemaline.getInsufqty() * viewdataschemaline.getQcRate() / 100;
                            if (oeeRate > 100D)
                            {
                                oeeRate = 100D;
                            }
                            viewdataschemaline.setOeeRate(oeeRate);
                            viewdataschemaline.setCreatedBy(10001L);
                            viewdataschemaline.setCreationDate(new Date());
                            viewdataschemalineService.insertforKanb(viewdataschemaline);
                            createViewDataWarnData(viewdataschemaline,listvbgh.get(i).getGroupName());
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
    private  void createViewDataWarnData(Viewdataschemaline viewdatatmp,String workline) throws Exception{
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String werks = viewdatatmp.getWerks();
        Date shiftstart = sdf2.parse(viewdatatmp.getShifttimebegin());
        Date shiftend = sdf2.parse(viewdatatmp.getShifttimeend());
        String workshop = viewdatatmp.getWorkshopName();
        String warnCreateTime = sdf2.format(new Date());
        String warnStatus = "0";
        String warnLevel = "";
        String msg = "";
        String maktx = viewdatatmp.getMaktx();
        List<ViewDataWarnUser> listwarnUser = new ArrayList<>();
        Date curdate = new Date();
        if (viewdatatmp.getMatnr().equals("11000179")) {
            if (viewdatatmp.getInsufqty() > 0 && viewdatatmp.getInsufqty() < 95) {
                String warnType = "1";
                List<ViewDataWarn> warnDatas = new ArrayList<>();
                ViewDataWarnUser user = new ViewDataWarnUser();
                user.setWerks(werks);
                user.setWarnType(warnType);

                if (viewdatatmp.getInsufqty() > 0 && viewdatatmp.getInsufqty() <= 80) {
                    warnLevel = "A";
                    msg = "产品生产进度低于80%";
                    user.setWarnLevel(warnLevel);
                    listwarnUser = viewDataWarnUserService.queryViewDataWarnUser(user);
                }

                if (viewdatatmp.getInsufqty() > 80 && viewdatatmp.getInsufqty() <= 85) {
                    warnLevel = "B";
                    msg = "产品生产进度低于85%";
                    user.setWarnLevel(warnLevel);
                    listwarnUser = viewDataWarnUserService.queryViewDataWarnUser(user);
                }

                if (viewdatatmp.getInsufqty() > 85 && viewdatatmp.getInsufqty() <= 90) {
                    warnLevel = "C";
                    msg = "产品生产进度低于90%";
                    user.setWarnLevel(warnLevel);
                    listwarnUser = viewDataWarnUserService.queryViewDataWarnUser(user);
                }
                ViewDataWarn warndata  = new ViewDataWarn();
                warndata.setWerks(werks);
                warndata.setWorkshop(viewdatatmp.getWorkshopName());
                warndata.setWorkline(workline);
                warndata.setWarnType("1");
                warndata.setWarnLevel(warnLevel);
                warnDatas = viewDataWarnService.queryData(warndata);
                if ((warnDatas.size() == 0 ) && ( curdate.getTime() >= shiftstart.getTime()) && ( curdate.getTime() < shiftend.getTime())) {
                    for (int k = 0; k < listwarnUser.size(); k++) {
                        UUID uuid = java.util.UUID.randomUUID();
                        String uuidstr = uuid.toString().replaceAll("-", "");
                        ViewDataWarn viewDataWarn = new ViewDataWarn();
                        viewDataWarn.setWerks(werks);
                        viewDataWarn.setWorkshop(workshop);
                        viewDataWarn.setWorkline(workline);
                        viewDataWarn.setWarnStatus(warnStatus);
                        viewDataWarn.setWarnCreateTime(warnCreateTime);
                        viewDataWarn.setWarnType(warnType);
                        viewDataWarn.setRecevierId(listwarnUser.get(k).getUserCode());
                        viewDataWarn.setReceiverName(listwarnUser.get(k).getUserName());
                        viewDataWarn.setWarnLevel(warnLevel);
                        viewDataWarn.setWarnContent("日期:" + sdf2.format(curdate) + " " + workshop + " " + workline + " " + maktx + " " + msg);
                        viewDataWarn.setUuid(uuidstr);
                        viewDataWarnService.insertData(viewDataWarn);
                    }
                }

                if (warnDatas.size() > 0) {
                    if (warnDatas.get(0).getWarnStatus().equals("0")) {

                    } else {
                        String createTime = warnDatas.get(0).getWarnCreateTime();
                        Long timecy = 0L;
                        timecy = (curdate.getTime() - sdf2.parse(createTime).getTime()) / 1000;
                        if (timecy >= 7200 && ( curdate.getTime() >= shiftstart.getTime()) && ( curdate.getTime() < shiftend.getTime())) {
                            //插入报警记录
                            for (int k = 0; k < listwarnUser.size(); k++) {
                                UUID uuid = java.util.UUID.randomUUID();
                                String uuidstr = uuid.toString().replaceAll("-", "");
                                ViewDataWarn viewDataWarn = new ViewDataWarn();
                                viewDataWarn.setWerks(werks);
                                viewDataWarn.setWorkshop(workshop);
                                viewDataWarn.setWorkline(workline);
                                viewDataWarn.setWarnStatus(warnStatus);
                                viewDataWarn.setWarnCreateTime(warnCreateTime);
                                viewDataWarn.setWarnType(warnType);
                                viewDataWarn.setRecevierId(listwarnUser.get(k).getUserCode());
                                viewDataWarn.setReceiverName(listwarnUser.get(k).getUserName());
                                viewDataWarn.setWarnLevel(warnLevel);
                                viewDataWarn.setWarnContent("日期:" + sdf2.format(curdate) + " " + workshop + " " + workline + " " + maktx + " " + msg);
                                viewDataWarn.setUuid(uuidstr);
                                viewDataWarnService.insertData(viewDataWarn);
                            }
                        }
                    }
                }
            }

            if (viewdatatmp.getQcRate() > 0 && viewdatatmp.getQcRate() < 95) {
                String warnType = "2";
                List<ViewDataWarn> warnDatas = new ArrayList<>();
                ViewDataWarnUser user = new ViewDataWarnUser();
                user.setWerks(werks);
                user.setWarnType(warnType);
                if (viewdatatmp.getQcRate() > 0 && viewdatatmp.getQcRate() <= 90) {
                    warnLevel = "C";
                    user.setWarnLevel(warnLevel);
                    msg = "产品合格率低于90%";
                    listwarnUser = viewDataWarnUserService.queryViewDataWarnUser(user);
                }

                if (viewdatatmp.getQcRate() > 90 && viewdatatmp.getQcRate() <= 93) {
                    warnLevel = "B";
                    user.setWarnLevel(warnLevel);
                    msg = "产品合格率低于93%";
                    listwarnUser = viewDataWarnUserService.queryViewDataWarnUser(user);
                }

                if (viewdatatmp.getQcRate() > 93 && viewdatatmp.getQcRate() <= 95) {
                    warnLevel = "A";
                    user.setWarnLevel(warnLevel);
                    msg = "产品合格率低于95%";
                    listwarnUser = viewDataWarnUserService.queryViewDataWarnUser(user);
                }
                ViewDataWarn warndata  = new ViewDataWarn();
                warndata.setWerks(werks);
                warndata.setWorkshop(viewdatatmp.getWorkshopName());
                warndata.setWorkline(workline);
                warndata.setWarnType("2");
                warndata.setWarnLevel(warnLevel);
                warnDatas = viewDataWarnService.queryData(warndata);
                if ((warnDatas.size() == 0) && ( curdate.getTime() >= shiftstart.getTime() + 1800 ) && ( curdate.getTime() < shiftend.getTime())) {
                    for (int k = 0; k < listwarnUser.size(); k++) {
                        UUID uuid = java.util.UUID.randomUUID();
                        String uuidstr = uuid.toString().replaceAll("-", "");
                        ViewDataWarn viewDataWarn = new ViewDataWarn();
                        viewDataWarn.setWerks(werks);
                        viewDataWarn.setWorkshop(workshop);
                        viewDataWarn.setWorkline(workline);
                        viewDataWarn.setWarnStatus(warnStatus);
                        viewDataWarn.setWarnCreateTime(warnCreateTime);
                        viewDataWarn.setWarnType(warnType);
                        viewDataWarn.setRecevierId(listwarnUser.get(k).getUserCode());
                        viewDataWarn.setReceiverName(listwarnUser.get(k).getUserName());
                        viewDataWarn.setWarnLevel(warnLevel);
                        viewDataWarn.setWarnContent("日期:" + sdf2.format(curdate) + " " + workshop + " " + workline + " " + maktx + " " + msg);
                        viewDataWarn.setUuid(uuidstr);
                        viewDataWarnService.insertData(viewDataWarn);
                    }
                }

                if (warnDatas.size() > 0) {
                    if (warnDatas.get(0).getWarnStatus().equals("0")) {

                    } else {
                        String createTime = warnDatas.get(0).getWarnCreateTime();
                        Long timecy = 0L;
                        timecy = (curdate.getTime() - sdf2.parse(createTime).getTime()) / 1000;
                        if (timecy >= 7200 && ( curdate.getTime() >= shiftstart.getTime() + 1800) && ( curdate.getTime() < shiftend.getTime())) {
                            //插入报警记录
                            for (int k = 0; k < listwarnUser.size(); k++) {
                                UUID uuid = java.util.UUID.randomUUID();
                                String uuidstr = uuid.toString().replaceAll("-", "");
                                ViewDataWarn viewDataWarn = new ViewDataWarn();
                                viewDataWarn.setWerks(werks);
                                viewDataWarn.setWorkshop(workshop);
                                viewDataWarn.setWorkline(workline);
                                viewDataWarn.setWarnStatus(warnStatus);
                                viewDataWarn.setWarnCreateTime(warnCreateTime);
                                viewDataWarn.setWarnType(warnType);
                                viewDataWarn.setRecevierId(listwarnUser.get(k).getUserCode());
                                viewDataWarn.setReceiverName(listwarnUser.get(k).getUserName());
                                viewDataWarn.setWarnLevel(warnLevel);
                                viewDataWarn.setWarnContent("日期:" + sdf2.format(curdate) + " " + workshop + " " + workline + " " + maktx + " " + msg);
                                viewDataWarn.setUuid(uuidstr);
                                viewDataWarnService.insertData(viewDataWarn);
                            }
                        }
                    }
                }

            }
        }
    }
    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return false;
    }
}
