package yj.core.marc.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.marc.service.IMarcService;

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

    @Override
    public List<Marc> queryByMarc(String werks, String matnr) {
        return marcMapper.queryByMarc(werks,matnr);
    }

    @Override
    public String queryByFileId(Long fileId) {
        return marcMapper.queryByFileId(fileId);
    }

    @Override
    public List<Marc> queryByMarc(IRequest requestContext, String werks, String matnr, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        marcMapper.queryByMarc(werks,matnr);
        return null;
    }

    @Override
    public List<Marc> queryByMarcDto(IRequest requestContext, Marc dto, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return marcMapper.queryByMarcDto(dto);
    }
}