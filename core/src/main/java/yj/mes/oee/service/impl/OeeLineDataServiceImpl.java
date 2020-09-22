package yj.mes.oee.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.mes.oee.dto.OeeLineData;
import yj.mes.oee.mapper.OeeLineDataMapper;
import yj.mes.oee.service.IOeeLineDataService;

import java.util.List;

@Service
@Transactional
public class OeeLineDataServiceImpl extends BaseServiceImpl<OeeLineData> implements IOeeLineDataService {
    @Autowired
    private OeeLineDataMapper oeeLineDataMapper;
    @Override
    public List<OeeLineData> queryOeeDataByLineCode(OeeLineData oeeLineData) {
        return oeeLineDataMapper.queryOeeDataByLineCode(oeeLineData);
    }
}
