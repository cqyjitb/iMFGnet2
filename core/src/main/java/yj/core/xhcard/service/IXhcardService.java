package yj.core.xhcard.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import java.util.List;

import yj.core.webservice_queryXhcard.dto.QueryXhcardReturnResult;
import yj.core.webservice_xhcard.dto.XhcardReturnResult;
import yj.core.xhcard.dto.Xhcard;

public  interface IXhcardService
        extends IBaseService<Xhcard>, ProxySelf<IXhcardService>
{
     int insertXhcard(List<Xhcard> paramList);

     int deleteXhcard(List<Xhcard> paramList);

     List<Xhcard> queryAfterSort(IRequest paramIRequest, Xhcard paramXhcard, int paramInt1, int paramInt2);

     XhcardReturnResult returnSyncXhcard(Xhcard paramXhcard);

     Xhcard selectByXhAndAufnr(String zxhbar);

     Xhcard selectByBacode(String zxhbar);

     QueryXhcardReturnResult selectByBacodeFromSap(String zxhbar,String matnr,String lgort,String qtype);

     int updateXhcard(List<Xhcard> list);

     List<Xhcard> selectByMatnrAndLgortSort(String matnr,String lgort);

     String selectMaxCharg(String matnr,String charg);

     int updateXhcardFromSap(List<Xhcard> list);

     int updateZsxwc(Xhcard xhcard);

     List<Xhcard> selectXbkc(Xhcard dto);

}
