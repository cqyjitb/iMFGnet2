/*
 * #{copyright}#
 */
package com.hand.hap.job.mapper;

import com.hand.hap.job.dto.SchedulerDto;

import java.util.List;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface SchedulerMapper {

    SchedulerDto selectByPrimaryKey(SchedulerDto key);

    List<SchedulerDto> selectSchedulers(SchedulerDto example);

}