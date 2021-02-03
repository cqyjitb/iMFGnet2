package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotinput.service.IShotInputService;
import yj.core.wipshotnum.service.IShotnumService;

import java.util.Date;
import java.util.List;

public class ShotInputJob2 extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(PassRateJob.class);
    @Autowired
    private IShotnumService shotnumService;
    @Autowired
    private IShotInputService shotInputService;

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
        JobDataMap dataMap = context.getMergedJobDataMap();
        String date = dataMap.getString("date");

        List<ShotInput> list = shotnumService.selectShotnum2(date);

        if (list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                ShotInput shotInput = shotInputService.queryShotInput(list.get(j));
                if (shotInput == null) {
                    shotInputService.insertShotInput(list.get(j));
                } else {
                    shotInputService.updateShotInput(list.get(j));
                }
            }
        }
    }
}
