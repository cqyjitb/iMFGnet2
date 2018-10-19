package yj.core.qpcd.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.qpcd.dto.Qpcd;
import yj.core.qpcd.service.IQpcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class QpcdController extends BaseController{

    @Autowired
    private IQpcdService service;


    @RequestMapping(value = "/sap/qpcd/query")
    @ResponseBody
    public ResponseData query(Qpcd dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sap/qpcd/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Qpcd> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/qpcd/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Qpcd> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }