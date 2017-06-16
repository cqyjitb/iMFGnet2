
package com.hand.hap.hr.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.UserAndRoles;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by libo on 2017/6/15.
 */
@Controller
public class EmployeeController extends BaseController {
    @Autowired
    private IEmployeeService employeeService;

    public EmployeeController() {
    }

    @RequestMapping({"/hr/employee/query"})
    @ResponseBody
    public ResponseData query(Employee employee, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pagesize, HttpServletRequest request) {
        IRequest requestContext = this.createRequestContext(request);
        return new ResponseData(this.employeeService.select(requestContext, employee, page, pagesize));
    }

    @RequestMapping({"hr/employee/queryAll"})
    @ResponseBody
    public ResponseData queryAll(Employee employee, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pagesize, HttpServletRequest request) {
        IRequest requestContext = this.createRequestContext(request);
        return new ResponseData(this.employeeService.queryAll(requestContext, employee, page, pagesize));
    }

    @RequestMapping({"/hr/employee/submit"})
    @ResponseBody
    public ResponseData submit(@RequestBody List<Employee> employees, BindingResult result, HttpServletRequest request) {
        this.getValidator().validate(employees, result);
        if(result.hasErrors()) {
            ResponseData requestContext1 = new ResponseData(false);
            requestContext1.setMessage(this.getErrorMessage(result, request));
            return requestContext1;
        } else {
            IRequest requestContext = this.createRequestContext(request);
            return new ResponseData(this.employeeService.batchUpdate(requestContext, employees));
        }
    }

    @RequestMapping({"hr/employee/create_user"})
    @ResponseBody
    public ResponseData createUserByEmployee(@RequestBody UserAndRoles userAndRoles, HttpServletRequest request) {
        IRequest request1 = this.createRequestContext(request);
        this.employeeService.createUserByEmployee(request1, userAndRoles);
        return new ResponseData();
    }

    @RequestMapping({"hr/employee/update_user"})
    @ResponseBody
    public ResponseData updateUser(@RequestBody UserAndRoles userAndRoles, HttpServletRequest request) {
        IRequest irequest = this.createRequestContext(request);
        this.employeeService.updateUser(irequest, userAndRoles);
        return new ResponseData();
    }

    @RequestMapping(value = "/hr/employee/queryByCode",method = RequestMethod.GET)
    @ResponseBody
    public ResponseData queryByCode(HttpServletRequest request){
        String code = request.getParameter("code");
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = this.employeeService.queryByCode(code);
        System.out.println(employee);
        list.add(employee);
        return new ResponseData(list);
    }
}
