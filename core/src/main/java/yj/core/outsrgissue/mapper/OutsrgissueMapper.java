package yj.core.outsrgissue.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.outsrgissue.dto.Outsrgissue;

import java.util.List;

public interface OutsrgissueMapper extends Mapper<Outsrgissue>{
    /**
     *  根据 外协采购订单号 行号 工厂 物料 供应商编码 查询采购订单已发货记录  917110140
     * @param ebeln
     * @param ebelp
     * @param werks
     * @param lifnr
     * @param matnr
     * @return
     */
    List<Outsrgissue> selectByContidion(@Param("ebeln") String ebeln,@Param("ebelp") String ebelp,@Param("werks") String werks,@Param("lifnr") String lifnr,@Param("matnr") String matnr);

    /**
     *  根据发料单号查询记录 按照行号 降序排序 917110140
     * @param issuenm
     * @return
     */
    List<Outsrgissue> selectByIssuenmDesc(@Param("issuenm") String issuenm);

    /**
     *  插入新记录 917110140
     * @param outsrgissue
     * @return
     */
    int insertNewRow(Outsrgissue outsrgissue);

    /**
     *  根据流转卡号查询外协发料记录
     * @param zpgdbar
     * @return
     */
    Outsrgissue selectByBarcode(String zpgdbar);
}