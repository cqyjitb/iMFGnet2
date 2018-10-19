package yj.core.webservice_server;

import java.util.List;
import javax.jws.WebService;

import yj.core.webservice_server.dto.*;

@WebService
public  interface IsyncAufnr
{
     ReturnMessage sync(List<Rec_afko> paramList, List<Rec_afvc> paramList1, List<Rec_marc> rec_marc, List<Rec_t435t> rec_t435ts,List<Rec_resb> rec_resbs);
}
