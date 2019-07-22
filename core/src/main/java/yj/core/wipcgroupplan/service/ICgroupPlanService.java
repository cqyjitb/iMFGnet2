package yj.core.wipcgroupplan.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipcgroupplan.dto.CgroupPlan;

import java.util.List;

public interface ICgroupPlanService extends IBaseService<CgroupPlan>, ProxySelf<ICgroupPlanService>{

    List<CgroupPlan> selectByErdat(String erdat);

}