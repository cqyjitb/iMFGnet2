package yj.mes.oee.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.mes.oee.dto.OeeLineData;

import java.util.List;

public interface IOeeLineDataService extends IBaseService<OeeLineData>,ProxySelf<IOeeLineDataService> {
    List<OeeLineData> queryOeeDataByLineCode(OeeLineData param);
}
