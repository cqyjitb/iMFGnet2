package yj.core.dispatch.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.dispatch.service.IInputLogService;

import java.util.List;

@Service
@Transactional
public class InputLogServiceImpl extends BaseServiceImpl<InputLog> implements IInputLogService{

    @Autowired
    InputLogMapper inputLogMapper;

     @Override

    //报功结果页面数据查询
     public  List<InputLog> queryAllBg(IRequest iRequest, InputLog inputLog, int page,int pageSize){
         PageHelper.startPage(page, pageSize);
         return inputLogMapper.queryAllBg(inputLog);
     };

    //报功日志界面数据查询
    public List<InputLog> queryAllLog(IRequest iRequest,InputLog inputLog,int page,int pageSize){
        PageHelper.startPage(page, pageSize);
        return  inputLogMapper.queryAllLog(inputLog);
    };


    //插入一条信息到confirmation_input_log
    public int insertInputLog(InputLog inputLog){
        return inputLogMapper.insertInputLog(inputLog);
    };

    //根据ID查询表格confirmation_input_log
    public int queryInputLogById(Long id){
        return inputLogMapper.queryInputLogById(id);
    };

}