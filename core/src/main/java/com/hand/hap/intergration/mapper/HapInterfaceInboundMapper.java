package com.hand.hap.intergration.mapper;

import com.hand.hap.intergration.dto.HapInterfaceInbound;
import com.hand.hap.mybatis.common.Mapper;

import java.util.List;

public interface HapInterfaceInboundMapper extends Mapper<HapInterfaceInbound> {

    List<HapInterfaceInbound> select (HapInterfaceInbound inbound);
}