package yj.core.dispatch.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dispatch.dto.Test;

import java.util.List;

public interface TestMapper extends Mapper<Test>{

    int updateTest(List<Test> test);

    int insertTest(Test test);

    int insertTestMany(List<Test> test);

    int deleteById(long id);

}