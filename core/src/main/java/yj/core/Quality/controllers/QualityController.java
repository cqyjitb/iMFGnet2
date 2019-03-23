package yj.core.Quality.controllers;

import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.OracleConn.OracleConn;
import yj.core.Quality.dto.Quality;
import yj.core.Quality.dto.QualityParam;
import yj.core.Quality.dto.QualityTree;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.service.ICardhService;
import yj.core.cust.dto.Cust;
import yj.core.cust.service.ICustService;
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
            rs.setSuccess(false);
            rs.setMessage("未能获取到符合条件的数据！");
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
              list.get(i).put("MAKTX2",marc.getMaktx());//机加物料描述
              if (!listmarc.contains(marc)){
                  listmarc.add(marc);
              }

              Zwipq zwipq = new Zwipq();
              zwipq = zwipqService.selectById(list.get(i).get("ZSXJLH").toString());
              //机加生产日期
              list.get(i).put("BUDATJJ",zwipq.getCreationDate());
              //机加班组
              listzwipq.add(zwipq);

              Cardh cardhjj = new Cardh();
              cardhjj = cardhService.selectByBarcode(list.get(i).get("ZPGDBAR").toString());
              list.get(i).put("ZBANZJJ",cardhjj.getShift());
              listcardhjj.add(cardhjj);

              Xhcard xhcard = new Xhcard();
              xhcard = xhcardService.selectByBacode(list.get(i).get("ZXHBAR").toString());
              listxhcard.add(xhcard);

              Cardh cardhyz = new Cardh();
              cardhyz = cardhService.selectByZxhbar(xhcard.getAufnr(),xhcard.getZxhnum());
              list.get(i).put("ZPGDBARYZ",cardhyz.getZpgdbar());//压铸流转卡
              list.get(i).put("MATNRYZ",cardhyz.getMatnr());//毛坯物料号
              list.get(i).put("ZMNUM",xhcard.getZmnum());//模号
              list.get(i).put("ZBANZYZ",xhcard.getZscbc());//压铸班组
              list.get(i).put("ZSCXYZ",xhcard.getZscx());//压铸产线
              list.get(i).put("ZBANB",cardhyz.getSfflg());//压铸班标
              list.get(i).put("MTLBD",cardhyz.getMtlbd());//铝材牌号

              list.get(i).put("FSDAT",cardhyz.getActgstrp());//首工序报工日期
              list.get(i).put("FSTIM",cardhyz.getActst());//首工序报工时间

              list.get(i).put("LTDAT",cardhyz.getActgltrp());//末工序报工时间
              list.get(i).put("LTTIM",cardhyz.getActdt());//末工序报工日期

              Marc marcYz = new Marc();
              marcYz = marcService.selectByMatnr(cardhyz.getMatnr());
              list.get(i).put("MAKTX",marcYz.getMaktx());
              list.get(i).put("AUFNRYZ",cardhyz.getAufnr());
              list.get(i).put("CHARGYZ",xhcard.getChargkc());
              listcardhyz.add(cardhyz);

              ProductsCfg productsCfg = productsCfgService.selectByLineidAndPMatnr(list.get(i).get("LINE_ID").toString(),list.get(i).get("ITEM_CODE").toString());

              //客户 客户名称
//              list.get(i).put("KUNNR",productsCfg.getKunnr());
//              Cust cust = new Cust();
//              cust = custService.selectByKunnr(productsCfg.getKunnr());
//              list.get(i).put("NAME1",cust.getName1());

              //托盘信息
              Ztbc0002 ztbc0002 = new Ztbc0002();
              ztbc0002 = ztbc0002Service.selectByTpcode(list.get(i).get("CARTON_CODE").toString(),param.getWerks());
              list.get(i).put("",ztbc0002.getZtxt2());

          }
        //2 客户编码 客户名称

        //3 机加生产订单

        //4
        List<QualityTree> treeList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            QualityTree qualityTree = new QualityTree();
            qualityTree.setId(list.get(i).get("BARCODE").toString());
            treeList.add(qualityTree);

            QualityTree qualityTree3 = new QualityTree();
            qualityTree3.setId("机加部分");
            qualityTree3.setParentId(list.get(i).get("BARCODE").toString());
            treeList.add(qualityTree3);

            QualityTree qualityTree35 = new QualityTree();
            qualityTree35.setId("产品编码");
            qualityTree35.setParentId("机加部分");
            qualityTree35.setZpgdbar(list.get(i).get("ITEM_CODE").toString() + " (" + list.get(i).get("MAKTX2").toString() + ")");
            treeList.add(qualityTree35);

            QualityTree qualityTree31 = new QualityTree();
            qualityTree31.setId("托盘码");
            qualityTree31.setParentId("机加部分");
            qualityTree31.setZpgdbar(list.get(i).get("CARTON_CODE").toString());
            treeList.add(qualityTree31);

            QualityTree qualityTree32 = new QualityTree();
            qualityTree32.setId("上线记录号");
            qualityTree32.setParentId("机加部分");
            qualityTree32.setZpgdbar(list.get(i).get("ZSXJLH").toString());
            treeList.add(qualityTree32);

            QualityTree qualityTree33 = new QualityTree();
            qualityTree33.setId("机加流转卡");
            qualityTree33.setParentId("机加部分");
            qualityTree33.setZpgdbar(list.get(i).get("ZPGDBAR").toString());
            treeList.add(qualityTree33);

            QualityTree qualityTree34 = new QualityTree();
            qualityTree34.setId("毛坯箱号");
            qualityTree34.setParentId("机加部分");
            qualityTree34.setZpgdbar(list.get(i).get("ZXHBAR").toString());
            treeList.add(qualityTree34);


            QualityTree qualityTree2 = new QualityTree();
            qualityTree2.setId("压铸部分");
            qualityTree2.setParentId(list.get(i).get("BARCODE").toString());
            treeList.add(qualityTree2);


            QualityTree qualityTree21 = new QualityTree();
            qualityTree21.setId("压铸流转卡");
            qualityTree21.setParentId("压铸部分");
            qualityTree21.setZpgdbar(list.get(i).get("ZPGDBARYZ").toString());
            treeList.add(qualityTree21);

            QualityTree qualityTree211 = new QualityTree();
            qualityTree211.setId("压铸流转卡");
            qualityTree211.setParentId("压铸产线");
            qualityTree211.setZpgdbar(list.get(i).get("ZSCXYZ").toString());
            treeList.add(qualityTree211);

            QualityTree qualityTree212 = new QualityTree();
            qualityTree212.setId("模号");
            qualityTree212.setParentId("压铸流转卡");
            qualityTree212.setZpgdbar(list.get(i).get("ZMNUM").toString());
            treeList.add(qualityTree212);

            QualityTree qualityTree213 = new QualityTree();
            qualityTree213.setId("压铸班组");
            qualityTree213.setParentId("压铸流转卡");
            qualityTree213.setZpgdbar(list.get(i).get("ZBANZYZ").toString());
            treeList.add(qualityTree213);

            QualityTree qualityTree214 = new QualityTree();
            qualityTree214.setId("压铸班标");
            qualityTree214.setParentId("压铸流转卡");
            qualityTree214.setZpgdbar(list.get(i).get("ZBANB").toString());
            treeList.add(qualityTree214);

            QualityTree qualityTree215 = new QualityTree();
            qualityTree215.setId("铝材牌号");
            qualityTree215.setParentId("压铸流转卡");
            qualityTree215.setZpgdbar(list.get(i).get("MTLBD").toString());
            treeList.add(qualityTree215);

            QualityTree qualityTree216 = new QualityTree();
            qualityTree216.setId("首工序报工时间");
            qualityTree216.setParentId("压铸流转卡");
            qualityTree216.setZpgdbar(list.get(i).get("FSDAT").toString()+ "" + list.get(i).get("FSTIM").toString());
            treeList.add(qualityTree216);

            QualityTree qualityTree217 = new QualityTree();
            qualityTree217.setId("末工序报工时间");
            qualityTree217.setParentId("压铸流转卡");
            qualityTree217.setZpgdbar(list.get(i).get("LTDAT").toString()+ "" + list.get(i).get("LTTIM").toString());
            treeList.add(qualityTree217);

            QualityTree qualityTree218 = new QualityTree();
            qualityTree218.setId("报工人");
            qualityTree218.setParentId("压铸流转卡");
            qualityTree218.setZpgdbar("917110140"+" 张滔");
            treeList.add(qualityTree218);



            QualityTree qualityTree22 = new QualityTree();
            qualityTree22.setId("毛坯物料");
            qualityTree22.setParentId("压铸部分");
            qualityTree22.setZpgdbar(list.get(i).get("MATNRYZ").toString()+" ("+list.get(i).get("MAKTX")+")");
            treeList.add(qualityTree22);

            QualityTree qualityTree23 = new QualityTree();
            qualityTree23.setId("压铸流转卡");
            qualityTree23.setParentId("生产订单");
            qualityTree23.setZpgdbar(list.get(i).get("AUFNRYZ").toString());
            treeList.add(qualityTree23);

            QualityTree qualityTree24 = new QualityTree();
            qualityTree24.setId("压铸批次");
            qualityTree24.setParentId("压铸部分");
            qualityTree24.setZpgdbar(list.get(i).get("CHARGYZ").toString());
            treeList.add(qualityTree24);

        }

        rs.setRows(treeList);
        return rs;
    }
}
