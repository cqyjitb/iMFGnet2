package yj.core.dispath.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.dispath.dto.Test;
import yj.core.dispath.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class TestController extends BaseController{

    @Autowired
    private ITestService service;


    @RequestMapping(value = "/confirmation/test/query")
    @ResponseBody
    public ResponseData query(Test dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/confirmation/test/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Test> dto){
        IRequest requestCtx = createRequestContext(request);
        for (Test t : dto) {

            service.deleteById(t.getId());
            service.insertTest(t);

        }
        return new ResponseData();
    };



    @RequestMapping(value = "/confirmation/test/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Test> dto){
        for (Test t : dto) {
            service.deleteById(t.getId());
        }
        return new ResponseData();
    }
    }