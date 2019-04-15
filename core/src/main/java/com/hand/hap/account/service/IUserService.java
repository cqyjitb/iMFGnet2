//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.account.service;

import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.system.service.IBaseService;
import java.util.List;

public interface IUserService extends IBaseService<User>, ProxySelf<IUserService> {
    User login(User var1) throws UserException;

    User updateByPrimaryKeySelective(IRequest var1, @StdWho User var2);

    User selectByUserName(String var1);

    void updatePassword(Long var1, String var2);

    void updateNotFirstLogin(Long var1, String var2);

    List<User> selectUsers(IRequest var1, User var2, int var3, int var4);

    List<IRole> selectUserAndRoles(IRequest var1, User var2, int var3, int var4);

    User selectById(Long userId);
}
