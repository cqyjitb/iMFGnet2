package yj.core.ztpp0023.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.ztpp0023.dto.Ztpp0023;
import yj.core.ztpp0023.service.IZtpp0023Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class Ztpp0023Controller extends BaseController{

    @Autowired
    private IZtpp0023Service service;


    @RequestMapping(value = "/sap/ztpp0023/query")
    @ResponseBody
    public ResponseData query(Ztpp0023 dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sap/ztpp0023/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Ztpp0023> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/ztpp0023/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Ztpp0023> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }