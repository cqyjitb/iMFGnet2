package yj.core.qcauditlist.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qcauditlist.dto.Qcauditprocessdtl;

import java.util.List;

public interface IQcauditprocessdtlService extends IBaseService<Qcauditprocessdtl>, ProxySelf<IQcauditprocessdtlService>{

    /**
     *  更新表数据 917110140
     * @param list
     * @return
     */
    int insertData(List<Qcauditprocessdtl> list);

    /**
     *  删除记录 917110140
     * @param recrodid
     * @return
     */
    int deleteById(String werks,String recrodid);

    /**
     *  根据工厂 id 查询已经保存的行记录 917110140
     * @param werks
     * @param recordid
     * @return
     */
    List<Qcauditprocessdtl> selectById(String werks,String recordid);
}