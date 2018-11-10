package yj.core.wipqcparamlines.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipqcparamlines.dto.QcparamLines;

public interface QcparamLinesMapper extends Mapper<QcparamLines>{

    /**
     *  根据生产线ID 查询产线参数配置信息 + 机加责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */
    QcparamLines selectForJj(@Param("lineId") Long line_id, @Param("werks") String werks);


    /**
     *  根据生产线ID 查询产线参数配置信息 + 压铸责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */

    QcparamLines selectForYz(@Param("lineId") Long line_id, @Param("werks") String werks);
}