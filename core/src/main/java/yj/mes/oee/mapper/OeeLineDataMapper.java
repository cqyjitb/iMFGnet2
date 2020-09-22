package yj.mes.oee.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.mes.oee.dto.OeeLineData;

import java.util.List;

public interface OeeLineDataMapper extends Mapper<OeeLineData> {
    List<OeeLineData> queryOeeDataByLineCode(OeeLineData oeeLineData);
}
