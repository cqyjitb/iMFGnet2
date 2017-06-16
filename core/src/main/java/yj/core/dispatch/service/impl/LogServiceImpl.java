package yj.core.dispath.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispath.dto.Log;
import yj.core.dispath.service.ILogService;

@Service
@Transactional
public class LogServiceImpl extends BaseServiceImpl<Log> implements ILogService{

}