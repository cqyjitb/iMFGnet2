package yj.core.outsrgrfe.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.outsrgrfe.dto.Outsrgrfe;

public interface IOutsrgrfeService extends IBaseService<Outsrgrfe>, ProxySelf<IOutsrgrfeService>{
    /**
     * 新增记录   917110140
     * @param outsrgrfe
     * @return
     */
    int insertOutsrgrfe(Outsrgrfe outsrgrfe);

    /**
     *  根据 工厂 生产订单 工序号 物料编码 供应商编码 查询  917110140
     * @param werks
     * @param aufnr
     * @param vornr
     * @param matnr
     * @param lifnr
     * @return
     */
    Outsrgrfe selectByCondition(String werks,String aufnr,String vornr,String matnr,String lifnr);

    /**
     *  根据 工厂 生产订单 工序号 物料编码 供应商编码 修改  917110140
     * @param outsrgrfe
     * @return
     */
    int updateByCondition(Outsrgrfe outsrgrfe);
}