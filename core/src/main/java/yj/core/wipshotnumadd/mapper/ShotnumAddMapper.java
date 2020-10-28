package yj.core.wipshotnumadd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wipshotnumadd.dto.ShotnumAdd;

import java.util.List;

public interface ShotnumAddMapper extends Mapper<ShotnumAdd> {

    /**
     * 插入新数据 918100064
     * @param shot
     * @return
     */
    int insertRow(ShotnumAdd shot);

    /**
     * 更新某条数据 918100064
     * @param dto
     */
    void updateRow(ShotnumAdd dto);

    /**
     * 根据主键查询数据 918100064
     * @param dto
     * @return
     */
    List<ShotnumAdd> isExit(ShotnumAdd dto);

    List<ShotnumAdd> selectShotnumAdd();
}
