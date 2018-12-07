package yj.core.qcaudithead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcaudithead.service.IQcauditheadService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QcauditheadServiceImpl extends BaseServiceImpl<Qcaudithead> implements IQcauditheadService{

}