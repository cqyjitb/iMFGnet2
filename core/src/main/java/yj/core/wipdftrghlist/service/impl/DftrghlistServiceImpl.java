package yj.core.wipdftrghlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.wipdftrghlist.dto.Dftrghlist;
import yj.core.wipdftrghlist.mapper.DftrghlistMapper;
import yj.core.wipdftrghlist.service.IDftrghlistService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DftrghlistServiceImpl extends BaseServiceImpl<Dftrghlist> implements IDftrghlistService{
    @Autowired
    private DftrghlistMapper dftrghlistMapper;
    @Override
    public List<Dftrghlist> selectByCondition(String werks, String matnr,String line_id, String shift, String gstrp) {
        return dftrghlistMapper.selectByCondition(werks,matnr,line_id,shift,gstrp);
    }

    @Override
    public int insertDftrghlist(Dftrghlist dftrghlist) {
        return dftrghlistMapper.insertDftrghlist(dftrghlist);
    }

    @Override
    public int updateDftrghlist(Dftrghlist dftrghlist) {
        return dftrghlistMapper.updateDftrghlist(dftrghlist);
    }

    @Override
    public int selectMaxItemByCondition(String werks, String matnr, String line_id, String shift, String gstrp) {

        return dftrghlistMapper.selectMaxItemByCondition(werks,matnr,line_id,shift,gstrp);
    }

    @Override
    public List<Dftrghlist> selectByLindIdAndZxhbar(String line_id, String classgrp, String zxhbar) {
        return dftrghlistMapper.selectByLindIdAndZxhbar(line_id,classgrp,zxhbar);
    }

    @Override
    public Dftrghlist selectByIdAndItem(String recordid, Long item) {
        return dftrghlistMapper.selectByIdAndItem(recordid,item);
    }

    @Override
    public int updateByIdAndItem(Dftrghlist dto) {
        return dftrghlistMapper.updateByIdAndItem(dto);
    }

    @Override
    public List<Dftrghlist> selectByZxhbar(String zxhbar) {
        return dftrghlistMapper.selectByZxhbar(zxhbar);
    }

    @Override
    public String selectMaxRecordid(String werks, String gstrp) {
        return dftrghlistMapper.selectMaxRecordid(werks,gstrp);
    }
}