package yj.core.zudlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface ZudlistMapper extends Mapper<Zudlist>{
    int insertItem(Zudlist zudlist);

    /**
     * 不合格品审理单1查询 918100064
     * @param dto
     * @return
     */
    List<Zudlist> selectZudlist(Zudlist dto);

    /**
     * 根据物料编码、不良原因查询数据的条数 918100064
     * @param matnr2
     * @param zissuetxt
     * @return
     */
    int selectDftdtl(@Param("matnr2")String matnr2, @Param("zissuetxt")String zissuetxt);
}