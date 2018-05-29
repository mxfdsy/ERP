package com.lmfun.common.interceptor.support.useroperationtrace;

import com.lmfun.pojo.po.trace.UserOperationTracePO;

/**
 * @Descriptions:
 * @Author: daijl
 * @Date : 2018-05-29 14:19
 */
public class DefaultDescBuilder implements UserOperationDescBuilder {

    private String content;

    public DefaultDescBuilder( String content ) {
        this.content = content;
    }

    @Override
    public String builder(UserOperationTracePO userOperationTracePO) {
        return getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
