package yj.core.qcauditlist.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.service.IQcauditlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class QcauditlistController extends BaseController{

    @Autowired
    private IQcauditlistService service;


    @RequestMapping(value = "/wip/qcauditlist/query")
    @ResponseBody
    public ResponseData query(Qcauditlist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qcauditlist/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Qcauditlist> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qcauditlist/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Qcauditlist> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }