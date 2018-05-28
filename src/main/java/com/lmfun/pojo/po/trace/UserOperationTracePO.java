package com.lmfun.pojo.po.trace;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.lmfun.pojo.po.BasePO;

import java.util.Date;

/**
 * @Descriptions:
 * @Author: daijl
 * @Date : 2018-05-16 11:09
 */
public class UserOperationTracePO extends BasePO {

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

    @JSONField(name = "business_type")
    private String businessType;

    @JSONField(name = "business_uids")
    private String businessUids;

    //执行时间，单位millisecond
    @JSONField(name = "execute_time")
    private Long executeTime;

    //执行结果，1成功，0失败
    @JSONField(name = "is_succeed")
    private Integer isSucceed;

    @JSONField(name = "original_value")
    private String originalValue;

    @JSONField(name = "new_value")
    private String newValue;

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

    public String getBusinessUids() {
        return businessUids;
    }

    public void setBusinessUids(String businessUids) {
        this.businessUids = businessUids;
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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
