package yj.core.assembonline.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.assembonline.dto.AssembliesOnline;
import yj.core.assembonline.service.IAssembliesOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class AssembliesOnlineController extends BaseController{

    @Autowired
    private IAssembliesOnlineService service;


    @RequestMapping(value = "/wip/assemblies/online/query")
    @ResponseBody
    public ResponseData query(AssembliesOnline dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/assemblies/online/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<AssembliesOnline> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/assemblies/online/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<AssembliesOnline> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }