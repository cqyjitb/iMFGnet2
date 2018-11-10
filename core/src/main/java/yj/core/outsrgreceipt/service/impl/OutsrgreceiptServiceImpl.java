package yj.core.outsrgreceipt.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;
import yj.core.outsrgreceipt.service.IOutsrgreceiptService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OutsrgreceiptServiceImpl extends BaseServiceImpl<Outsrgreceipt> implements IOutsrgreceiptService{

}