package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotinput.service.IShotInputService;
import yj.core.wipshotnum.service.IShotnumService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShotInputJob extends AbstractJob {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date prdDate = context.getFireTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(prdDate);
        cal.add(Calendar.DATE,-1);
        List<ShotInput> list = shotnumService.selectShotnum2(sdf.format(cal.getTime()));
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                shotInputService.insertShotInput(list.get(i));
            }
        }
        cal.add(Calendar.DATE,-1);
        List<ShotInput> list2 = shotnumService.selectShotnum2(sdf.format(cal.getTime()));
        if (list2.size() > 0){
            for (int i=0;i<list2.size();i++){
                ShotInput shotInput = shotInputService.queryShotInput(list2.get(i));
                if (shotInput == null){
                    shotInputService.insertShotInput(list2.get(i));
                }else {
                    shotInputService.updateShotInput(list2.get(i));
                }
            }
        }
        cal.add(Calendar.DATE,-1);
        List<ShotInput> list3 = shotnumService.selectShotnum2(sdf.format(cal.getTime()));
        if (list3.size() > 0){
            for (int i=0;i<list3.size();i++){
                ShotInput shotInput = shotInputService.queryShotInput(list3.get(i));
                if (shotInput != null){
                    shotInputService.updateShotInput(list3.get(i));
                }else {
                    shotInputService.insertShotInput(list3.get(i));
                }
            }
        }
    }
}
