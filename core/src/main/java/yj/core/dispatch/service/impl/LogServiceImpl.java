package yj.core.dispatch.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.service.ILogService;
import yj.core.webservice.components.ConfirmationWebserviceUtil;
import yj.core.webservice.dto.DTPP001ReturnResult;

@Service
@Transactional
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService{

    @Autowired
    ConfirmationWebserviceUtil webserviceUtil;

    @Override
    public DTPP001ReturnResult chargeAgainstConnector(InputLog input) {
        DTPP001ReturnResult returnResult = webserviceUtil.receiveConfirmation(input);
        //hap表数据修改
        return returnResult;
    }
}