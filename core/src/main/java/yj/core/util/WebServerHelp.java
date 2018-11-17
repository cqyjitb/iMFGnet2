package yj.core.util;

import java.util.Map;

public class WebServerHelp {

    private String username;
    private String password;

    public WebServerHelp(){
        //dev
//        this.username = "HAPUSER";
//        this.password = "Yjsap123@CQ";
        //prd
          this.username = "HAPUSER";
          this.password = "YJhap201707@CQ";

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
