package yj.core.dispath.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispath.dto.InputLog;
import yj.core.dispath.mapper.InputLogMapper;
import yj.core.dispath.service.IInputLogService;

@Service
@Transactional
public class InputLogServiceImpl extends BaseServiceImpl<InputLog> implements IInputLogService{

    @Autowired
    InputLogMapper inputLogMapper;

  //  @Override



}