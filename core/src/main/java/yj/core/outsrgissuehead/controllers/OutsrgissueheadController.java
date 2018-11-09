package yj.core.outsrgissuehead.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;
import yj.core.outsrgissuehead.service.IOutsrgissueheadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class OutsrgissueheadController extends BaseController{

    @Autowired
    private IOutsrgissueheadService service;


    @RequestMapping(value = "/wip/outsrgissuehead/query")
    @ResponseBody
    public ResponseData query(Outsrgissuehead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/outsrgissuehead/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Outsrgissuehead> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/outsrgissuehead/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Outsrgissuehead> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }