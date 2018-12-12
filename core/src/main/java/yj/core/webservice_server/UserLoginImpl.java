package yj.core.webservice_server;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.mapper.UserMapper;
import com.hand.hap.account.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import yj.core.webservice_server.dto.Rec_User;
import yj.core.webservice_server.dto.ReturnMessage;
import com.hand.hap.account.exception.UserException;

import javax.jws.WebService;

@WebService(endpointInterface="yj.core.webservice_server.IuserLogin", serviceName="loginView")
public class UserLoginImpl implements IuserLogin {
    @Autowired IUserService iUserService;

    @Override
    public ReturnMessage loginView(Rec_User rec_user) {

        ReturnMessage rs = new ReturnMessage();
        User user = new User();
        user.setUserName(rec_user.getUserName());
        user.setPassword(rec_user.getPassWord());
        try {
            iUserService.login(user);
            rs.setFlag("S");
        } catch (UserException e) {
            rs.setFlag("E");
            rs.setMessage(e.getMessage());
        }

        return rs;
    }
}
