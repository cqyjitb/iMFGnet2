package yj.core.outsrgreceipt.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.outsrgreceipt.dto.Outsrgreceipt;

import java.util.List;

public interface OutsrgreceiptMapper extends Mapper<Outsrgreceipt>{
    /**
     *  根据采购订单获取外协发料数据记录 917110140
     * @param ebeln
     * @return
     */
    List<Outsrgreceipt> selectByEbeln(String ebeln);

    /**
     *  根据流转卡号 状态 查询收货记录 917110140
     * @param zpgdbar
     * @param status
     * @return
     */
    Outsrgreceipt selectByZpgdbarAndStatus(@Param("zpgdbar") String zpgdbar, @Param("status") String status);
}