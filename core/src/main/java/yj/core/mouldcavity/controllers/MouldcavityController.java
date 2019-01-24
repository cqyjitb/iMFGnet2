package yj.core.mouldcavity.controllers;

import com.hand.hap.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import yj.core.mouldcavity.service.IMouldcavityService;

@Controller
public class MouldcavityController extends BaseController {

@Autowired
private IMouldcavityService service;

}