package yj.core.sysuser.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.sysuser.dto.SYSUser;
import yj.core.sysuser.mapper.SYSUserMapper;
import yj.core.sysuser.service.ISYSUserService;
@Service
@Transactional
public class SYSUserServiceImpl extends BaseServiceImpl<SYSUser> implements ISYSUserService {
    @Autowired
    private SYSUserMapper userMapper;
    @Override
    public SYSUser queryUserById(Long id) {
        return userMapper.queryUserById(id);
    }
}
