package com.lmfun.pojo.dto.user;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginRoleDTO implements Serializable {

    private static final long serialVersionUID = -6544122799775126570L;

    @JSONField(name = "role_uid")
    private String roleUid;
    
    @JSONField(name = "role_key")
    private String roleKey;

    public String getRoleUid() {
        return roleUid;
    }

    public void setRoleUid(String roleUid) {
        this.roleUid = roleUid;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginRoleDTO [roleUid=");
        builder.append(roleUid);
        builder.append(", roleKey=");
        builder.append(roleKey);
        builder.append("]");
        return builder.toString();
    }
}
