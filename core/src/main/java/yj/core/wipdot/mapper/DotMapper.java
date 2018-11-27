package yj.core.wipdot.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipdot.dto.Dot;

public interface DotMapper extends Mapper<Dot> {

    /**
     * 根据工序Id查询数据的条数 918100064
     * @param pointId
     * @return
     */
    int selectPoints(@Param("pointId")Integer pointId);
}