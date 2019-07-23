package yj.core.wipcgroupplan.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.mapper.CgroupPlanMapper;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CgroupPlanServiceImpl extends BaseServiceImpl<CgroupPlan> implements ICgroupPlanService{
    @Autowired
    private CgroupPlanMapper cgroupPlanMapper;
    @Override
    public List<CgroupPlan> selectByErdat(String erdat) {
        return cgroupPlanMapper.selectByErdat(erdat);
    }
}