package yj.core.qcaudithead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qcaudithead.dto.Qcaudithead;
import yj.core.qcauditlist.dto.Qcauditlist;

public interface IQcauditheadService extends IBaseService<Qcaudithead>, ProxySelf<IQcauditheadService>{
    /**
     *  根据工厂,生产日期 获取当前工厂当前生产日期 最大单号 917110140
     * @param werks
     * @param gstrp
     * @return
     */
    String selectMaxRecordId(String werks,String gstrp);

    /**
     *  插入新行 917110140
     * @param qcaudithead
     * @return
     */
    int insertNewRow(Qcaudithead qcaudithead);
}