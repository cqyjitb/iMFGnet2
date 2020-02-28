package yj.core.fevor.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.dto.Zwipqhz;

import java.util.List;

public interface IFevorService extends IBaseService<Fevor>, ProxySelf<IFevorService> {

    List<Fevor> selectFevor2 (String fevor);

    List<Fevor> selectFevorByWerks(String werks);

    Fevor selectByfevorSinger(String fevor);

    List<Zwipqhz> queryZwipqhz(IRequest requestContext, String unitCode, String lineId);
}
