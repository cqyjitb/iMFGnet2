package yj.kanb.equipment.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.afko.dto.Afko;
import yj.kanb.equipment.dto.Equipment;

import java.util.List;

public interface IEquipmentService  extends IBaseService<Equipment> ,ProxySelf<IEquipmentService> {

    List<Equipment> selectAllData();
}
