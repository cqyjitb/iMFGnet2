package yj.core.batchpds.controllers;

import jdk.internal.util.xml.impl.Input;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import sun.reflect.annotation.ExceptionProxy;
import yj.core.batchpds.dto.Batchpdlogs;
import yj.core.batchpds.dto.Batchpdsource;
import yj.core.batchpds.service.IBatchpdlogsService;
import yj.core.batchpds.service.IBatchpdsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.webservice.dto.DTPP001ReturnResult;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
    public class BatchpdsourceController extends BaseController{

    @Autowired
    private IBatchpdsourceService service;

    @Autowired
    private IInputLogService inputLogService;

    @Autowired
    private IBatchpdlogsService batchpdlogsService;


    @RequestMapping(value = "/sap/batchpdsource/querybyflag")
    @ResponseBody
    public ResponseData query(Batchpdsource dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.querybyflag(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/sap/batchpdsource/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Batchpdsource> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/sap/batchpdsource/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Batchpdsource> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = "/dispatch/dopandian")
    @ResponseBody
    public ResponseData dopandian(HttpServletRequest request,@RequestBody List<Batchpdsource> dto){
            String createdBy = "" + request.getSession().getAttribute("userId");
            List<InputLog> list = new ArrayList();
            int num = 0;
            if (dto.size() > 0){
                for (int i = 0;i<dto.size();i++){
                    IRequest requestContext = createRequestContext(request);
                    Batchpdsource bs = new Batchpdsource();
                    Batchpdlogs logs = new Batchpdlogs();
                    num = num + 1;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String newPostingdate = df.format(new Date()).substring(0,10).replaceAll("-","");
//                    String newPostingdate = "20180731";
                   String dispatch = dto.get(i).getZpgdbar();
                   InputLog inputLogtmp = new InputLog();
                   inputLogtmp.setDispatch(dispatch);
                   String operation = inputLogService.queryDispatchMaxOperation(dispatch);
                   inputLogtmp.setOperation(operation);
                    logs.setZpgdbar(dispatch);
                    logs.setVornr(operation);
                   if (operation == null){
                       logs.setZpgdbar(dispatch);
                       logs.setVornr(operation);
                       logs.setPostflag("E");
                       logs.setWirteoffflag("E");
                       logs.setWirteoffmsg("派工单错误，或工序错误！");
                       batchpdlogsService.insert(requestContext,logs);
                       bs.setZpgdbar(dispatch);
                       bs.setZflag("E");
                       service.updateflag(bs);
                       continue;
                   }
                   InputLog wirteoffinput = new InputLog();
                   wirteoffinput = inputLogService.queryByDispatchAndOperation(inputLogtmp);
                   //先记录原始报工记录的合格品数量，报废品数量，为后续重新报工做准备。
                    if (wirteoffinput == null){
                        logs.setZpgdbar(dispatch);
                        logs.setVornr(operation);
                        logs.setPostflag("E");
                        logs.setWirteoffflag("E");
                        logs.setWirteoffmsg("派工单错误，或工序错误！");
                        batchpdlogsService.insert(requestContext,logs);
                        bs.setZpgdbar(dispatch);
                        bs.setZflag("E");
                        service.updateflag(bs);
                        continue;
                    }
                    Double yeild = wirteoffinput.getYeild();
                    Double work_scrap = wirteoffinput.getWorkScrap();
                    Double row_scrap = wirteoffinput.getRowScrap();

                   //查询到需要冲销的记录之后第一步 进行冲销
                    wirteoffinput.setPostingDate(newPostingdate);
                    DTPP001ReturnResult dtpp001ReturnResultcx = inputLogService.writeOffDispatch(wirteoffinput);

                    if (dtpp001ReturnResultcx.getMSGTY().equals("S")){
                        //冲销成功
                        //1：记录日志

                        try {
//                                Thread.sleep(2000);
                            TimeUnit.MILLISECONDS.sleep(2000);
                        }catch (Exception e){

                        }

                         logs.setAufnr(wirteoffinput.getOrderno());
                         logs.setMatnr(wirteoffinput.getMaterial());
                         logs.setWirteoffdate(newPostingdate);
                         logs.setWirteoffflag("S");
                         logs.setWirteoffmsg("冲销成功");
                        //2：重新报工
                        wirteoffinput.setYeild(0.0D);
                        wirteoffinput.setWorkScrap(yeild + work_scrap);
                        wirteoffinput.setRowScrap(row_scrap);
                        wirteoffinput.setPostingDate(newPostingdate);
                        wirteoffinput.setDispatchLogicID(wirteoffinput.getBarcode().substring(14,18));
                        wirteoffinput.setCreated_by(createdBy);
                        DTPP001ReturnResult dtpp001ReturnResultbg = inputLogService.inputDispatch(wirteoffinput);

                        if (dtpp001ReturnResultbg.getMSGTY().equals("S")){
                            //重新报工成功
                            logs.setPostdate(newPostingdate);
                            logs.setPostflag("S");
                            logs.setPostmsg("报工成功");

                            bs.setZpgdbar(dispatch);
                            bs.setZflag("S");
                            service.updateflag(bs);
                            try {
//                                Thread.sleep(2000);
                              TimeUnit.MILLISECONDS.sleep(2000);
                            }catch (Exception e){

                            }

                        }else{
                            logs.setPostdate(newPostingdate);
                            logs.setPostflag("E");
                            logs.setPostmsg("报工失败:"+dtpp001ReturnResultbg.getMESSAGE());
                            bs.setZpgdbar(dispatch);
                            bs.setZflag("E");
                            service.updateflag(bs);

                        }

                    }else{
                        //冲销失败，记录日志 进行下一条
                        logs.setWirteoffdate(newPostingdate);
                        logs.setWirteoffflag("E");
                        logs.setWirteoffmsg("冲销失败:"+dtpp001ReturnResultcx.getMESSAGE());
                        bs.setZpgdbar(dispatch);
                        bs.setZflag("E");
                        service.updateflag(bs);
                        batchpdlogsService.insert(requestContext,logs);
                        continue;
                    }
                    //日志写数据库

                    batchpdlogsService.insert(requestContext,logs);
                }
            }
            ResponseData rs = new ResponseData();
            rs.setMessage("共处理"+ num + "笔盘点记录！,具体查看批处理日志记录！");
            rs.setSuccess(true);
            return  rs;
        }
    }