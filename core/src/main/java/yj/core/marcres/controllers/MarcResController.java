package yj.core.marcres.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.marcres.dto.MarcRes;
import yj.core.marcres.service.IMarcResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class MarcResController extends BaseController{

    @Autowired
    private IMarcResService service;


    @RequestMapping(value = "/sap/marc/res/query")
    @ResponseBody
    public ResponseData query(MarcRes dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sap/marc/res/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<MarcRes> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/marc/res/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<MarcRes> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }