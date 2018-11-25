package yj.core.outsrgreceipthead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.outsrgreceipthead.dto.Outsrgreceipthead;
import yj.core.outsrgreceipthead.mapper.OutsrgreceiptheadMapper;
import yj.core.outsrgreceipthead.service.IOutsrgreceiptheadService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutsrgreceiptheadServiceImpl extends BaseServiceImpl<Outsrgreceipthead> implements IOutsrgreceiptheadService{
    @Autowired
    private OutsrgreceiptheadMapper outsrgreceiptheadMapper;
    @Override
    public List<Outsrgreceipthead> selectByMatnrAndLifnrDesc(String matnr, String lifnr) {
        return outsrgreceiptheadMapper.selectByMatnrAndLifnrDesc(matnr,lifnr);
    }

    @Override
    public int insertNewRow(Outsrgreceipthead outsrgreceipthead) {
        return outsrgreceiptheadMapper.insertNewRow(outsrgreceipthead);
    }
}