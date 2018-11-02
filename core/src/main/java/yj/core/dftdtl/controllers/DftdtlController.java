package yj.core.dftdtl.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.dftdtl.dto.Dftdtl;
import yj.core.dftdtl.service.IDftdtlService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

    @Controller
    public class DftdtlController extends BaseController{

    @Autowired
    private IDftdtlService service;


    @RequestMapping(value = "/sap/dftdtl/query")
    @ResponseBody
    public ResponseData query(Dftdtl dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sap/dftdtl/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Dftdtl> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/dftdtl/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Dftdtl> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        /**
         * 根据已经质量原因代码，获取相应的二级原因代码
         * @param request
         * @param code
         * @return
         */
        @RequestMapping(value = {"/sap/dftdtl/selectByQpcode"}, method = {RequestMethod.GET})
        @ResponseBody
        public ResponseData selectByQpcode(HttpServletRequest request,String code,String matnr){
        ResponseData rs = new ResponseData();
        List list = new ArrayList();
        List<Dftdtl> dftdtlList = new ArrayList<>();
        dftdtlList = service.selectByQpcode(code,matnr);
        if (dftdtlList.size() > 0){
            list.add(dftdtlList);
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setMessage("未获取到质量原因代码:" + code + " 对应的下层原因代码，请联系管理员！");
            rs.setSuccess(false);
        }
        return rs;
        }

    }