package yj.core.wipqcparamlines.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.wipqcparamlines.dto.QcparamLines;
import yj.core.wipqcparamlines.service.IQcparamLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class QcparamLinesController extends BaseController{

    @Autowired
    private IQcparamLinesService service;


    @RequestMapping(value = "/wip/qcparam/lines/query")
    @ResponseBody
    public ResponseData query(QcparamLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qcparam/lines/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<QcparamLines> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qcparam/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<QcparamLines> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }