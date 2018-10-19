package yj.core.zrwklist.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zrwklist.dto.Zrwklist;

public interface ZrwklistMapper extends Mapper<Zrwklist>{
    int insertItem(Zrwklist list);
}