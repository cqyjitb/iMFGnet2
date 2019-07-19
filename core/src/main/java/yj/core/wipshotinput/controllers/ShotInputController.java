package yj.core.wipshotinput.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.wipshotinput.service.IShotInputService;

public class ShotInputController extends BaseController {

    @Autowired
    private IShotInputService service;
}
