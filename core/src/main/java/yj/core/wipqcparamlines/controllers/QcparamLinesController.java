package yj.core.wipqcparamlines.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.wipqcparamlines.dto.QcparamLines;
import yj.core.wipqcparamlines.dto.selectPageData;
import yj.core.wipqcparamlines.service.IQcparamLinesService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

    @Controller
    public class QcparamLinesController extends BaseController{

    @Autowired
    private IQcparamLinesService service;


    @RequestMapping(value = "/wip/qcparam/lines/query")
    @ResponseBody
    public ResponseData query(QcparamLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/qcparam/lines/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<QcparamLines> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/qcparam/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<QcparamLines> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        /**
         *  根据生产线ID 查询不合格品审理单2配置表 917110140
         * @param request
         * @return
         */
    @RequestMapping(value = {"/wip/qcparam/lines/selectByLineId"},method = {RequestMethod.POST})
    @ResponseBody
        public ResponseData selectByLineId(HttpServletRequest request,@RequestBody List<selectPageData> a){
        ResponseData rs = new ResponseData();
        List<QcparamLines> list = new ArrayList();
        String line_id = a.get(0).getLine_id();
        String werks = a.get(0).getWerks();

        QcparamLines qcparamLines = service.selectForYz(Long.parseLong(line_id),werks);
        if (qcparamLines != null){
            list.add(qcparamLines);
            rs.setSuccess(true);
            rs.setRows(list);
            return rs;
        }else{
            rs.setSuccess(false);
            rs.setMessage("没有获取到当前产线的配置配置信息！");
            return rs;
        }

        }

        /**
         * 在制队列预警查询页面请求 918100064
         * @param request
         * @param deptId
         * @param lineId
         * @return
         */
        @RequestMapping(value = {"/wip/qcparam/lines/selectByScale"},method = {RequestMethod.POST})
        @ResponseBody
        public ResponseData selectByScale(HttpServletRequest request,String deptId,String lineId){
            IRequest requestContext = createRequestContext(request);
            if(lineId == null){
                return new ResponseData(service.selectByScale(deptId,null));
            }else{
                return new ResponseData(service.selectByScale(deptId,Long.parseLong(lineId)));
            }
        }
    }