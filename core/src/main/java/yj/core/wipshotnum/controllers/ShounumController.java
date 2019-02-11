package yj.core.wipshotnum.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yj.core.afvc.dto.Afvc;
import yj.core.afvc.service.IAfvcService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.webservice_queryoldzpgdbar.components.QueryOldZpgdbarUtil;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryParm;
import yj.core.webservice_queryoldzpgdbar.dto.DtqueryReturn;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnum.service.IShotnumService;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ShounumController extends BaseController {

    @Autowired
    private IShotnumService service;
    @Autowired
    private IAfvcService afvcService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private QueryOldZpgdbarUtil queryOldZpgdbarUtil;

    /**
     * 压射号及压铸报工查询  918100064
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/wip/shotnum/queryShotnum")
    @ResponseBody
    public ResponseData queryShotnum(Shotnum dto, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        ResponseData rs = new ResponseData();
        dto.setPrdDateAfter(dto.getPrdDateAfter().substring(0,10));
        dto.setPrdDateBefore(dto.getPrdDateBefore().substring(0,10));
        List<Shotnum> shotnum = service.selectShotnum(dto,requestContext);
        return new ResponseData(shotnum);
    }

    /**
     *  保存压射记录 917110140
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/shotnum/commitRow"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData commitRow(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String arbpl = request.getParameter("arbpl");
        String erp_date = request.getParameter("prd_date");
        String shot_start = request.getParameter("shot_start");
        String shot_end = request.getParameter("shot_end");
        String banz = request.getParameter("banz");
        String banc = request.getParameter("banc");
        String zpgdbar = request.getParameter("zpgdbar");
        String ktext = "";
        String userName = request.getParameter("userName");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String crdate = sdf.format(new Date());
        String createdBy = request.getParameter("createdBy");
        String type = request.getParameter("type");
        String werks = "";
        if (type.equals("old")){
            DtqueryParm parm = new DtqueryParm();
            parm.setZpgdbar(zpgdbar);
            DtqueryReturn re = queryOldZpgdbarUtil.receiveConfirmation(parm);
            ktext = re.getArbpldesc();
            werks = re.getWerks();
        }else{
            List<Afvc> list = new ArrayList<>();
            list = afvcService.selectByArbpl(arbpl);
            if (list.size() > 0){
                ktext = list.get(0).getKtext();
            }

            Cardh cardh = new Cardh();
            cardh = cardhService.selectByBarcode(zpgdbar);
            werks = cardh.getWerks();
        }



        Shotnum shot = new Shotnum();
        shot.setArbpl(arbpl);
        shot.setZpgdbar(zpgdbar);
        shot.setWerks(werks);
        shot.setPrdDate(erp_date);
        shot.setShifts(banc);
        shot.setsClass(banz);
        shot.setShotStart(Long.parseLong(shot_start));
        shot.setShotEnd(Long.parseLong(shot_end));
        shot.setCrnam(userName);
        shot.setCrdat(crdate.substring(0,10));
        shot.setCrtim(crdate.substring(11,19));
        shot.setKtext(ktext);
        shot.setCreationDate(new Date());
        shot.setCreatedBy(Long.parseLong(createdBy));

        int i = service.insertRow(shot);
        if (i  == 1){
            rs.setSuccess(true);
            rs.setMessage("提交保存成功！");
        }else{
            rs.setMessage("提交保存失败");
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     *  处理页面请求 根据工厂 工作中心 生产日期 班次 查询记录
     * @param request
     * @return
     */
    @RequestMapping(value = {"/wip/shotnum/isExit"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData isExit(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        String werks = "";
        String arbpl = request.getParameter("arbpl");
        String prd_date = request.getParameter("erp_date");
        String shifts = request.getParameter("banc");
        String zpgdbar = request.getParameter("zpgdbar");
        String type = request.getParameter("type");

        if (type.equals("old")){
            DtqueryParm parm = new DtqueryParm();
            parm.setZpgdbar(zpgdbar);
            DtqueryReturn re = queryOldZpgdbarUtil.receiveConfirmation(parm);
            if (re.getMsgty().equals("S")){
                werks = re.getWerks();
            }
        }else{
            Cardh cardh = new Cardh();
            cardh = cardhService.selectByBarcode(zpgdbar);
            if (cardh == null){
                rs.setSuccess(false);
                rs.setMessage("压铸流转卡不存在，请检查流转卡！");
                return rs;
            }

            werks = cardh.getWerks();
        }

        List<Shotnum> list = new ArrayList<>();
        list = service.isExit(werks,arbpl,prd_date,shifts);
        if (list.size()>0){
            rs.setRows(list);
            rs.setMessage("已于创建时间"+list.get(0).getCrdat()+ " "+list.get(0).getCrtim()+"填报当前班次数据，是否重新填报？");
            rs.setSuccess(true);
            rs.setCode("X");

        }else{
            rs.setSuccess(true);
            rs.setCode("N");
        }
        return rs;
    }
}