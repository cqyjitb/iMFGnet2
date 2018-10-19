package yj.core.cardt.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import java.util.List;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.dto.VCardt;

public  interface ICardtService
        extends IBaseService<Cardt>, ProxySelf<ICardtService>
{
     int insertCardt(List<Cardt> paramList);

     int deleteCardt(List<Cardt> paramList);

     List<Cardt> selectByZpgdbar(String paramString);

     List<Cardt> queryAfterSort(IRequest paramIRequest, Cardt paramCardt, int paramInt1, int paramInt2);

     List<Cardt> selectBybarcode(String zpgdbar);

     VCardt selectViewVCardt(VCardt vCardt);

     Cardt selectByBarcodeAndKtsch(Cardt dto);

     int updateCardtConfirmed(Cardt dto);

     List<Cardt> selectByZpgdbarAsc(String zpgdbar);

     List<Cardt> selectByZpgdbarDesc(String zpgdbar);
}
