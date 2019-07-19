package yj.kanb.vblinegroupheader.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yj.kanb.vbgroupdtl.dto.Vbgroupdtl;
import yj.kanb.vbgroupdtl.mapper.VbgroupdtlMapper;
import yj.kanb.vblinegroupheader.dto.Vblinegroupheader;
import yj.kanb.vblinegroupheader.mapper.VblinegroupheaderMapper;
import yj.kanb.vblinegroupheader.service.IVblinegroupheaderService;
import yj.kanb.viewdataschemaline.mapper.ViewdataschemalineMapper;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
@Transactional
public class VblinegroupheaderServiceImpl extends BaseServiceImpl<Vblinegroupheader> implements IVblinegroupheaderService {
    @Autowired
    private VblinegroupheaderMapper vblinegroupheaderMapper;
    @Autowired
    private VbgroupdtlMapper vbgroupdtlMapper;
    @Autowired
    private ViewdataschemalineMapper viewdataschemalineMapper;
    @Override
    public List<Vblinegroupheader> selectAllGroup() {
        return vblinegroupheaderMapper.selectAllGroup();
    }

    @Override
    public List<Vblinegroupheader> queryLineGroupH(IRequest requestCtx, Vblinegroupheader dto) {
        return vblinegroupheaderMapper.selectLineGroupH(dto);
    }

    @Override
    public String deleteLineGroupH(IRequest requestCtx, List<Vblinegroupheader> dto) {
        if(dto.size() >0){
            for(int i=0;i<dto.size();i++){
                List<Vbgroupdtl> list = vbgroupdtlMapper.queryByGroupId(dto.get(i).getGroupId());
                if (list.size() > 0){
                    return "该产品已配置产线，不能删除!";
                }else{
                    viewdataschemalineMapper.deleteViewdataschemaline(dto.get(i).getGroupId());
                    vblinegroupheaderMapper.deleteLineGroupH(dto.get(i));
                }
            }
        }
        return null;
    }

    @Override
    public String insertOrUpdate(IRequest requestCtx, List<Vblinegroupheader> dto, String userId) {
        if(dto.size() >0){
            for(int i=0;i<dto.size();i++){
                Vblinegroupheader lineGroupH = dto.get(i);
                if("".equals(lineGroupH.getGroupId()) || lineGroupH.getGroupId() == null){
                    String groupId = UUID.randomUUID().toString();
                    lineGroupH.setGroupId(groupId);
                    lineGroupH.setCreatedBy(Long.valueOf(userId));
                    lineGroupH.setCreationDate(new Date());
                    vblinegroupheaderMapper.insertLineGroupH(lineGroupH);
                }else {
                    lineGroupH.setLastUpdatedBy(Long.valueOf(userId));
                    lineGroupH.setLastUpdateDate(new Date());
                    vblinegroupheaderMapper.updateLineGroupH(lineGroupH);
                }
            }
        }
        return null;
    }

    @Override
    public String setMessage(List<Vblinegroupheader> dto) {
        if(dto.size() >0){
            for(int i=0;i<dto.size();i++){
                if(dto.get(i).getBukrs() == null || "".equals(dto.get(i).getBukrs())){
                    return "公司不能为空！";
                }else if(dto.get(i).getWerks() == null || "".equals(dto.get(i).getWerks())){
                    return "工厂不能为空！";
                }else if(dto.get(i).getWorkshopId() == null || "".equals(dto.get(i).getWorkshopId())) {
                    return "车间ID不能为空！";
                }else if(dto.get(i).getLineId() == null){
                    return "产线组不能为空！";
                }else if(!("".equals(dto.get(i).getTempleteUrl()))){
                    try {
                        URL url = new URL(dto.get(i).getTempleteUrl());
                        URLConnection conn = url.openConnection();
                        conn.connect();
                    } catch (IOException e) {
                        return "模报URL格式不正确！";
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Vblinegroupheader> selectLineGroupH(IRequest requestCtx, String vbgroupId) {
        return vblinegroupheaderMapper.selectLineGroupH2(vbgroupId);
    }
}
