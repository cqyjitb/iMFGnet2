package yj.kanb.vblinegroupheader.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;

import java.util.List;

public interface VblinegroupheaderMapper extends Mapper<Vblinegroupheader> {
    /**
     * 看板车间产线组头表查询 918100064
     * @param dto
     * @return
     */
    List<Vblinegroupheader> selectLineGroupH(Vblinegroupheader dto);

    /**
     * 看板车间产线组头表删除 918100064
     * @param dto
     */
    void deleteLineGroupH(Vblinegroupheader dto);

    /**
     * 看板车间产线组头表修改 918100064
     * @param dto
     */
    void updateLineGroupH(Vblinegroupheader dto);

    /**
     * 看板车间产线组头表添加 918100064
     * @param dto
     */
    void insertLineGroupH(Vblinegroupheader dto);
}
