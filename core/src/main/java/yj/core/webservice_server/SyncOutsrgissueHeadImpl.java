package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import yj.core.outsrgissuehead.dto.Outsrgissuehead;
import yj.core.outsrgissuehead.mapper.OutsrgissueheadMapper;
import yj.core.outsrgissuehead.service.IOutsrgissueheadService;
import yj.core.outsrgreceipthead.mapper.OutsrgreceiptheadMapper;
import yj.core.webservice_server.dto.Rec_Outsrgissuehead;
import yj.core.webservice_server.dto.ReturnMessage;

import java.util.Date;

public class SyncOutsrgissueHeadImpl implements IsyncOutsrgissueHead {
    @Autowired
    private IOutsrgissueheadService outsrgissueheadService;
    @Override
    public ReturnMessage sync(Rec_Outsrgissuehead rec_outsrgissuehead) {
        ReturnMessage rs = new ReturnMessage();

        if (rec_outsrgissuehead.getIssuenm() == null){
            rs.setFlag("E");
            rs.setMessage("同步数据有误！");
        }

        Outsrgissuehead outsrgissuehead = new Outsrgissuehead();
        outsrgissuehead.setZipdat(rec_outsrgissuehead.getZipdat());
        outsrgissuehead.setZiptim(rec_outsrgissuehead.getZiptim());
        outsrgissuehead.setZipuser(rec_outsrgissuehead.getZipuser());
        outsrgissuehead.setLastUpdatedBy(10001L);
        outsrgissuehead.setLastUpdateDate(new Date());
        outsrgissuehead.setStatus(rec_outsrgissuehead.getStatus());
        outsrgissuehead.setPrtflag(rec_outsrgissuehead.getPrtflag());
        outsrgissuehead.setTxz01(rec_outsrgissuehead.getTxz01());
        outsrgissuehead.setWerks(rec_outsrgissuehead.getWerks());
        outsrgissuehead.setIssuenm(rec_outsrgissuehead.getIssuenm());
        outsrgissuehead.setMatnr(rec_outsrgissuehead.getMatnr());
        outsrgissuehead.setLifnr(rec_outsrgissuehead.getLifnr());
        OutsrgissueheadMapper outsrgissueheadMapper = ContextLoaderListener.getCurrentWebApplicationContext().getBean(OutsrgissueheadMapper.class);
        int num = outsrgissueheadMapper.updateOutsrgissueHead(outsrgissuehead);
        if (num == 1){
            rs.setMessage("同步成功！");
            rs.setFlag("S");
        }else{
            rs.setMessage("同步失败！");
            rs.setFlag("E");
        }
        return rs;
    }
}
