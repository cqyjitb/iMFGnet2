package yj.core.fevor.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.service.IFevorService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FevorController extends BaseController {
    @Autowired
    private IFevorService service;

    @RequestMapping(value = {"/sap/fevor/selectFevor2"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectfevor2(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        List<Fevor> list = new ArrayList<>();
        list = service.selectFevor2("");
        if (list.size() > 0){
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }

}
