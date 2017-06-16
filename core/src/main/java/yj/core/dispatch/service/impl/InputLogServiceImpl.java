package yj.core.dispatch.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.webservice.components.ConfirmationWebserviceUtil;
import yj.core.webservice.dto.DTPP001ReturnResult;

@Service
@Transactional
public class InputLogServiceImpl extends BaseServiceImpl<InputLog> implements IInputLogService{

    @Autowired
    private ConfirmationWebserviceUtil webserviceUtil;

    @Override
    public boolean inputDispatch(InputLog input) {
        Boolean flag = false;
        DTPP001ReturnResult returnResult = webserviceUtil.receiveConfirmation(input);
        if("S".equals(returnResult.getMSGTY())){
            //HAP添加数据
            flag = true;
        }else{
            //只存log
        }
        return flag;
    }
}