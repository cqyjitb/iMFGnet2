package yj.core.qcaudithead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.qcaudithead.dto.Qcauditprocessheader;
import yj.core.qcaudithead.service.IQcauditprocessheaderService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QcauditprocessheaderServiceImpl extends BaseServiceImpl<Qcauditprocessheader> implements IQcauditprocessheaderService{

}