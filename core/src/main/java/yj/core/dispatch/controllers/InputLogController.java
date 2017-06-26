package yj.core.dispatch.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.webservice.dto.DTPP001ReturnResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InputLogController extends BaseController{

    @Autowired
    private IInputLogService service;

    /*
    *报功日志界面查询
     */
    @RequestMapping(value = "/confirmation/input/log/queryLog")
    @ResponseBody
    public ResponseData queryLog(final InputLog dto, @RequestParam(defaultValue = DEFAULT_PAGE)final int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE)final int pageSize, final HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);

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


        List <InputLog> list = service.queryAllLog(requestContext,dto,page,pageSize);
        int s = 0;
        int e = 0 ;

        for (int i=0 ; i<list.size();i++){
            if (list.get(i).getMsgty().equals("S")){
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

        session.setAttribute("sucess", s);

        session.setAttribute("flase", e);


        return new ResponseData(list);
    }



    @RequestMapping(value = "/test/form")
    @ResponseBody
    public ResponseData query(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        int s =  (int)session.getAttribute("sucess");

        int e =  (int)session.getAttribute("flase");

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

        List<InputLog> list = service.queryAllWriteOff(requestContext,dto,page,pageSize);

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

        List<InputLog> list = service.queryAllResult(requestContext,dto,page,pageSize);


        for (int i=0 ; i<list.size();i++){
            if(list.get(i).getIsReversed().equals("0")){
                list.get(i).setIsReversed("正常");
            }else {
                list.get(i).setIsReversed("已冲销");
            }
        }


        return new ResponseData(list);
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

        List<DTPP001ReturnResult> list = new ArrayList<>();
        DTPP001ReturnResult returnResult = service.inputDispatch(inputLog);
        list.add(returnResult);
        return new ResponseData(list);
    }

    @RequestMapping(value = {"/confirmation/input/log/writeOff"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData writeOffDispatch( @RequestBody InputLog inputLog,HttpServletRequest request) {
        String createdBy = "" + request.getSession().getAttribute("userId");
        System.out.println(createdBy);
        inputLog.setCreated_by(createdBy);
        List<DTPP001ReturnResult> list = new ArrayList<>();
        DTPP001ReturnResult returnResult = service.writeOffDispatch(inputLog);
        list.add(returnResult);
        return new ResponseData(list);
    }




}