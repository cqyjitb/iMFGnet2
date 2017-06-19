package yj.core.dispatch.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.InputLog;
import yj.core.webservice.dto.DTPP001ReturnResult;

import java.util.List;

public interface IInputLogService extends IBaseService<InputLog>, ProxySelf<IInputLogService>{

    List<InputLog> queryAllBg(IRequest iRequest, InputLog inputLog, int page, int pageSize);//报功结果&报功冲销页面数据查询

    List<InputLog> queryAllLog(IRequest iRequest, InputLog inputLog, int page, int pageSize);//报功日志界面数据查询

    int insertInputLog(InputLog inputLog);//插入一条信息到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log

    public DTPP001ReturnResult inputDispatch(InputLog input);

    public void nextId(InputLog input);

    public DTPP001ReturnResult returnResultAndUpdateConfirmation(InputLog input);

}