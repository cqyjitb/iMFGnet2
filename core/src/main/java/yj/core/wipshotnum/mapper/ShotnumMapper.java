package yj.core.wipshotnum.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipshotnum.dto.Shotnum;

import java.util.List;

public interface ShotnumMapper extends Mapper<Shotnum> {
    List<Shotnum> selectShotnum(Shotnum dto);
}