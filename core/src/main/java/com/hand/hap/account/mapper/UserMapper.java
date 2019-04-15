//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.account.mapper;

import com.hand.hap.account.dto.User;
import com.hand.hap.mybatis.common.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends Mapper<User> {
    User selectByUserName(String var1);

    List<User> selectByIdList(List<Long> var1);

    int updatePassword(@Param("userId") Long var1, @Param("password") String var2);

    int updateFirstLogin(@Param("userId") Long var1, @Param("status") String var2);

    int updateBasic(User var1);

    List<User> selectUsers(User var1);

    User selectById(@Param("userId") String userId);
}
