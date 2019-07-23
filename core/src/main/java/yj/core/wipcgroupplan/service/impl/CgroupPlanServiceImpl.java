package yj.core.wipcgroupplan.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.mapper.CgroupPlanMapper;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CgroupPlanServiceImpl extends BaseServiceImpl<CgroupPlan> implements ICgroupPlanService{

    @Autowired
    private CgroupPlanMapper cgroupPlanMapper;

    @Override
    public List<CgroupPlan> selectCgroupPlan(CgroupPlan cgroupPlan, IRequest requestContext) {
        return cgroupPlanMapper.selectCgroupPlan(cgroupPlan);
    }

    @Override
    public String insertOrUpdate(CgroupPlan cgroupPlan, String userId, IRequest requestContext) {
        if (cgroupPlan.getDeptId() == null){
            return "部门不能为空！";
        }else if (cgroupPlan.getCgroup() == null){
            return "产线组不能为空！";
        }else if (cgroupPlan.getPlandate() == null){
            return "生产计划日期不能为空！";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String planDate = cgroupPlan.getPlandate().substring(0,10);
        cgroupPlan.setPlandate(planDate);
        if (planDate.compareTo(df.format(new Date())) < 0){
            return "生产计划日期不能小于当前日期！";
        }else if (cgroupPlan.getPlantimestart() == null){
            return "计划开始时间不能为空！";
        }else if (cgroupPlan.getPlantimeend() == null) {
            return "计划结束时间不能为空！";
        }else if (cgroupPlan.getPlantimestart().after(cgroupPlan.getPlantimeend())){
            return "计划开始时间必须小于计划结束时间！";
        }else if (cgroupPlan.getPlanqty() == null){
            return "计划产量不能为空！";
        }else if (cgroupPlan.getClassgrp() == null){
            return "班组不能为空！";
        }else if (cgroupPlan.getShift() == null){
            return "班次不能为空！";
        }else if ((cgroupPlan.getPlanqty().compareTo(0.0)) <= 0){
            return "计划产量必须大于0！";
        }else{
            SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
            int plantimestart = Integer.parseInt(df2.format(cgroupPlan.getPlantimestart()).replaceAll(":",""));
            int plantimeend = Integer.parseInt(df2.format(cgroupPlan.getPlantimeend()).replaceAll(":",""));
            if (cgroupPlan.getPlanpos() == null){
                List<CgroupPlan> list = cgroupPlanMapper.selectCgroupPlan(cgroupPlan);
                if (list.size() > 0){
                    for (int i=0;i<list.size();i++){
                        int plantimestart2 = Integer.parseInt(list.get(i).getPlantimestart2().replaceAll(":",""));
                        int plantimeend2 = Integer.parseInt(list.get(i).getPlantimeend2().replaceAll(":",""));
                        if((plantimeend2 <= plantimestart)||(plantimestart2>=plantimeend)){
                        }else{
                            return "开始时间及结束时间期间被使用，请重新输入！";
                        }
                    }
                    Integer planpos = list.get(0).getPlanpos();
                    cgroupPlan.setPlanpos(planpos + 1);
                }else{
                    cgroupPlan.setPlanpos(1);
                }
                cgroupPlan.setCreatedBy(Long.valueOf(userId));
                cgroupPlan.setCreationDate(new Date());
                cgroupPlanMapper.insertCgroupPlan(cgroupPlan);
            }else{
                List<CgroupPlan> list = cgroupPlanMapper.selectCgroupPlan(cgroupPlan);
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getPlanpos() != cgroupPlan.getPlanpos()){
                        int plantimestart2 = Integer.parseInt(list.get(i).getPlantimestart2().replaceAll(":",""));
                        int plantimeend2 = Integer.parseInt(list.get(i).getPlantimeend2().replaceAll(":",""));
                        if((plantimeend2 <= plantimestart)||(plantimestart2>=plantimeend)){
                        }else{
                            return "开始时间及结束时间期间被使用，请重新输入！";
                        }
                    }
                }
                cgroupPlan.setLastUpdatedBy(Long.valueOf(userId));
                cgroupPlan.setLastUpdateDate(new Date());
                cgroupPlanMapper.updateCgroupPlan(cgroupPlan);
            }
        }
        return null;
    }

    @Override
    public String deleteCgroupPlan(CgroupPlan cgroupPlan) {
        cgroupPlanMapper.deleteCgroupPlan(cgroupPlan);
        return null;
    }
    @Override
    public List<CgroupPlan> selectByErdat(String erdat) {
        return cgroupPlanMapper.selectByErdat(erdat);
    }
}