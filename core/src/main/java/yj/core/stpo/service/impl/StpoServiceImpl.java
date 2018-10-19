package yj.core.stpo.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.stpo.dto.Stpo;
import yj.core.stpo.service.IStpoService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StpoServiceImpl extends BaseServiceImpl<Stpo> implements IStpoService{

}