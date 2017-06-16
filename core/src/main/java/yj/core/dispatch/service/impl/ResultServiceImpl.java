package yj.core.dispath.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.dispath.dto.Result;
import yj.core.dispath.service.IResultService;

@Service
@Transactional
public class ResultServiceImpl extends BaseServiceImpl<Result> implements IResultService{

}