package yj.kanb.vbline.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbline.dto.Vbline;
import yj.kanb.vbline.service.IVblineService;
@Service
@Transactional
public class VblineServiceImpl extends BaseServiceImpl<Vbline> implements IVblineService {
}
