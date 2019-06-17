package yj.kanb.wippassrate.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.wippassrate.dto.PassRate;

public interface PassRateMapper extends Mapper<PassRate> {
    /**
     * 合格率报表的添加 918100064
     * @param dto
     */
    void insertPassRate(PassRate dto);
}
