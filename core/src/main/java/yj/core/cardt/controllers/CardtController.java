package yj.core.cardt.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;

@Controller
public class CardtController
        extends BaseController {
    @Autowired
    private ICardtService service;

    @RequestMapping({"/sap/cardt/query"})
    @ResponseBody
    public ResponseData query(Cardt dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardt/queryAfterSort"})
    @ResponseBody
    public ResponseData queryAfterSort(Cardt dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryAfterSort(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/cardt/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Cardt> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/cardt/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Cardt> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/sap/cardt/selectByZpgdbar"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByZpgdbar(HttpServletRequest request) {
        String zpgdbar = request.getParameter("zpgdbar");
        return new ResponseData(service.selectByZpgdbar(zpgdbar));
    }

    //根据标准值码和流转卡号查询工序记录
    @RequestMapping(value = {"/sap/cardt/selectByZpgdbarAndKtsch"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByBarcodeAndKtsch(HttpServletRequest request) {
        String zpgdbar = request.getParameter("zpgdbar");
        String ktsch = request.getParameter("ktsch");
        Cardt param = new Cardt();
        param.setZpgdbar(zpgdbar);
        param.setKtsch(ktsch);
        List<Cardt> list = new ArrayList<>();
        Cardt cardt = service.selectByBarcodeAndKtsch(param);
        if (cardt == null){
            ResponseData rs = new ResponseData(false);
            rs.setMessage("该派工单不存在标准值码:"+ktsch+"所对应的工序！");
            return rs;
        }else{
            ResponseData rs = new ResponseData(list);
            //判断工序是不是首工序
            List<Cardt> listasc = service.selectByZpgdbarAsc(zpgdbar);
            if (listasc.get(0).getVornr().equals(cardt.getVornr())){
                rs.setCode("FIRST");   
            }else{
                rs.setCode("");
            }
            list.add(cardt);

            rs.setSuccess(true);
            return rs;
        }
    }

}
