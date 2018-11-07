package yj.core.zwipq.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.zwipq.dto.Zwipq;

import java.util.List;
import java.util.Map;

public interface ZwipqMapper extends Mapper<Zwipq>{
    List<Zwipq> selectByLineIdAndZxhbar(@Param("line_id") String line_id,@Param("zxhbar") String zxhbar);
    //查询在制队列中队里顺序号
    void selectMaxQsenq(Map m);

    int InsertIntoZwipq(Zwipq zwipq);

    List<Zwipq> selectBylineidAndZxhbarAndZpgdbar(@Param("line_id") String line_id,@Param("zxhbar") String zxhbar,@Param("zpgdbar") String zpgdbar);

    //根据产线ID查询队列中的数据
    List<Zwipq> selectBylineforjjqj(String line_id);

    //查询队列中符合取件的数据
    List<Zwipq> selectForqj(@Param("line_id") String line_id,@Param("sfflg") String sfflg);

    //更新在制队列取件标识
    int updateForQj(Zwipq zwipq);

    Zwipq selectById(String zwipqid);

    List<Zwipq> selectByLineid(String line_id);

    int updateZqjklAndQenq(Zwipq zwipq);

    int updateQsenq(Zwipq zwipq);

    List<Zwipq> selectForJjxx(@Param("line_id") String line_id,@Param("classgrp") String classgrp);

    int updateZoffl(Zwipq zwipq);

    /**
     * 根据生产线、返工件标识、工件状态查询ZWIPQ表汇总上线数量 918100064
     * @param lineId
     * @param zremade
     * @param sfflg
     * @param diecd
     * @return
     */
    int selectZsxnum(@Param("lineId") String lineId,@Param("zremade") Integer zremade,@Param("sfflg") String sfflg,@Param("diecd") String diecd);

    /**
     * 产线在制队列明细查询 918100064
     * @param unitId
     * @param lineId
     * @param zremade
     * @param attr1After
     * @param attr1Before
     * @param shift
     * @param sfflg
     * @param diecd
     * @param zxhbar
     * @param zgjbar
     * @param online
     * @param zzxkl
     * @param zqjkl
     * @param zoffl
     * @param status
     * @return
     */
    List<Zwipq> selectZwipq(@Param("unitId")Long unitId, @Param("lineId") String lineId, @Param("zremade")Integer zremade,@Param("attr1After")String attr1After,
                            @Param("attr1Before")String attr1Before, @Param("shift")String shift, @Param("sfflg")String sfflg, @Param("diecd")String diecd,@Param("zxhbar")String zxhbar,
                            @Param("zgjbar")String zgjbar,@Param("online") Integer online, @Param("zzxkl")Integer zzxkl, @Param("zqjkl")Integer zqjkl, @Param("zoffl")Integer zoffl, @Param("status")Integer status);

}