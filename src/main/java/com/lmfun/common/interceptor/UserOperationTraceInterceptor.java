package com.lmfun.common.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lmfun.common.constant.enumeration.UserOperationTypeEnum;
import com.lmfun.common.interceptor.support.useroperationtrace.UserOperationDescBuilderHandler;
import com.lmfun.common.interceptor.support.useroperationtrace.annotation.UserOperationRecord;
import com.lmfun.common.util.IpAddressUtil;
import com.lmfun.common.util.SessionGetter;
import com.lmfun.pojo.po.trace.UserOperationTracePO;
import com.lmfun.service.trace.UserOperationTraceService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Aspect
@Component
public class UserOperationTraceInterceptor {

    private Logger logger = LoggerFactory.getLogger(UserOperationTraceInterceptor.class);

    private static final int DEFAULT_KEY_LOCATION = -1;

    @Autowired
    private UserOperationTraceService userOperationTraceService;

    @Autowired
    private UserOperationDescBuilderHandler userOperationDescBuilderHandler;

    @Around( value = "execution(public * *(..)) && @annotation(userOperationRecord)")
    public Object aroundMethod(ProceedingJoinPoint pjp, UserOperationRecord userOperationRecord) {
        Object result = null;
        UserOperationTracePO userOperationTracePO = new UserOperationTracePO();

        long beginTime = System.currentTimeMillis();
        userOperationTracePO.setOperationName(userOperationRecord.name());

        try {
            //获取相关的business参数
            Object[] args = pjp.getArgs();
            setBusinessParameters(args, userOperationRecord, userOperationTracePO);
            userOperationTracePO.setExecuteTime(System.currentTimeMillis() - beginTime);
            userOperationTracePO.setIsSucceed(1);
        }catch (Throwable throwable) {
            throwable.printStackTrace();
            userOperationTracePO.setIsSucceed(0);
        }

        asynInsertUserOperationTrace(userOperationTracePO);
        return result;
    }

    public void setBusinessParameters(Object[] args, UserOperationRecord userOperationRecord, UserOperationTracePO userOperationTracePO ) {
        userOperationTracePO.setBusinessUids(getBusinessUids(args,userOperationRecord));
        userOperationTracePO.setBusinessType(getBusinessType(userOperationRecord));
        userOperationTracePO.setDescription(userOperationDescBuilderHandler.getBuilder(userOperationRecord.descTemplateName()).builder(userOperationTracePO));
    }

    private String getBusinessUids(Object[] args, UserOperationRecord userOperationRecord){
        int parameterLocation = userOperationRecord.parameterLocation();
        if(args.length == 0){
            return null;
        }
        if(args.length > 0  && (userOperationRecord.parameterLocation() > args.length || userOperationRecord.parameterLocation() < 0)){
            return null;
        }
        if(args.length > 0 && !isValidKeyPath(userOperationRecord) && isValidIndex(args.length, parameterLocation) ){
            return args[parameterLocation].toString();
        }
        if( args.length>0 && isValidKeyPath(userOperationRecord) && isValidIndex(args.length, parameterLocation) && userOperationRecord.isArray() != 1 ){
            return getSingleBusinessUidFromJSON(args[parameterLocation], getWholePath(userOperationRecord));
        }
        if( args.length>0 && isValidIndex(args.length, parameterLocation)  && userOperationRecord.isArray()==1){
            return getBusinessUidsFromJSON(args[parameterLocation], userOperationRecord.path());
        }
        return null;
    }

    private String getWholePath(UserOperationRecord userOperationRecord ){
        if(StringUtils.isNotBlank(userOperationRecord.userOperationType().getIndexKey())) {
            return userOperationRecord.userOperationType().getIndexKey();
        }
        if( StringUtils.isNotBlank(userOperationRecord.path())) {
            return userOperationRecord.path();
        }
        return "";
    }

    private boolean isValidKeyPath(UserOperationRecord userOperationRecord) {
        return StringUtils.isNotBlank( getWholePath(userOperationRecord));
    }

    private String getBusinessUidsFromJSON(Object arg, String path){
        StringBuilder result = new StringBuilder("");
        String parentPathName = path.indexOf(".") == -1 ? "":path.substring(0, path.lastIndexOf("."));
        String key = path.indexOf(".") == -1 ? path : path.substring(path.lastIndexOf(".")+1);
        JSONArray parentJSONArray = getParentJSONArray(arg, parentPathName);
        try {
            for(int i=0; i<parentJSONArray.size(); i++) {
                result.append(parentJSONArray.getJSONObject(i).get(key));
                if(i < parentJSONArray.size() - 1 ) {
                    result.append(",");
                }
            }
        }catch (Exception e) {
            return null;
        }
        return result.toString();
    }

    private JSONArray getParentJSONArray(Object arg, String parentPathName) {
        JSONArray result = null;
        try {
            if(StringUtils.isBlank( parentPathName )){
                result = JSON.parseArray(arg.toString());
                return result;
            }
            String[] pathArray = parentPathName.split(",");
            JSONObject targetJSON = JSONObject.parseObject(arg.toString());
            for(int i=0; i<pathArray.length; i++) {
                if(i == pathArray.length - 1 ) {
                    result = targetJSON.getJSONArray(pathArray[i]);
                    return result;
                }
                targetJSON = targetJSON.getJSONObject(pathArray[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.parseArray("[]");
    }

    private String getSingleBusinessUidFromJSON(Object arg, String wholePath){
        if(StringUtils.isBlank(wholePath)){
            return null;
        }
        try {
            JSONObject targetJSON = JSONObject.parseObject(arg.toString());
            String pathArray[] = wholePath.split("\\.");
            for(int i = 0; i<pathArray.length; i++ ){
                if( i < pathArray.length - 1) {
                    targetJSON = targetJSON.getJSONObject(pathArray[i]);
                    continue;
                }
                return targetJSON.getString(pathArray[i]);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidIndex(int size, int index){
        return index < size && index >= 0;
    }


    private String getBusinessType(UserOperationRecord userOperationRecord){
        if(!userOperationRecord.userOperationType().equals(UserOperationTypeEnum.TYP_DEFAULT)){
            return userOperationRecord.userOperationType().name();
        }
        return userOperationRecord.customizedType();
    }

    @Async
    public void asynInsertUserOperationTrace(UserOperationTracePO userOperationTracePO) {
        userOperationTracePO.setOperationTime(Calendar.getInstance().getTime());
        userOperationTracePO.setRemoteAddress(IpAddressUtil.getIpAddr());

        //FIXME Hook: Blocked by userLogin, comment out temporary
//        LoginUserDTO user = SessionGetter.getUser();
//        if (null != user) {
//            userOperationTraceDTO.setOperatorUserUid(Long.valueOf(user.getUserUid()));
//            userOperationTraceDTO.setOperatorPhone(user.getPhone());
//            userOperationTraceDTO.setOperatorName(user.getName());
//        }
        logger.info("asynInsertUserOperationTrace异步记录客户操作行为，{}", JSON.toJSONString(userOperationTracePO));
        userOperationTraceService.insertUserOperationTrace(userOperationTracePO);
    }
}
