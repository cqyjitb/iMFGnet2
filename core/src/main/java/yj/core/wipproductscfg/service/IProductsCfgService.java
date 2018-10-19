package yj.core.wipproductscfg.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipproductscfg.dto.ProductsCfg;

import java.util.List;

public interface IProductsCfgService extends IBaseService<ProductsCfg>, ProxySelf<IProductsCfgService>{
    List<ProductsCfg> selectById(long line_id);

    int selectMaxByLineidAndMatnr(ProductsCfg dto);

    int selectMaxByLineidAndMatnrLgort(ProductsCfg dto);

    ProductsCfg selectByLineidAndMatnr(String line_id,String matnr);
}