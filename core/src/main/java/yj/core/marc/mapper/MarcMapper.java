package yj.core.marc.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.marc.dto.Marc;

public interface MarcMapper extends Mapper<Marc>{
    int isExit(String matnr);

    int updateByMatnr(Marc dto);

    int insertByMatnr(Marc dto);

    Marc selectByMatnr(String matnr);

    int saveChange(Marc marc);

}