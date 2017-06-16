package yj.core.dispatch.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class InputLogController extends BaseController{

    @Autowired
    private IInputLogService service;



    @RequestMapping(value = "/confirmation/input/log/query")
    @ResponseBody
    public ResponseData query(InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/confirmation/input/log/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<InputLog> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/confirmation/input/log/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<InputLog> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = "/confirmation/input/log/insertInputLog" ,method = RequestMethod.POST)
        @ResponseBody
        public ResponseData insertInputLog(HttpServletRequest request,@RequestBody InputLog input){
            IRequest requestCtx = createRequestContext(request);
            return new ResponseData(service.inputDispatch(input));
        }


    }