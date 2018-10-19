package yj.core.zudhead.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zrwkhead.dto.Zrwkhead;
import yj.core.zudhead.dto.Zudhead;

public interface IZudheadService extends IBaseService<Zudhead>, ProxySelf<IZudheadService>{
    String selectMaxNo(String curdat);
    int insertHead(Zudhead zudhead);
}