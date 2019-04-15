//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.account.mapper.UserMapper;
import com.hand.hap.account.service.IRole;
import com.hand.hap.account.service.IRoleService;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.security.IUserSecurityStrategy;
import com.hand.hap.security.PasswordManager;
import com.hand.hap.security.service.impl.UserSecurityStrategyManager;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    @Autowired
    private PasswordManager passwordManager;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserSecurityStrategyManager userSecurityStrategyManager;
    @Autowired
    @Qualifier("roleServiceImpl")
    private IRoleService roleService;

    public UserServiceImpl() {
    }

    public User insertSelective(IRequest request, User record) {
        if (StringUtils.isEmpty(record.getPassword())) {
            record.setPassword(this.passwordManager.getDefaultPassword());
        }

        record.setPasswordEncrypted(this.passwordManager.encode(record.getPassword()));
        List<IUserSecurityStrategy> userSecurityStrategies = this.userSecurityStrategyManager.getUserAuthenticationList();

        IUserSecurityStrategy userSecurityStrategy;
        for(Iterator var4 = userSecurityStrategies.iterator(); var4.hasNext(); record = userSecurityStrategy.beforeCreateUser(request, record)) {
            userSecurityStrategy = (IUserSecurityStrategy)var4.next();
        }

        record = (User)super.insertSelective(request, record);
        this.userMapper.updatePassword(record.getUserId(), record.getPasswordEncrypted());
        return record;
    }

    public User login(User user) throws UserException {
        if (user != null && !org.apache.commons.lang3.StringUtils.isAnyBlank(new CharSequence[]{user.getUserName(), user.getPassword()})) {
            User user1 = this.userMapper.selectByUserName(user.getUserName());
            if (user1 == null) {
                throw new UserException("error.login.name_password_not_match", "error.login.name_password_not_match", (Object[])null);
            } else if ("LOCK".equals(user1.getStatus())) {
                throw new UserException("error.user.account_expired", "error.user.account_expired", (Object[])null);
            } else if (user1.getStartActiveDate() != null && user1.getStartActiveDate().getTime() > System.currentTimeMillis()) {
                throw new UserException("error.user.account_expired", "error.user.account_expired", (Object[])null);
            } else if (user1.getEndActiveDate() != null && user1.getEndActiveDate().getTime() < System.currentTimeMillis()) {
                throw new UserException("error.user.account_expired", "error.user.account_expired", (Object[])null);
            } else if (!this.passwordManager.matches(user.getPassword(), user1.getPasswordEncrypted())) {
                throw new UserException("error.login.name_password_not_match", "error.login.name_password_not_match", (Object[])null);
            } else {
                return user1;
            }
        } else {
            throw new UserException("error.login.name_password_not_match", "error.login.name_password_not_match", (Object[])null);
        }
    }

    public User selectByUserName(String userName) {
        return this.userMapper.selectByUserName(userName);
    }

    public User updateByPrimaryKeySelective(IRequest request, @StdWho User record) {
        this.userMapper.updateBasic(record);
        return record;
    }

    @Transactional
    public void updatePassword(Long userId, String password) {
        String passwordEncrypted = this.passwordManager.encode(password);
        this.userMapper.updatePassword(userId, passwordEncrypted);
    }

    public void updateNotFirstLogin(Long userId, String status) {
        this.userMapper.updateFirstLogin(userId, status);
    }

    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public List<User> selectUsers(IRequest request, User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.userMapper.selectUsers(user);
    }

    public List<IRole> selectUserAndRoles(IRequest request, User user, int pageNum, int pageSize) {
        List<IRole> roles = this.roleService.selectRolesByUser(request, user);
        return roles;
    }

    @Override
    public User selectById(String userId) {
        return userMapper.selectById(userId);
    }
}
