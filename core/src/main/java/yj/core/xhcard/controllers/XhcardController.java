package yj.core.xhcard.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.bcel.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.resb.dto.Resb;
import yj.core.resb.service.IResbService;
import yj.core.webserver_weidu.components.WeiduWebserviceUtil;
import yj.core.webserver_weidu.dto.DTWEIDUParam;
import yj.core.webserver_weidu.dto.DTWEIDUReturn;
import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

@Controller
public class XhcardController
        extends BaseController {
    @Autowired
    private IXhcardService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IResbService resbService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IDftrghlistService dftrghlistService;

    @RequestMapping({"/sap/xhcard/query"})
    @ResponseBody
    public ResponseData query(Xhcard dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/xhcard/queryAfterSort"})
    @ResponseBody
    public ResponseData queryAfterSort(Xhcard dto, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        if (dto.getDatestr() != null) {
            dto.setDatestr(dto.getDatestr().substring(0, 10));
        }
        return new ResponseData(service.queryAfterSort(requestContext, dto, page, pageSize));
    }

    @RequestMapping({"/sap/xhcard/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Xhcard> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/xhcard/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Xhcard> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/sap/xhcard/queryXhcard"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByBacode(HttpServletRequest request, String zxhbar) {
        ResponseData rs = new ResponseData();
        List<Xhcard> list = new ArrayList<>();
        Xhcard xhcard = new Xhcard();
//        QueryXhcardReturnResult qrs = new QueryXhcardReturnResult();
//            qrs =  service.selectByBacodeFromSap(zxhbar);
//
//        if (qrs.getMSGTY().equals("S")){
//            xhcard = service.selectByBacode(zxhbar);
//            list.add(xhcard);
//            rs.setSuccess(true);
//            rs.setRows(list);
//        }else{
//            rs.setSuccess(false);
//            rs.setMessage(qrs.getMESSAGE());
//        }
        return rs;
    }

    @RequestMapping(value = {"/sap/xhcard/jjsxCheckZxhbar"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData jjsxCheckZxhbar(HttpServletRequest request, String zxhbar, String curZxhbar, String zpgdbar, Long line_id, String classgrp) {
        IRequest requestCtx = createRequestContext(request);
        List rslist = new ArrayList();
        ResponseData rs = new ResponseData();
        Cardh cardh = new Cardh();
        Cardh cardhjj = new Cardh();
        Afvc afvc = new Afvc();
        List<Afvc> listafvc = new ArrayList<>();
        Marc marc = new Marc();
        Afko afko = new Afko();
        InputLog inputLog = new InputLog();
        List<InputLog> inputLoglist = new ArrayList<>();
        List<Resb> resblist = new ArrayList<>();
        QueryXhcardReturnResult qrs = new QueryXhcardReturnResult();
        QueryXhcardReturnResult qrs1 = new QueryXhcardReturnResult();
        Xhcard xhcard = new Xhcard();
        Xhcard curxhcard = new Xhcard();
        //第一步 获取箱号记录

        qrs = service.selectByBacodeFromSap(zxhbar, "", "", "S");
        xhcard = service.selectByBacode(zxhbar);

        if (xhcard == null) {
            rs.setSuccess(false);
            rs.setMessage("当前毛坯框码：" + zxhbar + " 信息不存在，请确认毛坯框码是否正确！");
            return rs;
        }

        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById(Long.valueOf(line_id), classgrp);
        if (curlzk == null) {
            rs.setSuccess(false);
            rs.setMessage("该生产线无当前处理机加流转卡，请先绑定当前处理流转卡！");
            return rs;
        }

        cardh = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
        DTWEIDUParam param = new DTWEIDUParam();
        param.setMATNR(xhcard.getMatnr());
        param.setWERKS(xhcard.getWerks());
        param.setZBANB(cardh.getSfflg());
        param.setZMODEL(cardh.getDiecd().toUpperCase());
        param.setZXHBAR(xhcard.getZxhbar());

        //机加上线毛坯围堵查询
        WeiduWebserviceUtil weiduWebserviceUtil = new WeiduWebserviceUtil();
        DTWEIDUReturn dtweiduReturn = weiduWebserviceUtil.receiveConfirmation(param);
        if (dtweiduReturn.getMTYPE().equals("S")) {
            if (dtweiduReturn.getWEIDUFLG() != null) {
                if (dtweiduReturn.getWEIDUFLG().equals("1")) {

                    rs.setSuccess(false);
                    rs.setMessage("该毛坯框，班标：" + cardh.getSfflg() + ",属于围堵批次！不允许上线扫描！");
                    return rs;
                }
            }
//            if (dtweiduReturn.getSTATUS() == null){
//
//            }else if (dtweiduReturn.getSTATUS().equals("O")){
//                if (dtweiduReturn.getZXHBAR() == null){
//                    rs.setSuccess(false);
//                    rs.setMessage("该毛坯框，班标："+cardh.getSfflg()+",属于围堵批次！不允许上线扫描！");
//                    return rs;
//                }else{
//                    if (dtweiduReturn.getZXHBAR().equals(xhcard.getZxhbar())){
//                        //放行
//                    }else{
//                        rs.setSuccess(false);
//                        rs.setMessage("该毛坯框，班标："+cardh.getSfflg()+",属于围堵批次！不允许上线扫描！");
//                        return rs;
//                    }
//                }
//            }
        }

        if (xhcard.getZsxwc() != null) {
            if (xhcard.getZsxwc().equals("X")) {
                rs.setSuccess(false);
                rs.setMessage("当前毛坯框码：" + zxhbar + " 已完成上线，不能进行上线操作！");
                return rs;
            }
        }

        if (xhcard.getZsxwc() != null) {
            if (xhcard.getZsxwc().equals("H")) {
                rs.setSuccess(false);
                rs.setMessage("当前毛坯框码：" + zxhbar + " 已冻结，不能进行上线操作！");
                return rs;
            }
        }

        if (cardh.getStatus().equals("HOLD")) {
            rs.setSuccess(false);
            rs.setMessage("当前毛坯框对应的机加流转卡已被冻结，不允许进行机加上线操作！");
            return rs;
        }

        if (xhcard.getZxhzt() != null) {
            if (!xhcard.getZxhzt().equals("7")) {
                rs.setSuccess(false);
                rs.setMessage("当前毛坯框库存状态错误！");
                return rs;
            }
        }

        cardhjj = cardhService.selectByBarcode(zpgdbar);
        if (cardhjj == null) {
            rs.setSuccess(false);
            rs.setMessage("当前机加流转卡：" + zpgdbar + " 信息不存在，请确认该生产线当前机加流转卡信息！");
            return rs;
        }

        //判断扫描的箱号的库存地点是否与产线配置上的箱号一致。
        //1:查询是否配置了 产品信息到产线配置表
        ProductsCfg productsCfg = new ProductsCfg();
        productsCfg.setWerks(xhcard.getWerks());
        productsCfg.setPmatnr(cardhjj.getMatnr());
        productsCfg.setMatnr(xhcard.getMatnr());
        productsCfg.setLineId(Long.valueOf(line_id));
        int num = productsCfgService.selectMaxByLineidAndMatnr(productsCfg);
        if (num == 0) {
            rs.setSuccess(false);
            rs.setMessage("该毛坯物料:" + xhcard.getMatnr() + "非本线生产所用物料！");
            return rs;
        }
        productsCfg.setLgort(xhcard.getLgort());
        int num2 = productsCfgService.selectMaxByLineidAndMatnrLgort(productsCfg);
        if (num2 == 0) {
            rs.setSuccess(false);
            rs.setMessage("该毛坯框:" + xhcard.getZxhbar() + "库存地点：" + xhcard.getLgort() + "错误！");
            return rs;
        }


        curxhcard = service.selectByBacode(curlzk.getZxhbar());

        //查询所有生产线 当前毛坯箱号记录
        List<Curlzk> allcurlzk = new ArrayList<>();
        allcurlzk = curlzkService.selectAllLinesforZxhbar(line_id);
        String l_error = "E";
        //************************************************************************************************************//
        if (curxhcard == null) {
            //第二步 根据箱号获取流转卡记录
            cardh = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
            if (cardh.getStatus().equals("HOLD")) {
                rs.setMessage("当前箱号对应的流转卡处于HOLD状态，不允许继续上线！");
                rs.setSuccess(false);
            }
            cardhjj = cardhService.selectByBarcode(zpgdbar);

            //第三步 根据订单查询末工序记录
            listafvc = afvcService.selectByAufnr(xhcard.getAufnr());
            marc = marcService.selectByMatnr(xhcard.getMatnr());
            //afko
            afko = afkoService.selectByAufnr(cardhjj.getAufnr());
            //第三步  根据流转卡 获取报工记录
            inputLoglist = inputLogService.queryFirstResult(requestCtx, cardh.getZpgdbar(), listafvc.get(0).getVornr());
            if (inputLoglist.size() == 0) {
                rs.setSuccess(false);
                rs.setMessage("该箱号对应的流转卡尚未完成压铸末工序报工，不允许机加上线！");
                return rs;
            }

            //获取物料描述
            for (int i = 0; i < inputLoglist.size(); i++) {
                inputLoglist.get(i).setMatDesc(marc.getMaktx());
            }
            //resb
            resblist = resbService.selectByRsnum(afko.getRsnum());

            for (int i = 0; i < resblist.size(); i++) {
                if (resblist.get(i).getMatnr().equals(xhcard.getMatnr())) {
                    String lgort = resblist.get(i).getLgort();
                    String matnr = xhcard.getMatnr();
                    qrs1 = service.selectByBacodeFromSap(zxhbar, matnr, lgort, "M");
                    List<Xhcard> xhcardlist = service.selectByMatnrAndLgortSort(matnr, lgort);
                    if (xhcardlist.size() > 0) {
//                        if (!xhcardlist.get(0).getChargkc().equals(xhcard.getChargkc())) {
//                            rs.setSuccess(false);
//                            rs.setMessage("未按先进先出规则上线，请先上线批次为：" + xhcardlist.get(0).getChargkc() + " 的毛坯框！");
//                            return rs;
//
//                        }
                        for (int j = 0; j < xhcardlist.size(); j++) {//在先进先出中排除已经围堵的批次
                            String letgo = "";
                            if (Long.valueOf(xhcardlist.get(j).getChargkc()) < Long.valueOf(xhcard.getChargkc())) {
                                for (int w = 0; w < allcurlzk.size(); w++) {
                                    if (allcurlzk.get(w).getZxhbar().equals(xhcardlist.get(j).getZxhbar())) {
                                        letgo = "X";
                                    }
                                }
                                if (letgo.equals("X")) {//批次比当前箱号批次小的 ，如果小批次箱号属于其他线正在上线的箱 不属于先进先出考虑范围
                                    continue;
                                }

                                DTWEIDUParam param1 = new DTWEIDUParam();
                                Cardh cardhtmp = new Cardh();
                                cardhtmp = cardhService.selectByZxhbar(xhcardlist.get(j).getAufnr(), xhcardlist.get(j).getZxhnum());
                                if (cardhtmp.getStatus().equals("HOLD")) {//已经被冻结的不参与批次比较
                                    continue;
                                }
                                param1.setMATNR(xhcardlist.get(j).getMatnr());
                                param1.setWERKS(xhcardlist.get(j).getWerks());
                                param1.setZBANB(cardhtmp.getSfflg());
                                param1.setZMODEL(cardhtmp.getDiecd());
                                param1.setZXHBAR(xhcard.getZxhbar());
                                DTWEIDUReturn dtweiduReturn1 = new DTWEIDUReturn();
                                dtweiduReturn1 = weiduWebserviceUtil.receiveConfirmation(param1);
                                if (dtweiduReturn1.getMTYPE().equals("S")) {
                                    if (dtweiduReturn1.getWEIDUFLG() != null) {
                                        if (dtweiduReturn1.getWEIDUFLG().equals("1")) {

                                        } else {
                                            rs.setSuccess(false);
                                            rs.setMessage("未按先进先出规则上线，请先上线批次为：" + xhcardlist.get(j).getChargkc() + " 的毛坯框！");
                                            return rs;
                                        }
                                    }
                                }

                            }
                        }
                        l_error = "";
                    }
                }

            }

            if (l_error.equals("E")) {
                rs.setSuccess(false);
                rs.setMessage("该毛坯框物料:" + xhcard.getMatnr() + "不是本生产线所需物料,请检查!");
                return rs;
            }

            rs.setSuccess(true);
            rslist.add(inputLoglist);//返回报工记录
            rslist.add(xhcard);//返回扫描的箱号信息
            rs.setRows(rslist);
        } else if (!xhcard.getZxhbar().equals(curxhcard.getZxhbar())) {
            String newXhflg = "";
            if (!zxhbar.equals(curlzk.getZxhbar())) {//扫描的毛坯框码 和当前毛坯框码不一致的时候
                //取当前毛坯框流转卡
                Cardh curcardh = cardhService.selectByZxhbar(curxhcard.getAufnr(), curxhcard.getZxhnum());
                //判断当前毛坯框码是否完成上线

                if (!curcardh.getStatus().equals("HOLD")) {
                    if (curxhcard.getZsxwc() == null) {
                        rs.setSuccess(false);
                        rs.setMessage("上一毛坯框：" + curlzk.getZxhbar() + " 未完成上线！请使用该框码继续上线！");
                        return rs;
                    }

                    if (!curxhcard.getZsxwc().equals("X")) {
                        rs.setSuccess(false);
                        rs.setMessage("上一毛坯框：" + curlzk.getZxhbar() + " 未完成上线！请使用该框码继续上线！");
                        return rs;
                    }
                }

            }
            //第二步 根据箱号获取流转卡记录

            cardh = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
            //第三步 根据订单查询末工序记录

            listafvc = afvcService.selectByAufnr(xhcard.getAufnr());

            marc = marcService.selectByMatnr(xhcard.getMatnr());
            //afko

            afko = afkoService.selectByAufnr(cardhjj.getAufnr());

            //第三步  根据流转卡 获取报工记录

            inputLoglist = inputLogService.queryFirstResult(requestCtx, cardh.getZpgdbar(), listafvc.get(0).getVornr());
            if (inputLoglist.size() == 0) {
                rs.setSuccess(false);
                rs.setMessage("该箱号对应的流转卡尚未完成压铸末工序报工，不允许机加上线！");
                return rs;
            }
            //获取物料描述
            for (int i = 0; i < inputLoglist.size(); i++) {
                inputLoglist.get(i).setMatDesc(marc.getMaktx());
            }
            //resb
            resblist = resbService.selectByRsnum(afko.getRsnum());
            for (int i = 0; i < resblist.size(); i++) {
                if (resblist.get(i).getMatnr().equals(xhcard.getMatnr())) {
                    String lgort = resblist.get(i).getLgort();
                    String matnr = xhcard.getMatnr();
                    qrs1 = service.selectByBacodeFromSap(zxhbar, matnr, lgort, "M");
                    List<Xhcard> xhcardlist = service.selectByMatnrAndLgortSort(matnr, lgort);
                    if (xhcardlist.size() > 0) {
                        for (int j = 0; j < xhcardlist.size(); j++) {//在先进先出中排除已经围堵的批次
                            String letgo = "";
                            if (Long.valueOf(xhcardlist.get(j).getChargkc()) < Long.valueOf(xhcard.getChargkc())) {
                                for (int w = 0; w < allcurlzk.size(); w++) {
                                    if (allcurlzk.get(w).getZxhbar().equals(xhcardlist.get(j).getZxhbar())) {
                                        letgo = "X";
                                    }
                                }
                                if (letgo.equals("X")) {//批次比当前箱号批次小的 ，如果小批次箱号属于其他线正在上线的箱 不属于先进先出考虑范围
                                    continue;
                                }

                                DTWEIDUParam param1 = new DTWEIDUParam();
                                Cardh cardhtmp = new Cardh();
                                cardhtmp = cardhService.selectByZxhbar(xhcardlist.get(j).getAufnr(), xhcardlist.get(j).getZxhnum());
                                if (cardhtmp.getStatus().equals("HOLD")) {//已经冻结的毛坯框不参与批次的比较
                                    continue;
                                }
                                param1.setMATNR(xhcardlist.get(j).getMatnr());
                                param1.setWERKS(xhcardlist.get(j).getWerks());
                                param1.setZBANB(cardhtmp.getSfflg());
                                param1.setZMODEL(cardhtmp.getDiecd());
                                param1.setZXHBAR(xhcard.getZxhbar());
                                DTWEIDUReturn dtweiduReturn1 = new DTWEIDUReturn();
                                dtweiduReturn1 = weiduWebserviceUtil.receiveConfirmation(param1);
                                if (dtweiduReturn1.getMTYPE().equals("S")) {
                                    if (dtweiduReturn1.getWEIDUFLG() != null) {
                                        if (dtweiduReturn1.getWEIDUFLG().equals("1")) {

                                        } else {
                                            rs.setSuccess(false);
                                            rs.setMessage("未按先进先出规则上线，请先上线批次为：" + xhcardlist.get(j).getChargkc() + " 的毛坯框！");
                                            return rs;
                                        }
                                    }
                                }

                            } else if (Long.valueOf(xhcardlist.get(j).getChargkc()).equals(Long.valueOf(xhcard.getChargkc()))) {
                                break;
                            }
                        }
                    }
                    l_error = "";
                }

            }

            if (l_error.equals("E")) {
                rs.setSuccess(false);
                rs.setMessage("该毛坯框物料:" + xhcard.getMatnr() + "不是本生产线所需物料,请检查!");
                return rs;
            }

            rs.setSuccess(true);
            rslist.add(inputLoglist);//返回报工记录
            rslist.add(xhcard);//返回扫描的箱号信息
            rs.setRows(rslist);


        } else if (xhcard.getZxhbar().equals(curxhcard.getZxhbar())) {
            //第二步 根据箱号获取流转卡记录

            cardh = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
            //第三步 根据订单查询末工序记录
            if (cardh.getStatus().equals("HOLD")) {
                rs.setMessage("当前箱号对应的流转卡处于HOLD状态，不允许继续上线！");
                rs.setSuccess(false);
            }

            listafvc = afvcService.selectByAufnr(xhcard.getAufnr());

            marc = marcService.selectByMatnr(xhcard.getMatnr());
            //afko

            afko = afkoService.selectByAufnr(cardhjj.getAufnr());

            //第三步  根据流转卡 获取报工记录

            inputLoglist = inputLogService.queryFirstResult(requestCtx, cardh.getZpgdbar(), listafvc.get(0).getVornr());
            if (inputLoglist.size() == 0) {
                rs.setSuccess(false);
                rs.setMessage("该箱号对应的流转卡尚未完成压铸末工序报工，不允许机加上线！");
                return rs;
            }
            //获取物料描述
            for (int i = 0; i < inputLoglist.size(); i++) {
                inputLoglist.get(i).setMatDesc(marc.getMaktx());
            }
            //resb
            resblist = resbService.selectByRsnum(afko.getRsnum());
            for (int i = 0; i < resblist.size(); i++) {
                if (resblist.get(i).getMatnr().equals(xhcard.getMatnr())) {
                    String lgort = resblist.get(i).getLgort();
                    String matnr = xhcard.getMatnr();
                    qrs1 = service.selectByBacodeFromSap(zxhbar, matnr, lgort, "M");
                    l_error = "";
                }

            }

            if (l_error.equals("E")) {
                rs.setSuccess(false);
                rs.setMessage("该毛坯框物料:" + xhcard.getMatnr() + "不是本生产线所需物料,请检查!");
                return rs;
            }

            //查询毛坯不良品处理记录
            List<Dftrghlist> dftrghlists = dftrghlistService.selectByZxhbar(zxhbar);

            rs.setSuccess(true);
            rslist.add(inputLoglist);//返回报工记录
            rslist.add(xhcard);//返回扫描的箱号信息
            if (dftrghlists.size() > 0){
                rslist.add(dftrghlists);
            }
            rs.setRows(rslist);
        }
        return rs;
    }

    /**
     * 查询机加线边库库存
     *
     * @param request
     * @param dto
     * @return
     */

    @RequestMapping({"/sap/xhcard/queryXbkc"})
    @ResponseBody
    public ResponseData queryXbck(HttpServletRequest request, Xhcard dto) {
        IRequest requestContext = createRequestContext(request);
        if (dto.getZsxwc().equals("Y")) {
            dto.setZsxwc(null);
        }
        return new ResponseData(service.selectXbkc(dto));
    }

    @RequestMapping(value = {"/sap/xhcard/BlmpclCheckZxhbar"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    ResponseData blmpclCheckZxhbar(HttpServletRequest request, String zxhbar,String line_id) {
        ResponseData rs = new ResponseData();
        List list = new ArrayList();
        Xhcard xhcard = new Xhcard();
        xhcard = service.selectByBacode(zxhbar);
        if (xhcard == null) {
            rs.setSuccess(false);
            rs.setMessage("未能获取箱号信息,请检查箱号是否输入正确！");
            return rs;
        }

        if (!xhcard.getZxhzt().equals("7")) {
            rs.setMessage("箱号状态错误！");
            rs.setSuccess(false);
            return rs;
        }

        if (xhcard.getZsxwc() != null) {
            if (xhcard.getZsxwc().equals("X")) {
                rs.setMessage("该箱号已经完成上线，不允许进行不良毛坯处理！");
                rs.setSuccess(false);
                return rs;
            }
        }

        //获取产线配置 检查物料是否可以在产线加工
        ProductsCfg productsCfg = productsCfgService.selectByLineidAndMatnr(line_id,xhcard.getMatnr());
        if (productsCfg == null){
            rs.setSuccess(false);
            rs.setMessage("该毛坯不属于该产线加工！");
            return rs;
        }


        Marc marc = new Marc();
        marc = marcService.selectByMatnr(xhcard.getMatnr());
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByZxhbar(xhcard.getAufnr(), xhcard.getZxhnum());
        list.add(xhcard);
        list.add(marc);
        list.add(cardh);
        rs.setSuccess(true);
        rs.setRows(list);
        return rs;
    }

}
