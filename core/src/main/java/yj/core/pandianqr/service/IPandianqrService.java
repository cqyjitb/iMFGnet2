package yj.core.pandianqr.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.pandianqr.dto.Pandianqr;

public interface IPandianqrService extends IBaseService<Pandianqr>, ProxySelf<IPandianqrService>{
    int insertPdRow(Pandianqr pandianqr);
}