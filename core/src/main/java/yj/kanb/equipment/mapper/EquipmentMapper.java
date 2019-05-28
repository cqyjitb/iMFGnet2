package yj.kanb.equipment.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.equipment.dto.Equipment;

import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {
    List<Equipment> selectAllData();
}
