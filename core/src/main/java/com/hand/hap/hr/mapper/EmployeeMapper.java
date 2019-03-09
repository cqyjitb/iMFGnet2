//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.hr.mapper;

import com.hand.hap.hr.dto.Employee;
import com.hand.hap.mybatis.common.Mapper;
import java.util.List;
/**
 * Created by libo on 2017/6/15.
 */
public interface EmployeeMapper extends Mapper<Employee> {
    Employee queryByCode(String var1);

    Employee getDirector(String var1);

    Employee getDeptDirector(String var1);

    List<Employee> queryAll(Employee var1);

    List<Employee> selectEmployeeCode(String code);
}
