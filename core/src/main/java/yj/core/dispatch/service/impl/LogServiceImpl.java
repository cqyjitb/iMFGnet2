package yj.core.dispatch.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.dispatch.dto.Log;
import yj.core.dispatch.service.ILogService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService{

}