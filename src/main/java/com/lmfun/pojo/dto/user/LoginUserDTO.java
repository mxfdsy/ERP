package com.lmfun.pojo.dto.user;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.builder.ToStringBuilder;


import java.io.Serializable;
import java.util.List;
import java.util.Set;


public class LoginUserDTO implements Serializable {
    private static final long serialVersionUID = -6148782076805864643L;
    
    @JSONField(name = "session_key")
    private String sessionKey;
    
    @JSONField(name = "verified")
    private boolean verified;
    
    @JSONField(name = "user_uid")
    private String userUid;
    
    @JSONField(name = "login_name")
    private String loginName;
    
    @JSONField(name = "phone")
    private String phone;
    
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "gender")
    private String gender;
    
    @JSONField(name = "role_info")
    private List<LoginRoleDTO> roleInfo;
    
    @JSONField(name = "permission_info")
    private Set<String> permissionInfo;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<LoginRoleDTO> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<LoginRoleDTO> roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Set<String> getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(Set<String> permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sessionKey", sessionKey)
                .append("verified", verified)
                .append("userUid", userUid)
                .append("loginName", loginName)
                .append("phone", phone)
                .append("name", name)
                .append("gender", gender)
                .append("roleInfo", roleInfo)
                .append("permissionInfo", permissionInfo)
                .toString();
    }
}
