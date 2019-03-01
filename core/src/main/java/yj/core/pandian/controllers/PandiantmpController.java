package yj.core.pandian.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.pandian.dto.Pandiantmp;
import yj.core.pandian.service.IPandiantmpService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
    public class PandiantmpController extends BaseController{

    @Autowired
    private IPandiantmpService service;


    @RequestMapping(value = "/wip/pandiantmp/query")
    @ResponseBody
    public ResponseData query(Pandiantmp dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/pandiantmp/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Pandiantmp> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/pandiantmp/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Pandiantmp> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = {"/wip/pandiantmp/insertNewRow"}, method = {RequestMethod.GET})
        @ResponseBody
        public ResponseData pandiantmpSumbit(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String barcode = request.getParameter("barcode");
        String vornr = request.getParameter("vornr");
        Double num = Double.parseDouble(request.getParameter("num"));
        String userName = request.getParameter("userName");
        UUID uuid = java.util.UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        Long userId = Long.parseLong(request.getParameter("userId"));
            String werks = null;
            try {
                werks = new String(request.getParameter("werks").getBytes("iso8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Pandiantmp pdtmp = new Pandiantmp();
        pdtmp.setRcdid(uuidstr);
        pdtmp.setCardno(barcode);
        pdtmp.setCardh(vornr);
        pdtmp.setOperator(userName);
        pdtmp.setNum(num);
        pdtmp.setRcddat(new Date());
        pdtmp.setCreationDate(new Date());
        pdtmp.setCreatedBy(userId);
        pdtmp.setWerks(werks);

        int i = service.insertNewRow(pdtmp);
        if (i == 1){
            rs.setMessage("盘点数据保存成功！");
            rs.setSuccess(true);
        }else{
            rs.setMessage("盘点数据保存失败！");
            rs.setSuccess(false);
        }

        return rs;
        }
    }