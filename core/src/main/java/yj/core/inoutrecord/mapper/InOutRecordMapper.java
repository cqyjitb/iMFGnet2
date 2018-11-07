package yj.core.inoutrecord.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.inoutrecord.dto.InOutRecord;

import java.util.List;

public interface InOutRecordMapper extends Mapper<InOutRecord>{
        int insertQjrecode(InOutRecord inOutRecord);
        List<InOutRecord> selectforjjhj(@Param("line_id") String line_id, @Param("qjcodeval") String qjcodeval, @Param("lineiocfgval") String lineiocfgval,@Param("matnr") String matnr,@Param("hjtype") int hjtype);
        List<InOutRecord> selectNoReflg(@Param("line_id") String line_id, @Param("zotype") String zotype, @Param("vornr") String vornr, @Param("matnr") String matnr, @Param("sfflg") String sfflg,@Param("hjtype") int hjtype);
        int updateReflag(InOutRecord inOutRecord);
        List<InOutRecord> selectforZud(@Param("line_id") String line_id,@Param("classgrp") String classgrp);
        InOutRecord selectById(String zqjjlh);
        List<InOutRecord> selectforZrwk(@Param("line_id") String line_id,@Param("classgrp") String classgrp,@Param("zotype") String zotype);

        /**
         * 产线在制队列汇总表查询 918100064
         * @param lineId
         * @param unitId
         * @return
         */
        List<InOutRecord> selectforlines(@Param("lineId") String lineId,@Param("unitId")Long unitId);

        /**
         * 根据生产线、返工件标识、处理标识查询WIP_IN_OUT_RECORD表汇总取件数量（未报废） 918100064
         * @param lineId
         * @param zremade
         * @param sfflg
         * @param diecd
         * @return
         */
        int selectZoutnum(@Param("lineId")String lineId,@Param("zremade") Integer zremade,@Param("sfflg") String sfflg,@Param("diecd") String diecd);

}