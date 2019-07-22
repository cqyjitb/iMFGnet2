package yj.core.wipcgroupplan.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipcgroupplan.dto.CgroupPlan;

import java.util.List;

public interface CgroupPlanMapper extends Mapper<CgroupPlan>{

    List<CgroupPlan> selectByErdat(String erdat);

}