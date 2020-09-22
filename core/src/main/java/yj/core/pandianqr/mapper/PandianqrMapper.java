package yj.core.pandianqr.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.pandianqr.dto.Pandianqr;

public interface PandianqrMapper extends Mapper<Pandianqr>{
    int insertPdRow(Pandianqr pandianqr);
}