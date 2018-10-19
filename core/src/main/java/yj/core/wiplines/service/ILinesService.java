package yj.core.wiplines.service;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import yj.core.wiplines.dto.Lines;

public interface ILinesService extends IBaseService<Lines>, ProxySelf<ILinesService> {
    Lines selectById(Long line_id);
}