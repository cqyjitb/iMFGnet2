package yj.core.mouldcavity.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.mouldcavity.dto.Mouldcavity;
import yj.core.mouldcavity.mapper.MouldcavityMapper;
import yj.core.mouldcavity.service.IMouldcavityService;

@Service
@Transactional
public class MouldcavityServiceImpl extends BaseServiceImpl<Mouldcavity> implements IMouldcavityService {

    @Autowired
    private MouldcavityMapper mouldcavityMapper;

}