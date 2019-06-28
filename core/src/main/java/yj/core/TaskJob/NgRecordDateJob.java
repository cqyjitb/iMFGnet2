package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import com.hand.hap.job.dto.SimpleTriggerDto;
import com.hand.hap.job.service.IQuartzService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.kanb.wipdateclass.dto.DateClass;
import yj.kanb.wipdateclass.service.IDateClassService;
import yj.kanb.wipngrecord.dto.NgRecord;
import yj.kanb.wipngrecord.service.INgRecordService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NgRecordDateJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private INgRecordService ngRecodeService;
    @Autowired
    private IDateClassService dateClassService;
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
        List<DateClass> list2 = dateClassService.selectFromPage("NgRecordDateJob");
        String startDate = list2.get(0).getStartDate();
        String endDate = list2.get(0).getEndDate();
        List<InOutRecord> list = inOutRecordService.selectByNgRecode(startDate,endDate);
        if(list.size() > 0){
            for (int i=0;i<list.size();i++){
                InOutRecord inOutRecord = list.get(i);
                inOutRecord.setCreationDateBefore(startDate);
                inOutRecord.setCreationDateAfter(endDate);
                NgRecord ngRecord = new NgRecord();
                ngRecord.setWerks(inOutRecord.getWerks());
                ngRecord.setDeptId(inOutRecord.getDeptId());
                ngRecord.setLineId(inOutRecord.getLineId());
                ngRecord.setMatnr(inOutRecord.getMatnr2());
                ngRecord.setMaktx(inOutRecord.getMaktx());
                ngRecord.setErdat(new Date());
                List<InOutRecord> list1 = inOutRecordService.zissuetxtCount(inOutRecord);
                ngRecord.setQty(list1.size());
                ngRecord.setGmein(inOutRecord.getGmein());
                ngRecord.setZissuetxt(inOutRecord.getZissuetxt());
                ngRecord.setZotype(inOutRecord.getZotype());
                ngRecord.setZtext(inOutRecord.getZtext());
                ngRecord.setCreatedBy(10001L);
                ngRecord.setCreationDate(new Date());
                ngRecodeService.insertNgRecord(ngRecord);
            }
        }
    }
}
