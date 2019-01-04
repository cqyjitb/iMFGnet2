package yj.core.qcauditlist.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qcauditlist.dto.Qcauditlist;

import java.util.List;

public interface IQcauditlistService extends IBaseService<Qcauditlist>, ProxySelf<IQcauditlistService>{
    /**
     *  插入新数据记录 917110140
     * @param list
     * @return
     */
    int insertNewRow(List<Qcauditlist> list);

}