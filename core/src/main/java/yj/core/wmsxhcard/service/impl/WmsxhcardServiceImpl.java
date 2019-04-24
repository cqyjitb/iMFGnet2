package yj.core.wmsxhcard.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.wmsxhcard.dto.Wmsxhcard;
import yj.core.wmsxhcard.service.IWmsxhcardService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WmsxhcardServiceImpl extends BaseServiceImpl<Wmsxhcard> implements IWmsxhcardService{

}