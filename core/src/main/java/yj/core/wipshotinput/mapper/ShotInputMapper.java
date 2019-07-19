package yj.core.wipshotinput.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipshotinput.dto.ShotInput;

public interface ShotInputMapper extends Mapper<ShotInput>{
    /**
     * 压射号及报工统计表数据添加 918100064
     * @param dto
     */
    void insertShotInput(ShotInput dto);
}
