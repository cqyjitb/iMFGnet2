package yj.kanb.vbgroupdtl.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;
import yj.kanb.vbgroupdtl.mapper.VbgroupdtlMapper;
import yj.kanb.vbgroupdtl.service.IVbgroupdtlService;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VbgroupdtlServiceImpl extends BaseServiceImpl<Vbgroupdtl> implements IVbgroupdtlService {
    @Autowired
    private VbgroupdtlMapper vbgroupdtlMapper;

    @Override
    public String insertGroupDtl(List<Vbgroupdtl> dto, String userId) {
        if (dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Vbgroupdtl vbgroupdtl = dto.get(i);
                vbgroupdtl.setCreatedBy(Long.valueOf(userId));
                vbgroupdtl.setCreationDate(new Date());
                vbgroupdtlMapper.insertGroupDtl(vbgroupdtl);
            }
        }
        return null;
    }
}
