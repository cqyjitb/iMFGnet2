package yj.kanb.vbgroupheader.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.vbgroupheader.dto.Vbgroupheader;

import java.util.List;

public interface VbgroupheaderMapper extends Mapper<Vbgroupheader> {
    /**
     * 表vb_group_h数据添加 918100064
     * @param dto
     */
    void insertVbGroupH(Vbgroupheader dto);

    /**
     * 看板显示组查询 918100064
     * @param dto
     * @return
     */
    List<Vbgroupheader> queryGroupH(Vbgroupheader dto);
}
