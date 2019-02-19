package yj.core.wipshotnum.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.wipshotnum.dto.Shotnum;

import java.util.List;

public interface ShotnumMapper extends Mapper<Shotnum> {
    /**
     * 根据工厂 工作中心 生产日期 班次查询记录 918100064
     * @param dto
     * @return
     */
    List<Shotnum> selectShotnum(Shotnum dto);

    /**
     *  插入新记录 917110140
     * @param shot
     * @return
     */
    int insertRow(Shotnum shot);

    /**
     *  根据 工厂 工作中心 生产日期 班次 查询记录 917110140
     * @param werks
     * @param arbpl
     * @param prd_date
     * @param shifts
     * @return
     */
    List<Shotnum> isExit(@Param("werks") String werks, @Param("arbpl") String arbpl, @Param("prd_date") String prd_date, @Param("shifts") String shifts);
}