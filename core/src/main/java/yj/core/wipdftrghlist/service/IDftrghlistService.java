package yj.core.wipdftrghlist.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipdftrghlist.dto.Dftrghlist;

import java.util.List;

public interface IDftrghlistService extends IBaseService<Dftrghlist>, ProxySelf<IDftrghlistService>{
    /**
     * 按照工厂 机加生产线id 成品物料 机加班组 机加生产日期查询记录
     * @param werks
     * @param matnr
     * @param line_id
     * @param shift
     * @param gstrp
     * @return
     */
    Dftrghlist selectByCondition(String werks,String matnr,String line_id,String shift,String gstrp);

    /**
     * 插入新记录
     * @param dftrghlist
     * @return
     */
    int insertDftrghlist(Dftrghlist dftrghlist);

    /**
     * 更新记录
     * @param dftrghlist
     * @return
     */
    int updateDftrghlist(Dftrghlist dftrghlist);

    /**
     * 根据条件 查询符合条件的记录中的最大行号
     * @param werks
     * @param matnr
     * @param line_id
     * @param shift
     * @param gstrp
     * @return
     */
    int selectMaxItemByCondition(String werks,String matnr,String line_id,String shift,String gstrp);

    /**
     * 根据 生产线id 班组 箱号 查询不良品处理记录
     * @param line_id
     * @param classgrp
     * @param zxhbar
     * @return
     */
    List<Dftrghlist> selectByLindIdAndZxhbar(String line_id,String classgrp,String zxhbar);

    /**
     * 根据单号 行号 查询记录
     * @param recordid
     * @param item
     * @return
     */
    Dftrghlist selectByIdAndItem(String recordid,Long item);

    /**
     * 根据单号 行号 修改记录
     * @param dto
     * @return
     */
    int updateByIdAndItem(Dftrghlist dto);
}