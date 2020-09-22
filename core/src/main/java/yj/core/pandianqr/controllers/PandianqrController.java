package yj.core.pandianqr.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.fevor.dto.Fevor;
import yj.core.fevor.service.IFevorService;
import yj.core.pandianqr.dto.Pandianqr;
import yj.core.pandianqr.service.IPandianqrService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class PandianqrController extends BaseController {

    @Autowired
    private IPandianqrService service;
    @Autowired
    private IFevorService fevorService;

    @RequestMapping(value = "/wip/pandianqr/query")
    @ResponseBody
    public ResponseData query(Pandianqr dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/wip/pandianqr/submit")
    @ResponseBody
    public ResponseData update(HttpServletRequest request, @RequestBody List<Pandianqr> dto) {
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/wip/pandianqr/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<Pandianqr> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }

    @RequestMapping(value = {"/wip/pandianqr/insertPdRow"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public ResponseData insertPdRow(HttpServletRequest request) {
        ResponseData rs = new ResponseData();
        UUID uuid = java.util.UUID.randomUUID();
        String uuidstr = uuid.toString().replaceAll("-", "");
        String createdBy = "" + request.getParameter("createdBy");
        String fevorstr = request.getParameter("fevor") == null ? "" : request.getParameter("fevor");
        String zxhbar = request.getParameter("zxhbar") == null ? "" : request.getParameter("zxhbar");
        String username = request.getParameter("operator");
        Pandianqr pandianqr = new Pandianqr();
        pandianqr.setZbarcode(zxhbar);
        pandianqr.setId(uuidstr);
        pandianqr.setCreatedBy(Long.parseLong(createdBy));
        pandianqr.setCreationDate(new Date());
        pandianqr.setFevor(fevorstr);
        pandianqr.setUsername(username);
        if (!fevorstr.equals("")) {
            Fevor fevor = new Fevor();
            fevor = fevorService.selectByfevorSinger(pandianqr.getFevor());
            pandianqr.setFevorTxt(fevor.getTxt());
        } else {
            pandianqr.setFevorTxt("");
        }
        int result = service.insertPdRow(pandianqr);
        if (result == 1) {
            rs.setMessage("盘点数据保存成功");
            rs.setSuccess(true);
        } else {
            rs.setMessage("盘点数据保存失败");
            rs.setSuccess(false);
        }
        return rs;
    }


}