package yj.core.qppdrcd.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.qppdrcd.dto.Qppdrcd;

public interface IQppdrcdService extends IBaseService<Qppdrcd>, ProxySelf<IQppdrcdService>{
    int insertPdRow(Qppdrcd qppdrcd);
}