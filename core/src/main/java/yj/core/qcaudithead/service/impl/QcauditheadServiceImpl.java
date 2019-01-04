package yj.core.qcaudithead.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcaudithead.mapper.QcauditheadMapper;
import yj.core.qcaudithead.service.IQcauditheadService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QcauditheadServiceImpl extends BaseServiceImpl<Qcaudithead> implements IQcauditheadService{
    @Autowired
    private QcauditheadMapper qcauditheadMapper;
    @Override
    public String selectMaxRecordId(String werks, String gstrp) {
        return qcauditheadMapper.selectMaxRecordId(werks,gstrp);
    }

    @Override
    public int insertNewRow(Qcaudithead qcaudithead) {
        return qcauditheadMapper.insertNewRow(qcaudithead);
    }
}