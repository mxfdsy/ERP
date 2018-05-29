package com.lmfun.pojo.po.account;

import com.alibaba.fastjson.annotation.JSONField;

public class UserRolePO {
    @JSONField(name = "user_uid")
    private String userUid;
    
	@JSONField(name = "role_uid")
	private String roleUid;
	
	@JSONField(name = "role_key")
	private String roleKey;
	
	@JSONField(name = "mall_uid")
	private String mallUid;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

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

    public String getMallUid() {
        return mallUid;
    }

    public void setMallUid(String mallUid) {
        this.mallUid = mallUid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserRolePO [userUid=");
        builder.append(userUid);
        builder.append(", roleUid=");
        builder.append(roleUid);
        builder.append(", roleKey=");
        builder.append(roleKey);
        builder.append(", mallUid=");
        builder.append(mallUid);
        builder.append("]");
        return builder.toString();
    }
}
