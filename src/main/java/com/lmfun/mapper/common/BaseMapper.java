package com.lmfun.mapper.common;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMapper {
    
    String getUuid();

}
