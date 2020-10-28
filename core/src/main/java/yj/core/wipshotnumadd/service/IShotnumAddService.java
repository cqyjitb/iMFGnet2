package yj.core.wipshotnumadd.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipshotnumadd.dto.ShotnumAdd;

public interface IShotnumAddService extends IBaseService<ShotnumAdd>, ProxySelf<IShotnumAddService> {

    /**
     * 插入新数据 918100064
     * @param shot
     * @return
     */
    int insertRow(ShotnumAdd shot);
}
