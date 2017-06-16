//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.hr.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.account.dto.User;
import com.hand.hap.account.dto.UserRole;
import com.hand.hap.account.mapper.UserRoleMapper;
import com.hand.hap.account.service.IUserRoleService;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.UserAndRoles;
import com.hand.hap.hr.mapper.EmployeeMapper;
import com.hand.hap.hr.service.IEmployeeService;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Created by libo on 2017/6/15.
 */
@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    private IMessagePublisher messagePublisher;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public EmployeeServiceImpl() {
    }

    public List<Employee> batchUpdate(IRequest request, List<Employee> list) {
        super.batchUpdate(request, list);
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Employee e = (Employee)var3.next();
            this.messagePublisher.publish("employee.change", e);
        }

        return list;
    }

    public List<Employee> queryAll(IRequest requestContext, Employee employee, int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        return this.employeeMapper.queryAll(employee);
    }

    public void createUserByEmployee(IRequest request, UserAndRoles roles) {
        User u = (User)this.userService.insertSelective(request, roles.getUser());
        if(null != roles.getRoles()) {
            Long userId = u.getUserId();
            List roles1 = roles.getRoles();
            Iterator var6 = roles1.iterator();

            while(var6.hasNext()) {
                UserRole role = (UserRole)var6.next();
                role.setUserId(userId);
                this.userRoleService.insertSelective(request, role);
            }
        }

    }

    public void updateUser(IRequest irequest, UserAndRoles userAndRoles) {
        if(userAndRoles.getRoles() != null) {
            List newRoles = userAndRoles.getRoles();
            User user = userAndRoles.getUser();
            this.userRoleMapper.deleteByUserId(user.getUserId());
            Iterator var5 = newRoles.iterator();

            while(var5.hasNext()) {
                UserRole role = (UserRole)var5.next();
                role.setUserId(user.getUserId());
                this.userRoleService.insertSelective(irequest, role);
            }
        }

    }

    @Override
    public Employee queryByCode(String code) {
        return employeeMapper.queryByCode(code);
    }

    public Employee getDirector(String employeeCode) {
        return this.employeeMapper.getDirector(employeeCode);
    }

    public Employee getDeptDirector(String employeeCode) {
        return this.employeeMapper.getDeptDirector(employeeCode);
    }
}
