package yj.core.dftdtl.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.dftdtl.dto.Dftdtl;

import java.util.List;

public interface IDftdtlService extends IBaseService<Dftdtl>, ProxySelf<IDftdtlService>{
    /**
     * 根据一级质量原因代码 获取对应的二级原因代码
     * @param code
     * @return
     */
    List<Dftdtl> selectByQpcode(String code,String Matnr);
}