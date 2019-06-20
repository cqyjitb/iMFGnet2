package yj.kanb.wippassrate.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.kanb.wippassrate.dto.PassRate;
import yj.kanb.wippassrate.service.IPassRateService;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class PassRateController extends BaseController {
    @Autowired
    private IPassRateService service;

    /**
     * 合格率报表查询页面请求 918100064
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping(value = "/wip/pass/rate/queryPassRate")
    @ResponseBody
    public ResponseData queryPassRate(HttpServletRequest request, PassRate dto) {
        String btn = request.getParameter("btn");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        DecimalFormat df = new DecimalFormat("#0.00");
        List<PassRate> list = new ArrayList<PassRate>();
        if ("1".equals(btn)){
            String dateStart = dto.getDateStart().replaceAll("/","-");
            String dateEnd = dto.getDateEnd().replaceAll("/","-");
            dto.setDateStart(dateStart);
            dto.setDateEnd(dateEnd);
            list = service.queryPassRate(dto);
            if (list.size() > 0){
                for (int i=0;i<list.size();i++){
                    PassRate passRate = list.get(i);
                    String erdat = sdf.format(passRate.getErdat());
                    int gmnga = passRate.getGmnga();
                    int xmnga = passRate.getXmnga();
                    int rmnga = passRate.getRmnga();
                    int tmnga = gmnga + xmnga + rmnga;
                    String rate = df.format((gmnga*100/(float)tmnga));
                    String jjRate = df.format(((gmnga+rmnga)*100/(float)tmnga));
                    String mpRate = df.format(((gmnga+xmnga)*100/(float)tmnga));
                    passRate.setDateStart(erdat);
                    passRate.setTmnga(tmnga);
                    passRate.setRate(rate);
                    passRate.setJjRate(jjRate);
                    passRate.setMpRate(mpRate);
                }
            }
        }
        if ("2".equals(btn)){
            String dateStart = dto.getDateStart();
            Date dateStart2 = null;
            try {
                dateStart2 = sdf.parse(dateStart + "-01");
                cal.setTime(dateStart2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            dto.setDateStart(dateStart + "-01");
            dto.setDateEnd(dateStart + "-" + day);
            List<PassRate> list2 = service.queryPassRate(dto);
            if (list2.size() > 0){
                for (int i=1;i<=6;i++){
                    PassRate passRate = new PassRate();
                    int gmnga = 0,xmnga = 0,rmnga = 0;
                    Integer start = cal.get(Calendar.DAY_OF_WEEK);
                    if(start == 2){
                        start = 1;
                    }else if(i == 1){
                        start--;
                    }
                    int j;
                    for(j=start;j<=7;j++){
                        String date = sdf.format(cal.getTime());
                        for(int k=0;k<list2.size();k++){
                            if(date.equals(sdf.format(list2.get(k).getErdat()))){
                                gmnga += list2.get(k).getGmnga();
                                xmnga += list2.get(k).getXmnga();
                                rmnga += list2.get(k).getRmnga();
                                break;
                            }
                        }
                        if ((dto.getDateEnd()).equals(date)){
                            break;
                        }
                        cal.add(cal.DATE, 1);
                    }
                    int tmnga = gmnga + xmnga + rmnga;
                    passRate.setGmnga(gmnga);
                    passRate.setXmnga(xmnga);
                    passRate.setRmnga(rmnga);
                    passRate.setDateStart("第" + i + "周");
                    passRate.setTmnga(tmnga);
                    if(tmnga == 0){
                        passRate.setRate("0.00");
                        passRate.setJjRate("0.00");
                        passRate.setMpRate("0.00");
                    }else{
                        String rate = df.format((gmnga*100/(float)tmnga));
                        String jjRate = df.format(((gmnga+rmnga)*100/(float)tmnga));
                        String mpRate = df.format(((gmnga+xmnga)*100/(float)tmnga));
                        passRate.setRate(rate);
                        passRate.setJjRate(jjRate);
                        passRate.setMpRate(mpRate);
                    }
                    list.add(passRate);
                    if(j <= 7){
                        break;
                    }
                }
            }
        }
        if ("3".equals(btn)){
            String dateStart = dto.getDateStart();
            Integer yearStart = Integer.parseInt(dateStart.substring(0,4));
            Integer mouthStart = Integer.parseInt(dateStart.substring(5,7));
            String dateEnd = dto.getDateEnd();
            Integer yearEnd = Integer.parseInt(dateEnd.substring(0,4));
            Integer mouthEnd = Integer.parseInt(dateEnd.substring(5,7)) + 1;
            Integer result = (yearEnd-yearStart)*12 + (mouthEnd-mouthStart);
            try {
                Date dateStart2 = sdf.parse(dateStart + "-01");
                cal.setTime(dateStart2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (int i=0;i<result;i++){
                PassRate passRate = new PassRate();
                Integer day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                dto.setDateStart(sdf2.format(cal.getTime()) + "-01");
                dto.setDateEnd(sdf2.format(cal.getTime()) + "-" + day);
                List<PassRate> list2 = service.queryByMouth(dto);
                passRate.setDateStart(sdf2.format(cal.getTime()));
                int gmnga = list2.get(0).getGmnga();
                int xmnga = list2.get(0).getXmnga();
                int rmnga = list2.get(0).getRmnga();
                int tmnga = gmnga + xmnga + rmnga;
                passRate.setGmnga(gmnga);
                passRate.setXmnga(xmnga);
                passRate.setRmnga(rmnga);
                passRate.setTmnga(tmnga);
                if(tmnga == 0){
                    passRate.setRate("0.00");
                    passRate.setJjRate("0.00");
                    passRate.setMpRate("0.00");
                }else{
                    String rate = df.format((gmnga*100/(float)tmnga));
                    String jjRate = df.format(((gmnga+rmnga)*100/(float)tmnga));
                    String mpRate = df.format(((gmnga+xmnga)*100/(float)tmnga));
                    passRate.setRate(rate);
                    passRate.setJjRate(jjRate);
                    passRate.setMpRate(mpRate);
                }
                list.add(passRate);
                cal.add(cal.DATE,day);
            }
        }
        return new ResponseData(list);
    }
}
