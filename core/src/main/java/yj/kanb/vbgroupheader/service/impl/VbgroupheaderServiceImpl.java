package yj.kanb.vbgroupheader.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;
import yj.kanb.vbgroupheader.service.IVbgroupheaderService;

@Service
@Transactional
public class VbgroupheaderServiceImpl extends BaseServiceImpl<Vbgroupheader> implements IVbgroupheaderService{
}
