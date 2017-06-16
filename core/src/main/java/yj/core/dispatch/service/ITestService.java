package yj.core.dispath.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dispath.dto.Test;

import java.util.List;

public interface ITestService extends IBaseService<Test>, ProxySelf<ITestService>{

    int updateTest(IRequest requestContext,List<Test> test);

    int insertTestMany(IRequest requestContext,List<Test> test);

    int deleteById(long id);

    int insertTest(Test test);
}