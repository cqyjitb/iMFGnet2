package yj.kanb.equipment.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.equipment.dto.Equipment;
import yj.kanb.equipment.mapper.EquipmentMapper;
import yj.kanb.equipment.service.IEquipmentService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EquipmentServiceImpl extends BaseServiceImpl<Equipment> implements IEquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public List<Equipment> selectAllData() {
        return equipmentMapper.selectAllData();
    }
    @Override
    public List<Equipment> queryEquipment(Equipment dto) {
        return equipmentMapper.selectEquipment(dto);
    }

    @Override
    public void deleteEquipment(List<Equipment> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                equipmentMapper.deleteEquipment(dto.get(i));
            }
        }
    }

    @Override
    public String insertEquipment(List<Equipment> dto, String userId) {
        if(dto.size() > 0){
            Equipment result = equipmentMapper.selectByMac(dto.get(0).getMac());
            if(result == null){
                Equipment equipment = dto.get(0);
                String eqId = UUID.randomUUID().toString();
                equipment.setEqId(eqId);
                equipment.setCreatedBy(Long.valueOf(userId));
                equipment.setCreationDate(new Date());
                equipmentMapper.insertEquipment(equipment);
            }else{
                return "该MAC地址已存在，请重新输入！";
            }
        }
        return null;
    }

    @Override
    public void updateEquipment(List<Equipment> dto, String userId) {
        if(dto.size() > 0){
            Equipment equipment = dto.get(0);
            equipment.setLastUpdatedBy(Long.valueOf(userId));
            equipment.setLastUpdateDate(new Date());
            equipmentMapper.updateEquipment(equipment);
        }
    }
}
