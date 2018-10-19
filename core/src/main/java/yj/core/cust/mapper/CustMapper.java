package yj.core.cust.mapper;

import com.hand.hap.mybatis.common.Mapper;
import yj.core.cust.dto.Cust;

import java.util.List;

public interface CustMapper extends Mapper<Cust>{

    List<Cust> selectForLov(String kunnr);
}