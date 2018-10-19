package yj.core.cardt.mapper;

import com.hand.hap.mybatis.common.Mapper;
import java.util.List;
import yj.core.cardt.dto.Cardt;
import yj.core.cardt.dto.VCardt;

public  interface CardtMapper
        extends Mapper<Cardt>
{
     int insertCardt(Cardt paramCardt);

     int deleteCardt(Cardt paramCardt);

     List<Cardt> selectByZpgdbar(String paramString);

     List<Cardt> queryAfterSort(Cardt paramCardt);

     List<Cardt> selectBybarcode(String zpgdbar);

     VCardt selectViewVCardt(VCardt vCardt);

     Cardt selectByBarcodeAndKtsch(Cardt dto);

     int updateCardtConfirmed(Cardt dto);

     List<Cardt> selectByZpgdbarAsc(String zpgdbar);

     List<Cardt> selectByZpgdbarDesc(String zpgdbar);
}
