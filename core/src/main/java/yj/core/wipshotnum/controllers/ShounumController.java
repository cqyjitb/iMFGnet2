package yj.core.wipshotnum.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.service.IShotnumService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShounumController extends BaseController {

    @Autowired
    private IShotnumService service;

    /**
     * 压射号及压铸报工查询  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/queryShotnum")
    @ResponseBody
    public ResponseData queryShotnum(Shotnum dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        return new ResponseData(service.selectShotnum(dto,requestContext));
    }
}