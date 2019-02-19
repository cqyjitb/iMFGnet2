package yj.core.fevor.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.fevor.service.IFevorService;

@Controller
public class FevorController extends BaseController {
    @Autowired
    private IFevorService service;

}
