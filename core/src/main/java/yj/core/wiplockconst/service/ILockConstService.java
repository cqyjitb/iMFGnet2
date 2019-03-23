package yj.core.wiplockconst.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplockconst.dto.LockConst;

import java.util.List;

public interface ILockConstService extends IBaseService<LockConst>, ProxySelf<ILockConstService> {

    List<LockConst> selectFrompage(IRequest requestContext,LockConst dto,int page,int pageSize);
    String updateOrInsert(IRequest requestCtx,List<LockConst> dto,String userName);
}