package yj.kanb.kbtest.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.kbtest.dto.Kbtest;
import yj.kanb.kbtest.service.IKbtestService;

import java.util.List;

@Service
@Transactional
public class KbtestServiceImpl extends BaseServiceImpl<Kbtest> implements IKbtestService {

    @Autowired
    @Override
    public List<Kbtest> selectAllData() {
        return null;
    }
}
