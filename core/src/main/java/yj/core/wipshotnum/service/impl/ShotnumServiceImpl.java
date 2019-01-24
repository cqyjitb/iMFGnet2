package yj.core.wipshotnum.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.mapper.ShotnumMapper;
import yj.core.wipshotnum.service.IShotnumService;
import yj.core.zudlist.dto.Zudlist;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShotnumServiceImpl extends BaseServiceImpl<Shotnum> implements IShotnumService {

    @Autowired
    private ShotnumMapper shotnumMapper;
    @Autowired
    private InputLogMapper inputLogMapper;

    @Override
    public List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list = new ArrayList<Shotnum>();
        Shotnum shotnum = new Shotnum();
        if("Y".equals(dto.getTotal())){

        }else{
            list = shotnumMapper.selectShotnum(dto);
            if(list.size() > 0){
                for(int i=0;i<list.size();i++){
                    shotnum = list.get(i);
                    shotnum.setPrdDateAfter(shotnum.getPrdDate());
                    if(shotnum.getMdnum() != null){
                        shotnum.setShotNum((int)((shotnum.getShotEnd() - shotnum.getShotStart())*shotnum.getMdnum()));
                    }
                    int yeild = inputLogMapper.selectByOrderno(shotnum.getAufnr(),shotnum.getShifts(),shotnum.getPrdDate(),shotnum.getPrdDate());
                    shotnum.setYeild(yeild);
                }
            }
        }

        return list;
    }


}