package yj.core.plpo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.plpo.dto.Plpo;
import yj.core.plpo.service.IPlpoService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlpoServiceImpl extends BaseServiceImpl<Plpo> implements IPlpoService{

}