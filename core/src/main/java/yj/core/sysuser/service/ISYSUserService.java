package yj.core.sysuser.service;

import yj.core.sysuser.dto.SYSUser;

public interface ISYSUserService {
    SYSUser queryUserById(Long id);
}
