package com.lmfun.pojo.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class UserOperationTraceDTO extends BaseDTO{

    @JSONField(name = "user_operation_trace_uid")
    private Long userOperationTraceUid;

    @JSONField(name = "operator_user_uid")
    private Long operatorUserUid;

    @JSONField(name = "operator_phone")
    private String operatorPhone;

    @JSONField(name = "operator_name")
    private String operatorName;

    @JSONField(name = "remote_address")
    private String remoteAddress;

    @JSONField(name = "browser_name")
    private String browserName;

    @JSONField(name = "operation_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;

    @JSONField(name = "operation_name")
    private String operationName;

    //操作对应的主键名字
    @JSONField(name = "business_type")
    private String businessType;
    
    //操作对应的主键值。批量操作时，格式为主键id间用,间隔
    @JSONField(name = "business_uids")
    private String businessUids;

    //执行时间，单位millisecond
    @JSONField(name = "execute_time")
    private Long executeTime;

    //执行结果，1成功，0失败
    @JSONField(name = "is_succeed")
    private Integer isSucceed;

    //行为描述语句，用于直接显示在前端列表中
    @JSONField(name = "description")
    private String description;
    
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Long getUserOperationTraceUid() {
        return userOperationTraceUid;
    }

    public void setUserOperationTraceUid(Long userOperationTraceUid) {
        this.userOperationTraceUid = userOperationTraceUid;
    }

    public Long getOperatorUserUid() {
        return operatorUserUid;
    }

    public void setOperatorUserUid(Long operatorUserUid) {
        this.operatorUserUid = operatorUserUid;
    }

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setBusinessUids(String businessUids) {
        this.businessUids = businessUids;
    }

    public String getBusinessUids() {
        return businessUids;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(Integer isSucceed) {
        this.isSucceed = isSucceed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
