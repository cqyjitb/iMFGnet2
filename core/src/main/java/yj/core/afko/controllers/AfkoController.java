package yj.core.afko.controllers;


import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.dto.AfkoforZudlist;
import yj.core.afko.service.IAfkoService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.service.ICardtService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.service.IInputLogService;
import yj.core.dispatch.service.ILogService;
import yj.core.dispatch.service.IResultService;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.marc.service.IMarcService;
import yj.core.sccl.dto.Sccl;
import yj.core.sccl.service.IScclService;
import yj.core.sysuser.dto.SYSUser;
import yj.core.sysuser.service.ISYSUserService;
import yj.core.webservice_newbg.components.ConfirmationWebserviceUtilNew;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.core.zudhead.dto.Zudhead;
import yj.core.zudhead.service.IZudheadService;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zudlist.dto.ZudlistParam;
import yj.core.zudlist.service.IZudlistService;
import yj.core.zudlog.dto.Zudlog;
import yj.core.zudlog.service.IZudlogService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class AfkoController
        extends BaseController
{
    @Autowired
    private IAfkoService service;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IScclService scclService;
    @Autowired
    private IZudlistService zudlistService;
    @Autowired
    private IZudheadService zudheadService;
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IDftrghlistService dftrghlistService;
    @Autowired
    private IInOutRecordService iInOutRecordService;
    @Autowired
    private IZudlogService zudlogService;
    @Autowired
    private ICardtService cardtService;
    @Autowired
    private IInputLogService inputLogService;
    @Autowired
    private IResultService resultService;
    @Autowired
    private ILogService logService;
    @Autowired
    private ISYSUserService userService;



    class MantnrAndSum{
        private String matnr;
        private Long fsum;
        private Long gfsum ;
        private Long lfsum;
        private Long Qsum;
        private String itemsf;
        private String itemsq;
        private String zudnum;

        public String getZudnum() {
            return zudnum;
        }

        public void setZudnum(String zudnum) {
            this.zudnum = zudnum;
        }

        public MantnrAndSum(){
            this.gfsum = 0l;
            this.lfsum = 0l;
            this.fsum = 0l;
        }

        public Long getGfsum() {
            return gfsum;
        }

        public void setGfsum(Long gfsum) {
            this.gfsum = gfsum;
        }

        public Long getLfsum() {
            return lfsum;
        }

        public void setLfsum(Long lfsum) {
            this.lfsum = lfsum;
        }

        public String getMatnr() {
            return matnr;
        }

        public void setMatnr(String matnr) {
            this.matnr = matnr;
        }

        public Long getFsum() {
            return fsum;
        }

        public void setFsum(Long fsum) {
            this.fsum = fsum;
        }

        public Long getQsum() {
            return Qsum;
        }

        public void setQsum(Long qsum) {
            Qsum = qsum;
        }

        public String getItemsf() {
            return itemsf;
        }

        public void setItemsf(String itemsf) {
            this.itemsf = itemsf;
        }

        public String getItemsq() {
            return itemsq;
        }

        public void setItemsq(String itemsq) {
            this.itemsq = itemsq;
        }
    }
    @RequestMapping(value = {"/sap/afko/queryAfkoForZudlist"},method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData queryAfkoForZudlist(HttpServletRequest request,@RequestBody List<ZudlistParam> list){
        List<MantnrAndSum> tmplist = new ArrayList<>();
        List<AfkoforZudlist> afkolist = new ArrayList();
        if (list.size() > 0){
            String zudnum = list.get(0).getZudnum();
            for(int i=0;i<list.size();i++){
                Cardh cardhjj = new Cardh();
                Afko afko = new Afko();
                cardhjj = cardhService.selectByBarcode(list.get(i).getZpgdbar());
                if (cardhjj != null){
                    afko = service.selectByAufnr(cardhjj.getAufnr());
                    String status = afko.getStatus();
                    String auart = afko.getAuart();
                    if (auart.equals("QP02")){//
                        //为返工订单的情况
                        if (afkolist.size() > 0){
                            String is_have = "";
                            for (int j=0;j<afkolist.size();j++){
                                if (afkolist.get(j).getAufnr().equals(afko.getAufnr()) && list.get(i).getReviewc().equals("F")){
                                    afkolist.get(j).setFgnum(afkolist.get(j).getFgnum() + 1);
                                    if (list.get(i).getZqxdm2().substring(0,1).equals("M")){
                                        afkolist.get(j).setLfnum(afkolist.get(j).getLfnum() + 1);
                                    }else{
                                        afkolist.get(j).setGfnum(afkolist.get(j).getGfnum() + 1);
                                    }

                                    afkolist.get(j).setAuart(afko.getAuart());
                                    afkolist.get(j).setMatnr(afko.getPlnbez());
                                    afkolist.get(j).setGamng(afko.getGamng());
                                    afkolist.get(j).setGltrp(afko.getGltrp());
                                    afkolist.get(j).setGstrp(afko.getGstrp());
                                    afkolist.get(j).setAuartxt(afko.getAuart_txt());
                                    if (afkolist.get(j).getItemsf().equals(null)){
                                        afkolist.get(j).setItemsf(list.get(i).getItem());
                                    }else{
                                        afkolist.get(j).setItemsf(afkolist.get(j).getItemsf()+","+list.get(i).getItem());
                                    }

                                    afkolist.get(j).setZudnum(zudnum);
                                    is_have = "X";
                                }else if(afkolist.get(j).getAufnr().equals(afko.getAufnr()) && list.get(i).getReviewc().equals("Q")){
                                    afkolist.get(j).setQnum(afkolist.get(j).getQnum() + 1);
                                    afkolist.get(j).setAuart(afko.getAuart());
                                    afkolist.get(j).setMatnr(afko.getPlnbez());
                                    afkolist.get(j).setGamng(afko.getGamng());
                                    afkolist.get(j).setGltrp(afko.getGltrp());
                                    afkolist.get(j).setGstrp(afko.getGstrp());
                                    afkolist.get(j).setAuartxt(afko.getAuart_txt());

                                    if (afkolist.get(j).getItemsq().equals(null)){
                                        afkolist.get(j).setItemsq(list.get(i).getItem());
                                    }else{
                                        afkolist.get(j).setItemsq(afkolist.get(j).getItemsq()+","+list.get(i).getItem());
                                    }

                                    afkolist.get(j).setZudnum(zudnum);
                                    is_have = "X";
                                }
                            }

                            if (!is_have.equals("X") && list.get(i).getReviewc().equals("F")){
                                AfkoforZudlist akl = new AfkoforZudlist();
                                akl.setAufnr(afko.getAufnr());
                                akl.setAuart(afko.getAuart());
                                akl.setMatnr(afko.getPlnbez());
                                akl.setFgnum(list.get(i).getZdnum());
                                if (list.get(i).getZqxdm2().substring(0,1).equals("M")){
                                    akl.setLfnum(list.get(i).getZdnum());
                                }else{
                                    akl.setGfnum(list.get(i).getZdnum());
                                }
                                akl.setAuartxt(afko.getAuart_txt());
                                akl.setGstrp(afko.getGstrp());
                                akl.setGltrp(afko.getGltrp());
                                akl.setGamng(afko.getGamng());
                                akl.setItemsf(list.get(i).getItem());
                                akl.setItemsq("");
                                akl.setQnum(0L);
                                akl.setZudnum(zudnum);
                                afkolist.add(akl);
                            }else if (!is_have.equals("X") && list.get(i).getReviewc().equals("Q")){
                                AfkoforZudlist akl = new AfkoforZudlist();
                                akl.setAufnr(afko.getAufnr());
                                akl.setAuart(afko.getAuart());
                                akl.setMatnr(afko.getPlnbez());
                                akl.setQnum(list.get(i).getZdnum());
                                akl.setFgnum(0L);
                                akl.setAuartxt(afko.getAuart_txt());
                                akl.setGstrp(afko.getGstrp());
                                akl.setGltrp(afko.getGltrp());
                                akl.setGamng(afko.getGamng());
                                akl.setItemsq(list.get(i).getItem());
                                akl.setItemsf("");
                                akl.setZudnum(zudnum);
                                afkolist.add(akl);
                            }
                        }else{
                            if (list.get(i).getReviewc().equals("F")){
                                AfkoforZudlist akl = new AfkoforZudlist();
                                akl.setAufnr(afko.getAufnr());
                                akl.setAuart(afko.getAuart());
                                akl.setMatnr(afko.getPlnbez());
                                akl.setFgnum(list.get(i).getZdnum());
                                if (list.get(i).getZqxdm2().substring(0,1).equals("M")){
                                    akl.setLfnum(list.get(i).getZdnum());
                                }else{
                                    akl.setGfnum(list.get(i).getZdnum());
                                }
                                akl.setQnum(0L);
                                akl.setGamng(afko.getGamng());
                                akl.setGltrp(afko.getGltrp());
                                akl.setGstrp(afko.getGstrp());
                                akl.setAuartxt(afko.getAuart_txt());
                                akl.setItemsf(list.get(i).getItem());
                                akl.setItemsq("");
                                akl.setZudnum(zudnum);
                                afkolist.add(akl);
                            }else if (list.get(i).getReviewc().equals("Q")){
                                AfkoforZudlist akl = new AfkoforZudlist();
                                akl.setAufnr(afko.getAufnr());
                                akl.setAuart(afko.getAuart());
                                akl.setMatnr(afko.getPlnbez());
                                akl.setQnum(list.get(i).getZdnum());
                                akl.setFgnum(0L);
                                akl.setGamng(afko.getGamng());
                                akl.setGltrp(afko.getGltrp());
                                akl.setGstrp(afko.getGstrp());
                                akl.setAuartxt(afko.getAuart_txt());
                                akl.setItemsq(list.get(i).getItem());
                                akl.setItemsf("");
                                akl.setZudnum(zudnum);
                                afkolist.add(akl);
                            }

                        }

                    }else{
                            if (afkolist.size() > 0){
                                String is_have = "";
                                for (int j=0;j<afkolist.size();j++){
                                    if (afkolist.get(j).getAufnr().equals(afko.getAufnr()) && list.get(i).getReviewc().equals("F")){
                                        afkolist.get(j).setFgnum(afkolist.get(j).getFgnum() + 1);
                                        if (list.get(i).getZqxdm2().substring(0,1).equals("M")){
                                            afkolist.get(j).setLfnum(afkolist.get(j).getLfnum() + 1);
                                        }else{
                                            afkolist.get(j).setGfnum(afkolist.get(j).getGfnum() + 1);
                                        }
                                        if (afkolist.get(j).getItemsf().equals(null)){
                                            afkolist.get(j).setItemsf(list.get(i).getItem());
                                        }else{
                                            afkolist.get(j).setItemsf(afkolist.get(j).getItemsf()+","+list.get(i).getItem());
                                        }

                                        afkolist.get(j).setAuartxt(afko.getAuart_txt());
                                        afkolist.get(j).setAuart(afko.getAuart());
                                        afkolist.get(j).setMatnr(afko.getPlnbez());
                                        afkolist.get(j).setGltrp(afko.getGltrp());
                                        afkolist.get(j).setGstrp(afko.getGstrp());
                                        afkolist.get(j).setGamng(afko.getGamng());
                                        afkolist.get(j).setZudnum(zudnum);
                                        is_have = "X";
                                    }else if (afkolist.get(j).getAufnr().equals(afko.getAufnr()) && list.get(i).getReviewc().equals("Q")){
                                        afkolist.get(j).setQnum(afkolist.get(j).getQnum() + 1);
                                        if (afkolist.get(j).getItemsq().equals(null)){
                                            afkolist.get(j).setItemsq(list.get(i).getItem());
                                        }else{
                                            afkolist.get(j).setItemsq(afkolist.get(j).getItemsq()+","+list.get(i).getItem());
                                        }

                                        afkolist.get(j).setAuartxt(afko.getAuart_txt());
                                        afkolist.get(j).setAuart(afko.getAuart());
                                        afkolist.get(j).setMatnr(afko.getPlnbez());
                                        afkolist.get(j).setGltrp(afko.getGltrp());
                                        afkolist.get(j).setGstrp(afko.getGstrp());
                                        afkolist.get(j).setGamng(afko.getGamng());
                                        afkolist.get(i).setZudnum(zudnum);
                                        is_have = "X";
                                    }
                                }

                                if (!is_have.equals("X") && list.get(i).getReviewc().equals("F")){
                                    AfkoforZudlist akl = new AfkoforZudlist();
                                    akl.setAufnr(afko.getAufnr());
                                    akl.setAuart(afko.getAuart());
                                    akl.setMatnr(afko.getPlnbez());
                                    akl.setFgnum(list.get(i).getZdnum());
                                    if (list.get(i).getZqxdm2().substring(0,1).equals("M")){
                                        akl.setLfnum(list.get(i).getZdnum());
                                    }else{
                                        akl.setGfnum(list.get(i).getZdnum());
                                    }
                                    akl.setItemsf(list.get(i).getItem());
                                    akl.setItemsq("");
                                    akl.setGamng(afko.getGamng());
                                    akl.setQnum(0L);
                                    akl.setGstrp(afko.getGstrp());
                                    akl.setGltrp(afko.getGltrp());
                                    akl.setAuartxt(afko.getAuart_txt());
                                    akl.setZudnum(zudnum);
                                    afkolist.add(akl);
                                }else if (!is_have.equals("X") && list.get(i).getReviewc().equals("Q")){
                                    AfkoforZudlist akl = new AfkoforZudlist();
                                    akl.setAufnr(afko.getAufnr());
                                    akl.setAuart(afko.getAuart());
                                    akl.setMatnr(afko.getPlnbez());
                                    akl.setQnum(list.get(i).getZdnum());
                                    akl.setItemsq(list.get(i).getItem());
                                    akl.setItemsf("");
                                    akl.setGamng(afko.getGamng());
                                    akl.setFgnum(0l);
                                    akl.setGstrp(afko.getGstrp());
                                    akl.setGltrp(afko.getGltrp());
                                    akl.setAuartxt(afko.getAuart_txt());
                                    akl.setZudnum(zudnum);
                                    afkolist.add(akl);
                                }
                            }else{
                                if (list.get(i).getReviewc().equals("F")){
                                    AfkoforZudlist akl = new AfkoforZudlist();
                                    akl.setAufnr(afko.getAufnr());
                                    akl.setAuart(afko.getAuart());
                                    akl.setMatnr(afko.getPlnbez());
                                    akl.setFgnum(list.get(i).getZdnum());
                                    if (list.get(i).getZqxdm2().substring(0,1).equals("M")){
                                        akl.setLfnum(list.get(i).getZdnum());
                                    }else{
                                        akl.setGfnum(list.get(i).getZdnum());
                                    }
                                    akl.setQnum(0L);
                                    akl.setItemsf(list.get(i).getItem());
                                    akl.setItemsq("");
                                    akl.setGamng(afko.getGamng());
                                    akl.setGstrp(afko.getGstrp());
                                    akl.setGltrp(afko.getGltrp());
                                    akl.setAuartxt(afko.getAuart_txt());
                                    akl.setZudnum(zudnum);
                                    afkolist.add(akl);
                                }else if (list.get(i).getReviewc().equals("Q")){
                                    AfkoforZudlist akl = new AfkoforZudlist();
                                    akl.setAufnr(afko.getAufnr());
                                    akl.setAuart(afko.getAuart());
                                    akl.setMatnr(afko.getPlnbez());
                                    akl.setQnum(list.get(i).getZdnum());
                                    akl.setFgnum(0l);
                                    akl.setItemsq(list.get(i).getItem());
                                    akl.setItemsf("");
                                    akl.setGamng(afko.getGamng());
                                    akl.setGstrp(afko.getGstrp());
                                    akl.setGltrp(afko.getGltrp());
                                    akl.setAuartxt(afko.getAuart_txt());
                                    akl.setZudnum(zudnum);
                                    afkolist.add(akl);
                                }
                            }
                       // }
                    }
                }else{
                    ResponseData responseData = new ResponseData();
                    responseData.setCode("E");
                    responseData.setSuccess(true);
                    responseData.setMessage("没有获取到机加流转卡！");
                    return responseData;
                }
            }

            if (afkolist.size() > 0){
                for(int i=0;i<afkolist.size();i++){
                    //根据物料查询尚未关闭的机加订单来作为不合格品处理的生产订单
                    //筛选条件 订单类型QP01 或QP04 订单不能为已关闭的订单
                    AfkoforZudlist afkoforZudlist = new AfkoforZudlist();
                    afkoforZudlist = service.selectForZudlist(afkolist.get(i).getMatnr());
                    afkolist.get(i).setAufnr(afkoforZudlist.getAufnr());
                    afkolist.get(i).setAuartxt(afkoforZudlist.getAuartxt());
                    afkolist.get(i).setAuart(afkoforZudlist.getAuart());
                    afkolist.get(i).setGltrp(afkoforZudlist.getGltrp());
                    afkolist.get(i).setGstrp(afkoforZudlist.getGstrp());

                }
            }
        }
        //根据zudlist查询生产订单
        return new ResponseData(afkolist);
    }

    @RequestMapping(value = {"/sap/afko/processZudlist"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData processZudlist(HttpServletRequest request,@RequestBody List<AfkoforZudlist> list){
        String createdBy = "" + request.getSession().getAttribute("userId");
        Long id = Long.parseLong(request.getSession().getAttribute("userId").toString());
        SYSUser user = userService.queryUserById(id);
        String employeecode = user.getUserName();
        ResponseData responseData = new ResponseData();
        String msg = "";
        if (list.size() > 0){
            Zudhead zh = new Zudhead();
            zh = zudheadService.selectByZudnum(list.get(0).getZudnum());
            String lineId = list.get(0).getLineId();
            Lines lines = linesService.selectById(Long.parseLong(lineId));
            Long PlineId = lines.getPlineId();
            if (PlineId == null){
                PlineId = Long.parseLong(lineId);
            }

            for (int i = 0;i<list.size();i++){
                String itemsq = list.get(i).getItemsq();
                String itemsf = list.get(i).getItemsf();
                String[] a = {""};
                String[] b = {""};
                if (itemsq != null){
                    if (itemsq.contains(",")){
                        a = itemsq.split(",");
                    }else{
                        a[0] = itemsq;
                    }
                    processZudlistQ(zh,a,createdBy);
                }
                if (itemsf != null){
                    if (itemsf.contains(",")){
                        b = itemsf.split(",");
                    }else{
                        b[0] = itemsf;
                    }
                    msg = processZudlistF(list.get(i).getAufnr(),zh,b,createdBy,employeecode,PlineId);
                }
            }
        }
        if (msg.equals("S")){
            responseData.setMessage("不合格品审理单处理完成");
            responseData.setCode("S");
        }else{
            responseData.setMessage("不合格品审理单处理完成，单有部分错误，请查询日志记录！");
            responseData.setCode("E");
        }
        return responseData;
    }
    public String processZudlistF(String aufnr,Zudhead zudhead,String[] items,String createdBy,String employeecode,Long PlineId){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
        Cardh cardh = new Cardh();
        DTBAOGONGParameters parameters = new DTBAOGONGParameters();
        cardh = cardhService.selectTop1(aufnr);
        Cardt cardt = new Cardt();
        cardt.setZpgdbar(cardh.getZpgdbar());
        cardt.setKtsch("21001");
        cardt = cardtService.selectByBarcodeAndKtsch(cardt);
        Double sum_rmnga = 0d;
        Double sum_xmnga = 0d;
        List<Zudlist> list = new ArrayList<>();
        for(int i=0;i<items.length;i++){
            Zudlist zudlist = new Zudlist();
            zudlist = zudlistService.selectByIdAndItem(zudhead.getZudnum(),items[i]);
            if (zudlist.getZqxdm().substring(0, 1).equals("M")){
                sum_rmnga = sum_rmnga + zudlist.getZdnum();
            }else{
                sum_xmnga = sum_xmnga + zudlist.getZdnum();
            }
            list.add(zudlist);
        }
        parameters.setPWERK("1001");
        parameters.setAUFNR(aufnr);
        parameters.setVORNR(cardt.getVornr());
        parameters.setBUDAT(curdate);
        parameters.setDATUM(curdate);
        parameters.setGMNGA("0");
        parameters.setRMNGA(sum_rmnga.toString());
        parameters.setXMNGA(sum_xmnga.toString());
        parameters.setZSCBC("");
        parameters.setZSCX(PlineId.toString());
        parameters.setZMNUM("");
        parameters.setZPGDBAR(cardh.getZpgdbar());
        parameters.setZPGDBH(cardh.getZpgdbh());
        parameters.setRSNUM("");
        parameters.setRSPOS("");
        parameters.setREVERSE("");
        parameters.setATTR1(createdBy);
        parameters.setATTR2("");
        parameters.setATTR3("");
        parameters.setATTR4("");//报工类别
        parameters.setATTR5("");
        parameters.setATTR6("");
        parameters.setATTR7("");
        parameters.setATTR8("");
        parameters.setATTR9("");
        parameters.setATTR10("");
        parameters.setATTR11("");
        parameters.setATTR12("");
        parameters.setATTR13("");
        parameters.setATTR14("");
        parameters.setATTR15("");
        parameters.setUSERNAME(createdBy);
        parameters.setZTPBAR("");
        parameters.setLSTVOR("X");
        parameters.setFSTVOR("");
        parameters.setZPRTP("4");
        parameters.setAUART(cardh.getAuart());
        parameters.setARBPL(cardt.getArbpl());
        parameters.setCHARG("");
        parameters.setEmployeeCode(employeecode);
        UUID uuid2 = java.util.UUID.randomUUID();
        parameters.setBGUUID(uuid2.toString());

        ConfirmationWebserviceUtilNew confirmationWebserviceUtilNew = new ConfirmationWebserviceUtilNew();
        DTBAOGONGReturnResult returnResult = new DTBAOGONGReturnResult();
        List<DTBAOGONGParametersitem> parametersitems = new ArrayList<>();
        DTBAOGONGParametersitem parametersitem = new DTBAOGONGParametersitem();
        parametersitem.setSUBRSNUM("");
        parametersitem.setSUBRSPOS("");
        parametersitem.setBDMNG("");
        parametersitem.setMATNR("");
        parametersitem.setCHARG("");
        parametersitem.setLGORT("");
        parametersitem.setMEINS("");
        parametersitem.setWERKS("");
        parametersitems.add(parametersitem);
        returnResult = confirmationWebserviceUtilNew.receiveConfirmation(parameters, parametersitems);
        Date inDate = new Date();
        Log log = new Log();
        Result result = new Result();
        InputLog inputLog = new InputLog();
        inputLog.setBarcode(parameters.getZPGDBAR());
        inputLog.setOrderno(parameters.getAUFNR());
        inputLog.setDispatch(parameters.getZPGDBAR());
        inputLog.setOperation(parameters.getVORNR());
        inputLog.setYeild(Double.parseDouble(parameters.getGMNGA()));
        inputLog.setWorkScrap(Double.parseDouble(parameters.getXMNGA()));
        inputLog.setRowScrap(Double.parseDouble(parameters.getRMNGA()));
        inputLog.setClassgrp(parameters.getZSCBC());
        inputLog.setLine(parameters.getZSCX());
        inputLog.setModelNo("");
        inputLog.setPlant(parameters.getPWERK());
        inputLog.setPostingDate(parameters.getBUDAT());
        inputLog.setDispatchLogicID(parameters.getZPGDBH());
        inputLog.setCreated_by(parameters.getUSERNAME());
        inputLog.setAttr1(parameters.getATTR1());
        inputLog.setAttr2(parameters.getATTR2());
        inputLog.setAttr3(parameters.getATTR3());
        inputLog.setAttr4(parameters.getATTR4());
        inputLog.setAttr5(parameters.getATTR5());
        inputLog.setAttr6(parameters.getATTR6());
        inputLog.setAttr7(parameters.getATTR7());
        inputLog.setUserName(parameters.getUSERNAME());
        inputLog.setMaterial(returnResult.getMATNR());
        inputLog.setMatDesc(returnResult.getMAKTX());
        inputLog.setBguuid(parameters.getBGUUID());
        inputLogService.insertInputLog(inputLog);
        Long id = inputLogService.selectNextId();
        result.setPlant(inputLog.getPlant());
        result.setInputId(id);
        result.setIsReversed("0");
        result.setMaterial(inputLog.getMaterial());
        result.setMatDesc(inputLog.getMatDesc());
        result.setCreated_by(inputLog.getCreated_by());
        result.setConfirmationNo(returnResult.getRSNUM());
        result.setConfirmationPos(returnResult.getRSPOS());
        result.setFevor(returnResult.getFEVOR());
        result.setFevorTxt(returnResult.getTXT());
        result.setOperationDesc(returnResult.getLTXA1());
        log.setMsgty(returnResult.getMSGTY());
        log.setMsgtx(returnResult.getMESSAGE());
        log.setTranType("0");
        log.setRefId(id);
        log.setCreationDate(inDate);
        log.setLastUpdateDate(new Date());
        log.setCreated_by(inputLog.getCreated_by());
        resultService.insertResult(result);
        logService.insertLog(log);
        List<InOutRecord> listinoutRecord = new ArrayList<>();
        if (returnResult.getMSGTY().equals("S")) {

            for(int i=0;i<list.size();i++){
                InOutRecord inOutRecord = new InOutRecord();
                inOutRecord = iInOutRecordService.selectById(list.get(i).getZqjjlh());
                inOutRecord.setReflag(2L);
                inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                inOutRecord.setLastUpdateDate(new Date());
                listinoutRecord.add(inOutRecord);


                list.get(i).setRueck(returnResult.getRSNUM());
                list.get(i).setRmzhl(returnResult.getRSPOS());
                list.get(i).setStatus("1");//已处理
                list.get(i).setLastUpdatedBy(Long.valueOf(createdBy));
                list.get(i).setLastUpdateDate(new Date());
                zudlistService.updateItem(list.get(i));

                UUID uuid = java.util.UUID.randomUUID();
                String uuidstr = uuid.toString().replaceAll("-", "");
                Zudlog zudlog = new Zudlog();
                zudlog.setInputlogid(id);
                zudlog.setId(uuidstr);
                zudlog.setZudnum(list.get(i).getZudnum());
                zudlog.setItem(list.get(i).getItem());
                zudlog.setMsgtx(returnResult.getMESSAGE());
                zudlog.setStatus("1");
                zudlog.setCreatedBy(Long.valueOf(createdBy));
                zudlog.setCreationDate(new Date());
                zudlogService.insertLog(zudlog);
            }

            iInOutRecordService.batchUpdateReflag(listinoutRecord);
            if (cardh.getQtysp() != null){
                cardh.setQtysp(Double.valueOf(sum_xmnga + cardh.getQtysp()));//?
            }else{
                cardh.setQtysp(Double.valueOf(sum_xmnga));
            }

            if (cardh.getQtysm() != null){
                cardh.setQtysm(Double.valueOf(sum_rmnga + cardh.getQtysm()));//?
            }else{
                cardh.setQtysm(Double.valueOf(sum_rmnga));
            }
            cardh.setLastUpdatedBy(Long.valueOf(createdBy));
            cardh.setLastUpdatedDate(new Date());
            List<Cardh> listcardh = new ArrayList<>();
            listcardh.add(cardh);
            cardhService.updateCardhStatus(listcardh);

        }else{
            for(int i=0;i<list.size();i++){
                UUID uuid = java.util.UUID.randomUUID();
                String uuidstr = uuid.toString().replaceAll("-", "");
                Zudlog zudlog = new Zudlog();
                zudlog.setInputlogid(id);
                zudlog.setId(uuidstr);
                zudlog.setZudnum(list.get(i).getZudnum());
                zudlog.setItem(list.get(i).getItem());
                zudlog.setMsgtx(returnResult.getMESSAGE());
                zudlog.setStatus("1");
                zudlog.setCreatedBy(Long.valueOf(createdBy));
                zudlog.setCreationDate(new Date());
                zudlogService.insertLog(zudlog);
            }
        }

        List<Zudlist> listunprocess = zudlistService.selectByZudnumForUnprocess(zudhead.getZudnum());
        if (listunprocess.size() == 0){
            //所有行均已处理 更新表头状态
            zudhead.setStatus("1");
            zudhead.setLastUpdateDate(new Date());
            zudhead.setLastUpdatedBy(Long.valueOf(createdBy));
            zudheadService.updateHead(zudhead);
        }

        if (returnResult.getMSGTY().equals("S")) {
            return "S";
        }else {
            return "E";
        }

    }

    public void processZudlistQ(Zudhead zudhead,String[] items,String createdBy){
        for (int i=0;i<items.length;i++){
            Zudlist zudlist = new Zudlist();
            zudlist = zudlistService.selectByIdAndItem(zudhead.getZudnum(),items[i]);
            Lines lines = linesService.selectById(Long.parseLong(zudlist.getLineId()));
            zudlist.setStatus("1");
            zudlist.setLastUpdateDate(new Date());
            zudlist.setLastUpdatedBy(Long.valueOf(createdBy));
            zudlistService.updateItem(zudlist);
            if (zudlist.getZctype().equals("1")){
                Dftrghlist dftrghlist = new Dftrghlist();
                String[] strs = zudlist.getZqjjlh().split("_");
                dftrghlist = dftrghlistService.selectByIdAndItem(strs[1],Long.parseLong(strs[2]));
                dftrghlist.setLastUpdatedBy(Long.valueOf(createdBy));
                dftrghlist.setCancelFlag("0");
                dftrghlist.setLastUpdateDate(new Date());
                List<Dftrghlist> listdf = new ArrayList<>();
                listdf.add(dftrghlist);
                dftrghlistService.batchUpdateCancelflag(listdf);
            }else{
                InOutRecord inOutRecord = iInOutRecordService.selectById(zudlist.getZqjjlh());
                inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                inOutRecord.setLastUpdateDate(new Date());
                inOutRecord.setReflag(0L);
                iInOutRecordService.updateReflag(inOutRecord);
            }
            Zudlog zudlog = new Zudlog();
            UUID uuid = java.util.UUID.randomUUID();
            String uuidstr = uuid.toString().replaceAll("-", "");
            zudlog.setId(uuidstr);
            zudlog.setZudnum(zudlist.getZudnum());
            zudlog.setItem(zudlist.getItem());
            zudlog.setMsgtx("还回取还件队列");
            zudlog.setStatus("1");
            zudlog.setCreatedBy(Long.valueOf(createdBy));
            zudlog.setCreationDate(new Date());
            zudlogService.insertLog(zudlog);


        }
    }

    @RequestMapping({"/sap/afko/queryyz"})
    @ResponseBody
    public ResponseData queryyz(Afko dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        if (dto.getGstrp() != null){
            dto.setGstrp(dto.getGstrp().substring(0,10));
        }

        if (dto.getGltrp() != null){
            dto.setGltrp(dto.getGltrp().substring(0,10));
        }
        List<Afko> list = service.selectYaZu(requestContext, dto, page, pageSize);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++)
            {
                double gamng_bd = 0.0D;
                double cansum = 0.0D;

                List<Cardh> cardhList = cardhService.selectByAufnr(list.get(i).getAufnr());
                if (cardhList.size() > 0) {
                    for (int j = 0; j < cardhList.size(); j++)
                    {
                        if (cardhList.get(j).getEcqty() == null ){
                            gamng_bd += cardhList.get(j).getMenge().doubleValue();
                        }else{
                            if (cardhList.get(j).getEcqty() == 0){
                                gamng_bd += cardhList.get(j).getMenge().doubleValue();
                            }else{
                                gamng_bd += cardhList.get(j).getEcqty().doubleValue();
                            }
                        }

                        if (j == 0) {
                            list.get(i).setMaxno(cardhList.get(j).getZpgdbh());
                        }
                    }
                } else {
                    gamng_bd = 0.0D;
                }
                list.get(i).setGamng_bd(Double.valueOf(gamng_bd));
                if ( list.get(i).getUmrez() == null){
                    cansum = 0;
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }else{
                    cansum = Math.ceil(((list.get(i)).getGamng().doubleValue() - gamng_bd) / ((list.get(i)).getUmrez().doubleValue()) );
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }

            }
        }
        return new ResponseData(list);
    }
    @RequestMapping({"/sap/afko/queryjj"})
    @ResponseBody
    public ResponseData queryjj(Afko dto, @RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request)
    {
        IRequest requestContext = createRequestContext(request);
        if (dto.getGstrp() != null){
            dto.setGstrp(dto.getGstrp().substring(0,10));
        }

        if (dto.getGltrp() != null){
            dto.setGltrp(dto.getGltrp().substring(0,10));
        }
        List<Afko> list = service.selectJiJa(requestContext, dto, page, pageSize);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++)
            {
                double gamng_bd = 0.0D;
                double cansum = 0.0D;

                List<Cardh> cardhList = cardhService.selectByAufnr(list.get(i).getAufnr());
                if (cardhList.size() > 0) {
                    for (int j = 0; j < cardhList.size(); j++)
                    {
                        gamng_bd += cardhList.get(j).getMenge().doubleValue();

                        if (j == 0) {
                            list.get(i).setMaxno(cardhList.get(j).getZpgdbh());
                        }
                    }
                } else {
                    gamng_bd = 0.0D;
                }
                list.get(i).setGamng_bd(Double.valueOf(gamng_bd));
                Sccl sccl = scclService.selectByMatnr(list.get(i).getPlnbez(),list.get(i).getWerks());
                if ( sccl == null){
                    cansum = 0;
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }else{
                    list.get(i).setUmrez(sccl.getZsccl());
                    cansum = Math.ceil(((list.get(i)).getGamng().doubleValue() - gamng_bd) / (sccl.getZsccl().doubleValue()) );
                    (list.get(i)).setCansum(Double.valueOf(cansum));
                }

            }
        }
        return new ResponseData(list);
    }

    @RequestMapping({"/sap/afko/queryZuhe"})
    @ResponseBody
    public  ResponseData queryZuhe(Afko dto,@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="10") int pageSize, HttpServletRequest request){
        IRequest requestContext = createRequestContext(request);
        List<Afko> list = service.selectZuhe(requestContext, dto, page, pageSize);
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                //获取机加物料描述
                String qmaktx = marcService.selectByMatnr(list.get(i).getQmatnr()).getMaktx();
                list.get(i).setQmaktx(qmaktx);

                double gamng_bd= 0.0D;
                double cansum = 0.0D;
                //获取压铸订单已经生产流转卡的数量
                List<Cardh> cardhList = cardhService.selectByAufnr(list.get(i).getAufnr());
                if (cardhList.size() > 0) {
                    for (int j = 0; j < cardhList.size(); j++)
                    {
                        gamng_bd += cardhList.get(j).getMenge().doubleValue();
                        if (j == 0) {
                            list.get(i).setMaxno(cardhList.get(j).getZpgdbh());
                        }
                    }
                } else {
                    gamng_bd = 0.0D;
                }
                list.get(i).setGamng_bd(Double.valueOf(gamng_bd));
                cansum = Math.ceil(((list.get(i)).getGamng().doubleValue() - gamng_bd) / ((list.get(i)).getUmrez().doubleValue()) );
                (list.get(i)).setCansum(Double.valueOf(cansum));
            }
        }
        return new ResponseData(list);
    }

    @RequestMapping({"/sap/afko/submit"})
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Afko> dto)
    {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping({"/sap/afko/remove"})
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Afko> dto)
    {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     *  根据生产订单号 查询SAP_AFKO  917110140 2019-04-16
     * @return
     */
    @RequestMapping({"/sap/afko/selectByAufnr"})
    @ResponseBody
    public ResponseData selectByAufnr(HttpServletRequest request){
        ResponseData rs = new ResponseData();
            String aufnr = request.getParameter("aufnr");
        String matnr = request.getParameter("matnr");

        Afko afko = new Afko();
        afko = service.selectByAufnr(aufnr);
        if (afko == null){
            rs.setSuccess(false);
            rs.setMessage("生产订单不存在！请重新输入生产订单号！");
        }else{
            String status = afko.getStatus();
            if (status.contains("REL"))
            {
                rs.setSuccess(false);
                rs.setMessage("该生产订单已关闭！请重新输入生产订单号！");
            }else{
                if (!afko.getPlnbez().equals(matnr)){
                    rs.setSuccess(false);
                    rs.setMessage("生产订单物料与不合格品审理单2物料不一致！请重新输入生产订单号！");
                }else{
                    List<Cardh> cardhList = new ArrayList<>();
                    cardhList = cardhService.selectByAufnr(aufnr);
                    if (cardhList.size() > 0){
                        rs.setSuccess(true);
                    }else{
                        rs.setSuccess(false);
                        rs.setMessage("该生产订单尚未生成工序流转卡，请更换生产订单处理！");
                    }

                }
            }
        }
        return rs;
    }
}
