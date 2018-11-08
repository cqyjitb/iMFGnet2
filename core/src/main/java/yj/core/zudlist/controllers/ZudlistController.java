package yj.core.zudlist.controllers;

import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.service.IEmployeeService;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zudlist.service.IZudlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class ZudlistController extends BaseController{

    @Autowired
    private IZudlistService service;
    @Autowired
    private IEmployeeService employeeService;


    @RequestMapping(value = "/wip/zudlist/query")
    @ResponseBody
    public ResponseData query(Zudlist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/zudlist/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Zudlist> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/zudlist/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Zudlist> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
        /**
         *处理不合格品审理单1查询页面请求 918100064
         * @param dto
         * @param page
         * @param pageSize
         * @param request
         * @return
         */
        @RequestMapping(value = "/wip/zudlist/selectZudlist")
        @ResponseBody
        public ResponseData selectZudlist(Zudlist dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,String createdBy1,
                                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            if(createdBy1 != null && createdBy1 != ""){
                Employee employee = employeeService.queryByCode(createdBy1);
                dto.setCreatedBy(employee.getEmployeeId());
            }
            Zudlist zudlist  = resultFormat(dto);
            return new ResponseData(service.selectZudlist(requestContext,dto,page,pageSize));
        }

        /**
         *修改创建日期的方法 918100064
         * @param dto
         * @return
         */
        public Zudlist resultFormat(Zudlist dto){
            if ( dto.getCreationDateBefore() !=null){
                String cdBefore = dto.getCreationDateBefore().replace("00:00:00","23:59:59");
                dto.setCreationDateBefore(cdBefore);
            }
            return dto;
        }
    }