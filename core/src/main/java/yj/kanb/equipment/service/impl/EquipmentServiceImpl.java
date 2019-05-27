package yj.kanb.equipment.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.equipment.dto.Equipment;
import yj.kanb.equipment.service.IEquipmentService;

@Service
@Transactional
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment> implements IEquipmentService {

}
