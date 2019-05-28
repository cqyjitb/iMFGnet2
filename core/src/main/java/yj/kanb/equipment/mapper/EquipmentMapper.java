package yj.kanb.equipment.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.equipment.dto.Equipment;

import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {
    /**
     * 看板设备表查询 918100064
     * @param dto
     * @return
     */
    List<Equipment> selectEquipment(Equipment dto);
}
