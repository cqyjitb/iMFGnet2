package com.hand.hap.job;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KanbgetDateV2 extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IVblinegroupheaderService vblinegroupheaderService;
    @Autowired
    private ICgroupPlanService cgroupPlanService;
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
                for (int j=0;j<listplan.size();j++){
                    //根据产线组code来匹配产线组计划，如果存在产线组计划 判断当前时间是否在计划时间范围之内，如果在则根据时间范围取产线组对应产线的生产数据统计后显示看板
                    //如果不存在产线组计划或者当前时间不在计划时间范围之类，则删除原有产线看板数据，不显示任何内容。
                    if (listplan.get(j).getCgroup().equals(listvbgh.get(i).getLineId())){

//                        String planstar = listplan.get(j).getPlandate() + " " + listplan.get(j).getPlantimestart();
//                        String planend = listplan.get(j).getPlandate() +  " " + listplan.get(j).getPlantimeend();
//                        if (curdate.getTime() >= sdf2.parse(planstar).getTime() && curdate.getTime() <= sdf2.parse(planend).getTime()){
//                            l_get = "X";
//                        }
                    }
                }
            }
        }




    }

    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return super.isRefireImmediatelyWhenException();
    }
}
