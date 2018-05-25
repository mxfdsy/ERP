package com.lmfun.common.filter;

import com.google.gson.Gson;
import com.lmfun.common.constant.Constants;
import com.lmfun.common.constant.enumeration.BaseCode;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用注解标注过滤器
 * <br/>
 *
 * @author 蘑小菇
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器 <br/>
 * 属性filterName声明过滤器的名称,可选属性urlPatterns指定要过滤的URL模式,也可使用属性value来声明.<br/>
 * (指定要过滤的URL模式是必选属性)<br/>
 * @create 2016年9月21日
 */
@Component
@WebFilter(filterName = "loginSessionFilter", urlPatterns = "/*")
public class LoginSessionFilter implements Filter {

    private final PathMatcher pathMatcher = new AntPathMatcher();

    public final static List<String> IS_NOT_LOGIN_VALIDATE_PATH = new ArrayList<String>();

    static {
        IS_NOT_LOGIN_VALIDATE_PATH.add("/user/resetPassword.html");
        IS_NOT_LOGIN_VALIDATE_PATH.add("/user/sendSmsCode");
        IS_NOT_LOGIN_VALIDATE_PATH.add("/user/reset/nextStep");
        IS_NOT_LOGIN_VALIDATE_PATH.add("/user/reset/doRestPassWord");
    }

    /**
     * 静态资源后缀名
     */
    public final static String[] STATIC_RESCUE = new String[]{
            ".jpg", ".jpeg", ".gif", ".css", ".js", ".png", ".bmp", ".ico",
            ".txt", ".mp3", ".eot", ".svg", ".ttf", ".woff", ".woff2", ".map"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        if (isStaticRescue(uri) || isNotLoginValidate(uri)) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute("requestId", "");

        HttpSession session = request.getSession();
//TODO Becky
//        LoginUserDTO loginUser = (LoginUserDTO) session.getAttribute(Constants.LOGIN_USER);
        
        if (null != "") {
            // 已登录
            chain.doFilter(request, response);
            return;
        } else if (isAjaxReq(request)) {//如果是ajax 请求

            Map<String, Object> responseObject = new HashMap<>();
            responseObject.put("code", BaseCode.SESSION_INVALID.getCode());
            responseObject.put("errorMsg", BaseCode.SESSION_INVALID.getMessage());
            responseObject.put("referer", request.getHeader("referer"));
            session.setAttribute("redirectUrl", request.getHeader("referer"));//把url放到session

            response.setContentType("application/json;charset=utf-8");
            response.setStatus(BaseCode.SESSION_INVALID.getCode());
            java.io.PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            out.write(gson.toJson(responseObject));
            out.flush();
            out.close();
        } else {
            session.setAttribute("redirectUrl", uri);//把url放到session
            // 未登录------->返回登录页
            response.setContentType("text/html");
            response.getWriter().print("<script>document.location.href='/login'</script>");

            return;
        }

    }

    private boolean isStaticRescue(String url) {

        if (url.lastIndexOf(".") == -1) {
            return false;
        }

        String suffix = url.substring(url.lastIndexOf("."));

        return ArrayUtils.contains(STATIC_RESCUE, suffix);
    }

    /**
     * 白名单过滤
     *
     * @param uri
     * @return
     */
    private boolean isNotLoginValidate(String uri) {
        if (uri.equals("/")
                || uri.equals("/login")
                || uri.equals("/login.html")
                || uri.equals("/password/reset.html")
                || uri.endsWith("/authenticate")
                || uri.endsWith("/logout")
                || (uri.startsWith("/lmf/") && uri.endsWith("/callback"))
                ) {
            return true;
        }
        // 白名单
        for (String path : IS_NOT_LOGIN_VALIDATE_PATH) {
            if (pathMatcher.match(path, uri)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAjaxReq(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

}

