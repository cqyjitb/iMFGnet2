package yj.core.qppdrcd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.qppdrcd.dto.Qppdrcd;

public interface QppdrcdMapper extends Mapper<Qppdrcd>{

    int insertPdRow(Qppdrcd qppdrcd);
}