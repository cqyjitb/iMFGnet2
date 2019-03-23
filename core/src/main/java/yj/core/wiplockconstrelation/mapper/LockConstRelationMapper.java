package yj.core.wiplockconstrelation.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.wiplockconstrelation.dto.LockConstRelation;

import java.util.List;

public interface LockConstRelationMapper extends Mapper<LockConstRelation> {
    List<LockConstRelation> selectLockConstRelation(LockConstRelation lockConstRelation);
    void insertLockConstRelation(LockConstRelation lockConstRelation);
    void updateLockConstRelation(LockConstRelation lockConstRelation);
}