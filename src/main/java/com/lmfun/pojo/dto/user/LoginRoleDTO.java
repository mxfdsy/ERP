package com.lmfun.pojo.dto.user;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginRoleDTO implements Serializable {

    private static final long serialVersionUID = -6544122799775126570L;

    @JSONField(name = "role_uid")
    private String roleUid;
    
    @JSONField(name = "role_key")
    private String roleKey;
    
    @JSONField(name = "mall_uid")
    public List<String> mallUid;

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

    public List<String> getMallUid() {
        return mallUid;
    }

    public void setMallUid(List<String> mallUid) {
        this.mallUid = mallUid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoginRoleDTO [roleUid=");
        builder.append(roleUid);
        builder.append(", roleKey=");
        builder.append(roleKey);
        builder.append(", mallUid=");
        builder.append(mallUid);
        builder.append("]");
        return builder.toString();
    }

}
