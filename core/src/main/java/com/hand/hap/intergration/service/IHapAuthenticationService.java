package com.hand.hap.intergration.service;


/**
 * Created by Qixiangyu on 2017/3/29.
 */
public interface IHapAuthenticationService {

    /**
     * get oauth2 token by interface define
     */
    String getToken(AuthenticationAdapter authenticationAdapter);

    /**
     * update oauth2 access_token (Stored in redis) return access_token ,if
     * update sucess ，else return null
     */
    String updateToken(AuthenticationAdapter authenticationAdapter);

}
