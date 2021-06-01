package yj.kanb.viewdatawarn.service;

import yj.kanb.viewdatawarn.dto.ViewDataWarn;

import java.util.List;

public interface IViewDataWarnService {
    List<ViewDataWarn> queryData(ViewDataWarn data);

    int insertData(ViewDataWarn data);
}
