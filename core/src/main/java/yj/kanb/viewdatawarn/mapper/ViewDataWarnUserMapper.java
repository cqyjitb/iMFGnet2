package yj.kanb.viewdatawarn.mapper;

import yj.kanb.viewdatawarn.dto.ViewDataWarnUser;

import java.util.List;

public interface ViewDataWarnUserMapper {
    List<ViewDataWarnUser> queryBylevelandType(ViewDataWarnUser user);
}
