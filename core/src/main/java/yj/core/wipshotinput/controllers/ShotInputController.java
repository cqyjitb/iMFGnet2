package yj.core.wipshotinput.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotinput.service.IShotInputService;
import yj.core.wipshotnum.dto.Shotnum;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class ShotInputController extends BaseController {

    @Autowired
    private IShotInputService service;

    /**
     * 压射号异常明细表  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shot/input/queryShotInput")
    @ResponseBody
    public ResponseData queryShotInput(ShotInput dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        List<ShotInput> list = service.selectShotInput(dto);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal2 = Calendar.getInstance();
        if (list.size() > 0){
            List<ShotInput> list2 = new ArrayList<ShotInput>();
            for (int i=0;i<list.size();i++){
                if (!("3".equals(list.get(i).getShifts()))){
                    list2.add(list.get(i));
                }
            }
            for (int i=0;i<list.size();i++){
                ShotInput shotInput = list.get(i);
                if (shotInput.getShifts().equals("1")){
                    shotInput.setCheckError(0L);
                }else {
                    try {
                        cal2.setTime(sf.parse(shotInput.getPrdDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Integer week = cal2.get(Calendar.WEEK_OF_YEAR) % 2;
                    if ((cal2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) && (("0".equals(shotInput.getShiftSeq()))
                            || (("1".equals(shotInput.getShiftSeq())) && (week == 1)) || (("2".equals(shotInput.getShiftSeq())) && (week == 0)))) {
                        if (shotInput.getShifts().equals("2")) {
                            for (int j=0;j<list2.size();j++){
                                if ((shotInput.getArbpl().equals(list2.get(j).getArbpl()))&&(shotInput.getPrdDate().equals(list2.get(j).getPrdDate()))&&(list2.get(j).getShifts().equals("1"))){
                                    shotInput.setCheckError(list2.get(j).getShotEnd()-shotInput.getShotStart());
                                    list2.remove(j);
                                    break;
                                }
                            }
                        }else{
                            for (int j=0;j<list2.size();j++){
                                if ((shotInput.getArbpl().equals(list2.get(j).getArbpl()))&&(shotInput.getPrdDate().equals(list2.get(j).getPrdDate()))&&(list2.get(j).getShifts().equals("2"))){
                                    shotInput.setCheckError(list2.get(j).getShotEnd()-shotInput.getShotStart());
                                    list2.remove(j);
                                    break;
                                }
                            }
                            if (shotInput.getCheckError() == null){
                                for (int j=0;j<list2.size();j++){
                                    if ((shotInput.getArbpl().equals(list2.get(j).getArbpl()))&&(shotInput.getPrdDate().equals(list2.get(j).getPrdDate()))&&(list2.get(j).getShifts().equals("1"))){
                                        shotInput.setCheckError(list2.get(j).getShotEnd()-shotInput.getShotStart());
                                        list2.remove(j);
                                        break;
                                    }
                                }
                            }
                        }
                    }else{
                        for (int j=0;j<list2.size();j++){
                            if ((shotInput.getArbpl().equals(list2.get(j).getArbpl()))&&(shotInput.getPrdDate().equals(list2.get(j).getPrdDate()))&&(list2.get(j).getShifts().equals("1"))){
                                shotInput.setCheckError(list2.get(j).getShotEnd()-shotInput.getShotStart());
                                list2.remove(j);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return new ResponseData(list);
    }
}
