package yj.core.wipcgroupplan.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipcgroupplan.dto.CgroupPlan;

import java.util.List;

public interface ICgroupPlanService extends IBaseService<CgroupPlan>, ProxySelf<ICgroupPlanService>{
    /**
     * 产线组生产计划维护查询 918100064
     * @param cgroupPlan
     * @param requestContext
     * @return
     */
    List<CgroupPlan> selectCgroupPlan(CgroupPlan cgroupPlan, IRequest requestContext);

    /**
     * 产线组生产计划维护添加及修改 918100064
     * @param cgroupPlan
     * @param userId
     * @param requestContext
     */
    String insertOrUpdate(CgroupPlan cgroupPlan,String userId, IRequest requestContext);

    /**
     * 产线组生产计划维护删除 918100064
     * @param cgroupPlan
     */
    String deleteCgroupPlan(CgroupPlan cgroupPlan);
}