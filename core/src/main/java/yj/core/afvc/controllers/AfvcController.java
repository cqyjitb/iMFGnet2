package yj.core.afvc.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.webservice_queryoldzpgdbar.components.QueryOldZpgdbarUtil;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryParm;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryReturn;

@Controller
public class AfvcController
        extends BaseController
{
    @Autowired
    private IAfvcService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private QueryOldZpgdbarUtil queryOldZpgdbarUtil;




    @RequestMapping({"/sap/afvc/query"})
    @ResponseBody
    public ResponseData query(Afvc dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/afvc/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Afvc> dto)
    {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/afvc/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Afvc> dto)
    {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value={"/sap/afvc/selectByAufpl"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByAufnr(HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        String aufpl = request.getParameter("aufpl");
        List<Afvc> list = service.selectByAufpl(aufpl);
        return new ResponseData(list);
    }

    /**
     *  根据派工单号 取工作中心 917110140
     * @param request
     * @return
     */
    @RequestMapping(value = {"/sap/afvc/getArbpl"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByZpgdbar(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String zpgdbar = request.getParameter("zpgdbar");
        String type = request.getParameter("type");
        if (type.equals("new")){//新派工单
            Cardh cardh = new Cardh();
            cardh = cardhService.selectByBarcode(zpgdbar);
            if (cardh == null){
                rs.setMessage("派工单不存在，请检查派工单号是否错误！");
                rs.setSuccess(false);
                return rs;
            }

            Afko afko = new Afko();
            afko = afkoService.selectByAufnr(cardh.getAufnr());
            if (afko == null){
                rs.setMessage("派工单所对应的生产订单信息不存在！");
                rs.setSuccess(false);
                return rs;
            }else{
                if (!afko.getAuart().equals("YZ01") && !afko.getAuart().equals("YZ03") && !afko.getAuart().equals("YZ03"))
                {
                    rs.setSuccess(false);
                    rs.setMessage("请扫描压铸类派工单！");
                    return rs;
                }
            }
            Afvc afvc = service.selectByZpgdbar(zpgdbar);
            if (afvc != null){
                List<Afvc> list = new ArrayList<>();
                list.add(afvc);
                rs.setRows(list);
                rs.setSuccess(true);
            }else{
                rs.setMessage("派工单错误，未能获取到订单信息！");
                rs.setSuccess(false);
            }
        }else if (type.equals("old")){//老派工单
            DtqueryParm parm = new DtqueryParm();
            DtqueryReturn re = new DtqueryReturn();
            parm.setZpgdbar(zpgdbar);
            re = queryOldZpgdbarUtil.receiveConfirmation(parm);
            if (re.getMsgty().equals("S")){
                //根据工厂 物料号 获取模号和出模数

                List<Afvc> list = new ArrayList<>();
                Afvc afvc = new Afvc();
                afvc.setArbpl(re.getArbpl());
                afvc.setKtext(re.getArbpldesc());
                afvc.setMatnr(re.getMatnr());
                afvc.setMaktx(re.getMaktx());
                list.add(afvc);
                rs.setSuccess(true);
                rs.setRows(list);
            }else{
                rs.setSuccess(false);
                rs.setMessage(re.getMessage());
            }

        }

       return rs;
    }
}
