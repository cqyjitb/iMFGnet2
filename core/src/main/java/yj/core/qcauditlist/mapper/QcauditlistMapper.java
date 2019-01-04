package yj.core.qcauditlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.qcauditlist.dto.Qcauditlist;

import java.util.List;

public interface QcauditlistMapper extends Mapper<Qcauditlist>{
    /**
     *  插入新数据记录 917110140
     * @param qcauditlist
     * @return
     */
    int insertNewRow(Qcauditlist qcauditlist);
}