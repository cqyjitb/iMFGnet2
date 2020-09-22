package yj.core.pandianqr.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.pandianqr.dto.Pandianqr;
import yj.core.pandianqr.mapper.PandianqrMapper;
import yj.core.pandianqr.service.IPandianqrService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PandianqrServiceImpl extends BaseServiceImpl<Pandianqr> implements IPandianqrService{
    @Autowired
    private PandianqrMapper pandianqrMapper;
    @Override
    public int insertPdRow(Pandianqr pandianqr) {
        return pandianqrMapper.insertPdRow(pandianqr);
    }
}