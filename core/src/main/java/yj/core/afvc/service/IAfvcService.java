package yj.core.afvc.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import java.util.List;
import yj.core.afvc.dto.Afvc;

public abstract interface IAfvcService
        extends IBaseService<Afvc>, ProxySelf<IAfvcService>
{
     int updateSync(Afvc paramAfvc);

     int insertSync(Afvc paramAfvc);

     int selectReturnNum(Afvc paramAfvc);

     List<Afvc> selectByAufpl(String paramString);

     List<Afvc> selectByAufnr(String aufnr);
}
