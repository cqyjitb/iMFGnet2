package yj.core.qcauditlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;
import yj.core.qcauditlist.service.IQcauditprocessdtlService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QcauditprocessdtlServiceImpl extends BaseServiceImpl<Qcauditprocessdtl> implements IQcauditprocessdtlService{

}