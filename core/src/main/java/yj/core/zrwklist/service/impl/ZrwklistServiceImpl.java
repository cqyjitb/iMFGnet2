package yj.core.zrwklist.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.zrwklist.dto.Zrwklist;
import yj.core.zrwklist.mapper.ZrwklistMapper;
import yj.core.zrwklist.service.IZrwklistService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZrwklistServiceImpl extends BaseServiceImpl<Zrwklist> implements IZrwklistService{
    @Autowired
    private ZrwklistMapper zrwklistMapper;
    @Override
    public int insertItem(List<Zrwklist> list) {
        int sum = 0;
        if (list.size() > 0){
            for(int i=0;i<list.size();i++){
                sum = sum + zrwklistMapper.insertItem(list.get(i));
            }

        }
        return sum;
    }
}