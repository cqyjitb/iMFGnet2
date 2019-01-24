package yj.core.afko.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.afko.dto.Afko;

import java.util.List;

public  interface AfkoMapper
        extends Mapper<Afko>
{
     int insertSync(Afko paramAfko);

     int selectReturnNum(Afko paramAfko);

     int updateSync(Afko paramAfko);

     int selectExist(Afko paramAfko);

     List<Afko> selectZuhe(Afko paramAfko);

     List<Afko> selectYaZu(Afko paramAfko);

     List<Afko> selectJija(Afko paramAfko);

     Afko selectByAufnr(String aufnr);

     /**
      * 查询以4开头的生产管理员LOV_AFKO  918100064
      * @param fevor
      * @return
      */
     List<Afko> selectFromFevor(String fevor);
}
