package yj.core.zudlist.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.inoutrecord.dto.InOutRecord;
import yj.core.inoutrecord.mapper.InOutRecordMapper;
import yj.core.qjcode.dto.Qjcode;
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
    @Autowired
    private InOutRecordMapper inOutRecordMapper;
    @Autowired
    private QjcodeMapper qjcodeMapper;
    @Override
    public int insertItem(List<Zudlist> list) {
        int sum = 0;
        for (int i=0;i<list.size();i++){
            sum = sum + zudlistMapper.insertItem(list.get(i));
        }
        return sum;
    }

    @Override
    public List<Zudlist> selectZudlist(IRequest requestContext, Zudlist dto, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Zudlist> list = zudlistMapper.selectZudlist(dto);
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                Zudlist zudlist = list.get(i);
                InOutRecord inOutRecord = inOutRecordMapper.selectById(zudlist.getZqjjlh());
                Qjcode qjcode = qjcodeMapper.selectById(Integer.valueOf(zudlist.getZotype()));
                zudlist.setRcode(qjcode.getRcode());
                zudlist.setVornr_old(inOutRecord.getVornr());
            }
        }
        return list;
    }
}