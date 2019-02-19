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
}
