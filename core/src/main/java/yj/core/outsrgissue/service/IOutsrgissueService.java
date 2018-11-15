package yj.core.outsrgissue.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.outsrgissue.dto.Outsrgissue;

import java.util.List;

public interface IOutsrgissueService extends IBaseService<Outsrgissue>, ProxySelf<IOutsrgissueService>{
    /**
     *  根据 外协采购订单号 行号 工厂 物料 供应商编码 查询采购订单已发货记录  917110140
     * @param ebeln
     * @param ebelp
     * @param werks
     * @param lifnr
     * @param matnr
     * @return
     */
    List<Outsrgissue> selectByContidion(String ebeln,String ebelp,String werks,String lifnr,String matnr);

    /**
     *  根据发料单号查询记录 按照行号 降序排序 917110140
     * @param issuenm
     * @return
     */
    List<Outsrgissue> selectByIssuenmDesc(String issuenm);

    /**
     *  插入新记录 917110140
     * @param outsrgissue
     * @return
     */
    int insertNewRow(Outsrgissue outsrgissue);

    /**
     *  根据流转卡号查询外协发料记录
     * @param zpgdbar
     * @return
     */
    Outsrgissue selectByBarcode(String zpgdbar,String status);
}