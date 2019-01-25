package yj.core.mouldcavity.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.mouldcavity.dto.Mouldcavity;

public interface MouldcavityMapper extends Mapper<Mouldcavity> {
    /**
     * 根据物料编号查询模具型腔数量 918100064
     * @param matnr
     * @return
     */
    Integer selectByMatnr(@Param("matnr")String matnr);

}