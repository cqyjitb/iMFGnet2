package yj.kanb.kbtest.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.kanb.kbtest.dto.Kbtest;

import java.util.List;

public interface IKbtestService extends IBaseService<Kbtest>, ProxySelf<IKbtestService> {

    List<Kbtest> selectAll();
}
