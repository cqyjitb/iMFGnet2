package yj.core.outsrgreceipt.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.service.ICardtService;
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

    @RequestMapping(value = {"/wip/outsrgissue/wxsh"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData processWxsh(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        String barcode = request.getParameter("barcode");
        String lifnr = request.getParameter("lifnr");
        String lfsum = request.getParameter("lfsum");
        String gfsum = request.getParameter("gfsum");
        String hgsum = request.getParameter("hgsum");
        String yssum = request.getParameter("yssum");
        String thsum = request.getParameter("thsum");
        String hjsum = request.getParameter("hjsum");
        String createdBy = request.getParameter("createdBy");
        String mblnr = request.getParameter("mblnr");
        String mjahr = request.getParameter("mjahr");
        String rsnum = request.getParameter("rsnum");
        String rspos = request.getParameter("rspos");
        String vornr = request.getParameter("vornr");

        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);

        Outsrgissue outsrgissue = new Outsrgissue();
        outsrgissue = outsrgissueService.selectByBarcode(barcode,"1");

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
                l_update = "X";
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
}