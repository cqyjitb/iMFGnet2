package yj.core.qpcd.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.qpcd.dto.Qpcd;
import yj.core.qpcd.service.IQpcdService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QpcdServiceImpl extends BaseServiceImpl<Qpcd> implements IQpcdService{

}