package yj.core.fevor.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.HanaCon.HanaCon;
import yj.core.OracleConn.OracleConn;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.dto.Zwipqhz;
import yj.core.fevor.service.IFevorService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.service.IZwipqService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FevorController extends BaseController {
    @Autowired
    private IFevorService service;
    @Autowired
    private IZwipqService zwipqService;
    @Autowired
    private IServerSettingService serverSettingService;

    @RequestMapping(value = {"/sap/fevor/selectFevor2"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectfevor2(HttpServletRequest request){
        ResponseData rs = new ResponseData();
        List<Fevor> list = new ArrayList<>();
        list = service.selectFevor2(null);

        if (list.size() > 0){
            for (int i =0;i<list.size();i++){
                list.get(i).setTxt(list.get(i).getTxt().replaceAll("汽车机加",""));
            }
            rs.setRows(list);
            rs.setSuccess(true);
        }else{
            rs.setSuccess(false);
        }
        return rs;
    }

    /**
     * 产线在制队列汇总查询页面请求 918100064
     * @param request
     * @return
     */
    @RequestMapping(value = "/sap/fevor/queryZwipqhz")
    @ResponseBody
    public ResponseData queryZwipqhz(HttpServletRequest request) {
        String unitCode = request.getParameter("unitCode");
        String lineId = request.getParameter("lineId");
        String total = request.getParameter("total");
        IRequest requestContext = createRequestContext(request);
        List<Zwipqhz> list = new ArrayList<Zwipqhz>();
        List<Zwipqhz> list1 = service.queryZwipqhz(requestContext,unitCode,lineId);
        if(list1.size() > 0){
            for(int i=0;i<list1.size();i++){
                List<Zwipq> zwipq = new ArrayList<Zwipq>();
                List<Zwipq> zwipq2 = new ArrayList<Zwipq>();
                List<Zwipq> zwipq3 = new ArrayList<Zwipq>();
                List<Zwipq> zwipq4 = new ArrayList<Zwipq>();
                String werks = list1.get(i).getWerks();
                String pkgLineId = list1.get(i).getLineId();
                String matnr = list1.get(i).getMatnr2();
                ServerSetting serverSetting = serverSettingService.selectByLineId(werks,pkgLineId);
                if("Y".equals(total)){
                    zwipq = zwipqService.selectcharg(pkgLineId,matnr);
                    if(zwipq.size() > 0){
                        for(int j= 0;j<zwipq.size();j++){
                            Zwipqhz zwipqhz = new Zwipqhz();
                            zwipqhz.setWerks(werks);
                            zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                            zwipqhz.setTxt(list1.get(i).getTxt());
                            zwipqhz.setLineId(pkgLineId);
                            zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                            zwipqhz.setMatnr2(matnr);
                            zwipqhz.setMaktx(list1.get(i).getMaktx());
                            zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                            zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                            zwipqhz.setCharg(zwipq.get(j).getCharg());
                            Integer num = zwipqService.selectByzsxnum(pkgLineId,matnr,zwipq.get(j).getCharg()) + zwipqService.selectByzsxnum1(pkgLineId,matnr,zwipq.get(j).getCharg());
                            zwipqhz.setZsxnum(num + "");
                            list.add(zwipqhz);
                        }
                    }
                    zwipq2 = zwipqService.selectcharg2(pkgLineId,matnr);
                    if(zwipq2.size() > 0){
                        for(int j= 0;j<zwipq2.size();j++){
                            Zwipqhz zwipqhz = new Zwipqhz();
                            zwipqhz.setWerks(werks);
                            zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                            zwipqhz.setTxt(list1.get(i).getTxt());
                            zwipqhz.setLineId(pkgLineId);
                            zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                            zwipqhz.setMatnr2(matnr);
                            zwipqhz.setMaktx(list1.get(i).getMaktx());
                            zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                            zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                            zwipqhz.setCharg2(zwipq2.get(j).getCharg());
                            zwipqhz.setZsxnum2(zwipqService.selectByzsxnum2(pkgLineId,matnr,zwipq2.get(j).getCharg())+"");
                            list.add(zwipqhz);
                        }
                    }
                    zwipq3 = zwipqService.selectcharg3(pkgLineId,matnr);
                    if (zwipq3.size() > 0){
                        for (int j=0;j<zwipq3.size();j++){
                            Zwipqhz zwipqhz = new Zwipqhz();
                            zwipqhz.setWerks(werks);
                            zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                            zwipqhz.setTxt(list1.get(i).getTxt());
                            zwipqhz.setLineId(pkgLineId);
                            zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                            zwipqhz.setMatnr2(matnr);
                            zwipqhz.setMaktx(list1.get(i).getMaktx());
                            zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                            zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                            zwipqhz.setCharg3(zwipq3.get(j).getCharg());
                            zwipqhz.setCartonCode(zwipq3.get(j).getTpCode());
                            zwipqhz.setZsxnum3(zwipqService.selectByzsxnum3(pkgLineId,matnr,zwipqhz.getCharg3(),zwipqhz.getCartonCode()) + "");
                            list.add(zwipqhz);
                        }
                    }
                    zwipq4 = zwipqService.selectcharg4(pkgLineId,matnr);
                    if (zwipq4.size() > 0){
                        for (int j=0;j<zwipq4.size();j++){
                            Zwipqhz zwipqhz = new Zwipqhz();
                            zwipqhz.setWerks(werks);
                            zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                            zwipqhz.setTxt(list1.get(i).getTxt());
                            zwipqhz.setLineId(pkgLineId);
                            zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                            zwipqhz.setMatnr2(matnr);
                            zwipqhz.setMaktx(list1.get(i).getMaktx());
                            zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                            zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                            zwipqhz.setCharg4(zwipq4.get(j).getCharg());
                            zwipqhz.setCartonCode2(zwipq4.get(j).getTpCode());
                            zwipqhz.setZsxnum4(zwipqService.selectByzsxnum4(pkgLineId,matnr,zwipqhz.getCharg4(),zwipqhz.getCartonCode2()) + "");
                            list.add(zwipqhz);
                        }
                    }
                }else {
                    Zwipqhz zwipqhz = list1.get(i);
                    Integer zsxnum = zwipqService.selectByzsxnum(pkgLineId,matnr,null) + zwipqService.selectByzsxnum1(pkgLineId,matnr,null);
                    Integer zsxnum2 = zwipqService.selectByzsxnum2(pkgLineId,matnr,null);
                    Integer zsxnum3 = zwipqService.selectByzsxnum3(pkgLineId,matnr,null,null);
                    Integer zsxnum4 = zwipqService.selectByzsxnum4(pkgLineId,matnr,null,null);
                    if((zsxnum !=0) || (zsxnum2 !=0) || (zsxnum3 !=0) || (zsxnum4 !=0)){
                        zwipqhz.setWerks(werks);
                        zwipqhz.setUnitCode(list1.get(i).getUnitCode());
                        zwipqhz.setTxt(list1.get(i).getTxt());
                        zwipqhz.setLineId(pkgLineId);
                        zwipqhz.setDescriptions(list1.get(i).getDescriptions());
                        zwipqhz.setMatnr2(matnr);
                        zwipqhz.setMaktx(list1.get(i).getMaktx());
                        zwipqhz.setPmatnr(list1.get(i).getPmatnr());
                        zwipqhz.setPmaktx(list1.get(i).getPmaktx());
                        zwipqhz.setZsxnum(zsxnum+"");
                        zwipqhz.setZsxnum2(zsxnum2+"");
                        zwipqhz.setZsxnum3(zsxnum3+"");
                        zwipqhz.setZsxnum4(zsxnum4+"");
                        zwipqhz.setTotal((zsxnum+zsxnum2+zsxnum3+zsxnum4)+"");
                        list.add(zwipqhz);
                    }
                }
            }
        }
        return new ResponseData(list);
    }
}
