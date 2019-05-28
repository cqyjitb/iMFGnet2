package yj.kanb.equipment.service.impl;

import com.hand.hap.comm.DataSourceEnum;
import com.hand.hap.comm.DataSourceHolder;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.afko.dto.Afko;
import yj.core.afko.mapper.AfkoMapper;
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
    public List<Equipment> selectAllData() {
        return equipmentMapper.selectAllData();
    }
}
