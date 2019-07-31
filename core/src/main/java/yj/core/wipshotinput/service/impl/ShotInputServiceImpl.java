package yj.core.wipshotinput.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotinput.mapper.ShotInputMapper;
import yj.core.wipshotinput.service.IShotInputService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShotInputServiceImpl extends BaseServiceImpl<ShotInput> implements IShotInputService {

    @Autowired
    private ShotInputMapper shotInputMapper;

    @Override
    public String insertShotInput(ShotInput shotInput) {
        shotInput.setCreatedBy(10001L);
        shotInput.setCreationDate(new Date());
        shotInputMapper.insertShotInput(shotInput);
        return null;
    }

    @Override
    public List<ShotInput> selectShotInput(ShotInput dto) {
        return shotInputMapper.selectShotInput(dto);
    }

    @Override
    public String updateShotInput(ShotInput shotInput) {
        shotInput.setLastUpdatedBy(10001L);
        shotInput.setLastUpdateDate(new Date());
        shotInputMapper.updateShotInput(shotInput);
        return null;
    }

    @Override
    public ShotInput queryShotInput(ShotInput shotInput) {
        return shotInputMapper.queryShotInput(shotInput);
    }
}
