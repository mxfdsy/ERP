package com.lmfun.service.account;

import javax.servlet.http.HttpServletRequest;

public interface UserAccountService {

    boolean isLogin(HttpServletRequest request);

    String login(HttpServletRequest request, String loginName, String password);

}
