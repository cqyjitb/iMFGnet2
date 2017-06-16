package yj.core.dispatch.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.InputLog;

public interface IInputLogService extends IBaseService<InputLog>, ProxySelf<IInputLogService>{
    public boolean inputDispatch(InputLog input);
}