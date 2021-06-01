package yj.kanb.viewdatawarn.service;

import yj.kanb.viewdatawarn.dto.ViewDataWarnUser;

import java.util.List;

public interface IViewDataWarnUserService {
    List<ViewDataWarnUser> queryViewDataWarnUser(ViewDataWarnUser user);
}
