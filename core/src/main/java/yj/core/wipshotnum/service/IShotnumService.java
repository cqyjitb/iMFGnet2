package yj.core.wipshotnum.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipshotnum.dto.Shotnum;

import java.util.List;

public interface IShotnumService extends IBaseService<Shotnum>, ProxySelf<IShotnumService> {
    /**
     * 压射号及压铸报工查询  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext);

    /**
     *  插入新记录 917110140
     * @param shot
     * @return
     */
    int insertRow(Shotnum shot);
}