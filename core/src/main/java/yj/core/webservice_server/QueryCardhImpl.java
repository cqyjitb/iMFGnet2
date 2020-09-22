package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.dto.CardhRec;
import yj.core.cardh.service.ICardhService;
import yj.core.webservice_server.dto.Aufnr;
import yj.core.webservice_server.dto.Rec_queryCardh;
import yj.core.webservice_server.dto.ReturnQueryCardh;
import yj.core.xhcard.dto.Xhcard;
import yj.core.xhcard.service.IXhcardService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface="yj.core.webservice_server.IqueryCardh", serviceName="queryCardh")
public class QueryCardhImpl implements IqueryCardh {
    @Autowired
    private ICardhService cardhService;
    @Autowired
    private IXhcardService xhcardService;
    @Override
    public ReturnQueryCardh queryCardh(Rec_queryCardh rec_queryCardh) {
        //String zpgdbar = rec_queryCardh.getZpgdbar().equals("")?null:rec_queryCardh.getZpgdbar();
        //String aufnr = rec_queryCardh.getAufnr().equals("")?null:rec_queryCardh.getAufnr();
        List<Aufnr> al = rec_queryCardh.getAufnrs();
        String zpgdbar = rec_queryCardh.getZpgdbar();
        ReturnQueryCardh rs = new ReturnQueryCardh();
        List<CardhRec> list = new ArrayList<>();
        if(al == null){
            al = new ArrayList<>();
        }
        Cardh cardh = new Cardh();
        if ((zpgdbar!= null && !zpgdbar.equals("")) || al.size() > 0){
            List<Cardh> listcardh = new ArrayList<>();
            if (al.size() == 0){
                listcardh = cardhService.selectByAufnrOrZpgdbar(null,zpgdbar);
            }else{
                for (int i = 0;i<al.size();i++){
                    List<Cardh> listtmp = new ArrayList<>();
                    listtmp = cardhService.selectByAufnrOrZpgdbar(al.get(i).getAufnr(),null);
                    if (listtmp.size() > 0){
                        for (int j=0;j<listtmp.size();j++){
                            listcardh.add(listtmp.get(j));
                        }
                        listtmp.clear();
                    }
                }
            }

            if (listcardh == null){
                rs.setFlag("E");
                rs.setMessage("error!");
                return rs;
            }else{
                if (listcardh.size() > 0){
                    for (int i = 0;i<listcardh.size();i++){
                        CardhRec dto = new CardhRec();
                        dto.setAufnr(listcardh.get(i).getAufnr());
                        dto.setMatnr(listcardh.get(i).getMatnr());
                        dto.setZpgdbar(listcardh.get(i).getZpgdbar());
                        dto.setMenge(listcardh.get(i).getMenge().toString());
                        Xhcard xhcard = new Xhcard();
                        xhcard = xhcardService.selectForZxhbar(listcardh.get(i).getWerks(),listcardh.get(i).getAufnr(),listcardh.get(i).getZxhnum());
                        if (xhcard == null){
                            dto.setZxhbar("");
                        }else{
                            dto.setZxhbar(xhcard.getZxhbar());
                        }
                        list.add(dto);
                    }
                    rs.setFlag("S");
                    rs.setItem(list);
                    rs.setMessage("sucess!");
                    return rs;
                }else{
                    rs.setFlag("E");
                    rs.setMessage("error!");
                    return rs;
                }
            }
        }else{
                rs.setFlag("E");
                rs.setMessage("error!");
                return rs;
        }
    }
}
