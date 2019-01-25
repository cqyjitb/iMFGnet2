package yj.core.wipshotnum.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.afko.dto.Afko;
import yj.core.afko.mapper.AfkoMapper;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.mouldcavity.mapper.MouldcavityMapper;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.mapper.ShotnumMapper;
import yj.core.wipshotnum.service.IShotnumService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShotnumServiceImpl extends BaseServiceImpl<Shotnum> implements IShotnumService {

    @Autowired
    private ShotnumMapper shotnumMapper;
    @Autowired
    private InputLogMapper inputLogMapper;
    @Autowired
    private MouldcavityMapper mouldcavityMapper;
    @Autowired
    private AfkoMapper afkoMapper;
    @Autowired
    private CardhMapper cardhMapper;

    @Override
    public List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list = new ArrayList<Shotnum>();
        List<Shotnum> list1 = new ArrayList<Shotnum>();
        Cardh cardh = new Cardh();
        Shotnum shotnum = new Shotnum();
        InputLog inputLog = new InputLog();
        Afko afko = new Afko();
        int mdnum = 0,yeild = 0,shotNum = 0;
        if("Y".equals(dto.getTotal())){
            list1 = shotnumMapper.selectShotnum(dto);
            if(list1.size() > 0){
                String date = null;
                Long lists[] = new Long[list1.size()];
                for(int i=0;i<list1.size();i++){
                    date = list1.get(i).getCrdat().replace("-","") + list1.get(i).getCrtim().replace(":","");
                    lists[i] = Long.parseLong(date);
                }
                Long max = lists[0];
                int a = 0;
                if(list1.size() > 2){
                    for(int i=1;i<list1.size();i++){
                        if(lists[i] > max){
                            max = lists[i];
                            a = i;
                        }
                    }
                }else if(list1.size() == 2){
                    if(lists[1] > max){
                        max = lists[1];
                        a = 1;
                    }
                }
                afko = afkoMapper.selectByFevor(list1.get(a).getArbpl(),dto.getFevor());
                shotnum.setWerks(list1.get(0).getWerks());
                shotnum.setFevor(dto.getFevor());
                shotnum.setTxt(afko.getTxt());
                shotnum.setArbpl(list1.get(a).getArbpl());
                shotnum.setKtext(list1.get(a).getKtext());
                shotnum.setPrdDateAfter(dto.getPrdDateAfter());
                shotnum.setPrdDateBefore(dto.getPrdDateBefore());
                shotnum.setShotStart(list1.get(a).getShotStart());
                shotnum.setShotEnd(list1.get(a).getShotEnd());

                inputLog = inputLogMapper.selectByOrderno(afko.getAufnr(),null,dto.getPrdDateAfter(),dto.getPrdDateBefore());
                cardh = cardhMapper.selectByBarcode(list1.get(a).getZpgdbar());
                yeild = (inputLog.getYeild()).intValue();
                if(cardh != null){
                    mdnum = mouldcavityMapper.selectByMatnr(cardh.getMatnr());
                    shotNum = (int)((shotnum.getShotEnd() - shotnum.getShotStart())*mdnum);
                }
                shotnum.setYeild(yeild);
                shotnum.setShotNum(shotNum);
                shotnum.setWasteNum(shotNum - yeild);
                list.add(shotnum);
            }

        }else{
            list1 = shotnumMapper.selectShotnum(dto);
            if(list1.size() > 0){
                for(int i=0;i<list1.size();i++){
                    shotnum = list1.get(i);
                    afko = afkoMapper.selectByFevor(shotnum.getArbpl(),dto.getFevor());
                    if(afko != null){
                        inputLog = inputLogMapper.selectByOrderno(afko.getAufnr(),shotnum.getShifts(),shotnum.getPrdDate(),shotnum.getPrdDate());
                        cardh = cardhMapper.selectByBarcode(shotnum.getZpgdbar());

                        yeild = (inputLog.getYeild()).intValue();
                        if(cardh != null){
                            mdnum = mouldcavityMapper.selectByMatnr(cardh.getMatnr());
                            shotNum = (int)((shotnum.getShotEnd() - shotnum.getShotStart())*mdnum);
                        }
                        shotnum.setPrdDateAfter(shotnum.getPrdDate());
                        shotnum.setFevor(dto.getFevor());
                        shotnum.setTxt(afko.getTxt());
                        shotnum.setYeild(yeild);
                        shotnum.setShotNum(shotNum);
                        shotnum.setWasteNum(shotNum - yeild);
                        list.add(shotnum);
                    }else{
                        list1.remove(i);
                    }
                }
            }
        }
        return list;
    }

    @Override
    public int insertRow(Shotnum shot) {
        return shotnumMapper.insertRow(shot);
    }

    @Override
    public List<Shotnum> isExit(String werks, String arbpl, String prd_date, String shifts) {
        return shotnumMapper.isExit(werks,arbpl,prd_date,shifts);
    }
}