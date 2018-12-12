package yj.core.zudlog.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zudlog.dto.Zudlog;

public interface ZudlogMapper extends Mapper<Zudlog>{
    /**
     *  插入日志 917110140
     * @param zudlog
     * @return
     */
    int insertLog(Zudlog zudlog);
}