package yj.core.outsrgreceipt.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;

import java.util.List;

public interface IOutsrgreceiptService extends IBaseService<Outsrgreceipt>, ProxySelf<IOutsrgreceiptService>{
    /**
     *  根据采购订单获取外协发料数据记录 917110140
     * @param ebeln
     * @return
     */
    List<Outsrgreceipt> selectByEbeln(String ebeln);
}