package yj.core.dftdtl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import org.apache.ibatis.annotations.Param;
import yj.core.dftdtl.dto.Dftdtl;

import java.util.List;

public interface DftdtlMapper extends Mapper<Dftdtl>{
    Dftdtl selectForLov();
    /**
     * 根据一级质量原因代码 获取对应的二级原因代码
     * @param code
     * @return
     */
    List<Dftdtl> selectByQpcode(@Param("code") String code,@Param("matnr") String matnr);
}