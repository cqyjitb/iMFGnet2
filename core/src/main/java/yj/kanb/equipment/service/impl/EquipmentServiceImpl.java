package yj.kanb.equipment.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.equipment.dto.Equipment;
import yj.kanb.equipment.mapper.EquipmentMapper;
import yj.kanb.equipment.service.IEquipmentService;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment> implements IEquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> queryEquipment(Equipment dto) {
        return equipmentMapper.selectEquipment(dto);
    }
}
