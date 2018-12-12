package yj.core.zudlog.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zudlog.dto.Zudlog;

public interface IZudlogService extends IBaseService<Zudlog>, ProxySelf<IZudlogService>{
    /**
     *  插入日志 917110140
     * @param zudlog
     * @return
     */
    int insertLog(Zudlog zudlog);
}