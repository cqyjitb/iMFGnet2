/*
 * #{copyright}#
 */

package com.hand.hap.job;

import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author liyan.shi@hand-china.com
 */
public class SummaryTestJob extends AbstractJob {

    @Override
    public void safeExecute(JobExecutionContext context) {

        this.setExecutionSummary("Finish at " + new Date());
    }

    @Override
    public boolean isRefireImmediatelyWhenException() {
        return false;
    }

}