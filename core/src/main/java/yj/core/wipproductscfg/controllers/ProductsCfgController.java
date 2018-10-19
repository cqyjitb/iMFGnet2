package yj.core.wipproductscfg.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class ProductsCfgController extends BaseController{

    @Autowired
    private IProductsCfgService service;


    @RequestMapping(value = "/wip/products/cfg/query")
    @ResponseBody
    public ResponseData query(ProductsCfg dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/products/cfg/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<ProductsCfg> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/products/cfg/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<ProductsCfg> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }