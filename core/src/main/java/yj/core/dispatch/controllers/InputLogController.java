package yj.core.dispatch.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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


        return new ResponseData(service.queryAllLog(requestContext,dto,page,pageSize));
    }


    /*
    *报功结果/报功冲销界面查询
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

        List<InputLog> list = service.queryAllBg(requestContext,dto,page,pageSize);
/*

        for (InputLog i:list
             ) {
            System.out.println(i.getPostingDate());
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String d = dateFormat.format(i.getPostingDate());
            System.out.println(d);
            System.out.println(i);
        }
*/

        return new ResponseData(list);
    }


@RequestMapping(value = "/confirmation/input/log/submit")
@ResponseBody
public ResponseData update(HttpServletRequest request, BindingResult result, @RequestBody List<InputLog> dto){
    getValidator().validate(dto, result);
    if (result.hasErrors()) {
        ResponseData rd = new ResponseData(false);
        rd.setMessage(getErrorMessage(result, request));
        return rd;
    }
    IRequest requestCtx = createRequestContext(request);
    return new ResponseData(service.batchUpdate(requestCtx, dto));
}


}