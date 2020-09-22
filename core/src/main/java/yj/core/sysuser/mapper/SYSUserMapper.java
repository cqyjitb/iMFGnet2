package yj.core.sysuser.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.sysuser.dto.SYSUser;

public interface SYSUserMapper extends Mapper<SYSUser> {

    SYSUser queryUserById(@Param("id") Long id);
}
