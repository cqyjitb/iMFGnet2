package yj.core.outsrgreceipt.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;

import java.util.List;

public interface OutsrgreceiptMapper extends Mapper<Outsrgreceipt>{
    /**
     *  根据采购订单获取外协发料数据记录 917110140
     * @param ebeln
     * @return
     */
    List<Outsrgreceipt> selectByEbeln(String ebeln);
}