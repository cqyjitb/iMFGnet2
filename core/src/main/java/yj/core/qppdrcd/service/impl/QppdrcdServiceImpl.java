package yj.core.qppdrcd.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.qppdrcd.dto.Qppdrcd;
import yj.core.qppdrcd.service.IQppdrcdService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QppdrcdServiceImpl extends BaseServiceImpl<Qppdrcd> implements IQppdrcdService{

}