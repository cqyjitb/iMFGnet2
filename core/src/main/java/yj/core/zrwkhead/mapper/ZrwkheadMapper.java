package yj.core.zrwkhead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zrwkhead.dto.Zrwkhead;
import yj.core.zudhead.dto.Zudhead;

public interface ZrwkheadMapper extends Mapper<Zrwkhead>{
    String selectMaxNo(String curdat);
    int insertHead(Zrwkhead zrwkhead);
}