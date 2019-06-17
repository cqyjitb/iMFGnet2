package yj.kanb.wippassrate.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.wippassrate.dto.PassRate;

public interface IPassRateService extends IBaseService<PassRate>,ProxySelf<IPassRateService> {
    /**
     * 合格率报表数据任务处理 918100064
     * @param dto
     */
    void insertPassRate(PassRate dto);
}
