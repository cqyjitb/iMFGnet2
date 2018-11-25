package yj.core.outsrgreceipthead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.outsrgreceipthead.dto.Outsrgreceipthead;

import java.util.List;

public interface OutsrgreceiptheadMapper extends Mapper<Outsrgreceipthead>{
    /**
     *  根据物料编码 供应商编码查询收货单表头 降序排列 917110140
     * @param matnr
     * @param lifnr
     * @return
     */
    List<Outsrgreceipthead> selectByMatnrAndLifnrDesc(@Param("matnr") String matnr , @Param("lifnr") String lifnr);
    /**
     *  插入新行 917110140
     * @param outsrgreceipthead
     * @return
     */
    int insertNewRow(Outsrgreceipthead outsrgreceipthead);
}