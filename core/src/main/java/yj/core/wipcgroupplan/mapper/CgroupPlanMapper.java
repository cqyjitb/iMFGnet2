package yj.core.wipcgroupplan.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipcgroupplan.dto.CgroupPlan;

import java.util.List;

public interface CgroupPlanMapper extends Mapper<CgroupPlan>{
    /**
     * 产线组生产计划表查询 918100064
     * @param cgroupPlan
     * @return
     */
    List<CgroupPlan> selectCgroupPlan(CgroupPlan cgroupPlan);

    /**
     * 产线组生产计划表添加 918100064
     * @param cgroupPlan
     */
    void insertCgroupPlan(CgroupPlan cgroupPlan);

    /**
     * 产线组生产计划表修改 918100064
     * @param cgroupPlan
     */
    void updateCgroupPlan(CgroupPlan cgroupPlan);

    /**
     * 产线组生产计划表删除 918100064
     * @param cgroupPlan
     */
    void deleteCgroupPlan(CgroupPlan cgroupPlan);
    List<CgroupPlan> selectByErdat(String erdat);

}