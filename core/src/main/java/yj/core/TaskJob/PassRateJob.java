package yj.core.TaskJob;

import com.hand.hap.job.AbstractJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import yj.core.OracleConn.OracleConn;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.service.IInOutRecordService;
import yj.core.seversetting.dto.ServerSetting;
import yj.core.seversetting.service.IServerSettingService;
import yj.core.util.WebServerHelp;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.service.ILinesService;
import yj.kanb.wippassrate.dto.PassRate;
import yj.kanb.wippassrate.mapper.PassRateMapper;
import yj.kanb.wippassrate.service.IPassRateService;

import java.text.SimpleDateFormat;
import java.util.*;

public class PassRateJob extends AbstractJob {
    private static Logger log = LoggerFactory.getLogger(KanbGetDataJob.class);
    @Autowired
    private ILinesService linesService;
    @Autowired
    private IInOutRecordService inOutRecordService;
    @Autowired
    private IServerSettingService serverSettingService;
    @Autowired
    private IPassRateService passRateService;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate = context.getFireTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.MINUTE,-20);
        Date startDate = cal.getTime();
        String startDate2 = sdf.format(startDate);
        String endDate2 = sdf.format(endDate);
        List<InOutRecord> list1 = inOutRecordService.selectByCreateDate(startDate2,endDate2);
        if (list1.size() > 0){
            List<PassRate> list = new ArrayList<PassRate>();
            for (int i=0;i<list1.size();i++){
                Lines lines = linesService.selectById(Long.valueOf(list1.get(i).getLineId()));
                PassRate passRate = new PassRate();
                int j = 0;
                for(j=0;j<list.size();j++){
                    if((list.get(j).getLineId().equals(String.valueOf(lines.getPlineId()))) || (list.get(j).getLineId().equals(String.valueOf(lines.getLineId())))){
                        break;
                    }
                }
                if(j == list.size()){
                    if(lines.getPlineId() == null){

                    }else{
                        lines = linesService.selectById(lines.getPlineId());
                    }
                    passRate.setWerks(lines.getWerks());
                    passRate.setDeptId(lines.getDeptId());
                    passRate.setLineId(String.valueOf(lines.getLineId()));
                    passRate.setErdat(new Date());
                    passRate.setMatnr(list1.get(i).getMatnr2());
                    passRate.setMaktx(list1.get(i).getMaktx());
                    list.add(passRate);
                }
            }
            if (list.size() > 0){
                for (int i=0;i<list.size();i++){
                    PassRate passRate = list.get(i);
                    Integer gmnga = 0,xmnga = 0,rmnga = 0;
                    list1 = inOutRecordService.XmngaCount(passRate.getLineId(),passRate.getMatnr(),startDate2,endDate2);
                    if(list1.size() > 0){
                        xmnga = list1.size();
                    }
                    list1 = inOutRecordService.RmngaCount(passRate.getLineId(),passRate.getMatnr(),startDate2,endDate2);
                    if(list1.size() > 0){
                        rmnga = list1.size();
                    }
                    ServerSetting serverSetting = serverSettingService.selectByLineId(passRate.getWerks(),passRate.getLineId());
                    if(serverSetting != null){
                        WebServerHelp webServerHelp = new WebServerHelp();
                        OracleConn oracleConn = new OracleConn(webServerHelp.getMesOraUrl(),webServerHelp.getMesOraUserName(),webServerHelp.getMesOraPass(),webServerHelp.getMesOraDriver());
                        String sqlzx = "select ba.* from " + serverSetting.getDbUsername() + ".wip_pallet_sn_rel pa, " + serverSetting.getDbUsername() + ".mtl_barcode ba ";
                        String where = "where pa.line_id = "+ "'"+ passRate.getLineId() + "' and ba.item_code = '"+ passRate.getMatnr()+"' and ba.creation_date >= '"
                                + startDate2 + "' and ba.creation_date < '" + endDate2 +"' and pa.jb_status = 0 and pa.barcode_id = ba.barcode_id ";
                        String sql = sqlzx + where;
                        List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
                        try {
                            listMap = oracleConn.select(sql);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (listMap.size() > 0){
                            gmnga = list.size();
                        }
                    }
                    passRate.setGmnga(gmnga);
                    passRate.setXmnga(xmnga);
                    passRate.setRmnga(rmnga);
                    passRate.setCreatedBy(10001L);
                    passRate.setCreationDate(new Date());
                    passRateService.insertPassRate(passRate);
                }
            }
        }
    }
}
