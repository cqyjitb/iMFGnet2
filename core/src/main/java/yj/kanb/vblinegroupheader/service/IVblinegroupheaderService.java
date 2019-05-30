package yj.kanb.vblinegroupheader.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;

import java.util.List;

public interface IVblinegroupheaderService extends IBaseService<Vblinegroupheader>,ProxySelf<IVblinegroupheaderService> {
    /**
     *  查询车间产线分组
     * @return
     */
    List<Vblinegroupheader> selectAllGroup();
}
