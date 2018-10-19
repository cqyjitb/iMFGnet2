package yj.core.zrwklist.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zrwklist.service.IZrwklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class ZrwklistController extends BaseController{

    @Autowired
    private IZrwklistService service;


    @RequestMapping(value = "/wip/zrwklist/query")
    @ResponseBody
    public ResponseData query(Zrwklist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/zrwklist/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Zrwklist> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/zrwklist/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Zrwklist> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }