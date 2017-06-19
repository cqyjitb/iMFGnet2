package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dispatch.dto.InputLog;
import java.util.List;

public interface InputLogMapper extends Mapper<InputLog>{

    List<InputLog> queryAllBg(InputLog inputLog);//报功结果&报功冲销页面数据查询

    List<InputLog> queryAllLog(InputLog inputLog);//报功日志页面数据查询

    int insertInputLog(InputLog inputLog);//插入一条信息到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log

    /**
     * 获取当前报工工序历史信息
     * @param inputLog 报工信息
     * @return
     */
    public List<InputLog> confirmationInfoByOrdernoAndOperation(InputLog inputLog);

    /**
     * 获取前工序成功报工信息
     * @param inputLog 报工信息
     * @return
     */
    public List<InputLog> confirmationMaxOperationInfo(InputLog inputLog);

    Long selectNextId();
}