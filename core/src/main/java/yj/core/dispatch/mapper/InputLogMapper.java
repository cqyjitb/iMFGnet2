package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dispatch.dto.InputLog;
import java.util.List;

public interface InputLogMapper extends Mapper<InputLog>{

    List<InputLog> queryAllBg(InputLog inputLog);//报功结果页面数据查询

    int insertInputLog(InputLog inputLog);//插入一条信息到confirmation_input_log

    int queryInputLogById(Long id);//根据ID查询表格confirmation_input_log

}