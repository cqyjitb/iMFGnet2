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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                if ((list.get(i).getYeild()==0)&&(list.get(i).getWasteNum()==0)){
                    list.remove(i);
                    i--;
                }
            }
            for (int i=0;i<list.size();i++){
                ShotInput shotInput = list.get(i);
                if (i == 0){
                    shotInput.setCheckError(0L);
                }else{
                    Date prdDateBefore = null;
                    Date prdDateAfter = null;
                    try {
                        prdDateBefore = sf.parse(list.get(i-1).getPrdDate());
                        prdDateAfter = sf.parse(shotInput.getPrdDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (prdDateAfter.before(prdDateBefore)){
                        shotInput.setCheckError(0L);
                    }else {
                        shotInput.setCheckError(shotInput.getShotStart()-list.get(i-1).getShotEnd());
                    }
                }
            }
        }
        return new ResponseData(list);
    }
}
