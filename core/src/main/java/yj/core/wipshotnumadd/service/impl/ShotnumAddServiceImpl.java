package yj.core.wipshotnumadd.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipshotnumadd.dto.ShotnumAdd;
import yj.core.wipshotnumadd.mapper.ShotnumAddMapper;
import yj.core.wipshotnumadd.service.IShotnumAddService;

@Service
@Transactional
public class ShotnumAddServiceImpl extends BaseServiceImpl<ShotnumAdd> implements IShotnumAddService {

    @Autowired
    private ShotnumAddMapper shotnumAddMapper;

    @Override
    public int insertRow(ShotnumAdd shot) {
        return shotnumAddMapper.insertRow(shot);
    }

}
