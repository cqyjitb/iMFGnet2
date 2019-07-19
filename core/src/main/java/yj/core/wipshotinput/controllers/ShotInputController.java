package yj.core.wipshotinput.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotinput.service.IShotInputService;
import yj.core.wipshotnum.dto.Shotnum;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShotInputController extends BaseController {

    @Autowired
    private IShotInputService service;

    /**
     * 压射号异常明细表  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shot/input/queryShotInput")
    @ResponseBody
    public ResponseData queryShotInput(ShotInput dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        List<ShotInput> list = service.selectShotInput(dto);
        return new ResponseData(list);
    }
}
