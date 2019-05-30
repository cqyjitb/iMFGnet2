package yj.kanb.vblinegroupheader.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;

import java.util.List;

public interface VblinegroupheaderMapper extends Mapper<Vblinegroupheader> {
    List<Vblinegroupheader> selectAllGroup();
}
