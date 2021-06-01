package yj.kanb.viewdatawarn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.viewdatawarn.dto.ViewDataWarnUser;
import yj.kanb.viewdatawarn.mapper.ViewDataWarnUserMapper;
import yj.kanb.viewdatawarn.service.IViewDataWarnUserService;

import java.util.List;
@Service
@Transactional
public class ViewDataWarnUserServiceImpl implements IViewDataWarnUserService {
    @Autowired
    private ViewDataWarnUserMapper userMapper;
    @Override
    public List<ViewDataWarnUser> queryViewDataWarnUser(ViewDataWarnUser user) {
        return userMapper.queryBylevelandType(user);
    }
}
