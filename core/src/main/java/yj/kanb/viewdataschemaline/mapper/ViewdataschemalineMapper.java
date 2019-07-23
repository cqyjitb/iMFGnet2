package yj.kanb.viewdataschemaline.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.kanb.viewdataschemaline.dto.Viewdataschemaline;

public interface ViewdataschemalineMapper extends Mapper<Viewdataschemaline> {
    Viewdataschemaline selectforKanb(@Param("groupId") String groupId, @Param("matnr") String matnr, @Param("deptId") String deptId,
                                     @Param("bukrs") String bukrs,@Param("werks") String werks);

    Viewdataschemaline selectforKanb2(@Param("groupId") String groupId,@Param("werks") String werks);

    int insertforKanb(Viewdataschemaline viewdata);
    int updateforKanb(Viewdataschemaline viewdata);

    /**
     * 根据资源组ID删除 918100064
     * @param groupId
     */
    void deleteViewdataschemaline(@Param("groupId") String groupId);
}
