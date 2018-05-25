package com.lmfun.common.util;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class SessionGetter {
    /**
     * 登录用户的
     *
     * @return
     */
   /* public static LoginUserDTO getUser() {
        return (LoginUserDTO) getObjectValue(Constants.LOGIN_USER);
    }
    */
    /**
     * 登录用户的
     *
     * @return
     */
   /* public static String getLoginUserUid() {
        return (String) getObjectValue(Constants.USER_UID);
    }
*/
    public static String getRequstId() {
        return (String) getObjectValue("requstId");
    }

    public static Object getObjectValue(String key) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        return request.getSession().getAttribute(key);
    }

}