package yj.core.zrwkhead.controllers;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.zrwkhead.dto.Zrwkhead;
import yj.core.zrwkhead.dto.recPageDate;
import yj.core.zrwkhead.service.IZrwkheadService;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zrwklist.service.IZrwklistService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
    public class ZrwkheadController extends BaseController{

    @Autowired
    private IZrwkheadService service;
    @Autowired
    private IInOutRecordService iInOutRecordService;
    @Autowired
    private IZrwklistService zrwklistService;


    @RequestMapping(value = "/wip/zrwkhead/query")
    @ResponseBody
    public ResponseData query(Zrwkhead dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/wip/zrwkhead/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request,@RequestBody List<Zrwkhead> dto){
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/zrwkhead/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Zrwkhead> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    @RequestMapping(value = {"/wip/zrwkhead/createZrwk"},method = {RequestMethod.GET})
        @ResponseBody
        public ResponseData createZrwk(HttpServletRequest request){
        ResponseData responseData = new ResponseData();
        String createdBy = "" + request.getSession().getAttribute("userId");
        String dto = request.getParameter("dto");
        JSONArray jsonArray = JSONArray.fromObject(dto);
        List<Map<String, Object>> mapListJson = (List) jsonArray;
        List<recPageDate> listrec = new ArrayList<>();
        List<Zrwklist> listitem = new ArrayList<>();
        List<InOutRecord> listinoutRecord = new ArrayList<>();

        for (int i = 0; i < mapListJson.size(); i++) {
            Map<String, Object> obj = mapListJson.get(i);
            recPageDate rec = new recPageDate();
            rec.setLineId(obj.get("lineId").equals("null") ? "" : obj.get("lineId").toString());
            rec.setArbpr(obj.get("arbpr").equals("null") ? "" : obj.get("arbpr").toString());
            rec.setCharg(obj.get("charg").equals("null") ? "" : obj.get("charg").toString());
            rec.setCharg2(obj.get("charg2").equals("null") ? "" : obj.get("charg2").toString());
            rec.setDiecd(obj.get("diecd").equals("null") ? "" : obj.get("diecd").toString());
            rec.setGmein(obj.get("gmein").equals("null") ? "" : obj.get("gmein").toString());
            rec.setGstrp(obj.get("gstrp").equals("null") ? "" : obj.get("gstrp").toString());
            rec.setMark(obj.get("mark").equals("null") ? "" : obj.get("mark").toString());
            rec.setMatnr(obj.get("matnr").equals("null") ? "" : obj.get("matnr").toString());
            rec.setMatnr2(obj.get("matnr2").equals("null") ? "" : obj.get("matnr2").toString());
            rec.setReviewc(obj.get("reviewc").equals("null") ? "" : obj.get("reviewc").toString());
            rec.setSfflg(obj.get("sfflg").equals("null") ? "" : obj.get("sfflg").toString());
            rec.setVornr(obj.get("vornr").equals("null") ? "" : obj.get("vornr").toString());
            rec.setZbanc(obj.get("zbanc").equals("null") ? "" : obj.get("zbanc").toString());
            rec.setZbanz(obj.get("zbanz").equals("null") ? "" : obj.get("zbanz").toString());
            rec.setZgjbar(obj.get("zgjbar").equals("null") ? "" : obj.get("zgjbar").toString());
            rec.setZotype(obj.get("zotype").equals("null") ? "" : obj.get("zotype").toString());
            rec.setZpgdbar(obj.get("zpgdbar").equals("null") ? "" : obj.get("zpgdbar").toString());
            rec.setZpgdbar2(obj.get("zpgdbar2").equals("null") ? "" : obj.get("zpgdbar2").toString());
            rec.setZqjjlh(obj.get("zqjjlh").equals("null") ? "" : obj.get("zqjjlh").toString());
            rec.setZqxdm(obj.get("zqxdm").equals("null") ? "" : obj.get("zqxdm").toString());
            rec.setZrnum(obj.get("zrnum").equals("null") ? "" : obj.get("zrnum").toString());
            rec.setZrwktimes(obj.get("zrwktimes").equals("null") ? "" : obj.get("zrwktimes").toString());
            rec.setZxhbar(obj.get("zxhbar").equals("null") ? "" : obj.get("zxhbar").toString());
            listrec.add(rec);
        }
        if (listrec.size() > 0) {
            //创建返工返修审理单表头
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curdate = df.format(new Date()).substring(0, 10).replaceAll("-", "");
            Zrwkhead zrwkhead = new Zrwkhead();
            String maxno = service.selectMaxNo(curdate);
            String zudnum = "";
            if (maxno == null) {
                zudnum = "J" + curdate.substring(2, 8) + "001";
            } else {
                String no = maxno.substring(7, 10);
                Integer num = Integer.valueOf(no);
                num = num + 1;
                no = num.toString();
                int times = 3 - no.length();
                for (int i = 0; i < times; i++) {
                    no = "0" + no;
                }
                zudnum = "J" + curdate.substring(2, 8) + no;
            }
            zrwkhead.setZrwknum(zudnum);
            zrwkhead.setArbpr(listrec.get(0).getArbpr());
            zrwkhead.setCrdat(curdate);
            zrwkhead.setLineId(listrec.get(0).getLineId());
            zrwkhead.setZotype(listrec.get(0).getZotype());
            zrwkhead.setCreatedBy(Long.valueOf(createdBy));
            zrwkhead.setCreationDate(new Date());
            //准备审理单行数据
            for (int i = 0; i < listrec.size(); i++) {
                Zrwklist zrwklist = new Zrwklist();
                zrwklist.setZrwknum(zudnum);
                zrwklist.setItem(Integer.valueOf(i + 1).toString());
                zrwklist.setZqjjlh(listrec.get(i).getZqjjlh());
                zrwklist.setZpgdbar(listrec.get(i).getZpgdbar());
                zrwklist.setVornr(listrec.get(i).getVornr());
                zrwklist.setZxhbar(listrec.get(i).getZxhbar());
                zrwklist.setZpgdbar2(listrec.get(i).getZpgdbar2());
                zrwklist.setGstrp(listrec.get(i).getGstrp());
                zrwklist.setMatnr(listrec.get(i).getMatnr());
                zrwklist.setMatnr2(listrec.get(i).getMatnr2());
                zrwklist.setZgjbar(listrec.get(i).getZgjbar());
                if (listrec.get(i).getZrwktimes().equals("")){
                    zrwklist.setZrwktimes(0L);
                }else{
                    zrwklist.setZrwktimes(Long.valueOf(listrec.get(i).getZrwktimes()));
                }
                zrwklist.setZrnum(Long.valueOf(listrec.get(i).getZrnum()));
                zrwklist.setGmein(listrec.get(i).getGmein());
                zrwklist.setCharg2(listrec.get(i).getCharg2());
                zrwklist.setCharg(listrec.get(i).getCharg());
                zrwklist.setDiecd(listrec.get(i).getDiecd());
                zrwklist.setSfflg(listrec.get(i).getSfflg());
                zrwklist.setZqxdm(listrec.get(i).getZqxdm());
                zrwklist.setZbanc(listrec.get(i).getZbanc());
                zrwklist.setZbanz(listrec.get(i).getZbanz());
                zrwklist.setReviewc("G");
                zrwklist.setMark(listrec.get(i).getMark());
                zrwklist.setCreatedBy(Long.valueOf(createdBy));
                zrwklist.setReviewc(listrec.get(i).getReviewc());
                zrwklist.setCreationDate(new Date());
                listitem.add(zrwklist);

                InOutRecord inOutRecord = new InOutRecord();
                inOutRecord = iInOutRecordService.selectById(listrec.get(i).getZqjjlh());
                inOutRecord.setReflag(3L);
                inOutRecord.setLastUpdatedBy(Long.valueOf(createdBy));
                inOutRecord.setLastUpdateDate(new Date());
                listinoutRecord.add(inOutRecord);
            }
            int sum = 0;
            if (listitem.size() > 0){
                sum = sum + zrwklistService.insertItem(listitem);
                if (sum == listitem.size()){
                    service.insertHead(zrwkhead);
                    for (int i = 0;i<listinoutRecord.size();i++){
                        iInOutRecordService.updateReflag(listinoutRecord.get(i));
                    }
                }
            }
            responseData.setCode("S");
            responseData.setMessage("返工返修处理单创建成功！");
            responseData.setSuccess(true);
        }
        return responseData;
    }

    }