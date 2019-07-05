package yj.kanb.vbgroupdtl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;

import java.util.List;

public interface VbgroupdtlMapper extends Mapper<Vbgroupdtl> {
    /**
     * 设备产线表添加 918100064
     * @param dto
     */
    void insertGroupDtl(Vbgroupdtl dto);

    /**
     * 设备产线删除 918100064
     * @param dto
     */
    void deleteGroupDtl(Vbgroupdtl dto);

    /**
     * 根据设备ID查询 918100064
     * @param eqId
     * @return
     */
    List<Vbgroupdtl> queryByEqId(@Param("eqId")String eqId);

    /**
     * 根据资源组ID查询 918100064
     * @param groupId
     * @return
     */
    List<Vbgroupdtl> queryByGroupId(@Param("groupId")String groupId);
}
