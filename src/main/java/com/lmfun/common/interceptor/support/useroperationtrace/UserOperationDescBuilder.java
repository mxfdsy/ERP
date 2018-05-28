package com.lmfun.common.interceptor.support.useroperationtrace;


import com.lmfun.pojo.dto.UserOperationTraceDTO;

/**
 * @Descriptions:
 * @Author: daijl
 * @Date : 2018-05-25 9:45
 */
public interface UserOperationDescBuilder {

    String builder(UserOperationTraceDTO userOperationTraceDTO);
}
