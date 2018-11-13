package yj.core.outsrgissuehead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;

import java.util.List;

public interface OutsrgissueheadMapper extends Mapper<Outsrgissuehead>{

    /**
     *  根据 物流编码 供应商编码 查询发料单号 状态=0的记录
     * @param matnr
     * @param lifnr
     * @return
     */
    List<Outsrgissuehead> selectByMatnrAndLifnrDesc(@Param("matnr") String matnr, @Param("lifnr") String lifnr);

    /**
     *  插入新记录 917110140
     * @param outsrgissuehead
     * @return
     */
    int insertNewRow(Outsrgissuehead outsrgissuehead);
}