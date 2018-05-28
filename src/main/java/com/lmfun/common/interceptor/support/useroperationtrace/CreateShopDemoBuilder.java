package com.lmfun.common.interceptor.support.useroperationtrace;


import com.lmfun.pojo.dto.UserOperationTraceDTO;

/**
 * @Descriptions:
 * @Author: daijl
 * @Date : 2018-05-25 9:48
 */
public class CreateShopDemoBuilder implements UserOperationDescBuilder {

    private String DESC_FORMAT = "哥创建了商品‘%s’，这句是直观显示用的";

    @Override
    public String builder(UserOperationTraceDTO userOperationTraceDTO) {
        return String.format(DESC_FORMAT, getProductName(userOperationTraceDTO.getBusinessUids()));
    }

    public String getProductName( String productUid ){
        return "This is a demo";
    }
}
