package yj.core.wiplines.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wiplines.dto.Lines;

import java.util.List;

public interface LinesMapper extends Mapper<Lines>{
    Lines selectById(long line_id);

    List<Lines> selectAll();
}