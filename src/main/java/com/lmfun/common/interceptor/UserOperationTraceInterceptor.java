package com.lmfun.common.interceptor;


import com.lmfun.common.interceptor.support.useroperationtrace.UserOperationTraceSupport;
import com.lmfun.common.interceptor.support.useroperationtrace.annotation.UserOperationRecord;
import com.lmfun.common.util.IpAddressUtil;
import com.lmfun.pojo.po.trace.UserOperationTracePO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class UserOperationTraceInterceptor {

    private Logger logger = LoggerFactory.getLogger(UserOperationTraceInterceptor.class);

    @Autowired
    private UserOperationTraceSupport userOperationTraceSupport;

    @Around( value = "execution(public * *(..)) && @annotation(userOperationRecord)")
    public Object aroundMethod(ProceedingJoinPoint pjp, UserOperationRecord userOperationRecord) {
        logger.info("UserOperationTraceInterceptor进入记录用户行为拦截器, methodName:{}", pjp.getSignature().getName());
        Object result = null;
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();


        long beginTime = System.currentTimeMillis();
        Object[] args = pjp.getArgs();

        try {
            //获取相关的business参数
            result = pjp.proceed();
            userOperationTracePO.setExecuteTime(System.currentTimeMillis() - beginTime);
            userOperationTracePO.setIsSucceed(1);
            userOperationTracePO.setRemoteAddress(IpAddressUtil.getIpAddr());
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            userOperationTracePO.setIsSucceed(0);
        }

        userOperationTraceSupport.asynInsertUserOperationTrace(args, userOperationRecord, userOperationTracePO);
        logger.info("已发送异步插入用户行为记录");
        return result;
    }




}
