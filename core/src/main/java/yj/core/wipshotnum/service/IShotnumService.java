package yj.core.wipshotnum.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wipshotinput.dto.ShotInput;
import yj.core.wipshotnum.dto.Shotnum;
import yj.core.wipshotnumadd.dto.ShotnumAdd;

import java.util.List;

public interface IShotnumService extends IBaseService<Shotnum>, ProxySelf<IShotnumService> {
    /**
     * 压射号及压铸报工查询  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum(Shotnum dto, IRequest requestContext);

    /**
     *  插入新记录 917110140
     * @param shot
     * @return
     */
    int insertRow(Shotnum shot);

    /**
     *  根据 工厂 工作中心 生产日期 班次 查询记录 917110140
     * @param werks
     * @param arbpl
     * @param prd_date
     * @param shifts
     * @return
     */
    List<Shotnum> isExit(String werks,String arbpl,String prd_date,String shifts);

    /**
     * 压射号维护查询 918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> queryShotnum(Shotnum dto,IRequest requestContext);

    /**
     * 压射号维护修改 918100064
     * @param requestContext
     * @param dto
     * @param userId
     * @return
     */
    String updateShotnum(IRequest requestContext,List<Shotnum> dto,String userId);

    /**
     * 压射号维护删除 918100064
     * @param dto
     * @return
     */
    String deleteShotnum(List<Shotnum> dto);

    /**
     * 压射号及报工统计表（财务）  918100064
     * @param prdDate
     * @return
     */
    List<ShotInput> selectShotnum2(String prdDate);

    /**
     * 压射号异常明细表  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum3(Shotnum dto, IRequest requestContext);

    /**
     * 压射号及报工统计表  918100064
     * @param dto
     * @param requestContext
     * @return
     */
    List<Shotnum> selectShotnum4(Shotnum dto, IRequest requestContext);

    /**
     * 压射号及报工统计新增及修改数据整理 918100064
     * @return
     */
    List<ShotInput> selectShotnumNew();

    /**
     * 压射号及报工统计只报工无压射号数据 918100064
     * @param list
     * @return
     */
    List<ShotInput> selectShotnumAdd(List<ShotnumAdd> list);
}