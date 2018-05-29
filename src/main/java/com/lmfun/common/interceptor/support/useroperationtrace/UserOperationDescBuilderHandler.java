package com.lmfun.common.interceptor.support.useroperationtrace;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserOperationDescBuilderHandler {

    private final Map<String, UserOperationDescBuilder> builders = new HashMap<String, UserOperationDescBuilder>();

    public UserOperationDescBuilderHandler(){
        this.init();
    }

    private void init(){
        registerUserOperationDescBuilder("createProductDemo", new CreateShopDemoBuilder());
    }

    public UserOperationDescBuilder getBuilder(String builderName) {
        return builders.get(builderName);
    }

    private void registerUserOperationDescBuilder(String builderName, UserOperationDescBuilder userOperationDescBuilder){
        this.builders.put( builderName, userOperationDescBuilder);
    }


}
