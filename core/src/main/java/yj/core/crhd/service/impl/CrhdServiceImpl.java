package yj.core.crhd.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.crhd.dto.Crhd;
import yj.core.crhd.service.ICrhdService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CrhdServiceImpl extends BaseServiceImpl<Crhd> implements ICrhdService{

}