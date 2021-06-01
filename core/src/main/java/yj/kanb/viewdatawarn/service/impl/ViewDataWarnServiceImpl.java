package yj.kanb.viewdatawarn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.viewdatawarn.dto.ViewDataWarn;
import yj.kanb.viewdatawarn.mapper.ViewDataWarnMapper;
import yj.kanb.viewdatawarn.service.IViewDataWarnService;

import java.util.List;
@Service
@Transactional
public class ViewDataWarnServiceImpl implements IViewDataWarnService {
    @Autowired
    private ViewDataWarnMapper mapper;
    @Override
    public List<ViewDataWarn> queryData(ViewDataWarn data) {
        return mapper.queryDataLastTime(data);
    }

    @Override
    public int insertData(ViewDataWarn data) {
        return mapper.InsertViewDataWarn(data);
    }
}
