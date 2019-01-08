package yj.core.qcaudithead.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.opensaml.xml.signature.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcaudithead.mapper.QcauditheadMapper;
import yj.core.qcaudithead.service.IQcauditheadService;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

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

    @Override
    public List<Qcaudithead> selectForQcaudithead(IRequest requestCtx, Qcaudithead dto) {
        List<Qcaudithead> list = qcauditheadMapper.selectForQcaudithead(dto);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                list.get(i).setGstrp2(df.format(list.get(i).getGstrp()));
                list.get(i).setReportDate2(df.format(list.get(i).getReportDate()));
            }
        }
        return list;
    }
}