package com.lmfun.pojo.po.account;

import com.lmfun.pojo.po.BasePO;

public class UserAccountPO extends BasePO {
	private String userUid;
	
	private String name;
	
	private String loginName;
	
	private String password;
	
	private String phone;
	
    private String status;

	private String memo;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SimpleUserAccountPO [userUid=");
        builder.append(userUid);
        builder.append(", name=");
        builder.append(name);
        builder.append(", loginName=");
        builder.append(loginName);
        builder.append(", password=");
        builder.append(password);
        builder.append(", phone=");
        builder.append(phone);
        builder.append(", status=");
        builder.append(status);
        builder.append(", memo=");
        builder.append(memo);
        builder.append("]");
        return builder.toString();
    }
}
