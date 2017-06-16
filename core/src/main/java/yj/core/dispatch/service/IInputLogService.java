package yj.core.dispatch.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.InputLog;

import java.util.List;

public interface IInputLogService extends IBaseService<InputLog>, ProxySelf<IInputLogService>{

    List<InputLog> queryallBg();//报功结果页面数据查询

    int insertInputLog(InputLog inputLog);//插入一条信息打到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log
}