package yj.core.wipcgroupplan.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CgroupPlanServiceImpl extends BaseServiceImpl<CgroupPlan> implements ICgroupPlanService{

}