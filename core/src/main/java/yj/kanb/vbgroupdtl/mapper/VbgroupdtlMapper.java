package yj.kanb.vbgroupdtl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;

public interface VbgroupdtlMapper extends Mapper<Vbgroupdtl> {
    /**
     * 设备产线表添加 918100064
     * @param dto
     */
    void insertGroupDtl(Vbgroupdtl dto);
}
