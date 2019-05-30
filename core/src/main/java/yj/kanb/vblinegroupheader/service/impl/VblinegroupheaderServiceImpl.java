package yj.kanb.vblinegroupheader.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.mapper.VblinegroupheaderMapper;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;

import java.util.List;

@Service
@Transactional
public class VblinegroupheaderServiceImpl extends BaseServiceImpl<Vblinegroupheader> implements IVblinegroupheaderService {
    @Autowired
    private VblinegroupheaderMapper vblinegroupheaderMapper;
    @Override
    public List<Vblinegroupheader> selectAllGroup() {
        return vblinegroupheaderMapper.selectAllGroup();
    }
}
