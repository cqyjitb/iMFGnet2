package yj.core.logdtl.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.logdtl.dto.Logdtl;
import yj.core.logdtl.mapper.LogdtlMapper;
import yj.core.logdtl.service.ILogdtlService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogdtlServiceImpl extends BaseServiceImpl<Logdtl> implements ILogdtlService{
    @Autowired
    private LogdtlMapper logdtlMapper;
    @Override
    public int insertNewDtl(Logdtl logdtl) {
        return logdtlMapper.insertNewDtl(logdtl);
    }
}