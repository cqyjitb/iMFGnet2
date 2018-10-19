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
}