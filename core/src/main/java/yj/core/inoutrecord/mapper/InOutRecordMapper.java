package yj.core.inoutrecord.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface InOutRecordMapper extends Mapper<InOutRecord>{
        int insertQjrecode(InOutRecord inOutRecord);
        List<InOutRecord> selectforjjhj(@Param("line_id") String line_id, @Param("qjcodeval") String qjcodeval, @Param("lineiocfgval") String lineiocfgval,@Param("matnr") String matnr,@Param("hjtype") int hjtype);
        List<InOutRecord> selectNoReflg(@Param("line_id") String line_id, @Param("zotype") String zotype, @Param("vornr") String vornr, @Param("matnr") String matnr, @Param("sfflg") String sfflg,@Param("hjtype") int hjtype);
        int updateReflag(InOutRecord inOutRecord);
        /**
         *   917110140 修改成可以适应父产线 子产线共用的查询
         * @param Pline_id 父产线ID
         * @param line_id  产线ID
         * @param classgrp 班组
         * @return
         */
        List<InOutRecord> selectforZud(@Param("pline_id") String Pline_id, @Param("line_id") String line_id, @Param("classgrp") String classgrp);

        InOutRecord selectById(String zqjjlh);

        /** 917110140
         * @param pline_id
         * @param line_id
         * @param classgrp
         * @param zotype
         * @return
         */
        List<InOutRecord> selectforZrwk(@Param("pline_id") String pline_id,@Param("line_id") String line_id,@Param("classgrp") String classgrp,@Param("zotype") String zotype);

        /**
         * 产线在制队列汇总表查询 918100064
         * @param lineId
         * @param deptId
         * @return
         */
        List<InOutRecord> selectforlines(@Param("lineId") String lineId,@Param("deptId")String deptId);

        /**
         * 根据生产线、返工件标识、处理标识查询WIP_IN_OUT_RECORD表汇总取件数量（未报废） 918100064
         * @param lineId
         * @param zremade
         * @param sfflg
         * @param diecd
         * @return
         */
        int selectZoutnum(@Param("lineId")String lineId,@Param("zremade") Integer zremade,@Param("sfflg") String sfflg,@Param("diecd") String diecd);

        /**
         * 按产线、成品物料、工序流转卡集查询待处理数量、报废数量 918100064
         * @param lineId
         * @param matnr
         * @param zpgdbar
         * @param reflag
         * @return
         */
        int selectZoutnum1(@Param("lineId")String lineId,@Param("matnr")String matnr,@Param("zpgdbar")String zpgdbar,@Param("reflag")Integer reflag);


        /**
         * 根据物料编码、不良原因查询数据的条数 918100064
         * @param matnr2
         * @param zissuetxt
         * @return
         */
        int selectDftdtl(@Param("matnr2")String matnr2,@Param("zissuetxt")String zissuetxt);

}