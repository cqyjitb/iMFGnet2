package yj.core.zudlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
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
}