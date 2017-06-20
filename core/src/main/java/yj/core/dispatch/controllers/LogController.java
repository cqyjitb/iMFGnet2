package yj.core.dispatch.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.service.ILogService;
import yj.core.webservice.dto.DTPP001ReturnResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

    @Controller
    public class LogController extends BaseController{

    @Autowired
    private ILogService service;


    @RequestMapping(value = "/confirmation/log/query")
    @ResponseBody
    public ResponseData query(Log dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/confirmation/log/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Log> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/confirmation/log/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Log> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = "/confirmation/log/sap/charge")
        @ResponseBody
        public ResponseData chargeAgainst(HttpServletRequest request,@RequestBody List<InputLog> inputLogs){
            List<DTPP001ReturnResult> returnResults = new ArrayList<>();
            returnResults.add(service.chargeAgainstConnector(inputLogs.get(0)));
            return new ResponseData(returnResults);
        }




    }