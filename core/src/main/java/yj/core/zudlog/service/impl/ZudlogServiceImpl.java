package yj.core.zudlog.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.hr.dto.Employee;
import com.hand.hap.hr.mapper.EmployeeMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zudlog.dto.Zudlog;
import yj.core.zudlog.mapper.ZudlogMapper;
import yj.core.zudlog.service.IZudlogService;

import java.util.List;

@Service
@Transactional
public class ZudlogServiceImpl extends BaseServiceImpl<Zudlog> implements IZudlogService {
    @Autowired
    private ZudlogMapper zudlogMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public int insertLog(Zudlog zudlog) {
        return zudlogMapper.insertLog(zudlog);
    }

    @Override
    public List<Zudlog> selectFromPage(IRequest requestContext, Zudlog dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Zudlog> list = zudlogMapper.selectFromPage(dto);
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                Employee list1 = new Employee();
                list1.setEmployeeId(list.get(i).getCreatedBy());
                Employee employee = employeeMapper.selectOne(list1);
                if(employee == null){
                    list.get(i).setCreatedBy1("");
                }else{
                    list.get(i).setCreatedBy1(employee.getEmployeeCode());
                }
            }
        }
        return list;
    }
}