package yj.core.wmsxhcard.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.wmsxhcard.dto.Xhcard;
import yj.core.wmsxhcard.service.IXhcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class XhcardController extends BaseController{

    @Autowired
    private IXhcardService service;


    @RequestMapping(value = "/wms/xhcard/query")
    @ResponseBody
    public ResponseData query(Xhcard dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wms/xhcard/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Xhcard> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wms/xhcard/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Xhcard> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }