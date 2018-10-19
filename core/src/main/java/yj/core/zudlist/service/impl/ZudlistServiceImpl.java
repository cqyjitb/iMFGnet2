package yj.core.zudlist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}