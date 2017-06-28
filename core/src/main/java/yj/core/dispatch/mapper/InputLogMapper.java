package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.dispatch.dto.InputLog;
import java.util.List;

public interface InputLogMapper extends Mapper<InputLog>{

    List<InputLog> queryAllWriteOff(InputLog inputLog);//报功冲销页面数据查询

    List<InputLog> queryAllLog(InputLog inputLog);//报功日志页面数据查询

    List<InputLog> queryAllResult(InputLog inputLog);//报工结果

    int insertInputLog(InputLog inputLog);//插入一条信息到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log

    /**
     * 获取当前报工工序历史信息
     * @param inputLog 报工信息
     * @return
     */
    public List<InputLog> confirmationInfoByOrdernoAndOperation(InputLog inputLog);

    /**
     * 获取前工序最大工序
     * @param inputLog 报工信息
     * @return
     */
    String confirmationBeforeMaxOperation(InputLog inputLog);

    /**
     * 存在最大工序时，按派工单号和工序号查询信息
     * @param dispatch 派工单号
     * @param operation 工序号
     * @return
     */
    List<InputLog> confirmationExistMaxOperaInfo(@Param("dispatch") String dispatch, @Param("operation") String operation);

    Long selectNextId();

    List<InputLog> queryAllGTOperation(InputLog inputLog);
}