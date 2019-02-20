package yj.core.crhd.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.crhd.dto.Crhd;

public interface CrhdMapper extends Mapper<Crhd>{

    /**
     *  根据OBJID 查询记录数量 917110140
     * @param objid
     * @return
     */
    int selectnumByObjid(@Param("objid") String objid);

    /**
     *  更新行 917110140
     * @param crhd
     * @return
     */
    int updateRow(Crhd crhd);

    /**
     *  插入新行 917110140
     * @param crhd
     * @return
     */
    int insertRow(Crhd crhd);
}