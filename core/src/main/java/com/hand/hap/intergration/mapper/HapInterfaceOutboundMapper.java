package com.hand.hap.intergration.mapper;

import com.hand.hap.intergration.dto.HapInterfaceOutbound;
import com.hand.hap.mybatis.common.Mapper;

import java.util.List;

public interface HapInterfaceOutboundMapper extends Mapper<HapInterfaceOutbound> {

    List<HapInterfaceOutbound> select (HapInterfaceOutbound outbound);
}