package yj.core.qcaudithead.controllers;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;
import yj.core.qcaudithead.dto.Qcauditprocessheader;
import yj.core.qcaudithead.service.IQcauditheadService;
import yj.core.qcaudithead.service.IQcauditprocessheaderService;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;
import yj.core.qcauditlist.dto.recPageData;
import yj.core.qcauditlist.mapper.QcauditlistMapper;
import yj.core.qcauditlist.service.IQcauditlistService;
import yj.core.qcauditlist.service.IQcauditprocessdtlService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

    @Controller
    public class QcauditprocessheaderController extends BaseController{

    @Autowired
    private IQcauditprocessheaderService service;
    @Autowired
    private IQcauditlistService qcauditlistService;
    @Autowired
    private IQcauditheadService qcauditheadService;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private IQcauditprocessdtlService qcauditprocessdtlService;


    @RequestMapping(value = "/wip/qcauditprocessheader/query")
    @ResponseBody
    public ResponseData query(Qcauditprocessheader dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qcauditprocessheader/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Qcauditprocessheader> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qcauditprocessheader/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Qcauditprocessheader> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = "/wip/qcauditprocessheader/queryHeadAndDtl")
        @ResponseBody
        public ResponseData queryHeadAndDtl(HttpServletRequest request){
            ResponseData rs = new ResponseData();
            String werks = request.getParameter("werks");
            String recordid = request.getParameter("recordid");
            Qcauditprocessheader qh = new Qcauditprocessheader();
            qh = service.selectById(werks,recordid);
            List list = new ArrayList();
            List<Qcauditprocessdtl> listql = new ArrayList<>();
            listql = qcauditprocessdtlService.selectById(werks,recordid);

            if (qh != null){
                list.add(qh);
                rs.setCode("S");
            }else{
                rs.setCode("E");
            }

            if (listql.size() > 0){
                list.add(listql);
            }

            rs.setRows(list);
            rs.setSuccess(true);
            return rs;
        }


        @RequestMapping(value = {"/wip/qcauditprocessheader/saveData"}, method = {RequestMethod.POST})
        @ResponseBody
        public ResponseData saveData(HttpServletRequest request ,@RequestBody List<recPageData> a){
            ResponseData rs = new ResponseData();
            List<Qcauditlist> list = new ArrayList<>();
            List<Qcauditprocessdtl> list2 = new ArrayList<>();
            Qcauditprocessheader qcauditprocessheader = new Qcauditprocessheader();
            Double confirmnum = a.get(0).getConfirmnum();

            if (a.size() > 0) {
                for (int i = 0; i < a.size(); i++) {
                    Qcauditlist qcauditlist = new Qcauditlist();
                    qcauditlist.setRecordid(a.get(i).getRecordid());
                    qcauditlist.setWerks(a.get(i).getWerks());
                    qcauditlist.setItem(a.get(i).getItem());
                    list.add(qcauditlist);
                }
            }

            //准备行数据
            if (list.size() > 0){
                Qcauditlist qcauditlist = new Qcauditlist();

                for (int i=0;i< list.size();i++){
                    Qcauditprocessdtl qcauditprocessdtl = new Qcauditprocessdtl();
                    qcauditlist = qcauditlistService.selectBatch(list.get(i).getWerks(),list.get(i).getRecordid(),list.get(i).getItem());
                    qcauditprocessdtl.setGmein(qcauditlist.getGmein());
                    qcauditprocessdtl.setDiecd(qcauditlist.getDiecd());
                    qcauditprocessdtl.setDfectQty(qcauditlist.getDfectQty());
                    qcauditprocessdtl.setCharg(qcauditlist.getCharg());
                    qcauditprocessdtl.setCode(qcauditlist.getCode());
                    qcauditprocessdtl.setGstrp(qcauditlist.getGstrp());
                    qcauditprocessdtl.setItem(qcauditlist.getItem());
                    qcauditprocessdtl.setMatnr(qcauditlist.getMatnr());
                    qcauditprocessdtl.setMatnr2(qcauditlist.getMatnr2());
                    qcauditprocessdtl.setMenge(qcauditlist.getMenge());
                    qcauditprocessdtl.setRecordid(qcauditlist.getRecordid());
                    qcauditprocessdtl.setReflag(qcauditlist.getReflag());
                    qcauditprocessdtl.setSfflg(qcauditlist.getSfflg());
                    qcauditprocessdtl.setShift(qcauditlist.getShift());
                    qcauditprocessdtl.setTlevelcode(qcauditlist.getTlevelcode());
                    qcauditprocessdtl.setWerks(qcauditlist.getWerks());
                    qcauditprocessdtl.setYcharg(qcauditlist.getYcharg());
                    qcauditprocessdtl.setYgstrp(qcauditlist.getYgstrp());
                    qcauditprocessdtl.setYshift(qcauditlist.getYshift());
                    qcauditprocessdtl.setYzbanc(qcauditlist.getYzbanc());
                    qcauditprocessdtl.setZbanc(qcauditlist.getZbanc());
                    qcauditprocessdtl.setZgjbar(qcauditlist.getZgjbar());
                    qcauditprocessdtl.setZpgdbar(qcauditlist.getZpgdbar());
                    qcauditprocessdtl.setZqjjlh(qcauditlist.getZqjjlh());
                    qcauditprocessdtl.setZxhbar(qcauditlist.getZxhbar());
                    list2.add(qcauditprocessdtl);
                }

                //准备表头数据
                qcauditprocessheader = new Qcauditprocessheader();
                qcauditprocessheader.setWerks(list.get(0).getWerks());
                qcauditprocessheader.setConfirmQty(confirmnum);
                qcauditprocessheader.setRecordid(list.get(0).getRecordid());
            }

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            //将信息更新到表中
            try {
                int num1 = service.updateData(qcauditprocessheader);

                           qcauditprocessdtlService.deleteById(qcauditprocessheader.getWerks(),qcauditprocessheader.getRecordid());
                int num2 = qcauditprocessdtlService.insertData(list2);


                if (num2 == list2.size() && num1 == 1) {
                    transactionManager.commit(status);
                    rs.setSuccess(true);
                    rs.setMessage("合格品审理单2数据保存成功！");
                } else {
                    transactionManager.rollback(status);
                    rs.setMessage("合格品审理单2数据保存失败！");
                    rs.setSuccess(false);
                }
            } catch (Exception e) {
                transactionManager.rollback(status);
                rs.setMessage("合格品审理单2数据保存失败！");
                rs.setSuccess(false);
            }
            return rs;
        }
    }