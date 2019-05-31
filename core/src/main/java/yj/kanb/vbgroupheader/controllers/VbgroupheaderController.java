package yj.kanb.vbgroupheader.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;
import yj.kanb.vbgroupheader.service.IVbgroupheaderService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class VbgroupheaderController extends BaseController {
    @Autowired
    private IVbgroupheaderService service;

    @RequestMapping(value = {"/groupH/queryGroupH"})
    @ResponseBody
    public ResponseData queryGroupH(HttpServletRequest request, Vbgroupheader dto){

        return new ResponseData();
    }
}
