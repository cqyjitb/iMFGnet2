package yj.core.webservice_server;

import yj.core.dispatch.dto.InputLog;
import yj.core.webservice_newbg.dto.DTBAOGONGParameters;
import yj.core.webservice_newbg.dto.DTBAOGONGParametersitem;
import yj.core.webservice_newbg.dto.DTBAOGONGReturnResult;

import javax.jws.WebService;
import java.text.ParseException;
import java.util.List;

/**
 * Created by 917110140 on 2018/9/17.
 */
@WebService
public interface IJjbg {
    DTBAOGONGReturnResult callbg(DTBAOGONGParameters params, List<DTBAOGONGParametersitem> list);
}
