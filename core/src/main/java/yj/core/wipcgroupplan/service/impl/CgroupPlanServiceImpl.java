package yj.core.wipcgroupplan.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.mapper.CgroupPlanMapper;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
import org.springframework.transaction.annotation.Transactional;

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
        }else if (cgroupPlan.getPlandate().before(new Date())){
            return "生产计划日期不能小于当前日期！";
        }else if (cgroupPlan.getPlantimestart() == null){
            return "计划开始时间不能为空！";
        }else if (cgroupPlan.getPlantimeend() == null) {
            return "计划结束时间不能为空！";
        }else if (cgroupPlan.getPlantimestart().before(cgroupPlan.getPlantimeend())){
            return "计划开始时间必须小于计划结束时间！";
        }else if (cgroupPlan.getPlanqty() == null){
            return "计划产量不能为空！";
        }else if (cgroupPlan.getPlanqty() > 0.0){
            return "计划产量必须大于！";
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
        return null;
    }
}