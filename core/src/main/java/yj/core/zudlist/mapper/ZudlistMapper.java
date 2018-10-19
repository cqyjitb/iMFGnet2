package yj.core.zudlist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zudlist.dto.Zudlist;

public interface ZudlistMapper extends Mapper<Zudlist>{
    int insertItem(Zudlist zudlist);
}