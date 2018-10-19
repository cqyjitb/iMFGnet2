package yj.core.marc.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

    @Controller
    public class MarcController extends BaseController{

    @Autowired
    private IMarcService service;


    @RequestMapping(value = "/sap/marc/query")
    @ResponseBody
    public ResponseData query(Marc dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sap/marc/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Marc> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/marc/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Marc> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = "/sap/marc/save")
        @ResponseBody
        public ResponseData saveChange(HttpServletRequest request,@RequestBody List<Marc> dto){
            String createdBy = "" + request.getSession().getAttribute("userId");
            int sum = 0;
            if (dto.size() > 0){
                 for (int i = 0;i<dto.size();i++){
                     dto.get(i).setLastUpdatedBy(Long.valueOf(createdBy));
                     dto.get(i).setLastUpdatedDate(new Date());
                 }
                 sum = service.saveChange(dto);
            }

            ResponseData rs = new ResponseData();
            rs.setSuccess(true);
            rs.setMessage("已经修改数据记录" + sum + "条！");
            return rs;
        }
    }