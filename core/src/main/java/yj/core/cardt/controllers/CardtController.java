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
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;

@Controller
public class CardtController
        extends BaseController {
    @Autowired
    private ICardtService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IOutsrgrfeService iOutsrgrfeService;

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
        ResponseData rs = new ResponseData();
        String zpgdbar = request.getParameter("zpgdbar");
        String ktsch = request.getParameter("ktsch");

        Cardt param = new Cardt();
        param.setZpgdbar(zpgdbar);
        param.setKtsch(ktsch);
        List<Cardt> list = new ArrayList<>();
        Cardt cardt = service.selectByBarcodeAndKtsch(param);
        if (cardt == null){
            rs.setSuccess(true);
            rs.setMessage("该派工单不存在标准值码:"+ktsch+"所对应的工序！");
            return rs;
        }

        if (cardt.getSteus().equals("ZP02")){
            rs.setSuccess(false);
            rs.setMessage("外协工序请使用外协收货报工");
            return rs;
        }

            //判断工序是不是首工序
            List<Cardt> listasc = service.selectByZpgdbarAsc(zpgdbar);
            if (listasc.get(0).getVornr().equals(cardt.getVornr())){
                rs.setCode("FIRST");   
            }else{
                rs.setCode("");
            }
            list.add(cardt);
            rs.setRows(list);
            rs.setSuccess(true);
            return rs;

    }

}
