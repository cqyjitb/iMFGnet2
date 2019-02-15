package yj.core.dispatch.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.apache.bcel.generic.I2F;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.appidconf.dto.Appidconf;
import yj.core.appidconf.service.IAppidconfService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardhst.dto.Cardhst;
import yj.core.cardhst.service.ICardhstService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.dto.VCardt;
import yj.core.cardt.service.ICardtService;
import yj.core.cardt.service.impl.CardtServiceImpl;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.service.IOutsrgissueService;
import yj.core.pandian.dto.Pandian;
import yj.core.pandian.service.IPandianService;
import yj.core.webservice.dto.DTPP001ReturnResult;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class InputLogController extends BaseController{

    @Autowired
    private IInputLogService service;

    @Autowired
    private IPandianService pdservice;

    @Autowired
    private ICardtService cardtService;

    @Autowired
    private ICardhService cardhService;

    @Autowired
    private IAppidconfService appidconfService;

    @Autowired
    private ICardhstService cardhstService;

    @Autowired
    private IAfvcService afvcService;

    @Autowired
    private IAfkoService afkoService;

    @Autowired
    private IOutsrgissueService outsrgissueService;

    @Autowired
    private IXhcardService xhcardService;
    /*
    *报功日志界面查询
     */
    @RequestMapping(value = "/confirmation/input/log/queryLog")
    @ResponseBody
    public ResponseData queryLog(final InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE)final int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE)final int pageSize, final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

        InputLog inputLog =logFormat(dto);

        List <InputLog> list = service.queryAllLog(requestContext,inputLog,page,pageSize);
        List <InputLog> list1 = new ArrayList<>();
        if(list.size() != 0 || list != null){
            list1  = sessionSet(list,request);
        }
        return new ResponseData(list1);
    }



    @RequestMapping(value = "/test/form")
    @ResponseBody
    public ResponseData query(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        int s =  (int)session.getAttribute("success");

        int e =  (int)session.getAttribute("false");

        List list = new ArrayList();

        list.add(s);

        list .add(e);


        return new ResponseData(list);

    }



    /*
    *报工冲销
     */

    @RequestMapping(value = "/confirmation/input/log/queryWriteOff")
    @ResponseBody
    public ResponseData queryWriteOff(InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

        InputLog inputLog = writeOffFormat(dto);

        List<InputLog> list = service.queryAllWriteOff(requestContext,inputLog,page,pageSize);

        return new ResponseData(list);
    }


    /*
    *报工结果
     */

    @RequestMapping(value = "/confirmation/input/log/queryResult")
    @ResponseBody
    public ResponseData queryResult(InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                    @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

        InputLog inputLog  = resultFormat(dto);
        if (inputLog.getAttr6() != null){
            String attr6 = inputLog.getAttr6().substring(0,10);
            inputLog.setAttr6(attr6);
        }

        List<InputLog> list = service.queryAllResult(requestContext,inputLog,page,pageSize);


        for (int i=0 ; i<list.size();i++){
            if(list.get(i).getIsReversed().equals("0")){
                list.get(i).setIsReversed("正常");
            }else {
                list.get(i).setIsReversed("已冲销");
            }
        }


        return new ResponseData(list);
    }

    @RequestMapping(value = {"/confirmation/input/log/queryResultpd"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData queryResultpd(InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);

        InputLog inputLog  = resultFormat(dto);
        List<InputLog> list = service.queryAllResult(requestContext,inputLog,page,pageSize);


        for (int i=0 ; i<list.size();i++){
            if(list.get(i).getIsReversed().equals("0")){
                //list.get(i).setIsReversed("正常");

            }else {
                //list.get(i).setIsReversed("已冲销");
                list.remove(i);
                i= 0;
            }
        }


        return new ResponseData(list);
    }

    @RequestMapping(value={"/confirmation/input/log/inputFindHistory"}, method={RequestMethod.GET})
    @ResponseBody
    public ResponseData queryFirst(HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        System.out.println("*********************************");
        System.out.println(request.getParameter("dispatch"));
        System.out.println(request.getParameter("operation"));
        System.out.println("*********************************");

        String dispatch = request.getParameter("dispatch");
        String operation = request.getParameter("operation");

        List<InputLog> list = this.service.queryFirstResult(requestContext, dispatch, operation);

        return new ResponseData(list);
    }

    @RequestMapping(value={"/confirmation/input/log/queryBeforeResult"}, method={RequestMethod.GET})
    @ResponseBody
    public ResponseData queryBeforeResult(HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);
        String dispatch = request.getParameter("dispatch");
        String operation = request.getParameter("operation");
        List<InputLog> list = this.service.queryBeforeResult(requestContext, dispatch, operation);
        if (list.size() > 0){
            ResponseData rs = new ResponseData(list);
            rs.setSuccess(true);
            return rs;
        }else{
            ResponseData rs = new ResponseData(false);
            return rs;
        }

    }


    @RequestMapping(value = {"/confirmation/input/log/insertInputLog"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData inputDispatch( HttpServletRequest request) {
        InputLog inputLog = new InputLog();
        System.out.println(request.getParameter("a"));

        String createdBy1 = "" + request.getSession().getAttribute("userId");
        System.out.println(createdBy1);
        inputLog.setCreated_by(createdBy1);

        String  barcode = request.getParameter("a");
        String  postingDate =request.getParameter("b");
        String  orderno = request.getParameter("c");
        String  operation = request.getParameter("d");
        String  yeild = request.getParameter("e");
        String  workScrap = request.getParameter("f");
        String  rowScrap =  request.getParameter("g");
        String  classgrp =  request.getParameter("h");
        String  line =  request.getParameter("i");
        String  modelNo =  request.getParameter("j");
        String  plant =  request.getParameter("k");
        String  dispatch =  request.getParameter("l");
        String  dispatchLogicID =  request.getParameter("m");
        String  createdBy =  request.getParameter("n");
        String  attr1 =  request.getParameter("1");
        String  attr2 =  request.getParameter("2");
        String  attr3 =  request.getParameter("3");
        String  attr4 =  request.getParameter("4");
        String  attr5 =  request.getParameter("5");
        String  attr6 =  request.getParameter("6");
        String  attr7 =  request.getParameter("7");
        String  attr8 =  request.getParameter("8");
        String  attr9 =  request.getParameter("9");
        String  attr10 =  request.getParameter("10");
        String  attr11 =  request.getParameter("11");
        String  attr12 =  request.getParameter("12");
        String  attr13 =  request.getParameter("13");
        String  attr14 =  request.getParameter("14");
        String  attr15 =  request.getParameter("15");
         String userName = request.getParameter("16");
//        int length = attr7.length();
//        if ((length == 7) && (operation != "0030"))
//        {
//            String tmpattr7 = attr7.substring(0, 6) + classgrp.toLowerCase();
//            int i = attr7.indexOf(classgrp.toLowerCase());
//            if (i == -1) {
//                attr7 = tmpattr7;
//            }
//        }


        inputLog.setBarcode(barcode);
        inputLog.setOrderno(orderno);
        inputLog.setDispatch(dispatch);
        inputLog.setOperation(operation);
        inputLog.setYeild(Double.parseDouble(yeild));
        inputLog.setWorkScrap(Double.parseDouble(workScrap));
        inputLog.setRowScrap(Double.parseDouble(rowScrap));
        inputLog.setClassgrp(classgrp);
        inputLog.setLine(line);
        inputLog.setModelNo(modelNo);
        inputLog.setPlant(plant);
        inputLog.setPostingDate(postingDate);
        inputLog.setDispatchLogicID(dispatchLogicID);
        inputLog.setCreated_by(createdBy);

        inputLog.setAttr1(attr1);
        inputLog.setAttr2(attr2);
        inputLog.setAttr3(attr3);
        inputLog.setAttr4(attr4);
        inputLog.setAttr5(attr5);
        inputLog.setAttr6(attr6);
        inputLog.setAttr7(attr7);
        inputLog.setAttr8(attr8);
        inputLog.setAttr9(attr9);
        inputLog.setAttr10(attr10);
        inputLog.setAttr11(attr11);
        inputLog.setAttr12(attr12);
        inputLog.setAttr13(attr13);
        inputLog.setAttr14(attr14);
        inputLog.setAttr15(attr15);
        inputLog.setUserName(userName);

        List<DTPP001ReturnResult> list = new ArrayList<>();
        DTPP001ReturnResult returnResult = service.inputDispatch(inputLog);
        list.add(returnResult);
        return new ResponseData(list);
    }


    /**
     *  处理外协报工页面请求
     * @param request
     * @return
     */
    @RequestMapping(value = {"/confirmation/input/log/insertInputLogNWX"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData inputDispatchNWX(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        InputLog inputLog = new InputLog();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        String curtim  = df.format(new Date()).substring(11,19).replaceAll(":","");

        String barcode = request.getParameter("a");
        String  yssum = request.getParameter("b");
        String  thsum = request.getParameter("c");
        String  hjsum = request.getParameter("d");
        String  hgsum = request.getParameter("e");
        String  gfsum = request.getParameter("f");
        String  lfsum = request.getParameter("g");
        String  createdBy = request.getParameter("i");
        String  vornr = request.getParameter("j");
        String  userName = request.getParameter("k");
        String  isfirst = "";

        if (lfsum.equals("")){
            lfsum = "0";
        }

        if (gfsum.equals("")){
            gfsum = "0";
        }

        if (hgsum.equals("")){
            hgsum = "0";
        }

        if (yssum.equals("")){
            yssum = "0";
        }

        if (thsum.equals("")){
            thsum = "0";
        }

        if (hjsum.equals("")){
            hjsum = "0";
        }

        //获取流转卡信息
        Cardh card = new Cardh();
        card = cardhService.selectByBarcode(barcode);

        Afko afko = new Afko();
        afko = afkoService.selectByAufnr(card.getAufnr());

        Xhcard xhcard = new Xhcard();
        xhcard = xhcardService.selectForZxhbar(card.getWerks(),card.getAufnr(),card.getZxhnum());
        inputLog.setBarcode(barcode);
        inputLog.setOrderno(card.getAufnr());
        inputLog.setDispatch(barcode);
        inputLog.setOperation(vornr);
        inputLog.setYeild(Double.parseDouble(hgsum));
        inputLog.setWorkScrap(Double.parseDouble(gfsum));
        inputLog.setRowScrap(Double.parseDouble(lfsum));
        inputLog.setLine("");
        inputLog.setPlant(card.getWerks());
        inputLog.setPostingDate(curdate);
        inputLog.setDispatchLogicID(barcode.substring(14,18));
        inputLog.setCreated_by(createdBy);
        inputLog.setAttr1(createdBy);
        inputLog.setAttr2("");
        inputLog.setAttr3("");
        inputLog.setAttr4("");
        inputLog.setAttr5("");
        inputLog.setAttr6("");
        inputLog.setAttr8("");
        inputLog.setAttr9("");
        inputLog.setAttr10("");
        inputLog.setAttr11("");
        inputLog.setAttr12("");
        inputLog.setAttr13("");
        inputLog.setAttr14("");
        inputLog.setAttr15("5");
        inputLog.setUserName(userName);
        inputLog.setAuart(afko.getAuart());
        inputLog.setZtpbar(xhcard.getZxhbar());
        //获取发料单数据
        Outsrgissue outsrgissue = new Outsrgissue();
        List<Outsrgissue> list2 = outsrgissueService.selectBybarcodes(barcode,null);
        for (int i =0;i<list2.size();i++){
            if (list2.get(i).getStatus().equals("0")){
                outsrgissue = list2.get(i);
            }
        }

        //获取工序数据
        Cardt cardt = new Cardt();
        Cardt dto = new Cardt();
        dto.setZpgdbar(barcode);
        dto.setKtsch(outsrgissue.getKtsch());
        cardt = cardtService.selectByBarcodeAndKtsch(dto);

        List<Cardt> cardtasc = new ArrayList<>();
        List<Cardt> cardtdesc = new ArrayList<>();

        cardtasc = cardtService.selectByZpgdbarAsc(barcode);

        cardtdesc = cardtService.selectByZpgdbarDesc(barcode);
        String lstvor = "";
        String fstvor = "";

        if (cardtasc.get(0).getVornr().equals(vornr)){
            fstvor = "X";
            isfirst = "X";
        }

        if (cardtdesc.get(0).getVornr().equals(vornr)){
            lstvor = "X";
        }
        inputLog.setLstvor(lstvor);
        inputLog.setFstvor(fstvor);
        inputLog.setZprtp("5");
        if (fstvor.equals("X")){
            inputLog.setCharg("");
            inputLog.setClassgrp("");
            inputLog.setModelNo("");
            inputLog.setAttr7("");
        }else{
            inputLog.setCharg(card.getCharg2());
            inputLog.setModelNo(card.getDiecd());
            inputLog.setClassgrp(card.getShift());
            inputLog.setAttr7(card.getSfflg());
        }

        Cardhst cardhst = new Cardhst();
        inputLog.setArbpl(cardt.getArbpl());//工作中心
        if (inputLog.getArbpl() == null){
            inputLog.setArbpl("");
        }

        List<DTBAOGONGReturnResult> list = new ArrayList<>();
        DTBAOGONGReturnResult returnResult = service.inputDispatchNewWX(inputLog,card,cardt,isfirst);
        if ( returnResult.getMSGTY().equals("S")){
            //设置流转卡报工后状态
            long maxid = cardhstService.getMaxNo(inputLog.getDispatch()) + 1;
            List<Cardh> listcardh = new ArrayList<Cardh>();
            if (fstvor.equals("X")){
                card.setStatus2(card.getStatus());
                card.setStatus("FCNF");
            }

            if (lstvor.equals("X")){
                card.setStatus2(card.getStatus());
                card.setStatus("ECNF");
            }

            if (fstvor.equals("") && lstvor.equals("")){
                card.setStatus2(card.getStatus());
                card.setStatus("CNF");
            }

            cardhst.setZpgdbar(inputLog.getDispatch());
            cardhst.setIsactive("X");
            cardhst.setId(maxid);
            cardhst.setOperation(inputLog.getOperation());
            cardhst.setStatus(card.getStatus());

            if (inputLog.getFstvor().equals("X")){
                card.setFprddat(curdate);
                card.setShift("");
                card.setSfflg("");
                card.setDiecd("");
                card.setCharg2(returnResult.getCHARG());
                card.setActgstrp(curdate);
                card.setEcqty(Double.parseDouble(hgsum));
                card.setActst(curtim);
            }

            if (inputLog.getLstvor().equals("X")){
                card.setEprddat(curdate);
                card.setActgltrp(curdate);
                card.setActdt(curtim);
                card.setAlqty(Double.parseDouble(hgsum));
            }

            if  (card.getQtysm() == null){
                card.setQtysm(Double.parseDouble(lfsum));
            }else{
                card.setQtysm(card.getQtysm() + Double.parseDouble(gfsum));
            }

            if (card.getQtysp() == null){
                card.setQtysp(Double.parseDouble(gfsum));
            }else{
                card.setQtysp(card.getQtysp() + Double.parseDouble(gfsum));
            }
            listcardh.add(card);

            cardhService.updateCardhStatus(listcardh);

            //判断状态是否已经存在
            Cardhst tst = cardhstService.selectByBarcodeAndStatus(cardhst);
            if (tst == null){
                cardhstService.insertSingerStatus(cardhst);
            }else{

                cardhstService.updateStatus(cardhst);
            }
            //设置工序记录报工完成后Confirmed 标识
            cardt.setConfirmed("X");
            cardtService.updateCardtConfirmed(cardt);

        }else{
            rs.setSuccess(false);
            rs.setMessage(returnResult.getMESSAGE());
            return rs;
        }
        list.add(returnResult);
        rs.setRows(list);
        rs.setSuccess(true);
        return  rs;
    }


    @RequestMapping(value = {"/confirmation/input/log/insertInputLogN"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData inputDispatchN(HttpServletRequest request){
        InputLog inputLog = new InputLog();
        System.out.println(request.getParameter("a"));

        String createdBy1 = "" + request.getSession().getAttribute("userId");
        System.out.println(createdBy1);
        inputLog.setCreated_by(createdBy1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        String curtim  = df.format(new Date()).substring(11,19).replaceAll(":","");

        String  barcode = request.getParameter("a");
        String  postingDate =request.getParameter("b");
        String  orderno = request.getParameter("c");
        String  operation = request.getParameter("d");
        String  yeild = request.getParameter("e");
        String  workScrap = request.getParameter("f");
        String  rowScrap =  request.getParameter("g");
        String  classgrp =  request.getParameter("h");
        String  line =  request.getParameter("i");
        String  modelNo =  request.getParameter("j");
        String  plant =  request.getParameter("k");
        String  dispatch =  request.getParameter("l");
        String  dispatchLogicID =  request.getParameter("m");
        String  createdBy =  request.getParameter("n");
        String  attr1 =  request.getParameter("1");
        String  attr2 =  request.getParameter("2");
        String  attr3 =  request.getParameter("3");
        String  attr4 =  request.getParameter("4");
        String  attr5 =  request.getParameter("5");
        String  attr6 =  request.getParameter("6");
        String  attr7 =  request.getParameter("7");
        String  attr8 =  request.getParameter("8");
        String  attr9 =  request.getParameter("9");
        String  attr10 =  request.getParameter("10");
        String  attr11 =  request.getParameter("11");
        String  attr12 =  request.getParameter("12");
        String  attr13 =  request.getParameter("13");
        String  attr14 =  request.getParameter("14");
        String  attr15 =  request.getParameter("15");
        String userName = request.getParameter("16");
        String appid = request.getParameter("17");//手机APPid
        String ktsch = request.getParameter("18");//工序标准值码
        String charg = "";
        String isfirst= request.getParameter("19");//是否为首工序
//        int length = attr7.length();
//        if ((length == 7) && (!operation.equals("0030")))
//        {
//            String tmpattr7 = attr7.substring(0, 6) + classgrp.toLowerCase();
//            int i = attr7.indexOf(classgrp.toLowerCase());
//            if (i == -1) {
//                attr7 = tmpattr7;
//            }
//        }

        inputLog.setBarcode(barcode);
        inputLog.setOrderno(orderno);
        inputLog.setDispatch(dispatch);
        inputLog.setOperation(operation);
        inputLog.setYeild(Double.parseDouble(yeild));
        inputLog.setWorkScrap(Double.parseDouble(workScrap));
        inputLog.setRowScrap(Double.parseDouble(rowScrap));
        inputLog.setClassgrp(classgrp);
        inputLog.setLine(line);
        inputLog.setModelNo(modelNo);
        inputLog.setPlant(plant);
        inputLog.setPostingDate(postingDate);
        inputLog.setDispatchLogicID(dispatchLogicID);
        inputLog.setCreated_by(createdBy);

        inputLog.setAttr1(attr1);
        inputLog.setAttr2(attr2);
        inputLog.setAttr3(attr3);
        inputLog.setAttr4(attr4);
        inputLog.setAttr5(attr5);
        inputLog.setAttr6(attr6);
        inputLog.setAttr7(attr7);
        inputLog.setAttr8(attr8);
        inputLog.setAttr9(attr9);
        inputLog.setAttr10(attr10);
        inputLog.setAttr11(attr11);
        inputLog.setAttr12(attr12);
        inputLog.setAttr13(attr13);
        inputLog.setAttr14(attr14);
        inputLog.setAttr15(attr15);
        inputLog.setUserName(userName);

        //获取流转卡数据
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(dispatch);
        inputLog.setAuart(cardh.getAuart());//订单类型

        //获取APP与工序标准值码的绑定数据
        Appidconf appidconf = new Appidconf();
        appidconf = appidconfService.selectByAppid(appid);

        //获取工序数据
        Cardt cardt = new Cardt();
        Cardt dto = new Cardt();
        dto.setZpgdbar(dispatch);
        dto.setKtsch(ktsch);
        cardt = cardtService.selectByBarcodeAndKtsch(dto);

        List<Cardt> cardtasc = new ArrayList<>();
        List<Cardt> cardtdesc = new ArrayList<>();

        cardtasc = cardtService.selectByZpgdbarAsc(dispatch);

        cardtdesc = cardtService.selectByZpgdbarDesc(dispatch);
        String lstvor = "";
        String fstvor = "";

        if (cardtasc.get(0).getVornr().equals(operation)){
            fstvor = "X";
        }

        if (cardtdesc.get(0).getVornr().equals(operation)){
            lstvor = "X";
        }

        if (isfirst.equals("FIRST")){
            fstvor = "X";
        }


        inputLog.setLstvor(lstvor);
        inputLog.setFstvor(fstvor);

        if (appid.equals("app0001")){//压铸报工
            inputLog.setZprtp("1");//1:正常 2：盘亏调整 3：盘赢调整
            inputLog.setCharg("");
            inputLog.setZtpbar("");
        }

        if (appid.equals("app0002") || appid.equals("app0003") || appid.equals("app0004")){
            inputLog.setZprtp("1");//1:正常 2：盘亏调整 3：盘赢调整
            inputLog.setCharg("");
            inputLog.setZtpbar("");
        }

//        if (appid.equals("app0002")){
//            inputLog.setZprtp("1");
//            inputLog.setCharg(charg);//机加报工毛坯批次
////            inputLog.setFstvor("");
//        }

        Cardhst cardhst = new Cardhst();
        inputLog.setArbpl(cardt.getArbpl());//工作中心
        if (inputLog.getArbpl() == null){
            inputLog.setArbpl("");
        }


        List<DTBAOGONGReturnResult> list = new ArrayList<>();
        DTBAOGONGReturnResult returnResult = service.inputDispatchNew(inputLog,cardh,cardt,appidconf,isfirst);
        if ( returnResult.getMSGTY().equals("S")){
            //设置流转卡报工后状态
            long maxid = cardhstService.getMaxNo(inputLog.getDispatch()) + 1;
            List<Cardh> listcardh = new ArrayList<Cardh>();
            if (appid.equals("app0001")){

                if (inputLog.getFstvor().equals("X")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("FCNF");
                }

                if (inputLog.getLstvor().equals("X")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("ECNF");
                }


            }else if (appid.equals("app0002")){

                if (inputLog.getFstvor().equals("X")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("FCNF");
                }

                if (inputLog.getLstvor().equals("X")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("ECNF");
                }


                if (inputLog.getFstvor().equals("") && inputLog.getLstvor().equals("")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("CNF");
                }


            }else if (appid.equals("app0003")){

                if (inputLog.getFstvor().equals("X")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("FCNF");
                }

                if (inputLog.getLstvor().equals("X")){
                    cardh.setStatus2(cardh.getStatus());
                    cardh.setStatus("ECNF");
                }
            }

            cardhst.setZpgdbar(inputLog.getDispatch());
            cardhst.setIsactive("X");
            cardhst.setId(maxid);
            cardhst.setOperation(inputLog.getOperation());
            cardhst.setStatus(cardh.getStatus());

            if (inputLog.getFstvor().equals("X")){
                cardh.setFprddat(postingDate);
                cardh.setShift(classgrp);
                cardh.setSfflg(attr7);
                cardh.setDiecd(modelNo);
                cardh.setCharg2(returnResult.getCHARG());
                cardh.setActgstrp(postingDate);
                cardh.setEcqty(Double.parseDouble(yeild));
                cardh.setActst(curtim);
            }

            if (inputLog.getLstvor().equals("X")){
                cardh.setEprddat(postingDate);
                cardh.setActgltrp(postingDate);
                cardh.setActdt(curtim);
                cardh.setAlqty(Double.parseDouble(yeild));
            }

            if  (cardh.getQtysm() == null){
                cardh.setQtysm(Double.parseDouble(rowScrap));
            }else{
                cardh.setQtysm(cardh.getQtysm() + Double.parseDouble(rowScrap));
            }

            if (cardh.getQtysp() == null){
                cardh.setQtysp(Double.parseDouble(workScrap));
            }else{
                cardh.setQtysp(cardh.getQtysp() + Double.parseDouble(workScrap));
            }

            listcardh.add(cardh);

            cardhService.updateCardhStatus(listcardh);

            //判断状态是否已经存在
            Cardhst tst = cardhstService.selectByBarcodeAndStatus(cardhst);
            if (tst == null){
                cardhstService.insertSingerStatus(cardhst);
            }else{

                cardhstService.updateStatus(cardhst);
            }
            //设置工序记录报工完成后Confirmed 标识
            cardt.setConfirmed("X");
            cardtService.updateCardtConfirmed(cardt);

        }
        list.add(returnResult);
        return new ResponseData(list);

    }



    @RequestMapping(value = {"/confirmation/input/log/writeOff"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData writeOffDispatch( @RequestBody InputLog inputLog,HttpServletRequest request) {
        String createdBy = "" + request.getSession().getAttribute("userId");
        System.out.println(createdBy);
        inputLog.setCreated_by(createdBy);

        //冲销之前判断流转卡 是新版流转卡 还是老版流转卡 新版走新冲销流程 旧版走老冲销流程

        Cardh cardh = new Cardh();
        Cardhst cardhst = new Cardhst();
        List<Cardhst> listcardhst = new ArrayList<>();
        cardh = cardhService.selectByBarcode(inputLog.getDispatch());
        listcardhst = cardhstService.selectAllActive(inputLog.getDispatch());
        if (cardh == null){
            List<DTPP001ReturnResult> list = new ArrayList<>();
            DTPP001ReturnResult returnResult = service.writeOffDispatch(inputLog);
            list.add(returnResult);
            return new ResponseData(list);
        }else{
            if (inputLog.getAttr15().equals("5")){
                ResponseData rs = new ResponseData();
                rs.setSuccess(false);
                rs.setMessage("外协工序收货/报工只能通过手机APP进行冲销！");
                return rs;
            }

            String fstvor = "";
            String lstvor = "";
            String message= "";
            List<DTBAOGONGReturnResult> list = new ArrayList<DTBAOGONGReturnResult>();
            DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
                String l_error = "";

             if (cardh.getLgort() != null && cardh.getLgort() != ""){
                 returnResult.setMSGTY("E");
                 returnResult.setMESSAGE("流转卡已被转移至暂存区，不允许进行冲销操作");
                 list.add(returnResult);
                 return new ResponseData(list);
             }


               for (int i = 0;i<listcardhst.size();i++){
                   if (listcardhst.get(i).getStatus().equals("HOLD") && listcardhst.get(i).getIsactive().equals("X")){
                       l_error = "E";
                       message = "流转卡状态为HOLD，不允许进行冲销操作！";
                       break;
                   }


               }

            if (l_error.equals("E")){
                returnResult.setMSGTY("E");
                returnResult.setMESSAGE(message);
                list.add(returnResult);
                return new ResponseData(list);
            }

            //排除机加的报工冲销 机加报工冲销只能在装箱系统中操作
            Afko afko = new Afko();
            afko = afkoService.selectByAufnr(cardh.getAufnr());
            if (afko.getAuart().substring(0,1).equals("Q")){//机加订单
                returnResult.setMSGTY("E");
                returnResult.setMESSAGE("机加报工冲销，请到装箱系统冲销！");
                list.add(returnResult);
                return new ResponseData(list);
            }


                List<Afvc> afvclist = afvcService.selectByAufnr(inputLog.getOrderno());
                inputLog.setFstvor("");
                inputLog.setLstvor("");
                if (afvclist.get(afvclist.size() - 1).getVornr().equals(inputLog.getOperation())){
                    fstvor = "X";
                    inputLog.setFstvor(fstvor);

                }

                if (afvclist.get(0).getVornr().equals(inputLog.getOperation())){
                    lstvor = "X";
                    inputLog.setLstvor(lstvor);

                }

                inputLog.setAuart(cardh.getAuart());
                inputLog.setAttr5(cardh.getCharg());//设置箱号日期序列
                returnResult = service.writeOffDispatchNew(inputLog);
                if (returnResult.getMSGTY().equals("S")){

                    cardhst = listcardhst.get(0);//当前流转卡状态
                    //修改流转卡状态
                    if (cardhst.getOperation().equals(inputLog.getOperation())){//判断当前冲销工序是否是首工序或者末工序 只有首末工序需要设置状态
                        cardhst.setIsactive("");
                        cardh.setStatus2(cardhst.getStatus());
                        cardhstService.updateStatus(cardhst);
                        cardhst = listcardhst.get(1);
                        cardh.setStatus(cardhst.getStatus());
                    }

                    if (fstvor.equals("X")){//首工序冲销 需要 修改ACTSTRP ACTST FPRDATA SHIFT SFFLG DIECD ECQTY  QTYSM QTYSP
                        cardh.setFprddat("");
                        cardh.setShift("");
                        cardh.setSfflg("");
                        cardh.setDiecd("");
                        cardh.setEcqty(0D);
                        cardh.setQtysp(0D);
                        cardh.setQtysm(0D);
                        cardh.setLastUpdatedBy(Long.valueOf(createdBy));
                        cardh.setLastUpdatedDate(new Date());
                        cardhService.updateDatforFsvor(cardh.getZpgdbar());
                    }

                    if (lstvor.equals("X")){
                        cardh.setEprddat("");
                        cardh.setAlqty(0D);
                        cardh.setLastUpdatedBy(Long.valueOf(createdBy));
                        cardh.setLastUpdatedDate(new Date());
                        cardhService.updateDatforLsvor(cardh.getZpgdbar());
                    }

                }
                List<Cardh> listcardh = new ArrayList<>();
                listcardh.add(cardh);
                cardhService.updateCardhStatus(listcardh);
                if (fstvor.equals("X")){//首工序冲销 需要 修改ACTSTRP ACTST FPRDATA SHIFT SFFLG DIECD ECQTY  QTYSM QTYSP
                    cardhService.updateDatforFsvor(cardh.getZpgdbar());
                }

                if (lstvor.equals("X")){
                    cardhService.updateDatforLsvor(cardh.getZpgdbar());
                }
                List<Cardt> listcardt = new ArrayList<>();
                Cardt parmCardt = new Cardt();
                parmCardt.setZpgdbar(inputLog.getDispatch());
                parmCardt.setZgxbh(inputLog.getOperation());
                IRequest requestContext = createRequestContext(request);
                listcardt = cardtService.queryAfterSort(requestContext,parmCardt,1,1);
                if (listcardt.size() > 0){
                    for (int i = 0;i<listcardt.size();i++){
                        Cardt cardt = listcardt.get(i);
                        cardt.setConfirmed("");
                        cardtService.updateCardtConfirmed(cardt);
                    }
                }


            list.add(returnResult);
            return new ResponseData(list);
        }

    }


    public InputLog logFormat(InputLog dto){
        /*if(dto.getCreatDateAfter()==null){
            dto.setCreatDateAfter("0000-00-00 00:00:00");
        }
        if (dto.getCreatDateBefore()==null){
            dto.setCreatDateBefore("9999-01-01 23:59:59");
        }*/
        /*if(dto.getPostingDateAfter()==null){
            dto.setPostingDateAfter("0000-00-00");

        }
        if(dto.getPostingDateBefore() ==null){
            dto.setPostingDateBefore("9999-01-01");
        }*/

        if ( dto.getCreatDateBefore() !=null){
            String cdBefore;
            cdBefore = dto.getCreatDateBefore();
            cdBefore = dto.getCreatDateBefore().replace("00:00:00","23:59:59");
            dto.setCreatDateBefore(cdBefore);

        }
        if(dto.getPostingDateBefore() != null){
            String pdBefore;
            pdBefore = dto.getPostingDateBefore();
            pdBefore = dto.getPostingDateBefore().replace("00:00:00","23:59:59");
            dto.setPostingDateBefore(pdBefore);
        }
        if(dto.getTranType() != null){
            String tranType;
            tranType = dto.getTranType();
            if(tranType.equals("报工")){
                dto.setTranType("0");
            }else if(tranType.equals("冲销")){
                dto.setTranType("1");
            }
        }
        return dto;

    }
    public InputLog resultFormat(InputLog dto){
       /* if(dto.getCreatDateAfter()==null){
            dto.setCreatDateAfter("0000-00-00 00:00:00");
        }
        if (dto.getCreatDateBefore()==null){
            dto.setCreatDateBefore("9999-01-01 23:59:59");
        }
        if(dto.getPostingDateAfter()==null){
            dto.setPostingDateAfter("0000-00-00");

        }
        if(dto.getPostingDateBefore() ==null){
            dto.setPostingDateBefore("9999-01-01");
        }
*/
        if ( dto.getCreatDateBefore() !=null){
            String cdBefore;
            cdBefore = dto.getCreatDateBefore();
            cdBefore = dto.getCreatDateBefore().replace("00:00:00","23:59:59");
            dto.setCreatDateBefore(cdBefore);

        }
        if(dto.getPostingDateBefore() != null){
            String pdBefore;
            pdBefore = dto.getPostingDateBefore();
            pdBefore = dto.getPostingDateBefore().replace("00:00:00","23:59:59");
            dto.setPostingDateBefore(pdBefore);
        }
        if (dto.getIsReversed() != null){
            if (dto.getIsReversed().equals("正常")){
                dto.setIsReversed("0");
            }else {
                dto.setIsReversed("1");
            }


        }

        return dto;

    }
    public InputLog writeOffFormat(InputLog dto){

        /*if(dto.getCreatDateAfter()==null){
            dto.setCreatDateAfter("0000-00-00 00:00:00");
        }
        if (dto.getCreatDateBefore()==null){
            dto.setCreatDateBefore("9999-01-01 23:59:59");
        }
        if(dto.getPostingDateAfter()==null){
            dto.setPostingDateAfter("0000-00-00");

        }
        if(dto.getPostingDateBefore() ==null){
            dto.setPostingDateBefore("9999-01-01");
        }*/

        if ( dto.getCreatDateBefore() !=null){
            String cdBefore;
            cdBefore = dto.getCreatDateBefore();
            cdBefore = dto.getCreatDateBefore().replace("00:00:00","23:59:59");
            dto.setCreatDateBefore(cdBefore);

        }
        if(dto.getPostingDateBefore() != null){
            String pdBefore;
            pdBefore = dto.getPostingDateBefore();
            pdBefore = dto.getPostingDateBefore().replace("00:00:00","23:59:59");
            dto.setPostingDateBefore(pdBefore);
        }

        return dto;
    }

    public List<InputLog> sessionSet(List<InputLog> list,final HttpServletRequest request){

        int s = 0;
        int e = 0 ;

        for (int i=0 ; i<list.size();i++){
            if ("S".equals(list.get(i).getMsgty())){
                list.get(i).setMsgty("成功");
                s++;
            }else {
                list.get(i).setMsgty("失败");
                e++;
            }

            if (list.get(i).getTranType().equals("0")){
                list.get(i).setTranType("报工");
            }else {
                list.get(i).setTranType("冲销");
            }
        }


        HttpSession session = request.getSession();

        session.setAttribute("success", s);

        session.setAttribute("false", e);

        return list;

    }

    @RequestMapping(value = {"/confirmation/input/log/pandian"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData pandianDispatch(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        IRequest requestContext = createRequestContext(request);
        String createdBy = request.getParameter("createBy");
        String dispatch = request.getParameter("dispatch");
        String operation = request.getParameter("operation");
        String pd_sum = request.getParameter("pd_sum");
        String cfwz = request.getParameter("cfwz");
        String message = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newPostingdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        InputLog inputLog = new InputLog();
        inputLog.setDispatch(dispatch);
        inputLog.setOperation(operation);
        List<InputLog> hislist = service.queryAllWriteOff(requestContext,inputLog,1,100);

        InputLog hislog = new InputLog();
        hislog = hislist.get(0);

        double gamng = hislog.getYeild();//盘点前的报工数量
        if ( hislog.getYeild() > Double.parseDouble(pd_sum) ){//盘亏
            Long IOrderno = Long.parseLong(hislog.getOrderno());
            if ( (IOrderno >= 1000000000L && IOrderno <= 2999999999L) &&
                    ( hislog.getOperationDesc().equals("压铸") )      &&
                    ( hislog.getOperation().equals("0010"))){


                        rs = insertLog(hislist.get(0),gamng, pd_sum, cfwz,createdBy,message);


            }else{

                //先冲销
                hislog.setTranType("1");
                hislog.setPostingDate(newPostingdate);
                DTPP001ReturnResult dtpp001ReturnResult = service.writeOffDispatch(hislog);
                if ( dtpp001ReturnResult.getMSGTY().equals("S")){
                    double chayi = hislog.getYeild() - Double.parseDouble(pd_sum);
                    double newyeild = hislog.getYeild() - chayi;

                    hislog.setPostingDate(newPostingdate);
                    hislog.setYeild(newyeild);
                    hislog.setWorkScrap(hislog.getWorkScrap() + chayi);
                    hislog.setTranType("0");
                    hislog.setDispatchLogicID(hislog.getDispatch().substring(14,18));
                    hislog.setCreated_by(createdBy);
                    DTPP001ReturnResult dtpp001ReturnResult1 = service.inputDispatch(hislog);
                    if ( dtpp001ReturnResult1.getMSGTY().equals("S")){
                        message = "报工已调整";
                        rs = insertLog(hislist.get(0),gamng,pd_sum,cfwz,createdBy,message);

                    }else{
                        message = "冲销成功，补报工失败："+dtpp001ReturnResult1.getMESSAGE();
                        rs = insertLog(hislist.get(0),gamng,pd_sum,cfwz,createdBy,message);
                    }
                }else{//冲销失败 只记录日志
                    message = "冲销失败："+dtpp001ReturnResult.getMESSAGE();
                    rs = insertLog(hislist.get(0),gamng, pd_sum, cfwz,createdBy,message);

                }


            }



        }else if( hislog.getYeild() <= Double.parseDouble(pd_sum) ) {//盘赢 盘平 只记录日志
            message = "盘点成功.";
            rs = insertLog(hislist.get(0),gamng, pd_sum, cfwz,createdBy,message);

        }
        return rs;
    }

    public ResponseData insertLog(InputLog inputLog,double gamng,String pd_sum,String cfwz,String createdBy,String message){
        ResponseData rs = new ResponseData();
        Pandian pd = new Pandian();
        pd.setAufnr(inputLog.getOrderno());
        pd.setZpgdbar(inputLog.getBarcode());
        pd.setGamng(gamng);
        pd.setQrmng(Double.parseDouble(pd_sum));
        pd.setMatnr(inputLog.getMaterial());
        pd.setMaktx(inputLog.getMatDesc());
        pd.setLtxa1(inputLog.getOperationDesc());
        pd.setVornr(inputLog.getOperation());
        pd.setWerks(inputLog.getPlant());
        pd.setCreated_by(createdBy);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datastr = df.format(new Date());// new Date()为获取当前系统时间
        pd.setZpgdbh(inputLog.getDispatch().substring(14,18));
        pd.setPddat(datastr.substring(0,10));
        pd.setPdtim(datastr.substring(11,19));
        pd.setZcfwz(cfwz);
        pd.setZlylx("1");
        pd.setZmessage(message);

        int i = pdservice.insertpdlog(pd);
        if (i == 1){
            rs.setSuccess(true);
            rs.setCode("S");
            rs.setMessage(message);
        }else{
            rs.setSuccess(true);
            rs.setCode("E");
            rs.setMessage(message);
        }
        return rs;

    }

}