package yj.core.qcauditlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;
import yj.core.qcauditlist.mapper.QcauditprocessdtlMapper;
import yj.core.qcauditlist.service.IQcauditprocessdtlService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QcauditprocessdtlServiceImpl extends BaseServiceImpl<Qcauditprocessdtl> implements IQcauditprocessdtlService{
    @Autowired
    private QcauditprocessdtlMapper qcauditprocessdtlMapper;
    @Override
    public int insertData(List<Qcauditprocessdtl> list) {
        int num = 0;
        for (int i = 0;i<list.size();i++){

            num = num + qcauditprocessdtlMapper.insertData(list.get(i));
        }
        return num;
    }

    @Override
    public int deleteById(String werks,String recrodid) {
        return qcauditprocessdtlMapper.deleteById(werks,recrodid);
    }

    @Override
    public List<Qcauditprocessdtl> selectById(String werks, String recordid) {
        return qcauditprocessdtlMapper.selectById(werks,recordid);
    }
}