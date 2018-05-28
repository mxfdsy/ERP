package com.lmfun.mapper.trace;

import com.lmfun.pojo.po.trace.UserOperationTracePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserOperationTraceMapper {

    int insertUserOperationTrace(UserOperationTracePO userOperationTracePO);

    //TODO: 根据搜索条件，分页查询共页面展示

    //TODO: 根据搜索条件，获取商户下所有操作日志，供导出使用

}
