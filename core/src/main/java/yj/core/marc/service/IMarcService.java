package yj.core.marc.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.marc.dto.Marc;

import java.util.List;

public interface IMarcService extends IBaseService<Marc>, ProxySelf<IMarcService>{

    int isExit(String matnr);

    int updateByMatnr(Marc dto);

    int insertByMatnr(Marc dto);

    Marc selectByMatnr(String matnr);

    int saveChange(List<Marc> list);

    /**
     * 产品物料查询  918100064
     * @param werks
     * @param matnr
     * @return
     */
    List<Marc> queryByMarc(String werks,String matnr);

    List<Marc> queryByMarc(IRequest requestContext, String werks, String matnr, int page, int pageSize);

    List<Marc> queryByMarcDto(IRequest requestContext,Marc dto,int page, int pageSize);

    String queryByFileId(Long fileId);
}