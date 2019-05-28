package yj.kanb.equipment.controllers;


import com.hand.hap.comm.DataSourceEnum;
import com.hand.hap.comm.DataSourceHolder;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import yj.core.afko.dto.Afko;
import yj.core.afko.service.IAfkoService;
import yj.kanb.equipment.dto.Equipment;
import yj.kanb.equipment.service.IEquipmentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EquimentContorller  extends BaseController {

    @Autowired
    private IEquipmentService service;
    @Autowired
    private IAfkoService afkoService;

    @RequestMapping(value = {"/kanb/equipment/selectAfko"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData selectAfko(HttpServletRequest request){
        DataSourceHolder.setDataSources(DataSourceEnum.mySqlDataSource.getKey());
        List<Equipment> list = service.selectAllData();

        DataSourceHolder.setDataSources(DataSourceEnum.mainDataSource.getKey());
        Afko afko = afkoService.selectByAufnr("1000000411");

        ResponseData rs = new ResponseData();

        rs.setSuccess(true);
        return rs;

    }

}
