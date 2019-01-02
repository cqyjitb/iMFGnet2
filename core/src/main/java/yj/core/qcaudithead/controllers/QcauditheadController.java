package yj.core.qcaudithead.controllers;

import oracle.jdbc.proxy.annotation.Post;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcaudithead.service.IQcauditheadService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class QcauditheadController extends BaseController{

    @Autowired
    private IQcauditheadService service;


    @RequestMapping(value = "/wip/qcaudithead/query")
    @ResponseBody
    public ResponseData query(Qcaudithead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qcaudithead/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Qcaudithead> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qcaudithead/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Qcaudithead> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
        @RequestMapping(value = {"/wip/qcaudithead/createQcaudit1"},method = {RequestMethod.POST})
        @ResponseBody
        public ResponseData createQcaudit(HttpServletRequest request,@RequestBody List<InOutRecord> dto){
        ResponseData rs = new ResponseData();
        if (dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                //根据数据 生成不合格品审理单表头 和行
            }
        }else{
            rs.setMessage("");
            rs.setSuccess(false);
        }
        return rs;
    }
    }