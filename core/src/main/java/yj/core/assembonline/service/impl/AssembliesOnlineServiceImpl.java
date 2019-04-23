package yj.core.assembonline.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.assembonline.dto.AssembliesOnline;
import yj.core.assembonline.service.IAssembliesOnlineService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssembliesOnlineServiceImpl extends BaseServiceImpl<AssembliesOnline> implements IAssembliesOnlineService{

}