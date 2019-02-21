package yj.core.crhd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.crhd.dto.Crhd;

import java.util.List;

public interface CrhdMapper extends Mapper<Crhd>{
    /**
     * 根据工厂、车间条件加载工作中心LOV_ARBPL 918100064
     * @param werks
     * @param veran
     * @return
     */
    List<Crhd> selectByVeran(@Param("werks")String werks,@Param("veran")String veran);
}