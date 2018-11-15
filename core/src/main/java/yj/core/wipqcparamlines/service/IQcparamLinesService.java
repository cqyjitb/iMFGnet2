package yj.core.wipqcparamlines.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipqcparamlines.dto.QcparamLines;

public interface IQcparamLinesService extends IBaseService<QcparamLines>, ProxySelf<IQcparamLinesService>{

    /**
     *  根据生产线ID 查询产线参数配置信息 + 机加责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */
    QcparamLines selectForJj(Long line_id,String werks);

    /**
     *  根据生产线ID 查询产线参数配置信息 + 压铸责任部门 917110140
     * @param line_id
     * @param werks
     * @return
     */
    QcparamLines selectForYz(Long line_id,String werks);
}