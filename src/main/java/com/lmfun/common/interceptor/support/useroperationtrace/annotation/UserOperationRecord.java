package com.lmfun.common.interceptor.support.useroperationtrace.annotation;


import com.lmfun.common.constant.enumeration.UserOperationTypeEnum;

import java.lang.annotation.*;

/**
 * @Descriptions:
 * @Author: daijl
 * @Date : 2018-05-16 15:03
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserOperationRecord {
    String name() default "";

    UserOperationTypeEnum userOperationType() default UserOperationTypeEnum.TYP_DEFAULT;

    int  parameterLocation() default -1;

    String customizedType() default "";

    int isArray() default 0;

    String path() default "";

    String descTemplateName();
}

//UserOperationRecord(name ="", parameterLocation = 0, customizedType = "TYP_PROMOTION", path="card.promotion.sub_promotion_uid")
