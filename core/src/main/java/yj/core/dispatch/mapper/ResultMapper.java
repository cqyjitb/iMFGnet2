package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dispatch.dto.Result;

public interface ResultMapper extends Mapper<Result>{

    int queryResultById(Long id);//根据ID查询数据confirmation_result

    int insertResult(Result result);//插入一条数据到confirmation_result

}