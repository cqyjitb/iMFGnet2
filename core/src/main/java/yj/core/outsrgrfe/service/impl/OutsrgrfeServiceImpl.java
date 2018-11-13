package yj.core.outsrgrfe.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.mapper.OutsrgrfeMapper;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutsrgrfeServiceImpl extends BaseServiceImpl<Outsrgrfe> implements IOutsrgrfeService{
    @Autowired
    private OutsrgrfeMapper outsrgrfeMapper;
    @Override
    public int insertOutsrgrfe(Outsrgrfe outsrgrfe) {
        return outsrgrfeMapper.insertOutsrgrfe(outsrgrfe);
    }

    @Override
    public Outsrgrfe selectByCondition(String werks, String aufnr, String vornr, String matnr, String lifnr) {
        return outsrgrfeMapper.selectByCondition(werks,aufnr,vornr,matnr,lifnr);
    }

    @Override
    public int updateByCondition(Outsrgrfe outsrgrfe) {
        return outsrgrfeMapper.updateByCondition(outsrgrfe);
    }

    @Override
    public List<Outsrgrfe> selectForSortl(String lifnr) {
        return outsrgrfeMapper.selectForSortl(lifnr);
    }
}