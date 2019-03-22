package yj.core.wiplockusers.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.wiplockusers.dto.LockUsers;
import yj.core.wiplockusers.service.ILockUsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LockUsersController extends BaseController {

    @Autowired
    private ILockUsersService service;

}