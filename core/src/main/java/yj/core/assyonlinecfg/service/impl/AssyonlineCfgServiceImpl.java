package yj.core.assyonlinecfg.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.assyonlinecfg.dto.AssyonlineCfg;
import yj.core.assyonlinecfg.service.IAssyonlineCfgService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssyonlineCfgServiceImpl extends BaseServiceImpl<AssyonlineCfg> implements IAssyonlineCfgService{

}