package yj.kanb.kbtest.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.kbtest.dto.Kbtest;

import java.util.List;

public interface KbtestMapper extends Mapper<Kbtest> {
    List<Kbtest> selectAllData();
}
