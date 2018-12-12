package yj.core.zudlog.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zudlog.dto.Zudlog;
import yj.core.zudlog.mapper.ZudlogMapper;
import yj.core.zudlog.service.IZudlogService;

@Service
@Transactional
public class ZudlogServiceImpl extends BaseServiceImpl<Zudlog> implements IZudlogService {
    @Autowired
    private ZudlogMapper zudlogMapper;
    @Override
    public int insertLog(Zudlog zudlog) {
        return zudlogMapper.insertLog(zudlog);
    }
}