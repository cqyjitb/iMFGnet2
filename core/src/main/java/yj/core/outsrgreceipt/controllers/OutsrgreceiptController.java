package yj.core.outsrgreceipt.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardhst.dto.Cardhst;
import yj.core.cardhst.service.ICardhstService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.service.IOutsrgissueService;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;
import yj.core.outsrgreceipt.service.IOutsrgreceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.outsrgreceipthead.dto.Outsrgreceipthead;
import yj.core.outsrgreceipthead.service.IOutsrgreceiptheadService;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import yj.core.webserver_outsrgreceipt.components.SyncOutsrgreceiptWebserviceUtil;
import yj.core.webserver_outsrgreceipt.dto.DTOUTSRGRECEIPTHead;
import yj.core.webserver_outsrgreceipt.dto.DTOUTSRGRECEIPTReturn;
import yj.core.webserver_outsrgreceipt.dto.DTOUTSRGRECEIPTitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OutsrgreceiptController extends BaseController {

    @Autowired
    private IOutsrgreceiptService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IOutsrgissueService outsrgissueService;
    @Autowired
    private IOutsrgreceiptheadService outsrgreceiptheadService;
    @Autowired
    private IOutsrgrfeService outsrgrfeService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private IAfkoService afkoService;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private ICardhstService cardhstService;
    @Autowired
    private ICardtService cardtService;


    @RequestMapping(value = "/wip/outsrgreceipt/query")
    @ResponseBody
    public ResponseData query(Outsrgreceipt dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/outsrgreceipt/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Outsrgreceipt> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/outsrgreceipt/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Outsrgreceipt> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/outsrgreceipt/syncwxsh"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData processWxsh(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String barcode = request.getParameter("a");
        String lifnr = request.getParameter("b");
        String lfsum = request.getParameter("c");
        String gfsum = request.getParameter("d");
        String hgsum = request.getParameter("e");
        String yssum = request.getParameter("f");
        String thsum = request.getParameter("g");
        String hjsum = request.getParameter("h");
        String createdBy = request.getParameter("i");
        String mblnr = request.getParameter("j");
        String mjahr = request.getParameter("k");
        String rsnum = request.getParameter("l");
        String rspos = request.getParameter("m");
        String vornr = request.getParameter("n");

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

        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);

        Outsrgissue outsrgissue = new Outsrgissue();
        outsrgissue = outsrgissueService.selectByBarcode(barcode,"0");

        Outsrgrfe outsrgrfe = new Outsrgrfe();
        outsrgrfe = outsrgrfeService.selectByCondition(cardh.getWerks(),cardh.getAufnr(),vornr,cardh.getMatnr(),lifnr);

        Marc marc = new Marc();
        marc = marcService.selectByMatnr(cardh.getMatnr());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        String curtim  = df.format(new Date()).substring(11,19).replaceAll(":","");
        String l_update = "";
        //准备表头数据

        Outsrgreceipthead outsrgreceipthead = new Outsrgreceipthead();
        List<Outsrgreceipthead> list = new ArrayList<>();
        list = outsrgreceiptheadService.selectByMatnrAndLifnrDesc(cardh.getMatnr(),lifnr);
        if (list.size() == 0){
            //产生新的单号 F+ 年 + 月 + 6位流水
            String no = "S" + curdate.substring(2,6) + "000001";
            outsrgreceipthead.setReceiptnm(no);
            outsrgreceipthead.setZdpuser(createdBy);
            outsrgreceipthead.setZdptim(curtim);
            outsrgreceipthead.setCreationDate(new Date());
            outsrgreceipthead.setZipuser("");
            outsrgreceipthead.setCreatedBy(Long.valueOf(createdBy));
            outsrgreceipthead.setWerks(cardh.getWerks());
            outsrgreceipthead.setStatus("0");
            outsrgreceipthead.setPrtflag("0");
            outsrgreceipthead.setMatnr(cardh.getMatnr());
            outsrgreceipthead.setLifnr(lifnr);
            outsrgreceipthead.setZdpdat(curdate);
            outsrgreceipthead.setZipdat(curdate);
            outsrgreceipthead.setZiptim(curtim);

        }else{
            if (list.get(0).getStatus().equals("0")){
                //使用以前的单号

            }else{
                outsrgreceipthead = list.get(0);
                if (!outsrgreceipthead.getReceiptnm().substring(1,5).equals(curdate.substring(2,6))){
                    String no = "S" + curdate.substring(2,6) + "000001";
                    outsrgreceipthead.setReceiptnm(no);
                }else{
                    String mxnum = outsrgreceipthead.getReceiptnm().substring(5,11);
                    int num = Integer.valueOf(mxnum) + 1;
                    mxnum = String.format("%06d",num);
                    outsrgreceipthead.setReceiptnm("S" + curdate.substring(2,6) + mxnum);
                    outsrgreceipthead.setZdpuser(createdBy);
                    outsrgreceipthead.setZdptim(curtim);
                    outsrgreceipthead.setCreationDate(new Date());
                    outsrgreceipthead.setZipuser("");
                    outsrgreceipthead.setCreatedBy(Long.valueOf(createdBy));
                    outsrgreceipthead.setWerks(cardh.getWerks());
                    outsrgreceipthead.setStatus("0");
                    outsrgreceipthead.setPrtflag("0");
                    outsrgreceipthead.setMatnr(cardh.getMatnr());
                    outsrgreceipthead.setLifnr(lifnr);
                    outsrgreceipthead.setZdpdat(curdate);
                    outsrgreceipthead.setZipdat(curdate);
                    outsrgreceipthead.setZiptim(curtim);
                }
            }
        }

        //准备行数据
        Outsrgreceipt outsrgreceipt = new Outsrgreceipt();
        outsrgreceipt = service.selectByZpgdbarAndStatus(barcode,"1");
        if (outsrgreceipt != null) {
            //使用以前的行项目
            l_update = "X";
            outsrgreceipt.setStatus("0");
            outsrgreceipt.setLastUpdatedBy(Long.valueOf(createdBy));
            outsrgreceipt.setLastUpdateDate(new Date());
            outsrgreceipt.setZthnum(Double.parseDouble(thsum));
            outsrgreceipt.setZshnum(Double.parseDouble(gfsum) + Double.parseDouble(lfsum) + Double.parseDouble(hgsum));
            outsrgreceipt.setZpgdbar(barcode);
            outsrgreceipt.setZlost(Double.parseDouble(yssum));
            outsrgreceipt.setZlfnum(Double.parseDouble(lfsum));
            outsrgreceipt.setZgfnum(Double.parseDouble(gfsum));
            outsrgreceipt.setZdsuser(createdBy);
            outsrgreceipt.setMjahr(mjahr);
            outsrgreceipt.setMblnr(mblnr);
            outsrgreceipt.setZthnum(Double.parseDouble(thsum));
            outsrgreceipt.setZdsdat(curdate);
            outsrgreceipt.setZdstim(curtim);
            outsrgreceipt.setZeile("");
            outsrgreceipt.setRueck(rsnum);
            outsrgreceipt.setRmzhl(rspos);
            outsrgreceipt.setTtreceipts(Double.parseDouble(hjsum));
        }else{
            Long item = 0L;
            outsrgreceipt = new Outsrgreceipt();
            if (outsrgreceipthead.getReceiptnm() == null){
                List<Outsrgreceipt> listmx = service.selectByReceiptDesc(list.get(0).getReceiptnm());
                item = listmx.get(0).getItem() + 1;

                outsrgreceipt.setRmzhl(rspos);
                outsrgreceipt.setRueck(rsnum);
                outsrgreceipt.setZeile("");
                outsrgreceipt.setZdstim(curtim);
                outsrgreceipt.setZdsdat(curdate);
                outsrgreceipt.setZthnum(Double.parseDouble(thsum));
                outsrgreceipt.setMblnr(mblnr);
                outsrgreceipt.setMjahr(mjahr);
                outsrgreceipt.setZdsuser(createdBy);
                outsrgreceipt.setZgfnum(Double.parseDouble(gfsum));
                outsrgreceipt.setZlfnum(Double.parseDouble(lfsum));
                outsrgreceipt.setZlost(Double.parseDouble(yssum));
                outsrgreceipt.setZpgdbar(barcode);
                outsrgreceipt.setZshnum(Double.parseDouble(gfsum) + Double.parseDouble(lfsum) + Double.parseDouble(hgsum) + Double.parseDouble(thsum));
                outsrgreceipt.setStatus("0");
                outsrgreceipt.setCreatedBy(Long.valueOf(createdBy));
                outsrgreceipt.setCreationDate(new Date());
                outsrgreceipt.setWerks(cardh.getWerks());
                outsrgreceipt.setVsnda(outsrgissue.getVsnda());
                outsrgreceipt.setVornr(vornr);
                outsrgreceipt.setTxz01(outsrgissue.getTxz01());
                outsrgreceipt.setTtreceipts(Double.parseDouble(hjsum));
                outsrgreceipt.setSfflg(cardh.getSfflg());
                outsrgreceipt.setReceiptnm(list.get(0).getReceiptnm());
                outsrgreceipt.setMenge(outsrgrfe.getMenge());
                outsrgreceipt.setMatnr(cardh.getMatnr());
                outsrgreceipt.setMatkl(marc.getMatkl());
                outsrgreceipt.setLifnr(lifnr);
                outsrgreceipt.setKtsch(outsrgissue.getKtsch());
                outsrgreceipt.setIssuenmitem(outsrgissue.getItem().toString());
                outsrgreceipt.setIssuenm(outsrgissue.getIssuenm());
                outsrgreceipt.setGmein(cardh.getGmein());
                outsrgreceipt.setEbelp(outsrgissue.getEbelp());
                outsrgreceipt.setEbeln(outsrgissue.getEbeln());
                outsrgreceipt.setDiecd(cardh.getDiecd());
                outsrgreceipt.setDeductntenm("");
                outsrgreceipt.setCharg(cardh.getCharg2());
                outsrgreceipt.setItem(item);

            }else{
                item = 1L;
                outsrgreceipt.setRmzhl(rspos);
                outsrgreceipt.setRueck(rsnum);
                outsrgreceipt.setZeile("");
                outsrgreceipt.setZdstim(curtim);
                outsrgreceipt.setZdsdat(curdate);
                outsrgreceipt.setZthnum(Double.parseDouble(thsum));
                outsrgreceipt.setMblnr(mblnr);
                outsrgreceipt.setMjahr(mjahr);
                outsrgreceipt.setZdsuser(createdBy);
                outsrgreceipt.setZgfnum(Double.parseDouble(gfsum));
                outsrgreceipt.setZlfnum(Double.parseDouble(lfsum));
                outsrgreceipt.setZlost(Double.parseDouble(yssum));
                outsrgreceipt.setZpgdbar(barcode);
                outsrgreceipt.setZshnum(Double.parseDouble(gfsum) + Double.parseDouble(lfsum) + Double.parseDouble(hgsum) + Double.parseDouble(thsum));
                outsrgreceipt.setStatus("0");
                outsrgreceipt.setCreatedBy(Long.valueOf(createdBy));
                outsrgreceipt.setCreationDate(new Date());
                outsrgreceipt.setWerks(cardh.getWerks());
                outsrgreceipt.setVsnda(outsrgissue.getVsnda());
                outsrgreceipt.setVornr(vornr);
                outsrgreceipt.setTxz01(outsrgissue.getTxz01());
                outsrgreceipt.setTtreceipts(Double.parseDouble(hjsum));
                outsrgreceipt.setSfflg(cardh.getSfflg());
                outsrgreceipt.setReceiptnm(outsrgreceipthead.getReceiptnm());
                outsrgreceipt.setMenge(outsrgrfe.getMenge());
                outsrgreceipt.setMatnr(cardh.getMatnr());
                outsrgreceipt.setMatkl(marc.getMatkl());
                outsrgreceipt.setLifnr(lifnr);
                outsrgreceipt.setKtsch(outsrgissue.getKtsch());
                outsrgreceipt.setIssuenmitem(outsrgissue.getItem().toString());
                outsrgreceipt.setIssuenm(outsrgissue.getIssuenm());
                outsrgreceipt.setGmein(cardh.getGmein());
                outsrgreceipt.setEbelp(outsrgissue.getEbelp());
                outsrgreceipt.setEbeln(outsrgissue.getEbeln());
                outsrgreceipt.setDiecd(cardh.getDiecd());
                outsrgreceipt.setDeductntenm("");
                outsrgreceipt.setCharg(cardh.getCharg2());
                outsrgreceipt.setItem(item);


            }
        }
        int result = 0;
        SyncOutsrgreceiptWebserviceUtil syncOutsrgreceiptWebserviceUtil = new SyncOutsrgreceiptWebserviceUtil();
        DTOUTSRGRECEIPTHead head = new DTOUTSRGRECEIPTHead();
        DTOUTSRGRECEIPTitem item = new DTOUTSRGRECEIPTitem();
        if (outsrgreceipthead.getReceiptnm() != null){
            head.setZdpdat(outsrgreceipthead.getZdpdat());
            head.setReceiptnm(outsrgreceipthead.getReceiptnm());
            head.setPrtflag(outsrgreceipthead.getPrtflag());
            head.setLifnr(outsrgreceipthead.getLifnr());
            head.setMatnr(outsrgreceipthead.getMatnr());
            head.setZipuser(outsrgreceipthead.getZipuser());
            head.setZipdat(outsrgreceipthead.getZipdat());
            head.setZiptim(outsrgreceipthead.getZiptim());
            head.setWerks(outsrgreceipthead.getWerks());
            head.setStatus(outsrgreceipthead.getStatus());
            head.setZdptim(outsrgreceipthead.getZdptim());
            head.setZdpuser(outsrgreceipthead.getZdpuser());
        }else{
            head.setZdpdat("");
            head.setReceiptnm("");
            head.setPrtflag("");
            head.setLifnr("");
            head.setMatnr("");
            head.setZipuser("");
            head.setZipdat("");
            head.setZiptim("");
            head.setWerks("");
            head.setStatus("");
            head.setZdptim("");
            head.setZdpuser("");
        }

        if (outsrgreceipt != null){
            item.setZthnum(outsrgreceipt.getZthnum());
            item.setZshnum(outsrgreceipt.getZshnum());
            item.setZpgdbar(outsrgreceipt.getZpgdbar());
            item.setZlost(outsrgreceipt.getZlost());
            item.setZlfnum(outsrgreceipt.getZlfnum());
            item.setZgfnum(outsrgreceipt.getZgfnum());
            item.setZeile(outsrgreceipt.getZeile());
            item.setZdsuser(outsrgreceipt.getZdsuser());
            item.setZdstim(outsrgreceipt.getZdstim());
            item.setZdsdat(outsrgreceipt.getZdsdat());
            item.setWerks(outsrgreceipt.getWerks());
            item.setVsnda(outsrgreceipt.getVsnda());
            item.setVornr(outsrgreceipt.getVornr());
            item.setTxz01(outsrgreceipt.getTxz01());
            item.setTtreceipts(outsrgreceipt.getTtreceipts());
            item.setStatus(outsrgreceipt.getStatus());
            item.setSfflg(outsrgreceipt.getSfflg());
            item.setRueck(outsrgreceipt.getRueck());
            item.setRmzhl(outsrgreceipt.getRmzhl());
            item.setReceiptnm(outsrgreceipt.getReceiptnm());
            item.setMjahr(outsrgreceipt.getMjahr());
            item.setMenge(outsrgreceipt.getMenge());
            item.setMblnr(outsrgreceipt.getMblnr());
            item.setMatnr(outsrgreceipt.getMatnr());
            item.setMatkl(outsrgreceipt.getMatkl());
            item.setLifnr(outsrgreceipt.getLifnr());
            item.setKtsch(outsrgreceipt.getKtsch());
            item.setItem(outsrgreceipt.getItem().toString());
            item.setIssuenm(outsrgreceipt.getIssuenm());
            item.setIssuenmitem(outsrgreceipt.getIssuenmitem());
            item.setGmein(outsrgreceipt.getGmein());
            item.setEbelp(outsrgreceipt.getEbelp());
            item.setEbeln(outsrgreceipt.getEbeln());
            item.setDiecd(outsrgreceipt.getDiecd());
            item.setDeductntenm(outsrgreceipt.getDeductntenm());
            item.setCharg(outsrgreceipt.getCharg());
        }

        DTOUTSRGRECEIPTReturn DTRE = new DTOUTSRGRECEIPTReturn();
        DTRE = syncOutsrgreceiptWebserviceUtil.receiveConfirmation(head,item);
        if (DTRE.getMSGTY().equals("S")){
            if (outsrgreceipthead.getReceiptnm() != null){
                result = outsrgreceiptheadService.insertNewRow(outsrgreceipthead);
                if (result != 1){
                    rs.setMessage("数据保存失败，请联系管理员！");
                    rs.setSuccess(false);
                    return rs;
                }else{
                    if (l_update.equals("X")){
                        result = service.updateOutsrgreceipt(outsrgreceipt);
                    }else{
                        result = service.insertNewRow(outsrgreceipt);
                    }

                    if (result != 1){
                        rs.setMessage("数据保存失败，请联系管理员！");
                        rs.setSuccess(false);
                        return rs;
                    }
                }
            }else{
                if (l_update.equals("X")){
                    result = service.updateOutsrgreceipt(outsrgreceipt);
                }else{
                    result = service.insertNewRow(outsrgreceipt);
                }

                if (result != 1){
                    rs.setMessage("数据保存失败，请联系管理员！");
                    rs.setSuccess(false);
                    return rs;
                }
            }
        }else{
            rs.setSuccess(false);
            rs.setMessage(DTRE.getMESSAGE());
            return rs;
        }
        rs.setSuccess(true);
        return rs;
    }

    @RequestMapping(value = {"/wip/outsrgreceipt/selectForCxwxsh"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectForCxwxsh(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String barcode = request.getParameter("barcode");
        Outsrgreceipt outsrgreceipt = new Outsrgreceipt();
        outsrgreceipt = service.selectByZpgdbarAndStatus(barcode,"0");
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);

        if (cardh == null){
            rs.setSuccess(false);
            rs.setMessage("流转卡不存在，请扫描正确的流转卡编号！");
            return rs;
        }


        if (cardh.getStatus().equals("HOLD")){
            rs.setSuccess(false);
            rs.setMessage("流转卡状态为HOLD，不能进行外协收货冲销操作！");
            return rs;
        }

        if (outsrgreceipt == null){
            rs.setMessage("该流转卡没有进行外协收货！无法冲销！");
            rs.setSuccess(false);
            return rs;
        }

        Marc marc = new Marc();
        marc = marcService.selectByMatnr(outsrgreceipt.getMatnr());

        List outsrgreceipts = new ArrayList<>();
        outsrgreceipts.add(outsrgreceipt);
        outsrgreceipts.add(marc);
        rs.setRows(outsrgreceipts);
        rs.setSuccess(true);
        return rs;
    }

    @RequestMapping(value = {"/wip/outsrgreceipt/processCxwxsh"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData processCxwxsh(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        //查询流转卡信息
        String barcode = request.getParameter("barcode");
        String userId = request.getParameter("userId");
        //查询查询收货单信息
        Outsrgreceipt outsrgreceipt = new Outsrgreceipt();
        outsrgreceipt = service.selectByZpgdbarAndStatus(barcode,"0");
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);
        Cardhst cardhst = new Cardhst();
        List<Cardhst> listcardhst = new ArrayList<>();
        listcardhst = cardhstService.selectAllActive(barcode);
        //查询报工日志
        InputLog inputLog = new InputLog();
        inputLog.setDispatch(barcode);
        inputLog.setOperation(outsrgreceipt.getVornr());
        inputLog = inputLogService.queryByDispatchAndOperation(inputLog);

        if (inputLog == null){
            rs.setMessage("没有获取到该流转卡外协工序报工记录！");
            rs.setSuccess(false);
            return rs;
        }

        if (!inputLog.getAttr15().equals("5")){
            rs.setMessage("该流转卡对应的报工记录报工类型错误！");
            rs.setSuccess(false);
            return rs;
        }

        if (cardh.getLgort() != null && cardh.getLgort() != ""){
            rs.setSuccess(false);
            rs.setMessage("流转卡已被转移至暂存区，不允许进行冲销操作");
            return  rs;
        }

        //排除机加的报工冲销 机加报工冲销只能在装箱系统中操作
        Afko afko = new Afko();
        afko = afkoService.selectByAufnr(cardh.getAufnr());
        if (afko.getAuart().substring(0,1).equals("Q")){//机加订单
            rs.setSuccess(false);
            rs.setMessage("机加报工冲销，请到装箱系统冲销！");
            return rs;
        }



        String fstvor = "";
        String lstvor = "";
        String message= "";
        List<DTBAOGONGReturnResult> list = new ArrayList<DTBAOGONGReturnResult>();
        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
        String l_error = "";

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
        returnResult = inputLogService.writeOffDispatchNew(inputLog);
        if (returnResult.getMSGTY().equals("S")){
            outsrgreceipt.setMjahr(returnResult.getMJAHR());
            outsrgreceipt.setMblnr(returnResult.getMBLNR());
            cardhst = listcardhst.get(0);//当前流转卡状态
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
                cardh.setLastUpdatedBy(Long.valueOf(userId));
                cardh.setLastUpdatedDate(new Date());
                cardhService.updateDatforFsvor(cardh.getZpgdbar());
            }

            if (lstvor.equals("X")){
                cardh.setEprddat("");
                cardh.setAlqty(0D);
                cardh.setLastUpdatedBy(Long.valueOf(userId));
                cardh.setLastUpdatedDate(new Date());
                cardhService.updateDatforLsvor(cardh.getZpgdbar());
            }

        }else{
            rs.setSuccess(false);
            rs.setMessage(returnResult.getMESSAGE());
            return rs;
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

        //修改发料单行项目状态 并同步到sap
        outsrgreceipt.setStatus("1");
        outsrgreceipt.setLastUpdateDate(new Date());
        outsrgreceipt.setLastUpdatedBy(Long.valueOf(userId));

        //准备接口表头 行
        DTOUTSRGRECEIPTHead head = new DTOUTSRGRECEIPTHead();
        DTOUTSRGRECEIPTitem item = new DTOUTSRGRECEIPTitem();

        head.setZdpdat("");
        head.setReceiptnm("");
        head.setPrtflag("");
        head.setLifnr("");
        head.setMatnr("");
        head.setZipuser("");
        head.setZipdat("");
        head.setZiptim("");
        head.setWerks("");
        head.setStatus("");
        head.setZdptim("");
        head.setZdpuser("");

        item.setZthnum(outsrgreceipt.getZthnum());
        item.setZshnum(outsrgreceipt.getZshnum());
        item.setZpgdbar(outsrgreceipt.getZpgdbar());
        item.setZlost(outsrgreceipt.getZlost());
        item.setZlfnum(outsrgreceipt.getZlfnum());
        item.setZgfnum(outsrgreceipt.getZgfnum());
        item.setZeile(outsrgreceipt.getZeile());
        item.setZdsuser(outsrgreceipt.getZdsuser());
        item.setZdstim(outsrgreceipt.getZdstim());
        item.setZdsdat(outsrgreceipt.getZdsdat());
        item.setWerks(outsrgreceipt.getWerks());
        item.setVsnda(outsrgreceipt.getVsnda());
        item.setVornr(outsrgreceipt.getVornr());
        item.setTxz01(outsrgreceipt.getTxz01());
        item.setTtreceipts(outsrgreceipt.getTtreceipts());
        item.setStatus(outsrgreceipt.getStatus());
        item.setSfflg(outsrgreceipt.getSfflg());
        item.setRueck(outsrgreceipt.getRueck());
        item.setRmzhl(outsrgreceipt.getRmzhl());
        item.setReceiptnm(outsrgreceipt.getReceiptnm());
        item.setMjahr(outsrgreceipt.getMjahr());
        item.setMenge(outsrgreceipt.getMenge());
        item.setMblnr(outsrgreceipt.getMblnr());
        item.setMatnr(outsrgreceipt.getMatnr());
        item.setMatkl(outsrgreceipt.getMatkl());
        item.setLifnr(outsrgreceipt.getLifnr());
        item.setKtsch(outsrgreceipt.getKtsch());
        item.setItem(outsrgreceipt.getItem().toString());
        item.setIssuenm(outsrgreceipt.getIssuenm());
        item.setIssuenmitem(outsrgreceipt.getIssuenmitem());
        item.setGmein(outsrgreceipt.getGmein());
        item.setEbelp(outsrgreceipt.getEbelp());
        item.setEbeln(outsrgreceipt.getEbeln());
        item.setDiecd(outsrgreceipt.getDiecd());
        item.setDeductntenm(outsrgreceipt.getDeductntenm());
        item.setCharg(outsrgreceipt.getCharg());
        DTOUTSRGRECEIPTReturn DTRE = new DTOUTSRGRECEIPTReturn();
        SyncOutsrgreceiptWebserviceUtil syncOutsrgreceiptWebserviceUtil = new SyncOutsrgreceiptWebserviceUtil();
        DTRE = syncOutsrgreceiptWebserviceUtil.receiveConfirmation(head,item);
        if (DTRE.getMSGTY().equals("S")){
            int num = service.updateOutsrgreceipt(outsrgreceipt);
            if (num != 1){
                rs.setMessage("冲销成功，同步数据成功，单更新IMFGnet平台数据失败，请联系管理员！");
                rs.setSuccess(false);
                return rs;
            }
        }else{
            rs.setSuccess(false);
            rs.setMessage("冲销成功，但是保存数据和同步数据失败，请联系管理员！");
            return rs;
        }
        rs.setSuccess(true);
        return rs;
    }
}