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




}