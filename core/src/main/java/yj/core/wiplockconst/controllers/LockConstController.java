package yj.core.wiplockconst.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.wiplockconst.dto.LockConst;
import yj.core.wiplockconst.service.ILockConstService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LockConstController extends BaseController {

    @Autowired
    private ILockConstService service;

    /**
     *
     * @param dto
     * @param page
     * @param pageSize
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/lock/const/queryLockConst")
    @ResponseBody
    public ResponseData queryLockConst(LockConst dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectFrompage(requestContext,dto,page,pageSize));
    }

    /**
     *
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lock/const/submitLockConst")
    @ResponseBody
    public ResponseData updateLockConst(HttpServletRequest request, @RequestBody List<LockConst> dto) {
        ResponseData rs =  new ResponseData();
        IRequest requestCtx = createRequestContext(request);
        String userName = "" + request.getSession().getAttribute("userName");
        String str = setMessage(dto);
        if(str == null){
            String result = service.updateOrInsert(requestCtx, dto,userName);
            rs.setMessage(result);
        }else{
            rs.setSuccess(false);
            rs.setMessage(str);
        }
        return rs;
    }

    /**
     *
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/lock/const/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<LockConst> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    /**
     *
     * @param dto
     * @return
     */
    String setMessage(List<LockConst> dto){
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                if(dto.get(i).getWerks() == null || dto.get(i).getWerks() == ""){
                    return "工厂不能为空";
                }else if(dto.get(i).getLineId() == null){
                    return "产线ID不能为空";
                }else if(dto.get(i).getConstType() == null || dto.get(i).getConstType() == ""){
                    return "常量类型不能为空";
                }else if(dto.get(i).getConstValue() == null || dto.get(i).getConstValue() == ""){
                    return "常量描述不能为空";
                }
            }
        }
        return null;
    }
}