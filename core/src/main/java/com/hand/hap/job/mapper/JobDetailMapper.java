/*
 * #{copyright}#
 */

package com.hand.hap.job.mapper;

import com.hand.hap.job.dto.JobDetailDto;
import com.hand.hap.job.dto.JobInfoDetailDto;

import java.util.List;

/**
 *
 * @author shengyang.zhou@hand-china.com
 */
public interface JobDetailMapper {
    JobDetailDto selectByPrimaryKey(JobDetailDto key);

    List<JobDetailDto> selectJobDetails(JobDetailDto example);

    List<JobInfoDetailDto> selectJobInfoDetails(JobDetailDto example);
}