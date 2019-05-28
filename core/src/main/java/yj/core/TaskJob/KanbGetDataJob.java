package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.kanb.kbtest.service.IKbtestService;

import java.util.Date;
import java.util.UUID;

/**
 *  看板后台取数任务
 */
public class KanbGetDataJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
    @Autowired
    private IKbtestService kbtestService;
    @Override
    protected boolean isRefireImmediatelyWhenException() {
        return false;
    }

    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
        JobDetail detail = context.getJobDetail();
        JobKey key = detail.getKey();
        TriggerKey triggerKey = context.getTrigger().getKey();
        String msg = "KanbGetDataJob Test<insertNewData>! - . jobKey:" + key + ", triggerKey:" + triggerKey + ", execTime:" + new Date();
        log.info(msg);
        UUID uuid = UUID.randomUUID();
        kbtestService.insertNewData(uuid.toString());
    }
}
