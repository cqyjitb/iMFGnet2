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
}