package yj.core.afvc.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;

@Controller
public class AfvcController
        extends BaseController
{
    @Autowired
    private IAfvcService service;

    @RequestMapping({"/sap/afvc/query"})
    @ResponseBody
    public ResponseData query(Afvc dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/afvc/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Afvc> dto)
    {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/afvc/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Afvc> dto)
    {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value={"/sap/afvc/selectByAufpl"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByAufnr(HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        String aufpl = request.getParameter("aufpl");
        List<Afvc> list = service.selectByAufpl(aufpl);
        return new ResponseData(list);
    }
}
