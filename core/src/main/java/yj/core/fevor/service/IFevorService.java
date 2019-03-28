package yj.core.fevor.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.fevor.dto.Fevor;

import java.util.List;

public interface IFevorService extends IBaseService<Fevor>, ProxySelf<IFevorService> {

    List<Fevor> selectFevor2 (String fevor);

    Fevor selectByfevorSinger(String fevor);
}
