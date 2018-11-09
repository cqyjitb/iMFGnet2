package yj.core.outsrgissue.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.outsrgissue.dto.Outsrgissue;
import yj.core.outsrgissue.service.IOutsrgissueService;
import org.springframework.transaction.annotation.Transactional;
import yj.core.outsrgrfe.service.IOutsrgrfeService;

import java.util.List;

@Service
@Transactional
public class OutsrgissueServiceImpl extends BaseServiceImpl<Outsrgissue> implements IOutsrgissueService{

    @Autowired
    private IOutsrgissueService outsrgissueService;
    @Override
    public List<Outsrgissue> selectByContidion(String ebeln, String ebelp, String werks, String lifnr, String matnr) {
        return outsrgissueService.selectByContidion(ebeln,ebelp,werks,lifnr,matnr);
    }
}