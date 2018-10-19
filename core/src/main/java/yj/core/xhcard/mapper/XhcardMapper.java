package yj.core.xhcard.mapper;

import com.hand.hap.mybatis.common.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import yj.core.xhcard.dto.Xhcard;

public  interface XhcardMapper
        extends Mapper<Xhcard>
{
     int insertXhcard(Xhcard paramXhcard);

     int deleteXhcard(Xhcard paramXhcard);

     List<Xhcard> queryAfterSort(Xhcard paramXhcard);

     Xhcard selectByXhAndAufnr(String zxhbar);

     Xhcard selectByBacode(String zxhbar);

     int updateXhcard(Xhcard paramXhcard);

     List<Xhcard> selectByMatnrAndLgortSort(@Param("matnr") String matnr,@Param("lgort") String lgort);

     String selectMaxCharg(@Param("matnr") String matnr,@Param("charg") String charg );

     int updateZsxwc(Xhcard xhcard);
}
