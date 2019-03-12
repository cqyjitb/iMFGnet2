package yj.core.wipshotnum.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.afko.dto.Afko;
import yj.core.afko.mapper.AfkoMapper;
import yj.core.dispatch.dto.InputLog;
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
import java.util.*;

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
    private ShiftstimeMapper shiftstimeMapper;
    @Autowired
    private MarcMapper marcMapper;

    @Override
    public List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext) {
        List<Shotnum> list1 = shotnumMapper.selectShotnum(dto.getWerks(),dto.getFevor(),null,null,dto.getPrdDateAfter(),dto.getPrdDateBefore());
        List<Shotnum> list = new ArrayList<Shotnum>();
        List<Shotnum> list2 = new ArrayList<Shotnum>();
        List<Afko> afko = new ArrayList<Afko>();
        List<InputLog> inputLog = new ArrayList<InputLog>();
        Shotnum shotnum = new Shotnum();
        Marc marc = new Marc();
        Shiftstime shiftstime = new Shiftstime();
        if(list1.size() > 0){
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat sfWeek = new SimpleDateFormat("EEEE");
            Calendar cal = new GregorianCalendar();
            if("Y".equals(dto.getTotal())){
                for(int i=0;i<list1.size();i++){
                    list.add(list1.get(i));
                    for(int j=i+1;j<list1.size();j++){
                        if(list1.get(i).getArbpl().equals(list1.get(j).getArbpl())){
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for(int i=0;i<list.size();i++){
                    Integer mdnum = 1,shotNum = 0,yeild = 0;
                    Long grgew = 0L;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(),shotnum.getFevor(),null,
                            shotnum.getArbpl(),dto.getPrdDateAfter(),dto.getPrdDateBefore());
                    String minTime = list2.get(0).getPrdDate();
                    String maxTime = list2.get(0).getPrdDate();
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for(int a=0;a<list2.size();a++){
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(),list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if(mdnum != null && mdnum > 1){
                            Integer shotNum1 = ((int)(list2.get(a).getShotEnd() - list2.get(a).getShotStart())*(mdnum-1));
                            shotNum = shotNum + shotNum1;
                            if(marc != null){
                                if(marc.getBrgew() == null){
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + Math.round(shotNum1 * 2 * marc.getBrgew());
                            }
                        }else{
                            if(marc != null){
                                if(marc.getBrgew() == null){
                                    marc.setBrgew(0.0);
                                }
                                grgew = grgew + Math.round(((int)(list2.get(a).getShotEnd() - list2.get(a).getShotStart())) * marc.getBrgew());
                            }
                        }
                        if(minTime.compareTo(list2.get(a).getPrdDate()) > 0){
                            minTime = list2.get(a).getPrdDate();
                        }else{
                            maxTime = list2.get(a).getPrdDate();
                        }
                        if(list2.get(a).getShotStart() < startMin){
                            startMin = list2.get(a).getShotStart();
                        }
                        if(list2.get(a).getShotEnd() > endMax){
                            endMax = list2.get(a).getShotEnd();
                        }
                    }
                    shotnum.setPrdDateAfter(minTime);
                    shotnum.setPrdDateBefore(maxTime);
                    shotNum = (int)(shotNum + (endMax - startMin));
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(grgew);
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    afko = afkoMapper.selectByFevor(shotnum.getArbpl());
                    if(afko.size() > 0){
                        shiftstime = shiftstimeMapper.selectByShift("1");
                        Shiftstime shiftstime2 = shiftstimeMapper.selectByShift("3");
                        String startDate = (minTime + " " + shiftstime.getBgsTime());
                        try {
                            cal.setTime(sf.parse(maxTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        cal.add(cal.DATE,1);
                        String endDate = sf.format(cal.getTime())+ " " + shiftstime2.getBgeTime();
                        for(int j=0;j<afko.size();j++){
                            yeild = yeild + inputLogMapper.selectByOrderno(afko.get(j).getAufnr(),startDate,endDate);
                        }
                    }
                    shotnum.setYeild(yeild);
                    shotnum.setWasteNum(shotNum - yeild);
                }
            }else{
                for(int i=0;i<list1.size();i++){
                    list.add(list1.get(i));
                    for(int j=i+1;j<list1.size();j++){
                        if((list1.get(i).getArbpl().equals(list1.get(j).getArbpl()))&&(list1.get(i).getPrdDate().equals(list1.get(j).getPrdDate()))&&
                                (list1.get(i).getShifts().equals(list1.get(j).getShifts()))){
                            list1.remove(j);
                            j--;
                        }
                    }
                }
                for(int i=0;i<list.size();i++){
                    Integer mdnum = 1,shotNum = 0,yeild = 0;
                    Long grgew = 0L;
                    shotnum = list.get(i);
                    list2 = shotnumMapper.selectShotnum(shotnum.getWerks(),shotnum.getFevor(),shotnum.getShifts(),
                            shotnum.getArbpl(),shotnum.getPrdDate(),shotnum.getPrdDate());
                    Long startMin = list2.get(0).getShotStart();
                    Long endMax = list2.get(0).getShotEnd();
                    for(int a=0;a<list2.size();a++){
                        mdnum = mouldcavityMapper.selectByMatnr(list2.get(a).getMatnr(),list2.get(a).getMdno());
                        marc = marcMapper.selectByMatnr(list2.get(a).getMatnr());
                        if(mdnum == null){
                            mdnum = 1;
                        }
                        Integer shotNum1 = ((int)(list2.get(a).getShotEnd() - list2.get(a).getShotStart())*mdnum);
                        shotNum = shotNum + shotNum1;
                        if(marc != null){
                            if(marc.getBrgew() == null){
                                marc.setBrgew(0.0);
                            }
                            grgew = grgew + Math.round(shotNum1 * marc.getBrgew());
                        }
                        if(a > 0){
                            if(list2.get(a).getShotEnd() > endMax){
                                endMax = list2.get(a).getShotEnd();
                            }
                            if(list2.get(a).getShotStart() < startMin){
                                startMin = list2.get(a).getShotStart();
                            }
                        }
                    }
                    shotnum.setPrdDateAfter(shotnum.getPrdDate());
                    shotnum.setShotStart(startMin);
                    shotnum.setShotEnd(endMax);
                    shotnum.setShotNum(shotNum);
                    shotnum.setBrgew(grgew);
                    shiftstime = shiftstimeMapper.selectByShift(shotnum.getShifts());
                    String date = null;
                    try {
                        date = sfWeek.format(sf.parse(shotnum.getPrdDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    afko = afkoMapper.selectByFevor(shotnum.getArbpl());
                    if(afko.size() > 0){
                        if("星期日".equals(date)){
                            String startDate = shotnum.getPrdDate() + " " + shiftstime.getZsTime();
                            String endDate = shotnum.getPrdDate();
                            if(shotnum.getShifts().equals("3")){
                                try {
                                    cal.setTime(sf.parse(endDate));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                cal.add(cal.DATE,1);
                                endDate = sf.format(cal.getTime())+ " " + shiftstime.getZeTime();
                            }else{
                                endDate = endDate + " " + shiftstime.getZeTime();
                            }
                            for(int j=0;j<afko.size();j++){
                                yeild = yeild + inputLogMapper.selectByOrderno(afko.get(j).getAufnr(),startDate,endDate);
                            }
                        }else{
                            String startDate = shotnum.getPrdDate() + " " + shiftstime.getBgsTime();
                            String endDate = shotnum.getPrdDate();
                            if(shotnum.getShifts().equals("3")){
                                try {
                                    cal.setTime(sf.parse(endDate));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                cal.add(cal.DATE,1);
                                endDate = sf.format(cal.getTime())+ " " + shiftstime.getBgeTime();
                            }else{
                                endDate = endDate + " " + shiftstime.getBgeTime();
                            }
                            for(int j=0;j<afko.size();j++){
                                yeild = yeild + inputLogMapper.selectByOrderno(afko.get(j).getAufnr(),startDate,endDate);
                            }
                        }
                    }
                    shotnum.setYeild(yeild);
                    shotnum.setWasteNum(shotNum - yeild);
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

    @Override
    public List<Shotnum> queryShotnum(Shotnum dto, IRequest requestContext) {
        return shotnumMapper.queryShotnum(dto);
    }

    @Override
    public String updateShotnum(IRequest requestContext, List<Shotnum> dto, String userId) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                Shotnum shotnum = dto.get(i);
                shotnum.setLastUpdatedBy(Long.valueOf(userId));
                shotnum.setLastUpdateDate(new Date());
                shotnumMapper.updateShotnum(shotnum);
            }
        }
        return null;
    }

    @Override
    public String deleteShotnum(List<Shotnum> dto) {
        if(dto.size() > 0){
            for(int i=0;i<dto.size();i++){
                shotnumMapper.deleteShotnum(dto.get(i));
            }
        }
        return null;
    }
}