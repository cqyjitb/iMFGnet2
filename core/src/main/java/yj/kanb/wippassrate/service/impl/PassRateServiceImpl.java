package yj.kanb.wippassrate.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.wippassrate.dto.PassRate;
import yj.kanb.wippassrate.mapper.PassRateMapper;
import yj.kanb.wippassrate.service.IPassRateService;

@Service
@Transactional
public class PassRateServiceImpl extends BaseServiceImpl<PassRate> implements IPassRateService {
    @Autowired
    private PassRateMapper passRateMapper;

    @Override
    public void insertPassRate(PassRate dto) {
        passRateMapper.insertPassRate(dto);
    }
}
