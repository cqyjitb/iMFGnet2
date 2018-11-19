package yj.core.zwipq.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.webservice_migo.dto.DTMIGOReturn;
import yj.core.zwipq.dto.Zwipq;

import java.util.List;
import java.util.Map;

public interface IZwipqService extends IBaseService<Zwipq>, ProxySelf<IZwipqService>{
    List<Zwipq> selectByLineIdAndZxhbar(String line_id,String zxhbar);
    Map selectMaxQsenq(Map m);
    int InsertIntoZwipq(List<Zwipq> list);
    DTMIGOReturn callMigo(String zxhbar,int cynum,String line_id,String bwart,int createBy,String zpgdbar);
    List<Zwipq> selectBylineidAndZxhbarAndZpgdbar(String line_id,String zxhbar,String zpgdbar);
    List<Zwipq> selectBylineforjjqj(String line_id);
    List<Zwipq> selectForqj(String line_id,String attr6);
    int updateForQj(List<Zwipq> zwipqs);
    Zwipq selectById(String zwipqid);
    List<Zwipq> selectByLineid(String line_id);
    int updateZqjklAndQenq(Zwipq zwipq);
    int updateQsenqBatch(List<Zwipq> list);
    List<Zwipq> selectForJjxx(String line_id,String classgrp);
    int updateZoffl(List<Zwipq> list);
    List<Zwipq> selectZwipq(IRequest request, String deptId, String lineId, Integer zremade,
                            String attr1After, String attr1Before, String shift, String sfflg, String diecd,
                            String zxhbar, String zgjbar, Integer online, Integer zzxkl, Integer zqjkl, Integer zoffl, Integer status);
    List<Zwipq> selectIORZwipq(IRequest request, String deptId, String lineId, String pmatnr, String attr1After, String attr1Before, String shift);

    /**
     *  根据箱号查询在制队列数据 917110140
     * @param zxhbar
     * @return
     */
    List<Zwipq> selectByZxhbar(String zxhbar);
}