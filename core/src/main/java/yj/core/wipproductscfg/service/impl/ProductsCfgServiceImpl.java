package yj.core.wipproductscfg.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.mapper.ProductsCfgMapper;
import yj.core.wipproductscfg.service.IProductsCfgService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductsCfgServiceImpl extends BaseServiceImpl<ProductsCfg> implements IProductsCfgService{
    @Autowired
    private ProductsCfgMapper productsCfgMapper;

    @Override
    public List<ProductsCfg> selectById(long line_id) {
        return productsCfgMapper.selectById(line_id);
    }

    @Override
    public int selectMaxByLineidAndMatnr(ProductsCfg dto) {
        return productsCfgMapper.selectMaxByLineidAndMatnr(dto);
    }

    @Override
    public int selectMaxByLineidAndMatnrLgort(ProductsCfg dto) {
        return productsCfgMapper.selectMaxByLineidAndMatnrLgort(dto);
    }

    @Override
    public ProductsCfg selectByLineidAndMatnr(String line_id, String matnr) {
        return productsCfgMapper.selectByLineidAndMatnr(line_id,matnr);
    }
}