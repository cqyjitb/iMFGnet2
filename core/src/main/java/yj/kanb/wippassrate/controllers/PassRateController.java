package yj.kanb.wippassrate.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.kanb.wippassrate.service.IPassRateService;

@Controller
public class PassRateController extends BaseController {
    @Autowired
    private IPassRateService service;

}
