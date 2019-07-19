package yj.core.wipshotinput.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotnum.service.IShotnumService;

import java.util.List;

public interface IShotInputService extends IBaseService<ShotInput>,ProxySelf<IShotnumService> {
    /**
     * 压射号及报工统计表数据添加 918100064
     * @param dto
     * @return
     */
    String insertShotInput(List<ShotInput> dto);

    /**
     * 压射号及报工统计表查询 918100064
     * @param dto
     * @return
     */
    List<ShotInput> selectShotInput(ShotInput dto);
}
