package yj.core.wipproductscfg.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipproductscfg.dto.ProductsCfg;

import java.util.List;

public interface ProductsCfgMapper extends Mapper<ProductsCfg>{
    List<ProductsCfg> selectById(long line_id);

    int selectMaxByLineidAndMatnr(ProductsCfg dto);
    int selectMaxByLineidAndMatnrLgort(ProductsCfg dto);
    ProductsCfg selectByLineidAndPMatnr(@Param("line_id") String line_id,@Param("matnr") String matnr);

    ProductsCfg selectByLineidAndMatnr(@Param("line_id") String line_id,@Param("matnr") String matnr);

    /**
     * 根据lineId查询 918100064
     * @param lineId
     * @return
     */
    List<ProductsCfg> selectByLineId(@Param("lineId") String lineId);

    /**
     * 机加产线产品配置维护查询 918100064
     * @param productsCfg
     * @return
     */
    List<ProductsCfg> selectFromPage(ProductsCfg productsCfg);

    /**
     * 根据主键查询数据的条数 918100064
     * @param werks
     * @param lineId
     * @param pmatnr
     * @return
     */
    int isExit(@Param("werks") String werks,@Param("lineId") Long lineId,@Param("pmatnr") String pmatnr);

    /**
     * 机加产线产品配置维护修改 918100064
     * @param productsCfg
     */
    void updateProductsCfg(ProductsCfg productsCfg);

    /**
     * 机加产线产品配置维护添加 918100064
     * @param productsCfg
     */
    void insertProductsCfg(ProductsCfg productsCfg);

    /**
     * 机加产线产品配置维护删除 918100064
     * @param productsCfg
     */
    void deleteProductsCfg(ProductsCfg productsCfg);
}