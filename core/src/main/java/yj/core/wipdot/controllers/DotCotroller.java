package yj.core.wipdot.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.wipdot.dto.Dot;
import yj.core.wipdot.service.IDotService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DotCotroller extends BaseController{
    @Autowired
    private IDotService service;

    @RequestMapping(value = "/wip/dot/query")
    @ResponseBody
    public ResponseData query(Dot dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/dot/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Dot> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/dot/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Dot> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}
