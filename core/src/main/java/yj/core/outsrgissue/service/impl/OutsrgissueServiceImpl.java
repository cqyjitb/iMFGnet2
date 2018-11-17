package yj.core.outsrgissue.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.mapper.OutsrgissueMapper;
import yj.core.outsrgissue.service.IOutsrgissueService;
import org.springframework.transaction.annotation.Transactional;
import yj.core.outsrgrfe.service.IOutsrgrfeService;

import java.util.List;

@Service
@Transactional
public class OutsrgissueServiceImpl extends BaseServiceImpl<Outsrgissue> implements IOutsrgissueService{

    @Autowired
    private OutsrgissueMapper OutsrgissueMapper;
    @Override
    public List<Outsrgissue> selectByContidion(String ebeln, String ebelp, String werks, String lifnr, String matnr) {
        return OutsrgissueMapper.selectByContidion(ebeln,ebelp,werks,lifnr,matnr);
    }

    @Override
    public List<Outsrgissue> selectByIssuenmDesc(String issuenm) {
        return OutsrgissueMapper.selectByIssuenmDesc(issuenm);
    }

    @Override
    public int insertNewRow(Outsrgissue outsrgissue) {
        return OutsrgissueMapper.insertNewRow(outsrgissue);
    }

    @Override
    public Outsrgissue selectByBarcode(String zpgdbar,String status) {
        return OutsrgissueMapper.selectByBarcode(zpgdbar,status);
    }

    @Override
    public int updateOutsrgissue(Outsrgissue outsrgissue) {
        return OutsrgissueMapper.updateOutsrgissue(outsrgissue);
    }

    @Override
    public List<Outsrgissue> selectByIssuenmAndStatus(String issuenm, String status) {
        return OutsrgissueMapper.selectByIssuenmAndStatus(issuenm,status);
    }
}