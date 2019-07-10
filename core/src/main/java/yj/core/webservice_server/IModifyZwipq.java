package yj.core.webservice_server;

import yj.core.webservice_server.dto.Rec_ModifyZwipq;
import yj.core.webservice_server.dto.ReturnModifyZwipq;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface IModifyZwipq {
    ReturnModifyZwipq modify(List<Rec_ModifyZwipq> list);
}
