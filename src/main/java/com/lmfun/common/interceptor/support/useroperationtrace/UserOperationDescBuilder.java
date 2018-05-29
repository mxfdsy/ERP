package com.lmfun.common.interceptor.support.useroperationtrace;


import com.lmfun.pojo.po.trace.UserOperationTracePO;

/**
 * @Descriptions:
 * @Author: daijl
 * @Date : 2018-05-25 9:45
 */
public interface UserOperationDescBuilder {

    String builder(UserOperationTracePO userOperationTracePO);
}
