package yj.core.outsrgrfe.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.outsrgrfe.dto.Outsrgrfe;

import java.util.List;

public interface OutsrgrfeMapper extends Mapper<Outsrgrfe>{
    /**
     * 新增记录   917110140
     * @param outsrgrfe
     * @return
     */
    int insertOutsrgrfe(Outsrgrfe outsrgrfe);

    /**
     *  根据 工厂 生产订单 工序号 物料编码 供应商编码 查询  917110140
     * @param werks
     * @param aufnr
     * @param vornr
     * @param matnr
     * @param lifnr
     * @return
     */
    Outsrgrfe selectByCondition(@Param("werks") String werks, @Param("aufnr") String aufnr, @Param("vornr") String vornr, @Param("matnr") String matnr, @Param("lifnr") String lifnr);

    /**
     *  根据 工厂 生产订单 工序号 物料编码 供应商编码 修改  917110140
     * @param outsrgrfe
     * @return
     */
    int updateByCondition(Outsrgrfe outsrgrfe);

    /**
     * 根据供应商编码查询 外协采购订单接口表记录
     * @param lifnr
     * @return
     */
    List<Outsrgrfe> selectForSortl(String lifnr);

    List<Outsrgrfe> selectAllLifnr();

    /**
     *  根据sap传入的条件取值 917110140
     * @param werks
     * @param matnr
     * @param lifnr
     * @param ktsch
     * @return
     */
    List<Outsrgrfe> sapquery(@Param("werks") String werks,@Param("matnr") String matnr,@Param("lifnr") String lifnr,@Param("ktsch") String ktsch);
}