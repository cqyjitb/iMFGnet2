package yj.core.dispath.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.dispath.dto.Test;
import yj.core.dispath.mapper.TestMapper;
import yj.core.dispath.service.ITestService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TestServiceImpl extends BaseServiceImpl<Test> implements ITestService{

    @Autowired
    TestMapper testMapper;

    @Override
    public  int updateTest(IRequest requestContext, List<Test> test){
        return testMapper.updateTest(test);
    };

     public  int insertTest(Test test){
         return testMapper.insertTest(test);
     };

     public  int insertTestMany(IRequest requestContext,List<Test> test){
         return testMapper.insertTestMany(test);
     };

     public  int deleteById(long id){
         return  testMapper.deleteById(id);
     };
}