package yj.core.wipcgroupplan.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import yj.core.wipcgroupplan.dto.CgroupPlan;
import yj.core.wipcgroupplan.service.ICgroupPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

    @Controller
    public class CgroupPlanController extends BaseController{

    @Autowired
    private ICgroupPlanService service;

        /**
         * 产线组生产计划维护页面查询请求 918100064
         * @param dto
         * @param request
         * @return
         */
    @RequestMapping(value = "/wip/cgroup/plan/queryCgroupPlan")
    @ResponseBody
    public ResponseData queryCgroupPlan(CgroupPlan dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectCgroupPlan(dto,requestContext));
    }

        /**
         * 产线组生产计划维护页面添加及修改请求 918100064
         * @param request
         * @param dto
         * @return
         */
    @RequestMapping(value = "/wip/cgroup/plan/submitCgroupPlan")
    @ResponseBody
    public ResponseData updateCgroupPlan(HttpServletRequest request,@RequestBody List<CgroupPlan> dto){
        IRequest requestCtx = createRequestContext(request);
        ResponseData rs = new ResponseData();
        String userId ="" + request.getSession().getAttribute("userId");
        if (dto.size() > 0){
            for (int i=1;i>=0;i--){
                CgroupPlan cgroupPlan = dto.get(i);
                String result = service.insertOrUpdate(cgroupPlan,userId,requestCtx);
                if (result == null){
                    cgroupPlan.setRemarks("保存成功！");
                }else {
                    cgroupPlan.setRemarks("保存失败！");
                    rs.setSuccess(false);
                    rs.setMessage(result);
                }
            }
            rs.setRows(dto);
        }
        return rs;
    }

        /**
         * 产线组生产计划维护页面删除请求 918100064
         * @param request
         * @param dto
         * @return
         */
    @RequestMapping(value = "/wip/cgroup/plan/removeCgroupPlan")
    @ResponseBody
    public ResponseData deleteCgroupPlan(HttpServletRequest request,@RequestBody List<CgroupPlan> dto){
        if (dto.size() > 0){
            for (int i=0;i<dto.size();i++){
                service.deleteCgroupPlan(dto.get(i));
            }
        }
        return new ResponseData();
    }
    }