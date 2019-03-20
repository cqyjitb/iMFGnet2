package yj.core.Quality.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QualityController extends BaseController {
    //质量追溯报表

    @RequestMapping(value = "/sap/XXX")
    @ResponseBody
    public ResponseData queryReport(HttpServletRequest request) {
        String unitCode = request.getParameter("unitCode");
        String lineId = request.getParameter("lineId");
        String matnr = request.getParameter("matnr");
        String tpcode = request.getParameter("tpcode");
        String zgjbar = request.getParameter("zgjbar");
        String jjgstrpbefor = request.getParameter("jjgstrpbefore");
        String jjgstrpafter = request.getParameter("jjgstrpafter");
        String jjbanz = request.getParameter("jjbanz");
        String jjcharg = request.getParameter("jjcharg");
        String yzgstrpbefor = request.getParameter("yzgstrpbefore");
        String yzgstrpafter = request.getParameter("yzgstrpafter");
        String yzbanz = request.getParameter("yzbanz");
        String yzcharg = request.getParameter("yzcharg");


        return null;
    }
}
