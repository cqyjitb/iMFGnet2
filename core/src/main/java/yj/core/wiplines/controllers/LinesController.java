package yj.core.wiplines.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.web.bind.annotation.*;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.resb.dto.Resb;
import yj.core.resb.service.IResbService;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.service.ICurlzkService;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LinesController extends BaseController {

    @Autowired
    private ILinesService service;
    @Autowired
    private ICurlzkService curlzkService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IMarcService marcService;

    @RequestMapping(value = "/wip/lines/query")
    @ResponseBody
    public ResponseData query(Lines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/lines/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Lines> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Lines> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/lines/selectById"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectById(HttpServletRequest request, Long line_id, String classgrp) {
        List list = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String erdat = df.format(new Date()).substring(0, 10);
        ResponseData rs = new ResponseData(list);
        //获取生产线信息
        Lines lines = service.selectById(line_id);
        if (lines == null) {
            rs.setSuccess(false);
            rs.setMessage("生产线ID错误，请从新输入正确的生产线ID!");
            return rs;
        }

        if (lines.getOnlinetype().equals("1")){
            rs.setSuccess(false);
            rs.setMessage("该生产线上线类型为二维码上线，请在终检台扫码取件/还件！");
            return rs;
        }

        //获取产线当前运行配置
        Curlzk curlzk = new Curlzk();
        curlzk = curlzkService.selectById(lines.getLineId(), classgrp);
        if (curlzk == null) {
            rs.setSuccess(false);
            rs.setMessage("该生产线无当前处理机加流转卡，请先绑定当前处理流转卡！");
            return rs;
        }


        List<Curlzk> curlzkList = new ArrayList<>();
        curlzkList.add(curlzk);
        List<Cardh> cardhList = new ArrayList<>();
        Cardh cardhjj = cardhService.selectByBarcode(curlzk.getZpgdbar());
        if (cardhjj == null){
            rs.setSuccess(false);
            rs.setMessage("无法获取当前机加流转卡:"+ curlzk.getZpgdbar() +"的数据记录！请联系管理员！");
            return rs;
        }

        Marc marc = marcService.selectByMatnr(cardhjj.getMatnr());

        List<ProductsCfg> listpcfg = new ArrayList<>();
        ProductsCfg pc = new ProductsCfg();
        pc.setLineId(Long.valueOf(line_id));
        pc.setPmatnr(marc.getMatnr());
        pc = productsCfgService.selectByLineidAndMatnr(Long.valueOf(line_id).toString(),marc.getMatnr());
        if (pc != null){
            listpcfg.add(pc);
        }

        List<Marc> marclist = new ArrayList<>();
        marclist.add(marc);
        cardhList.add(cardhjj);
        List<ProductsCfg> prolist = new ArrayList<>();
        prolist = productsCfgService.selectById(line_id);
        if (prolist.size() <= 0) {
            rs.setSuccess(false);
            rs.setMessage("生产线：" + line_id + "尚未配置产品基础信息，请先维护产品配置。");
            return rs;
        }
        rs.setSuccess(true);
        list.add(curlzkList);
        list.add(prolist);
        list.add(lines);
        list.add(cardhList);
        list.add(marclist);
        list.add(listpcfg);
        rs.setRows(list);
        return rs;
    }
}