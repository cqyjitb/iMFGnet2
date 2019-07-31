package yj.core.wipshotinput.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipshotinput.dto.ShotInput;

import java.util.List;

public interface ShotInputMapper extends Mapper<ShotInput>{
    /**
     * 压射号及报工统计表数据添加 918100064
     * @param dto
     */
    void insertShotInput(ShotInput dto);

    /**
     * 压射号及报工统计表查询 918100064
     * @param dto
     * @return
     */
    List<ShotInput> selectShotInput(ShotInput dto);

    /**
     * 压射号及报工统计表数据修改 918100064
     * @param shotInput
     * @return
     */
    void updateShotInput(ShotInput shotInput);

    /**
     * 查询压射号及报工统计表数据的存在 918100064
     * @param shotInput
     * @return
     */
    ShotInput queryShotInput(ShotInput shotInput);
}
