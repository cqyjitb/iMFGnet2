package yj.core.qcaudithead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.qcaudithead.dto.Qcaudithead;

public interface QcauditheadMapper extends Mapper<Qcaudithead>{
    /**
     *  根据工厂,生产日期 获取当前工厂当前生产日期 最大单号 917110140
     * @param werks
     * @param gstrp
     * @return
     */
    String selectMaxRecordId(@Param("werks") String werks, @Param("gstrp") String gstrp);

    /**
     *  插入新行 917110140
     * @param qcaudithead
     * @return
     */
    int insertNewRow(Qcaudithead qcaudithead);
}