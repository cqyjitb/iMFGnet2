package yj.core.zudlist.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.hr.mapper.EmployeeMapper;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.qjcode.mapper.QjcodeMapper;
import yj.core.zudlist.dto.Zudlist;
import yj.core.zudlist.mapper.ZudlistMapper;
import yj.core.zudlist.service.IZudlistService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZudlistServiceImpl extends BaseServiceImpl<Zudlist> implements IZudlistService{
    @Autowired
    private ZudlistMapper zudlistMapper;
    @Override
    public int insertItem(List<Zudlist> list) {
        int sum = 0;
        for (int i=0;i<list.size();i++){
            sum = sum + zudlistMapper.insertItem(list.get(i));
        }
        return sum;
    }

    @Override
    public List<Zudlist> selectZudlist(IRequest requestContext, Zudlist dto) {
        List<Zudlist> list = zudlistMapper.selectZudlist(dto);
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                if("".equals(list.get(i).getReviewc()) || list.get(i).getReviewc() == null){
                    list.get(i).setReviewc("F");
                }
            }
        }
        return list;
    }


    @Override
    public int updateItem(Zudlist zudlist) {
        return zudlistMapper.updateItem(zudlist);
    }

    @Override
    public List<Zudlist> selectByZudnumForUnprocess(String zudnum) {
        return zudlistMapper.selectByZudnumForUnprocess(zudnum);
    }

    @Override
    public Zudlist selectByIdAndItem(String zudnum, String item) {
        return zudlistMapper.selectByIdAndItem(zudnum,item);
    }
}