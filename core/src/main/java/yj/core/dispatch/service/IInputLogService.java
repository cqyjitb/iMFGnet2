package yj.core.dispatch.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.InputLog;
import yj.core.webservice.dto.DTPP001ReturnResult;

public interface IInputLogService extends IBaseService<InputLog>, ProxySelf<IInputLogService>{
    public DTPP001ReturnResult inputDispatch(InputLog input);
}