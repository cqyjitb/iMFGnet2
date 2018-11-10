package yj.core.wipqcparamlines.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipqcparamlines.dto.QcparamLines;
import yj.core.wipqcparamlines.mapper.QcparamLinesMapper;
import yj.core.wipqcparamlines.service.IQcparamLinesService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QcparamLinesServiceImpl extends BaseServiceImpl<QcparamLines> implements IQcparamLinesService{
    @Autowired
    private QcparamLinesMapper qcparamLinesMapper;

    @Override
    public QcparamLines selectForJj(Long line_id, String werks) {
        return qcparamLinesMapper.selectForJj(line_id,werks);
    }

    @Override
    public QcparamLines selectForYz(Long line_id, String werks) {
        return qcparamLinesMapper.selectForYz(line_id,werks);
    }
}