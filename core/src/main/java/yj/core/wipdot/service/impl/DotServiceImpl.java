package yj.core.wipdot.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipdot.dto.Dot;
import yj.core.wipdot.mapper.DotMapper;
import yj.core.wipdot.service.IDotService;

@Service
@Transactional
public class DotServiceImpl extends BaseServiceImpl<Dot> implements IDotService{
    @Autowired
    private DotMapper dotMapper;

}
