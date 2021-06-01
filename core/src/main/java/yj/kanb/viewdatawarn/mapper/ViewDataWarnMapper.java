package yj.kanb.viewdatawarn.mapper;

import yj.kanb.viewdatawarn.dto.ViewDataWarn;

import java.util.List;

public interface ViewDataWarnMapper {
    List<ViewDataWarn> queryDataLastTime(ViewDataWarn data);

    int InsertViewDataWarn(ViewDataWarn data);


}
