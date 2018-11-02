package yj.core.dftdtl.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.dftdtl.dto.Dftdtl;
import yj.core.dftdtl.service.IDftdtlService;
import yj.core.dftdtl.mapper.DftdtlMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DftdtlServiceImpl extends BaseServiceImpl<Dftdtl> implements IDftdtlService{
    @Autowired
    private DftdtlMapper dftdlMapper;
    @Override
    public List<Dftdtl> selectByQpcode(String code,String matnr) {
        return dftdlMapper.selectByQpcode(code,matnr);
    }
}