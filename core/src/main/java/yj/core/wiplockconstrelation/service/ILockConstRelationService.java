package yj.core.wiplockconstrelation.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplockconstrelation.dto.LockConstRelation;

import java.util.List;

public interface ILockConstRelationService extends IBaseService<LockConstRelation>, ProxySelf<ILockConstRelationService> {
    List<LockConstRelation> selectFromPage(IRequest requestContext,LockConstRelation dto,int page,int pageSize);
    String updateOrInsert(IRequest requestCtx,List<LockConstRelation> dto,String userName);
}