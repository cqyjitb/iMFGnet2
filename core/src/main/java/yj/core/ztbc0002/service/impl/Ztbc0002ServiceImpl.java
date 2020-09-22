package yj.core.ztbc0002.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.webserver_readtp.components.ReadTpWebserviceUtil;
import yj.core.webserver_readtp.receiver.DTREADTPRes;
import yj.core.webserver_readtp.sender.DTREADTPReq;
import yj.core.ztbc0002.dto.Ztbc0002;
import yj.core.ztbc0002.mapper.Ztbc0002Mapper;
import yj.core.ztbc0002.service.IZtbc0002Service;

@Service
@Transactional
public class Ztbc0002ServiceImpl extends BaseServiceImpl<Ztbc0002> implements IZtbc0002Service {
    @Autowired
    private Ztbc0002Mapper ztbc0002Mapper;
    @Autowired
    private ReadTpWebserviceUtil readTpWebserviceUtil;

    @Override
    public Ztbc0002 selectByTpcode(String ztpbar, String werks) {
        return ztbc0002Mapper.selectByTpcode(ztpbar, werks);
    }

    @Override
    public Ztbc0002 queryTpBarFromWS(String ztpbar) {
        DTREADTPReq req = new DTREADTPReq();
        DTREADTPReq.ITEM item = new DTREADTPReq.ITEM();
        item.setZTPBAR(ztpbar);
        req.setITEM(item);
        DTREADTPRes res = readTpWebserviceUtil.receiveConfirmation(req);
        Ztbc0002 ztbc0002 = new Ztbc0002();
        if (res != null) {
            if (res.getRETURN().getZFLAG().equals("S")) {
                ztbc0002.setZtpbar(res.getRETURN().getZTPBAR());
                ztbc0002.setMenge(Double.parseDouble(res.getRETURN().getMENGE()));
                ztbc0002.setZtpzt(res.getRETURN().getZTPZT());
            } else {
                ztbc0002.setZtpbar(res.getRETURN().getZTPBAR());
            }
        }
        return ztbc0002;
    }
}