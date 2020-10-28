package yj.core.wipshotnumadd.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.wipshotnumadd.service.IShotnumAddService;

@Controller
public class ShotnumAddControllers extends BaseController {

    @Autowired
    private IShotnumAddService service;

}
