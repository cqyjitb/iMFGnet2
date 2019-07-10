package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import yj.core.webservice_server.dto.Rec_ModifyZwipq;
import yj.core.webservice_server.dto.ReturnModifyZwipq;
import yj.core.zwipq.service.IZwipqService;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface="yj.core.webservice_server.IModifyZwipq", serviceName="ModifyZwipq")
public class ModifyZwipqImpl implements IModifyZwipq {

    @Autowired
    private IZwipqService zwipqService;
    @Override
    public ReturnModifyZwipq modify(List<Rec_ModifyZwipq> list) {
        ReturnModifyZwipq re = new ReturnModifyZwipq();
        if (list.size() == 0){
            re.setMsgty("E");
            re.setMessage("未接收到数据记录");
            return re;
        }


            int num = zwipqService.updateByTpcode(list);
        re.setMessage("修改入库标识"+num+"条");
        re.setMsgty("S");
        return re;
    }
}

