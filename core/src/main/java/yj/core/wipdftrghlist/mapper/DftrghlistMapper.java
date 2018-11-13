package yj.core.wipdftrghlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipdftrghlist.dto.Dftrghlist;

import java.util.List;

public interface DftrghlistMapper extends Mapper<Dftrghlist>{
    /**
     * 按照工厂 机加生产线id 成品物料 机加班组 机加生产日期查询记录
     * @param werks
     * @param matnr
     * @param line_id
     * @param shift
     * @param gstrp
     * @return
     */
    List<Dftrghlist> selectByCondition(@Param("werks") String werks,@Param("matnr") String matnr,@Param("line_id") String line_id,@Param("shift") String shift,@Param("gstrp") String gstrp);

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
    int selectMaxItemByCondition(@Param("werks") String werks,@Param("matnr") String matnr,@Param("line_id") String line_id,@Param("shift") String shift,@Param("gstrp") String gstrp);
    /**
     * 根据 生产线id 班组 箱号 查询不良品处理记录
     * @param line_id
     * @param classgrp
     * @param zxhbar
     * @return
     */
    List<Dftrghlist> selectByLindIdAndZxhbar(@Param("line_id") String line_id,@Param("classgrp") String classgrp,@Param("zxhbar") String zxhbar);

    /**
     * 根据单号 行号 查询记录
     * @param recordid
     * @param item
     * @return
     */
    Dftrghlist selectByIdAndItem(@Param("recordid") String recordid,@Param("item") Long item);

    /**
     * 根据单号 行号 修改记录
     * @param dto
     * @return
     */
    int updateByIdAndItem(Dftrghlist dto);
}