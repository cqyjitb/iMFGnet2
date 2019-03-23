package yj.core.wiplockusers.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wiplockusers.dto.LockUsers;
import yj.core.wiplockusers.mapper.LockUsersMapper;
import yj.core.wiplockusers.service.ILockUsersService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LockUsersServiceImpl extends BaseServiceImpl<LockUsers> implements ILockUsersService {
    @Autowired
    private LockUsersMapper lockUsersMapper;

}