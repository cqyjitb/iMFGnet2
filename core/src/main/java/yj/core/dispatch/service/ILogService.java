package yj.core.dispatch.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.webservice.dto.DTPP001ReturnResult;

public interface ILogService extends IBaseService<Log>, ProxySelf<ILogService>{
    public DTPP001ReturnResult chargeAgainstConnector(InputLog input);

}