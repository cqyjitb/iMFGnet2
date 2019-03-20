package yj.core.qcauditlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.qcauditlist.dto.Qcauditlist;
import yj.core.qcauditlist.mapper.QcauditlistMapper;
import yj.core.qcauditlist.service.IQcauditlistService;
import org.springframework.transaction.annotation.Transactional;
import yj.core.wipqcparamlines.mapper.QcparamLinesMapper;

import java.util.List;

@Service
@Transactional
public class QcauditlistServiceImpl extends BaseServiceImpl<Qcauditlist> implements IQcauditlistService{
    @Autowired
    private QcauditlistMapper qcauditlistMapper;
    @Override
    public int insertNewRow(List<Qcauditlist> list) {
        int num = 0;
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                num = num + qcauditlistMapper.insertNewRow(list.get(i));
            }
        }
        return num;
    }

    @Override
    public List<Qcauditlist> selectById(String werks, String recordid) {
        return qcauditlistMapper.selectById(werks,recordid);
    }

    @Override
    public List<Qcauditlist> selectCounts(String werks, String recordid) {
        return qcauditlistMapper.selectCounts(werks,recordid);
    }

    @Override
    public int deleteById(String werks, String recordid) {
        return qcauditlistMapper.deleteById(werks,recordid);
    }
}