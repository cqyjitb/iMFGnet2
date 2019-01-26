package yj.core.mouldcavity.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.mouldcavity.dto.Mouldcavity;

import java.util.List;

public interface MouldcavityMapper extends Mapper<Mouldcavity> {
    /**
     * 根据物料编号查询模具型腔数量 918100064
     * @param matnr
     * @return
     */
    Integer selectByMatnr(@Param("matnr")String matnr);

    /**
     * 产品模具型腔表查询  918100064
     * @param dto
     * @return
     */
    List<Mouldcavity> selectMouldcavity(Mouldcavity dto);

    /**
     * 产品模具型腔表添加  918100064
     * @param dto
     */
    void insertMouldcavity(Mouldcavity dto);

    /**
     * 产品模具型腔表修改  918100064
     * @param dto
     */
    void updateMouldcavity(Mouldcavity dto);

    /**
     * 产品模具型腔表删除  918100064
     * @param dto
     */
    void deleteMouldcavity(Mouldcavity dto);
}