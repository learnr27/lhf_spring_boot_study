package com.lhf.springboot.mapper;



import com.lhf.springboot.entity.po.TtlProductInfoPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author kundy
 * @create 2019/2/16 10:42 AM
 */
@Mapper
public interface TtlProductInfoMapper {

    List<TtlProductInfoPo> listProduct(Map<String, Object> map);

}
