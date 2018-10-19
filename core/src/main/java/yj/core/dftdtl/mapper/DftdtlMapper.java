package yj.core.dftdtl.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.dftdtl.dto.Dftdtl;

public interface DftdtlMapper extends Mapper<Dftdtl>{
    Dftdtl selectForLov();
}