package yj.core.fevor.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.fevor.dto.Fevor;

import java.util.List;

public interface FevorMapper extends Mapper<Fevor> {
    /**
     *
     * @param fevor
     * @return
     */
    List<Fevor> selectFevor(@Param("fevor") String fevor);

    /**
     *  查询 1 2 3 开头的记录 917110140
     * @param fevor
     * @return
     */
    List<Fevor> selectFevor2(@Param("fevor") String fevor);
}
