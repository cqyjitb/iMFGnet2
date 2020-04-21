package yj.core.ztbc0002.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.webservice_queryXhcard.components.QueryXhcardWebserviceUtil;
import yj.core.webservice_queryXhcard.dto.QueryXhcardParam;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_readztpbar.components.ReadZtpbarWebserviceUtil;
import yj.core.webservice_readztpbar.dto.DTReadZtpbarReturnResult;
import yj.core.webservice_readztpbar.dto.ReadZtpbarParam;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.ztbc0002.dto.Ztbc0002;
import yj.core.ztbc0002.service.IZtbc0002Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Ztbc0002Controller extends BaseController {

    @Autowired
    private IZtbc0002Service service;
    @Autowired
    private ReadZtpbarWebserviceUtil readZtpbarWebserviceUtil;
    @Autowired
    private QueryXhcardWebserviceUtil queryXhcardWebserviceUtil;
    @Autowired
    private IXhcardService xhcardService;

    @RequestMapping(value = "/sap/ztbc0002/query")
    @ResponseBody
    public ResponseData query(Ztbc0002 dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/sap/ztbc0002/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Ztbc0002> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/ztbc0002/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Ztbc0002> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/ztbc0002/querytpbar"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData queryTpBarFromWS(HttpServletRequest request) {
        String ztpbar = request.getParameter("ztpbar");
        Ztbc0002 ztbc0002 = service.queryTpBarFromWS(ztpbar);


        //根据托盘条码查询sap托盘明细数据

        String orderno = request.getParameter("orderno");
        ReadZtpbarParam param = new ReadZtpbarParam();
        DTReadZtpbarReturnResult res = new DTReadZtpbarReturnResult();
        param.setZTPBAR(ztpbar);
        res = readZtpbarWebserviceUtil.receiveConfirmation(param);
        if (res.getTYPE().equals("E")) {
            ResponseData rs = new ResponseData();
            rs.setSuccess(false);
            rs.setMessage(res.getMESSAGE());
            return rs;
        }

        //判断托盘是否存在多个订单拼托的情况
        //1'循环 查询SAP箱号接口
        List<Ztbc0002> ztbc0002s = new ArrayList<>();
        List listaufnrstr = new ArrayList();
        List<Xhcard> listtmp = new ArrayList<>();
        ztbc0002s = res.getZtbc0002List();

        for (int i = 0; i < ztbc0002s.size(); i++) {
            QueryXhcardParam param1 = new QueryXhcardParam();
            param1.setZxhbar(ztbc0002s.get(i).getZxhbar());
            param1.setQtype("");
            param1.setMatnr("");
            param1.setLgort("");
            QueryXhcardReturnResult queryXhcardReturnResult = new QueryXhcardReturnResult();
            queryXhcardReturnResult = queryXhcardWebserviceUtil.receiveConfirmation(param1);
            if (queryXhcardReturnResult.getMSGTY().equals("E")) {
                ResponseData rs = new ResponseData();
                rs.setSuccess(false);
                rs.setMessage(queryXhcardReturnResult.getMESSAGE());
                return rs;
            } else {
                Xhcard xhctmp = new Xhcard();
                xhctmp.setZxhbar(ztbc0002s.get(i).getZxhbar());
                xhctmp.setAufnr(orderno);
                Xhcard xhcard = xhcardService.selectByBacode(ztbc0002s.get(i).getZxhbar());
                if (!listaufnrstr.contains(xhcard.getAufnr().trim())) {
                    listaufnrstr.add(xhcard.getAufnr().trim());
                }

                listtmp.add(xhcard);


            }
        }
        Double sum = 0D;

        //判断箱号的状态
        for (int i = 0; i < listtmp.size(); i++) {
            if (listtmp.get(i).getAufnr().trim().equals(orderno)) {
                if (!listtmp.get(i).getZxhzt().equals("1")) {
                    ResponseData rs = new ResponseData();
                    rs.setSuccess(false);
                    rs.setMessage("该托盘已报工，请勿重复报工！！");
                    return rs;
                } else {
                    sum = sum + Double.parseDouble(listtmp.get(i).getMenge().trim());
                }
            }

        }
        ztbc0002.setMenge(sum);

        ResponseData rs = new ResponseData();
        rs.setSuccess(true);
        List list = new ArrayList();
        list.add(ztbc0002);
        rs.setRows(list);
        return rs;
    }
}