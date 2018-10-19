package yj.core.wipcurlzk.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class CurlzkController extends BaseController{

    @Autowired
    private ICurlzkService service;


    @RequestMapping(value = "/wip/curlzk/query")
    @ResponseBody
    public ResponseData query(Curlzk dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/curlzk/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Curlzk> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/curlzk/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Curlzk> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }