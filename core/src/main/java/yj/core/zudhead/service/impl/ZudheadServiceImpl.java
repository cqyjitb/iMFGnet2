package yj.core.zudhead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.zudhead.dto.Zudhead;
import yj.core.zudhead.mapper.ZudheadMapper;
import yj.core.zudhead.service.IZudheadService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZudheadServiceImpl extends BaseServiceImpl<Zudhead> implements IZudheadService{
    @Autowired
    private ZudheadMapper zudheadMapper;
    @Override
    public String selectMaxNo(String curdat) {
        return zudheadMapper.selectMaxNo(curdat);
    }

    @Override
    public int insertHead(Zudhead zudhead) {
        return zudheadMapper.insertHead(zudhead);
    }
}