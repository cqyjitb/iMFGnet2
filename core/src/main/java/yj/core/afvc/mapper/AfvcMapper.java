package yj.core.afvc.mapper;

import com.hand.hap.mybatis.common.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import yj.core.afvc.dto.Afvc;

public  interface AfvcMapper
        extends Mapper<Afvc>
{
    public abstract int updateSync(Afvc paramAfvc);

    public abstract int insertSync(Afvc paramAfvc);

    public abstract int selectReturnNum(Afvc paramAfvc);

    public abstract List<Afvc> selectByAufpl(String paramString);

    List<Afvc> selectByAufnr(String aufnr);

    /**
     * 工作中心查询 LOV_AFVC  918100064
     * @param arbpl
     * @return
     */
    List<Afvc> selectByArbpl(@Param("arbpl") String arbpl);
}
