package yj.core.qcauditlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.service.IQcauditlistService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QcauditlistServiceImpl extends BaseServiceImpl<Qcauditlist> implements IQcauditlistService{

}