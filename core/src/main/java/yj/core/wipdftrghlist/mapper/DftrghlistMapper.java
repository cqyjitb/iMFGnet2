package yj.core.wipdftrghlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipdftrghlist.dto.Dftrghlist;

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
    Dftrghlist selectByCondition(@Param("werks") String werks,@Param("matnr") String matnr,@Param("line_id") String line_id,@Param("shift") String shift,@Param("gstrp") String gstrp);

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
}