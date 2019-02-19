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
import yj.core.dispatch.mapper.InputLogMapper;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.mouldcavity.mapper.MouldcavityMapper;
import yj.core.shiftstime.dto.Shiftstime;
import yj.core.shiftstime.mapper.ShiftstimeMapper;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.mapper.ShotnumMapper;
import yj.core.wipshotnum.service.IShotnumService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    private ShiftstimeMapper shiftstimeMapper;
    @Autowired
    private MarcMapper marcMapper;

    @Override
    public List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list = new ArrayList<Shotnum>();
        List<Shotnum> list1 = new ArrayList<Shotnum>();
        Cardh cardh = new Cardh();
        Shotnum shotnum = new Shotnum();
        List<Shotnum> shotnums = new ArrayList<Shotnum>();
        List<Afko> afko = new ArrayList<Afko>();
        Shiftstime shiftstime = new Shiftstime();
        Marc marc = new Marc();
        Integer mdnum = 0,yeild = 0,shotNum = 0;
        Long grgew = 0L;
        if("Y".equals(dto.getTotal())){
            List<Shotnum> list2 = shotnumMapper.selectShotnum(dto);
            if(list2.size() > 0){
                for(int i=0;i<list2.size();i++){
                    if(i == 0){
                        afko = afkoMapper.selectByFevor(list2.get(0).getArbpl(),dto.getFevor());
                        if(afko.size() != 0) {
                            list1.add(list2.get(0));
                        }
                    }else{
                        afko = afkoMapper.selectByFevor(list2.get(i).getArbpl(),dto.getFevor());
                        if(afko.size() != 0){
                            String arbpl1 = list2.get(i-1).getArbpl();
                            for(int j=i;j<list2.size();j++){
                                String arbpl2 = list2.get(j).getArbpl();
                                if(arbpl1.equals(arbpl2)){
                                    continue;
                                }else{
                                    List<Afko> afko1 = afkoMapper.selectByFevor(list2.get(j).getArbpl(),dto.getFevor());
                                    if(afko1.size() != 0){
                                        list1.add(list2.get(j));
                                    }
                                }
                            }
                        }
                    }
                }
                for(int j=0;j<list1.size();j++){
                    dto.setArbpl(list1.get(j).getArbpl());
                    shotnums = shotnumMapper.selectShotnum(dto);
                    Long startMin = shotnums.get(0).getShotStart();
                    Long endMax = shotnums.get(0).getShotEnd();
                    for(int a=1;a<shotnums.size();a++){
                        if(shotnums.get(a).getShotStart() < startMin){
                            startMin = shotnums.get(a).getShotStart();
                        }
                        if(shotnums.get(a).getShotEnd() > endMax){
                            endMax = shotnums.get(a).getShotEnd();
                        }
                    }
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    afko = afkoMapper.selectByFevor(list1.get(j).getArbpl(),dto.getFevor());
                    shiftstime = shiftstimeMapper.selectByShift("1");
                    cardh = cardhMapper.selectByBarcode(list1.get(j).getZpgdbar());
                    if(cardh != null){
                        if(Integer.parseInt(cardh.getActst().replace(":","")) >= Integer.parseInt(shiftstime.getBgsTime().replace(":",""))){
                            if(afko.size() > 0){
                                for(int i=0;i<afko.size();i++){
                                    yeild = yeild + inputLogMapper.selectByOrderno(afko.get(i).getAufnr(),null,dto.getPrdDateAfter(),dto.getPrdDateBefore());
                                }
                                mdnum = mouldcavityMapper.selectByMatnr(list1.get(j).getMatnr());
                                if(mdnum == null){
                                    mdnum = 1;
                                }
                                shotNum = ((int)(endMax - startMin)*mdnum);
                                marc = marcMapper.selectByMatnr(list1.get(j).getMatnr());
                                if(marc != null){
                                    grgew = Math.round(yeild * marc.getBrgew());
                                }
                                shotnum.setWerks(list1.get(j).getWerks());
                                shotnum.setFevor(dto.getFevor());
                                shotnum.setTxt(afko.get(0).getTxt());
                                shotnum.setArbpl(list1.get(j).getArbpl());
                                shotnum.setKtext(list1.get(j).getKtext());
                                shotnum.setPrdDateAfter(dto.getPrdDateAfter());
                                shotnum.setPrdDateBefore(dto.getPrdDateBefore());
                                shotnum.setBrgew(grgew);
                                shotnum.setYeild(yeild);
                                shotnum.setShotNum(shotNum);
                                shotnum.setWasteNum(shotNum - yeild);
                                list.add(shotnum);
                            }
                        }
                    }
                }
            }
        }else{
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sfWeek = new SimpleDateFormat("EEEE");
            List<Shotnum> list2 = shotnumMapper.selectShotnum(dto);
            if(list2.size() > 0){
                for(int i=0;i<list2.size();i++){
                    if(i == 0){
                        afko = afkoMapper.selectByFevor(list2.get(0).getArbpl(),dto.getFevor());
                        if(afko.size() != 0){
                            list1.add(list2.get(0));
                        }
                    }else{
                        afko = afkoMapper.selectByFevor(list2.get(i).getArbpl(),dto.getFevor());
                        if(afko.size() != 0){
                            for(int j=i;j<list2.size();j++){
                                if((list2.get(i-1).getArbpl().equals(list2.get(j).getArbpl()))&&(list2.get(i-1).getPrdDate().equals(list2.get(j).getPrdDate()))&&
                                        (list2.get(i-1).getShifts().equals(list2.get(j).getShifts()))){
                                    continue;
                                }else {
                                    List<Afko> afko1 = afkoMapper.selectByFevor(list2.get(j).getArbpl(),dto.getFevor());
                                    if(afko1.size() != 0){
                                        list1.add(list2.get(j));
                                    }
                                }
                            }
                        }
                    }
                }
                for(int i=0;i<list1.size();i++){
                    shotnum = list1.get(i);
                    dto.setArbpl(shotnum.getArbpl());
                    dto.setPrdDateBefore(shotnum.getPrdDate());
                    dto.setPrdDateAfter(shotnum.getPrdDate());
                    dto.setShifts(shotnum.getShifts());
                    shotnums = shotnumMapper.selectShotnum(dto);
                    Long startMin = shotnums.get(0).getShotStart();
                    Long endMax = shotnums.get(0).getShotEnd();
                    for(int a=1;a<shotnums.size();a++){
                        if(shotnums.get(a).getShotEnd() > endMax){
                            endMax = shotnums.get(a).getShotEnd();
                        }
                        if(shotnums.get(a).getShotStart() < startMin){
                            startMin = shotnums.get(a).getShotStart();
                        }
                    }
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    afko = afkoMapper.selectByFevor(shotnum.getArbpl(),dto.getFevor());
                    if(afko.size() != 0){
                        shiftstime = shiftstimeMapper.selectByShift(shotnum.getShifts());
                        cardh = cardhMapper.selectByBarcode(shotnum.getZpgdbar());
                        String date = null;
                        if(cardh != null){
                            try {
                                date = sfWeek.format(sf.parse(cardh.getActgstrp()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if("星期日".equals(date)){
                                if(Integer.parseInt(cardh.getActst().replace(":","")) >= Integer.parseInt(shiftstime.getZsTime().replace(":",""))) {
                                    if(afko.size() > 0){
                                        for(int j=0;j<afko.size();j++){
                                            yeild = yeild + inputLogMapper.selectByOrderno(afko.get(j).getAufnr(),shotnum.getShifts(),shotnum.getPrdDate(),shotnum.getPrdDate());
                                        }
                                        mdnum = mouldcavityMapper.selectByMatnr(shotnum.getMatnr());
                                        if(mdnum == null){
                                            mdnum = 1;
                                        }
                                        shotNum = ((int)(endMax - startMin)*mdnum);
                                        marc = marcMapper.selectByMatnr(shotnum.getMatnr());
                                        if(marc != null){
                                            grgew = Math.round(yeild * marc.getBrgew());
                                        }
                                        shotnum.setBrgew(grgew);
                                        shotnum.setPrdDateAfter(shotnum.getPrdDate());
                                        shotnum.setFevor(dto.getFevor());
                                        shotnum.setTxt(afko.get(0).getTxt());
                                        shotnum.setYeild(yeild);
                                        shotnum.setShotNum(shotNum);
                                        shotnum.setWasteNum(shotNum - yeild);
                                        list.add(shotnum);
                                    }
                                }
                            }else{
                                if(Integer.parseInt(cardh.getActst().replace(":","")) >= Integer.parseInt(shiftstime.getBgsTime().replace(":",""))){
                                    if(afko.size() > 0){
                                        for(int j=0;j<afko.size();j++){
                                            yeild = yeild + inputLogMapper.selectByOrderno(afko.get(j).getAufnr(),shotnum.getShifts(),shotnum.getPrdDate(),shotnum.getPrdDate());
                                        }
                                        mdnum = mouldcavityMapper.selectByMatnr(shotnum.getMatnr());
                                        if(mdnum == null){
                                            mdnum = 1;
                                        }
                                        shotNum = ((int)(endMax - startMin)*mdnum);
                                        marc = marcMapper.selectByMatnr(shotnum.getMatnr());
                                        if(marc != null){
                                            grgew = Math.round(yeild * marc.getBrgew());
                                        }
                                        shotnum.setPrdDateAfter(shotnum.getPrdDate());
                                        shotnum.setFevor(dto.getFevor());
                                        shotnum.setTxt(afko.get(0).getTxt());
                                        shotnum.setYeild(yeild);
                                        shotnum.setShotNum(shotNum);
                                        shotnum.setWasteNum(shotNum - yeild);
                                        shotnum.setBrgew(grgew);
                                        list.add(shotnum);
                                    }
                                }
                            }
                        }
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