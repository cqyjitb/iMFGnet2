package yj.core.wipproductscfg.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.mapper.ProductsCfgMapper;
import yj.core.wipproductscfg.service.IProductsCfgService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public ProductsCfg selectByLineidAndPMatnr(String line_id, String matnr) {
        return productsCfgMapper.selectByLineidAndPMatnr(line_id,matnr);
    }

    @Override
    public ProductsCfg selectByLineidAndMatnr(String line_id, String matnr) {
        return productsCfgMapper.selectByLineidAndMatnr(line_id,matnr);
    }

    @Override
    public List<ProductsCfg> selectFromPage(IRequest requestContext, ProductsCfg dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return productsCfgMapper.selectFromPage(dto);
    }

    @Override
    public String updateOrInsert(IRequest requestContext, List<ProductsCfg> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                ProductsCfg productsCfg = dto.get(i);
                if("Y".equals(productsCfg.getCustbarcodeynCarton())){
                    productsCfg.setCustbarcodeynCarton("1");
                }else{
                    productsCfg.setCustbarcodeynCarton("0");
                }if("Y".equals(productsCfg.getCustbarcodeynBox())){
                    productsCfg.setCustbarcodeynBox("1");
                }else{
                    productsCfg.setCustbarcodeynBox("0");
                }
                int num = productsCfgMapper.isExit(productsCfg.getWerks(),productsCfg.getLineId(),productsCfg.getPmatnr());
                if (num == 1){
                    productsCfg.setLastUpdatedDate(new Date());
                    productsCfg.setLastUpdatedBy(Long.valueOf(userId));
                    productsCfgMapper.updateProductsCfg(productsCfg);
                }else{
                    productsCfg.setCreationDate(new Date());
                    productsCfg.setCreatedBy(Long.valueOf(userId));
                    productsCfgMapper.insertProductsCfg(productsCfg);
                }
            }
        }
        return null;
    }

    @Override
    public void deleteProductsCfg(List<ProductsCfg> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                ProductsCfg productsCfg = dto.get(i);
                productsCfgMapper.deleteProductsCfg(productsCfg);
            }
        }

    }
}