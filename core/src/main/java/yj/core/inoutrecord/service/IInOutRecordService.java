package yj.core.inoutrecord.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface IInOutRecordService extends IBaseService<InOutRecord>, ProxySelf<IInOutRecordService>{
    int insertQjrecode(List<InOutRecord> inOutRecord);
    List<InOutRecord> selectforjjhj(String line_id,String qjcodeval,String lineiocfgval,String matnr,int hjtype);
    List<InOutRecord> selectNoReflg(String line_id,String zotype,String vornr,String sfflg,String matnr,int hjtype);
    int updateReflag(InOutRecord inOutRecord);
//    List<Zudlist> selectforZud(String line_id, String classgrp, int page, int pagesize, IRequest iRequest);

    /**
     *   917110140 修改成可以适应父产线 子产线共用的查询
     * @param pline_id 父产线ID
     * @param line_id  产线ID
     * @param classgrp 班组
     * @return
     */
    List<Zudlist> selectforZud(String pline_id,String line_id, String classgrp);
    InOutRecord selectById(String zqjjlh);
    int batchUpdateReflag(List<InOutRecord> list);

    /** 917110140 修改成可以适应父产线 子产线共用的查询
     * @param pline_id
     * @param line_id
     * @param classgrp
     * @param zotype
     * @param iRequest
     * @return
     */
    List<InOutRecord> selectforZrwk(String pline_id,String line_id,String classgrp,String zotype,IRequest iRequest);
    List<InOutRecord> selectforlines(IRequest request, String lineId, String deptId);
    int selectZoutnum(String lineId, Integer zremade,String sfflg,String diecd);
    int selectZsxnum(String lineId, Integer zremade,String sfflg,String diecd);

}