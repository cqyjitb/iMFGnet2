package yj.core.marcres.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.marcres.dto.MarcRes;
import yj.core.marcres.service.IMarcResService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarcResServiceImpl extends BaseServiceImpl<MarcRes> implements IMarcResService{

}