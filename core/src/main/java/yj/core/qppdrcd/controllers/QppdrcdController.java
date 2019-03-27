package yj.core.qppdrcd.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.qppdrcd.dto.Qppdrcd;
import yj.core.qppdrcd.service.IQppdrcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class QppdrcdController extends BaseController{

    @Autowired
    private IQppdrcdService service;


    @RequestMapping(value = "/wip/qppdrcd/query")
    @ResponseBody
    public ResponseData query(Qppdrcd dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qppdrcd/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Qppdrcd> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qppdrcd/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Qppdrcd> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }