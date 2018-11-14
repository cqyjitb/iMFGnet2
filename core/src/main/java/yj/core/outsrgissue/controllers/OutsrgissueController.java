package yj.core.outsrgissue.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.service.IOutsrgissueService;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;
import yj.core.outsrgissuehead.service.IOutsrgissueheadService;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import yj.core.webservice_outsrgissue.components.SyncOutsrgissueWebserviceUtil;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEhead;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEitem;
import yj.core.webservice_outsrgissue.dto.DTOUTSRGISSUEreturn;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    @Controller
    public class OutsrgissueController extends BaseController{

    @Autowired
    private IOutsrgissueService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IOutsrgissueheadService outsrgissueheadService;
    @Autowired
    private IOutsrgrfeService outsrgrfeService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private ICardtService cardtService;

    @RequestMapping(value = "/wip/outsrgissue/query")
    @ResponseBody
    public ResponseData query(Outsrgissue dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/outsrgissue/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Outsrgissue> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/outsrgissue/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Outsrgissue> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/outsrgissue/wxfl"},method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData processWxfl(HttpServletRequest request,String barcode,String lifnr,String userId,String ebeln,String menge,String vornr){
        ResponseData rs = new ResponseData();
        Cardh cardh = new Cardh();
        cardh = cardhService.selectByBarcode(barcode);

        Outsrgrfe outsrgrfe = outsrgrfeService.selectByCondition(cardh.getWerks(),cardh.getAufnr(),vornr,cardh.getMatnr(),lifnr);
        Marc marc = new Marc();
        marc = marcService.selectByMatnr(cardh.getMatnr());
        Cardt cardt = new Cardt();
        cardt = cardtService.selectByZpgdbarAndVornr(cardh.getZpgdbar(),vornr);
        //查询并生成发料单号
        List<Outsrgissuehead> list = new ArrayList<>();
        list = outsrgissueheadService.selectByMatnrAndLifnrDesc(cardh.getMatnr(),lifnr);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0,10).replaceAll("-","");
        String issuenm = "F" + curdate.substring(2,5) + "000001";
        //准备表头数据
        Outsrgissuehead outsrgissuehead = new Outsrgissuehead();
        if (list.size() ==0 ){
            //产生新的单号 F+ 年 + 月 + 6位流水
            outsrgissuehead.setIssuenm(issuenm);
            outsrgissuehead.setLifnr(lifnr);
            outsrgissuehead.setMatnr(cardh.getMatnr());
            outsrgissuehead.setPrtflag("");
            outsrgissuehead.setStatus("0");
            outsrgissuehead.setTxz01(cardt.getLtxa1());
            outsrgissuehead.setWerks(cardh.getWerks());
            outsrgissuehead.setCreatedBy(Long.valueOf(userId));
            outsrgissuehead.setCreationDate(new Date());
        }else{
            if (list.get(list.size() -1).getStatus().equals("0")){
                //使用以前的单号

            }else{
                //产生新的单号 获取当前流水
                outsrgissuehead = list.get(list.size() - 1);
                if (!outsrgissuehead.getIssuenm().substring(1,4).equals(curdate.substring(2,5))){
                    outsrgissuehead.setIssuenm(issuenm);
                }else{
                    String mxnum = outsrgissuehead.getIssuenm().substring(5,11);
                    int num = Integer.valueOf(mxnum) + 1;
                    mxnum = String.valueOf(num);
                    for (int i = 0;i<6-mxnum.length();i++){
                        mxnum = "0" + mxnum;
                    }

                    outsrgissuehead.setIssuenm("F" + curdate.substring(2,5) + mxnum);
                    outsrgissuehead.setLifnr(lifnr);
                    outsrgissuehead.setMatnr(cardh.getMatnr());
                    outsrgissuehead.setPrtflag("");
                    outsrgissuehead.setStatus("0");
                    outsrgissuehead.setTxz01(cardt.getLtxa1());
                    outsrgissuehead.setWerks(cardh.getWerks());
                    outsrgissuehead.setCreatedBy(Long.valueOf(userId));
                    outsrgissuehead.setCreationDate(new Date());
                }
            }
        }

        //准备行数据
        Long item = 0l;
        if (outsrgissuehead.getIssuenm() == null){
            //使用以前单号
            issuenm = list.get(list.size() - 1).getIssuenm();
            //根据单号查询交货明细最大行号
            List<Outsrgissue> listmx = service.selectByIssuenmDesc(issuenm);
            item = listmx.get(0).getItem() + 1;

        }else{
            item = 1l;
        }
        Outsrgissue outsrgissue = new Outsrgissue();
        outsrgissue.setDiecd(cardh.getDiecd());
        outsrgissue.setEbeln(outsrgrfe.getEbeln());
        outsrgissue.setEbelp(outsrgrfe.getEbelp());
        outsrgissue.setEtenr("");
        outsrgissue.setGmein(cardh.getGmein());
        outsrgissue.setIssuenm(issuenm);
        outsrgissue.setItem(item);
        outsrgissue.setKtsch(cardt.getKtsch());
        outsrgissue.setLifnr(lifnr);
        outsrgissue.setMatkl(marc.getMatkl());
        outsrgissue.setMatnr(cardh.getMatnr());
        outsrgissue.setMenge(outsrgrfe.getMenge());
        outsrgissue.setSfflg(cardh.getSfflg());
        outsrgissue.setSortl(outsrgrfe.getSortl());
        outsrgissue.setTxz01(cardt.getLtxa1());
        outsrgissue.setVornr(vornr);
        outsrgissue.setVsnda(outsrgrfe.getVsnda());
        outsrgissue.setWerks(cardh.getWerks());
        outsrgissue.setZisnum(Double.valueOf(menge));
        outsrgissue.setZpgdbar(barcode);
        outsrgissue.setCreatedBy(Long.valueOf(userId));
        outsrgissue.setCreationDate(new Date());
        outsrgissue.setStatus("0");
        outsrgissue.setCharg(cardh.getCharg2());
        int result = 0;

        SyncOutsrgissueWebserviceUtil syncOutsrgissueWebserviceUtil = new SyncOutsrgissueWebserviceUtil();
        DTOUTSRGISSUEhead head = new DTOUTSRGISSUEhead();
        DTOUTSRGISSUEitem items = new DTOUTSRGISSUEitem();
        if (outsrgissuehead.getIssuenm() != null){
            head.setIssuenm(outsrgissuehead.getIssuenm());
            head.setLifnr(outsrgissuehead.getLifnr());
            head.setMatnr(outsrgissuehead.getMatnr());
            head.setPrtflag(outsrgissuehead.getPrtflag());
            head.setStatus(outsrgissuehead.getStatus());
            head.setTxz01(outsrgissuehead.getTxz01());
            head.setWerks(outsrgissuehead.getWerks());
        }else{
            head.setIssuenm("");
            head.setLifnr("");
            head.setMatnr("");
            head.setPrtflag("");
            head.setStatus("");
            head.setTxz01("");
            head.setWerks("");
        }

        if (outsrgissue != null){
            items.setDiecd(outsrgissue.getDiecd());
            items.setEbeln(outsrgissue.getEbeln());
            items.setEbelp(outsrgissue.getEbelp());
            items.setEtenr(outsrgissue.getEtenr());
            items.setGmein(outsrgissue.getGmein());
            items.setIssuenm(outsrgissue.getIssuenm());
            items.setItem(outsrgissue.getItem());
            items.setKtsch(outsrgissue.getKtsch());
            items.setLifnr(outsrgissue.getLifnr());
            items.setMatkl(outsrgissue.getMatkl());
            items.setMatnr(outsrgissue.getMatnr());
            items.setMenge(outsrgissue.getMenge());
            items.setSfflg(outsrgissue.getSfflg());
            items.setSortl(outsrgissue.getSortl());
            items.setTxz01(outsrgissue.getTxz01());
            items.setVornr(outsrgissue.getVornr());
            items.setVsnda(outsrgissue.getVsnda());
            items.setWerks(outsrgissue.getWerks());
            items.setZisnum(outsrgissue.getZisnum());
            items.setZpgdbar(outsrgissue.getZpgdbar());
            items.setCharg(outsrgissue.getCharg());
            items.setStatus(outsrgissue.getStatus());

        }
        DTOUTSRGISSUEreturn DTRE = new DTOUTSRGISSUEreturn();
        DTRE = syncOutsrgissueWebserviceUtil.receiveConfirmation(head,items);
        if (DTRE.getMSGTY().equals("S")){
            if (outsrgissuehead.getIssuenm() != null){
                result = outsrgissueheadService.insertNewRow(outsrgissuehead);
                if ( result != 1){
                    rs.setMessage("数据保存失败，请联系管理员！");
                    rs.setSuccess(false);
                    return rs;
                }else{
                    result = service.insertNewRow(outsrgissue);
                    if (result != 1){
                        rs.setMessage("数据保存失败，请联系管理员！");
                        rs.setSuccess(false);
                        return rs;
                    }
                }
            }else{
                result = service.insertNewRow(outsrgissue);
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