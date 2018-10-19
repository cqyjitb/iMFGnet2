package yj.core.zudhead.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.zudhead.dto.Zudhead;

public interface ZudheadMapper extends Mapper<Zudhead>{
    String selectMaxNo(String curdat);
    int insertHead(Zudhead zudhead);
}