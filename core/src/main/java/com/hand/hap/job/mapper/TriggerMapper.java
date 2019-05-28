/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import com.hand.hap.job.dto.TriggerDto;

import java.util.List;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface TriggerMapper {
    TriggerDto selectByPrimaryKey(TriggerDto key);

    List<TriggerDto> selectTriggers(TriggerDto example);

}