package yj.kanb.marcres.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.kanb.marcres.dto.MarcRes;
import yj.kanb.marcres.service.IMarcResService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MarcResServiceImpl extends BaseServiceImpl<MarcRes> implements IMarcResService {

}