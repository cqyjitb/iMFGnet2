package yj.core.zudlist.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.zudlist.dto.Zudlist;

import java.util.List;

public interface IZudlistService extends IBaseService<Zudlist>, ProxySelf<IZudlistService>{
    int insertItem(List<Zudlist> list);
}