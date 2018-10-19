package yj.core.dftdtl.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.dftdtl.dto.Dftdtl;
import yj.core.dftdtl.service.IDftdtlService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DftdtlServiceImpl extends BaseServiceImpl<Dftdtl> implements IDftdtlService{

}