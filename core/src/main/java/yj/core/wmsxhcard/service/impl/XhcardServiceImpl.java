package yj.core.wmsxhcard.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.wmsxhcard.dto.Xhcard;
import yj.core.wmsxhcard.service.IXhcardService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class XhcardServiceImpl extends BaseServiceImpl<Xhcard> implements IXhcardService{

}