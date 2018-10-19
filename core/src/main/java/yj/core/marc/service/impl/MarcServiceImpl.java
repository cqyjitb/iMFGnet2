package yj.core.marc.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.marc.service.IMarcService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MarcServiceImpl extends BaseServiceImpl<Marc> implements IMarcService{

    @Autowired
    private MarcMapper marcMapper;
    @Override
    public int isExit(String matnr) {
        return marcMapper.isExit(matnr);
    }

    @Override
    public int updateByMatnr(Marc dto) {
        return marcMapper.updateByMatnr(dto);
    }

    @Override
    public int insertByMatnr(Marc dto) {
        return marcMapper.insertByMatnr(dto);
    }

    @Override
    public Marc selectByMatnr(String matnr) {
        return marcMapper.selectByMatnr(matnr);
    }

    @Override
    public int saveChange(List<Marc> list) {
        int sum = 0;
        for (int i = 0;i<list.size();i++){
            sum = sum + marcMapper.saveChange(list.get(i));
        }
        return sum;
    }
}