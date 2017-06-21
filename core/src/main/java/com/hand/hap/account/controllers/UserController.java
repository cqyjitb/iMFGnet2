//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.account.controllers;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    public UserController() {
    }

    @RequestMapping(
            value = {"/sys/user/query"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public ResponseData selectUsers(HttpServletRequest request, @ModelAttribute User user, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pagesize) {
        IRequest iRequest = this.createRequestContext(request);
        return new ResponseData(this.userService.selectUsers(iRequest, user, page, pagesize));
    }

    @RequestMapping(
            value = {"/sys/user/submit"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public ResponseData submitUsers(@RequestBody List<User> users, BindingResult result, HttpServletRequest request) throws BaseException {
        this.getValidator().validate(users, result);
        if(result.hasErrors()) {
            ResponseData requestCtx1 = new ResponseData(false);
            requestCtx1.setMessage(this.getErrorMessage(result, request));
            return requestCtx1;
        } else {
            IRequest requestCtx = this.createRequestContext(request);
            this.userService.batchUpdate(requestCtx, users);
            return new ResponseData(users);
        }
    }

    @RequestMapping(
            value = {"/sys/user/remove"},
            method = {RequestMethod.POST}
    )
    public ResponseData remove(@RequestBody List<User> users) throws BaseException {
        this.userService.batchDelete(users);
        return new ResponseData(users);
    }

    @RequestMapping(
            value = {"/sys/userrole/query"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public ResponseData selectUserAndRoles(HttpServletRequest request, @ModelAttribute User user, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pagesize) {
        IRequest iRequest = this.createRequestContext(request);
        List users = this.userService.selectUsers(iRequest, user, page, pagesize);
        return users != null && users.size() != 0?new ResponseData(this.userService.selectUserAndRoles(iRequest, (User)users.get(0), page, pagesize)):new ResponseData((List)null);
    }

    @RequestMapping(value = {"/sys/userrole/queryByUserName"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectByUserName(HttpServletRequest request) {
        String username = (String) request.getParameter("username");
        List<User> list = new ArrayList<>();
        User u = userService.selectByUserName(username);
        list.add(u);
        return new ResponseData(list);
    }

}
