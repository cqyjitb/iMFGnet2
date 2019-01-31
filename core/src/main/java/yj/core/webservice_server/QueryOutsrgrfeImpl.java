package yj.core.webservice_server;

import org.springframework.beans.factory.annotation.Autowired;
import yj.core.outsrgrfe.dto.Outsrgrfe;
import yj.core.outsrgrfe.service.IOutsrgrfeService;
import yj.core.webservice_server.dto.Rec_queryoutsrgrfe;

import java.util.ArrayList;
import java.util.List;

public class QueryOutsrgrfeImpl implements IqueryOutsrgrfe {
    @Autowired
    private IOutsrgrfeService outsrgrfeService;
    @Override
    public List<Rec_queryoutsrgrfe> QueryOutsrgrfe(Rec_queryoutsrgrfe req) {
        String werks = req.getWerks();
        String matnr = req.getMatnr();
        String lifnr = req.getLifnr();
        String ktsch = req.getKtsch();
        List<Outsrgrfe> list = new ArrayList<>();
        List<Rec_queryoutsrgrfe> list2= new ArrayList<>();
        list = outsrgrfeService.sapquery(werks,matnr,lifnr,ktsch);
        if (list.size() > 0){
            for (int i = 0;i<list.size();i++){
                Rec_queryoutsrgrfe rec = new Rec_queryoutsrgrfe();
                rec.setKtsch(list.get(i).getKtsch());
                rec.setLifnr(list.get(i).getLifnr());
                rec.setMatnr(list.get(i).getMatnr());
                rec.setWerks(list.get(i).getWerks());
                list2.add(rec);
            }
        }
        return list2;
    }
}
