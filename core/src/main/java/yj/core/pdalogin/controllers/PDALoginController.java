package yj.core.pdalogin.controllers;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by libo on 2017/6/10.
 */
@Controller
public class PDALoginController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/yujiang/login",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData loginView(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        try {
            iUserService.login(user);
            return new ResponseData(true);
        } catch (UserException e) {
            ResponseData rd= new ResponseData(false);
            rd.setMessage(e.getMessage());
            return rd;
        }
    }

}
