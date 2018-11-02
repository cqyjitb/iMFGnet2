package yj.core.wiplines.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.mapper.LinesMapper;
import yj.core.wiplines.service.ILinesService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinesServiceImpl extends BaseServiceImpl<Lines> implements ILinesService{
    @Autowired
    private LinesMapper linesMapper;
    @Override
    public Lines selectById(Long line_id) {
        return linesMapper.selectById(line_id);
    }

    @Override
    public Lines selectByIdForBlmpcl(Long line_id) {
        return linesMapper.selectById(line_id);
    }
}