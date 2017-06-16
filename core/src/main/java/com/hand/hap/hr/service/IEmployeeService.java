//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.hr.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.dto.UserAndRoles;
import com.hand.hap.system.service.IBaseService;
import java.util.List;
/**
 * Created by libo on 2017/6/15.
 */
public interface IEmployeeService extends IBaseService<Employee>, ProxySelf<IEmployeeService> {
    List<Employee> queryAll(IRequest var1, Employee var2, int var3, int var4);

    void createUserByEmployee(IRequest var1, UserAndRoles var2);

    Employee getDirector(String var1);

    Employee getDeptDirector(String var1);

    void updateUser(IRequest var1, UserAndRoles var2);

    Employee queryByCode(String code);
}
