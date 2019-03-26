package yj.core.Quality.controllers;

import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.HanaCon.HanaCon;
import yj.core.OracleConn.OracleConn;
import yj.core.Quality.dto.Quality;
import yj.core.Quality.dto.QualityParam;
import yj.core.Quality.dto.QualityTree;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cust.dto.Cust;
import yj.core.cust.service.ICustService;
import yj.core.dispatch.dto.InputLog;
import yj.core.dispatch.service.IInputLogService;
import yj.core.fevor.service.IFevorService;
import yj.core.marc.dto.Marc;
import yj.core.marc.service.IMarcService;
import yj.core.wipproductscfg.dto.ProductsCfg;
import yj.core.wipproductscfg.service.IProductsCfgService;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;
import yj.core.ztbc0002.dto.Ztbc0002;
import yj.core.ztbc0002.service.IZtbc0002Service;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.service.IZwipqService;


import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QualityController extends BaseController {
    //质量追溯报表
    @Autowired
    private IMarcService marcService;
    @Autowired
    private IZwipqService zwipqService;
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IXhcardService xhcardService;
    @Autowired
    private IProductsCfgService productsCfgService;
    @Autowired
    private ICustService custService;
    @Autowired
    private IZtbc0002Service ztbc0002Service;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IInputLogService inputLogService;

    @RequestMapping(value = "/wip/Qaulity/quertQaulityReport")
    @ResponseBody
    public ResponseData queryReport(HttpServletRequest request, QualityParam param) {
        ResponseData rs = new ResponseData();
        String unitCode = param.getUnitCode();
        String lineId = param.getLineId();
        String matnr = param.getMatnr();
        String tpcode = param.getTpcode();
        String zgjbar = param.getZgjbar();
        String jjgstrpbefor = param.getJjgstrpbefor();
        String jjgstrpafter = param.getJjgstrpafter();
        String jjbanz = param.getJjbanz();
        String jjcharg = param.getJjcharg();
        String yzgstrpbefor = param.getYzgstrpbefor();
        String yzgstrpafter = param.getYzgstrpafter();
        String yzbanz = param.getYzbanz();
        String yzcharg = param.getYzcharg();
        String werks = param.getWerks();
        String id = param.getId() == null ?"": param.getId();
        String project = param.getProject() == null ?"": param.getProject();
        String parentId = param.getParentId() == null ?"": param.getParentId();

        OracleConn oracleConn = new OracleConn();
        //根据条件获取装箱段段数据
        String sqlzx = "select a.main_id,a.item_code,a.barcode,c.carton_code,b.zpgdbar,b.zxhbar,b.rsnum,b.rspos,b.zsxjlh,b.line_id,b.created_by from MES_M264L01.wip_main_data  a"
                +" inner join  MES_M264L01.wip_pallet_sn_rel  b on a.main_id = b.main_id"
                +" inner join  MES_M264L01.mtl_barcode c on b.barcode_id = c.barcode_id";
        String where = " where b.line_id = " + "'" + param.getLineId() + "' ";
        if (param.getMatnr() != null){
            where = where + "and a.item_code = " + "'" + param.getMatnr() + "' ";
        }

        if (param.getTpcode() != null){
            where = where + "and c.carton_code = " + "'" + param.getTpcode() + "' ";
        }

        if (param.getZgjbar() != null){
            where = where + "and a.serial_no = " + "'" + param.getZgjbar() + "' ";
        }

        String sql = sqlzx + where;
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {
            list = oracleConn.select(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (list.size()>0){
            rs.setSuccess(true);
        }else{
            rs.setSuccess(true);
        }


        //根据装箱数据来获取报工平台上的部分数据
        //1 物料描述
        List<Marc> listmarc = new ArrayList<>();
        List<Zwipq> listzwipq = new ArrayList<>();
        List<Cardh> listcardhjj = new ArrayList<>();
        List<Cardh> listcardhyz = new ArrayList<>();
        List<Xhcard> listxhcard = new ArrayList<>();
        List<ProductsCfg> listcfg = new ArrayList<>();

          for (int i=0;i<list.size();i++){
              Marc marc = new Marc();
              marc = marcService.selectByMatnr(list.get(i).get("ITEM_CODE").toString());
              if (marc != null){
                  list.get(i).put("MAKTX2",marc.getMaktx() == null ? "": marc.getMaktx());//机加物料描述
                  if (!listmarc.contains(marc)){
                      listmarc.add(marc);
                  }
              }else{
                  list.get(i).put("MAKTX2","");//机加物料描述
                  if (!listmarc.contains(marc)){
                      listmarc.add(marc);
                  }
              }


              Zwipq zwipq = new Zwipq();
              zwipq = zwipqService.selectById(list.get(i).get("ZSXJLH") == null ?"":list.get(i).get("ZSXJLH").toString());
              if (zwipq != null){
                  //机加生产日期
                  list.get(i).put("BUDATJJ",zwipq.getCreationDate() == null ?"":zwipq.getCreationDate());
                  //机加班组
                  list.get(i).put("USERIDJJ",zwipq.getCreatedBy() == null ?"":zwipq.getCreatedBy());
                  if (zwipq.getCreatedBy() != null){
                      Employee employee =  employeeService.queryById(zwipq.getCreatedBy());
                      list.get(i).put("USERCODEJJ",employee.getEmployeeCode() == null ?"":employee.getEmployeeCode());
                  }else{
                      list.get(i).put("USERCODEJJ","");
                  }
                  listzwipq.add(zwipq);
              }else{
                  //机加生产日期
                  list.get(i).put("BUDATJJ","");
                  //机加班组
                  listzwipq.add(zwipq);
              }

              Cardh cardhjj = new Cardh();
              cardhjj = cardhService.selectByBarcode(list.get(i).get("ZPGDBAR") == null ?"":list.get(i).get("ZPGDBAR").toString());
              if (cardhjj != null){
                  list.get(i).put("ZBANZJJ",cardhjj.getShift() == null ?"":cardhjj.getShift());
                  list.get(i).put("AUFNRJJ",cardhjj.getAufnr() == null ?"":cardhjj.getAufnr());
                  listcardhjj.add(cardhjj);
              }else{
                  list.get(i).put("ZBANZJJ","");
                  listcardhjj.add(cardhjj);
              }


              Xhcard xhcard = new Xhcard();
              xhcard = xhcardService.selectByBacode(list.get(i).get("ZXHBAR").toString());
              listxhcard.add(xhcard);

              Cardh cardhyz = new Cardh();
              if (xhcard != null){
                  cardhyz = cardhService.selectByZxhbar(xhcard.getAufnr(),xhcard.getZxhnum());
                  list.get(i).put("ZPGDBARYZ",cardhyz.getZpgdbar() == null ?"":cardhyz.getZpgdbar());//压铸流转卡
                  list.get(i).put("MATNRYZ",cardhyz.getMatnr() == null ?"":cardhyz.getMatnr());//毛坯物料号
                  list.get(i).put("ZMNUM",xhcard.getZmnum() == null ?"":xhcard.getZmnum());//模号
                  list.get(i).put("ZBANZYZ",xhcard.getZscbc() == null ?"":xhcard.getZscbc());//压铸班组
                  list.get(i).put("ZSCXYZ",xhcard.getZscx() == null ?"":xhcard.getZscx());//压铸产线
                  list.get(i).put("ZBANB",cardhyz.getSfflg() == null ?"":cardhyz.getSfflg());//压铸班标
                  list.get(i).put("MTLBD",cardhyz.getMtlbd() == null ?"":cardhyz.getMtlbd());//铝材牌号

                  list.get(i).put("FSDAT",cardhyz.getActgstrp() == null ?"":cardhyz.getActgstrp());//首工序报工日期
                  list.get(i).put("FSTIM",cardhyz.getActst() == null ?"":cardhyz.getActst());//首工序报工时间

                  list.get(i).put("LTDAT",cardhyz.getActgltrp() == null ?"":cardhyz.getActgltrp());//末工序报工时间
                  list.get(i).put("LTTIM",cardhyz.getActdt() == null ?"":cardhyz.getActdt());//末工序报工日期

                  InputLog inputLog = new InputLog();
                  InputLog dto = new InputLog();
                  dto.setDispatch(cardhyz.getZpgdbar());
                  dto.setOperation("0010");
                  inputLog = inputLogService.queryByDispatchAndOperation(dto);
                  if (inputLog != null){
                      list.get(i).put("USERCODEYZ1",inputLog.getAttr1() == null ?"":inputLog.getAttr1());
                      list.get(i).put("USERCODEYZ2",inputLog.getAttr2() == null ?"":inputLog.getAttr2());
                      Employee employee1 = new Employee();
                      if (inputLog.getAttr1() != null){
                          employee1 = employeeService.queryByCode(employee1.getEmployeeCode());
                          if (employee1 != null){
                              list.get(i).put("USERIDYZ1",employee1.getEmployeeId() == null ?"":employee1.getEmployeeId());
                          }else{
                              list.get(i).put("USERIDYZ1","");
                          }

                      }

                      Employee employee2 = new Employee();
                      if (inputLog.getAttr2() != null){
                          employee2 = employeeService.queryByCode(employee2.getEmployeeCode());
                          if (employee2 != null){
                              list.get(i).put("USERIDYZ2",employee2.getEmployeeId() == null ?"":employee2.getEmployeeId());
                          }else{
                              list.get(i).put("USERIDYZ2","");
                          }

                      }

                  }else{
                      list.get(i).put("USERCODEYZ1","");
                      list.get(i).put("USERCODEYZ2","");
                      list.get(i).put("USERIDYZ1","");
                      list.get(i).put("USERIDYZ2","");
                  }

              }else{

                  list.get(i).put("ZPGDBARYZ","");//压铸流转卡
                  list.get(i).put("MATNRYZ","");//毛坯物料号
                  list.get(i).put("ZMNUM","");//模号
                  list.get(i).put("ZBANZYZ","");//压铸班组
                  list.get(i).put("ZSCXYZ","");//压铸产线
                  list.get(i).put("ZBANB","");//压铸班标
                  list.get(i).put("MTLBD","");//铝材牌号

                  list.get(i).put("FSDAT","");//首工序报工日期
                  list.get(i).put("FSTIM","");//首工序报工时间

                  list.get(i).put("LTDAT","");//末工序报工时间
                  list.get(i).put("LTTIM","");//末工序报工日期
              }




              Marc marcYz = new Marc();
              marcYz = marcService.selectByMatnr(cardhyz.getMatnr());
              if (marcYz != null){
                  list.get(i).put("MAKTX",marcYz.getMaktx() == null ?"":marcYz.getMaktx());
                  list.get(i).put("AUFNRYZ",cardhyz.getAufnr() == null ?"":cardhyz.getAufnr());
                  list.get(i).put("CHARGYZ",xhcard.getChargkc() == null ?"":xhcard.getChargkc());
                  listcardhyz.add(cardhyz);
              }else{
                  list.get(i).put("MAKTX","");
                  list.get(i).put("AUFNRYZ","");
                  list.get(i).put("CHARGYZ","");
                  listcardhyz.add(cardhyz);
              }


              ProductsCfg productsCfg = productsCfgService.selectByLineidAndPMatnr(list.get(i).get("LINE_ID").toString(),list.get(i).get("ITEM_CODE").toString());

              //客户 客户名称
//              list.get(i).put("KUNNR",productsCfg.getKunnr());
//              Cust cust = new Cust();
//              cust = custService.selectByKunnr(productsCfg.getKunnr());
//              list.get(i).put("NAME1",cust.getName1());

              //托盘信息
              Ztbc0002 ztbc0002 = new Ztbc0002();
              ztbc0002 = ztbc0002Service.selectByTpcode(list.get(i).get("CARTON_CODE").toString(),param.getWerks());
              list.get(i).put("ZTPBZ",ztbc0002.getZtxt2());


              //根据托盘码 查询SAP 成品入库时间
              HanaCon hanaCon = new HanaCon();
              List<Map<String, Object>> listztbc0004 = new ArrayList<Map<String,Object>>();
              String ztbc0004sql = "select * from SAPABAP1.ZTBC0004 where ZDATE =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(0,8) + "'" +
                      " AND ZTPNUM =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(9,13) + "'" +
                      " AND MANDT = '300' AND ZTYPE = '2'";
              try {
                  listztbc0004 = hanaCon.select(ztbc0004sql);
                  if (listztbc0004.size() > 0){
                      list.get(i).put("BUDATJJRK",listztbc0004.get(0).get("BUDAT").toString());
                      list.get(i).put("ZPTIMEJJRK",listztbc0004.get(0).get("ZPTIME").toString());

                      //入库物料凭证
                      list.get(i).put("MBLNRJJRK",listztbc0004.get(0).get("MBLNR").toString());
                      list.get(i).put("MJAHRJJRK",listztbc0004.get(0).get("MJAHR").toString());
                  }else{
                      list.get(i).put("BUDATJJRK","");
                      list.get(i).put("ZPTIMEJJRK","");
                      list.get(i).put("MBLNRJJRK","");
                      list.get(i).put("MJAHRJJRK","");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }

              //根据托盘条码获取出库信息
              List<Map<String, Object>> listztbc0005 = new ArrayList<Map<String,Object>>();
              String ztbc0005sql = "select * from SAPABAP1.ZTBC0005 where ZDATE =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(0,8) + "'" +
                      " AND ZTPNUM =" + "'" + list.get(i).get("CARTON_CODE").toString().substring(9,13) + "'" +
                      " AND MANDT = '300' AND ZGZBS = '3' AND ZZDEL = ''";

              try {
                  listztbc0005 = hanaCon.select(ztbc0005sql);
                  if (listztbc0005.size() > 0){
                      list.get(i).put("VBELNJJ",listztbc0005.get(0).get("VBELN").toString());
                      list.get(i).put("BUDATJJCK",listztbc0005.get(0).get("CHDAT").toString());
                      list.get(i).put("ZPTIMEJJCK",listztbc0005.get(0).get("CHTIM").toString());
                  }else{
                      list.get(i).put("VBELNJJ","");
                      list.get(i).put("BUDATJJCK","");
                      list.get(i).put("ZPTIMEJJCK","");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }

              //根据机加报确认号 计数器获取托盘条码批次
              List<Map<String, Object>> listztbc0018 = new ArrayList<Map<String,Object>>();
              String ztbc0018sql = "select * from SAPABAP1.ZTBC0018 where RUECK = " + "'" + list.get(i).get("RSNUM").toString()+ "'" +
                      " and RMZHL = " + "'" + list.get(i).get("RSPOS").toString() + "' and mandt = '300' and ZTRAN_TYPE = '01'";

              try {
                  listztbc0018 = hanaCon.select(ztbc0018sql);
                  if (listztbc0018.size() > 0){
                      list.get(i).put("CHARGJJ",listztbc0018.get(0).get("CHARG").toString());

                  }else{
                      list.get(i).put("CHARGJJ","");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }



          }
        //2 客户编码 客户名称

        //3 机加生产订单

        //4
        List<QualityTree> treeList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            QualityTree qualityTree = new QualityTree();
            qualityTree.setId(list.get(i).get("BARCODE").toString());
            qualityTree.setProject("产品二维码:"+list.get(i).get("BARCODE").toString());
            treeList.add(qualityTree);

            QualityTree qualityTree3 = new QualityTree();
            qualityTree3.setId(list.get(i).get("BARCODE").toString()+"3");
            qualityTree3.setParentId(list.get(i).get("BARCODE").toString());
            qualityTree3.setProject("机加部分");
            treeList.add(qualityTree3);


            QualityTree qualityTree31 = new QualityTree();
            qualityTree31.setId(list.get(i).get("BARCODE").toString()+"31");
            qualityTree31.setParentId(list.get(i).get("BARCODE").toString()+"3");
            qualityTree31.setContent(list.get(i).get("CARTON_CODE").toString());
            qualityTree31.setProject("托盘码");
            treeList.add(qualityTree31);

            QualityTree qualityTree35 = new QualityTree();
            qualityTree35.setId(list.get(i).get("BARCODE").toString()+"35");
            qualityTree35.setParentId(list.get(i).get("BARCODE").toString()+"3");
            qualityTree35.setContent(list.get(i).get("ITEM_CODE").toString() + " (" + list.get(i).get("MAKTX2").toString() + ")");
            qualityTree35.setProject("产品编码");
            treeList.add(qualityTree35);

//
            QualityTree qualityTree32 = new QualityTree();
            qualityTree32.setId(list.get(i).get("BARCODE").toString()+"32");
            qualityTree32.setParentId(list.get(i).get("BARCODE").toString()+"3");
            qualityTree32.setContent(list.get(i).get("ZSXJLH").toString());
            qualityTree32.setProject("上线记录号");
            treeList.add(qualityTree32);

            QualityTree qualityTree34 = new QualityTree();
            qualityTree34.setId(list.get(i).get("BARCODE").toString()+"34");
            qualityTree34.setParentId(list.get(i).get("BARCODE").toString()+"3");
            qualityTree34.setContent(list.get(i).get("USERCODEJJ").toString());
            qualityTree34.setProject("机加上线操作者");
            treeList.add(qualityTree34);
//
            QualityTree qualityTree33 = new QualityTree();
            qualityTree33.setId(list.get(i).get("BARCODE").toString()+"33");
            qualityTree33.setParentId(list.get(i).get("BARCODE").toString()+"3");
            qualityTree33.setContent(list.get(i).get("ZPGDBAR").toString());
            qualityTree33.setProject("机加流转卡");
            treeList.add(qualityTree33);
//



            QualityTree qualityTree2 = new QualityTree();
            qualityTree2.setId(list.get(i).get("BARCODE").toString()+"2");
            qualityTree2.setParentId(list.get(i).get("BARCODE").toString());
            qualityTree2.setProject("压铸部分");
            treeList.add(qualityTree2);


            QualityTree qualityTree21 = new QualityTree();
            qualityTree21.setId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree21.setParentId(list.get(i).get("BARCODE").toString()+"2");
            qualityTree21.setContent(list.get(i).get("ZPGDBARYZ").toString());
            qualityTree21.setProject("压铸流转卡");
            treeList.add(qualityTree21);
//
            QualityTree qualityTree211 = new QualityTree();
            qualityTree211.setId(list.get(i).get("BARCODE").toString()+"211");
            qualityTree211.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree211.setContent(list.get(i).get("ZSCXYZ").toString());
            qualityTree211.setProject("压铸产线");
            treeList.add(qualityTree211);
//

            QualityTree qualityTree212 = new QualityTree();
            qualityTree212.setId(list.get(i).get("BARCODE").toString()+"212");
            qualityTree212.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree212.setContent(list.get(i).get("ZMNUM").toString());
            qualityTree212.setProject("模号");
            treeList.add(qualityTree212);
//


            QualityTree qualityTree213 = new QualityTree();
            qualityTree213.setId(list.get(i).get("BARCODE").toString()+"213");
            qualityTree213.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree213.setContent(list.get(i).get("ZBANZYZ").toString());
            qualityTree213.setProject("压铸班组");
            treeList.add(qualityTree213);
//

            QualityTree qualityTree214 = new QualityTree();
            qualityTree214.setId(list.get(i).get("BARCODE").toString()+"214");
            qualityTree214.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree214.setContent(list.get(i).get("ZBANB").toString());
            qualityTree214.setProject("压铸班标");
            treeList.add(qualityTree214);
//


            QualityTree qualityTree215 = new QualityTree();
            qualityTree215.setId(list.get(i).get("BARCODE").toString()+"215");
            qualityTree215.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree215.setContent(list.get(i).get("MTLBD").toString());
            qualityTree215.setProject("铝材牌号");
            treeList.add(qualityTree215);
//

            QualityTree qualityTree216 = new QualityTree();
            qualityTree216.setId(list.get(i).get("BARCODE").toString()+"216");
            qualityTree216.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree216.setContent(list.get(i).get("FSDAT").toString()+" "+list.get(i).get("FSTIM").toString());
            qualityTree216.setProject("首工序报工时间");
            treeList.add(qualityTree216);
//

//
            QualityTree qualityTree217 = new QualityTree();
            qualityTree217.setId(list.get(i).get("BARCODE").toString()+"217");
            qualityTree217.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree217.setContent(list.get(i).get("LTDAT").toString()+ " " + list.get(i).get("LTTIM").toString());
            qualityTree217.setProject("末工序报工时间");
            treeList.add(qualityTree217);



            QualityTree qualityTree218 = new QualityTree();
            qualityTree218.setId(list.get(i).get("BARCODE").toString()+"218");
            qualityTree218.setParentId(list.get(i).get("BARCODE").toString()+"21");
            qualityTree218.setContent(list.get(i).get("USERCODEYZ1").toString() +"" + list.get(i).get("USERCODEYZ2"));
            qualityTree218.setProject("报工人");
            treeList.add(qualityTree218);
//
//
            QualityTree qualityTree22 = new QualityTree();
            qualityTree22.setId(list.get(i).get("BARCODE").toString()+"22");
            qualityTree22.setParentId(list.get(i).get("BARCODE").toString()+"2");
            qualityTree22.setContent(list.get(i).get("MATNRYZ").toString()+" ("+list.get(i).get("MAKTX")+")");
            qualityTree22.setProject("毛坯物料");
            treeList.add(qualityTree22);


            QualityTree qualityTree23 = new QualityTree();
            qualityTree23.setId(list.get(i).get("BARCODE").toString()+"23");
            qualityTree23.setParentId(list.get(i).get("BARCODE").toString()+"2");
            qualityTree23.setContent(list.get(i).get("AUFNRYZ").toString());
            qualityTree23.setProject("压铸流转卡");
            treeList.add(qualityTree23);
//

            QualityTree qualityTree24 = new QualityTree();
            qualityTree24.setId(list.get(i).get("BARCODE").toString()+"24");
            qualityTree24.setParentId(list.get(i).get("BARCODE").toString()+"2");
            qualityTree24.setContent(list.get(i).get("CHARGYZ").toString());
            qualityTree24.setProject("压铸批次");
            treeList.add(qualityTree24);

            QualityTree qualityTree4 = new QualityTree();
            qualityTree4.setId(list.get(i).get("BARCODE").toString()+"4");
            qualityTree4.setParentId(list.get(i).get("BARCODE").toString());
            qualityTree4.setProject("物流信息");
            treeList.add(qualityTree4);

            QualityTree qualityTree41 = new QualityTree();
            qualityTree41.setId(list.get(i).get("BARCODE").toString()+"41");
            qualityTree41.setParentId(list.get(i).get("BARCODE").toString() + "4");
            qualityTree41.setProject("成品部分");
            treeList.add(qualityTree41);

            QualityTree qualityTree411 = new QualityTree();
            qualityTree411.setId(list.get(i).get("BARCODE").toString()+"411");
            qualityTree411.setParentId(list.get(i).get("BARCODE").toString() + "41");
            qualityTree411.setProject("成品入库时间");
            qualityTree411.setContent("日期:" + list.get(i).get("BUDATJJRK").toString() + " "+"时间:" + list.get(i).get("ZPTIMEJJRK").toString());
            treeList.add(qualityTree411);

            QualityTree qualityTree412 = new QualityTree();
            qualityTree412.setId(list.get(i).get("BARCODE").toString()+"412");
            qualityTree412.setParentId(list.get(i).get("BARCODE").toString() + "41");
            qualityTree412.setProject("成品入库凭证");
            qualityTree412.setContent("凭证年度："+list.get(i).get("MJAHRJJRK").toString() + " " + "凭证号:"+list.get(i).get("MBLNRJJRK").toString());
            treeList.add(qualityTree412);

            QualityTree qualityTree413 = new QualityTree();
            qualityTree413.setId(list.get(i).get("BARCODE").toString()+"413");
            qualityTree413.setParentId(list.get(i).get("BARCODE").toString() + "41");
            qualityTree413.setProject("成品出库时间");
            qualityTree413.setContent("日期:" + list.get(i).get("BUDATJJCK").toString() + " " + "时间:" + list.get(i).get("ZPTIMEJJCK").toString());
            treeList.add(qualityTree413);

            QualityTree qualityTree414 = new QualityTree();
            qualityTree414.setId(list.get(i).get("BARCODE").toString()+"414");
            qualityTree414.setParentId(list.get(i).get("BARCODE").toString() + "41");
            qualityTree414.setProject("成品交货单");
            qualityTree414.setContent(list.get(i).get("VBELNJJ").toString());
            treeList.add(qualityTree414);

            QualityTree qualityTree415 = new QualityTree();
            qualityTree415.setId(list.get(i).get("BARCODE").toString()+"415");
            qualityTree415.setParentId(list.get(i).get("BARCODE").toString() + "41");
            qualityTree415.setProject("成品批次");
            qualityTree415.setContent(list.get(i).get("CHARGJJ").toString());
            treeList.add(qualityTree415);

            QualityTree qualityTree5 = new QualityTree();
            qualityTree5.setId(list.get(i).get("BARCODE").toString()+"5");
            qualityTree5.setParentId(list.get(i).get("BARCODE").toString());
            qualityTree5.setProject("分零件信息");
            treeList.add(qualityTree5);

        }

        if (!id.equals("") && project.equals("分零件信息")){
            //根据ID 获取分零件信息
            for (int i = 0;i<list.size();i++){
                if (list.get(i).get("BARCODE").toString().equals(parentId)){
                    String aufnrjj = list.get(i).get("AUFNRJJ").toString();
                    if (aufnrjj.length()< 12){
                        for (int j=0;j<=12-aufnrjj.length();j++){
                            aufnrjj = "0" + aufnrjj;
                        }
                    }

                    if (!aufnrjj.equals("")){
                        String sqlflj = "select DISTINCT r.matnr, mk.maktx from sapabap1.resb r " +
                                "inner join sapabap1.mara m on r.MATNR = m.MATNR " +
                                " INNER JOIN SAPABAP1.MAKT mk ON r.matnr = mk.matnr " +
                                " INNER JOIN SAPABAP1.AFKO AF ON af.aufpl = r.aufpl " +
                                " where af.aufnr =" + "'" + aufnrjj + "'" + " AND m.MATKL = '3102' and af.mandt = '300'";
                        HanaCon hanaCon = new HanaCon();
                        List<Map<String, Object>> listresb = new ArrayList<Map<String,Object>>();

                        try {
                            listresb = hanaCon.select(sqlflj);
                            if (listresb.size() > 0){
                                for (int k =0;k < listresb.size();k ++){
                                    QualityTree qualityTreetmp = new QualityTree();
                                    qualityTreetmp.setId(id + k);
                                    qualityTreetmp.setParentId(id);
                                    qualityTreetmp.setContent("物料编码: " + listresb.get(k).get("MATNR").toString() + "  描述:" + listresb.get(k).get("MAKTX").toString());
                                    qualityTreetmp.setProject("组件" + (k+1));
                                    treeList.add(qualityTreetmp);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }

        rs.setRows(treeList);
        return rs;
    }
}
