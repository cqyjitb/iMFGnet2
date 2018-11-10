package yj.core.outsrgrfe.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class OutsrgrfeController extends BaseController{

    @Autowired
    private IOutsrgrfeService service;


    @RequestMapping(value = "/wip/outsrgrfe/query")
    @ResponseBody
    public ResponseData query(Outsrgrfe dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/outsrgrfe/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Outsrgrfe> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/outsrgrfe/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Outsrgrfe> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }