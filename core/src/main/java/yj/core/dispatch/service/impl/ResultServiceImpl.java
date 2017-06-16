package yj.core.dispatch.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import yj.core.dispatch.dto.Result;
import yj.core.dispatch.service.IResultService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResultServiceImpl extends BaseServiceImpl<Result> implements IResultService{

}