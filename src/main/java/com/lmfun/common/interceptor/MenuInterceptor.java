package com.lmfun.common.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MenuInterceptor implements HandlerInterceptor {

    static final Log log = LogFactory.getLog(MenuInterceptor.class);
    private final static List<String> white_list = new ArrayList<String>();

    static {
        white_list.add("test.html");
    }



    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView modelview)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        getCodeId(request,response);
        return true;
    }

    public static void getCodeId(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
//        List<MenuDTO> data = (List<MenuDTO>) session.getAttribute("menu");
        /*if(data==null||data.size()==0){
			try {
	    		response.setContentType("text/html");
				response.getWriter().print("<script>document.location.href='/login'</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			 return;
        }
        if(!isWhite(uri)){
            int resultSkip=0;
            for(MenuDTO menu:data){
            	if(StringUtils.isNotBlank(menu.getSkipUrl())&& uri.contains(menu.getSkipUrl())){
                	resultSkip++;
            	}
            }
            if(resultSkip==0){
            	try {
            		response.setContentType("text/html");
    				response.getWriter().print("<script>document.location.href='/login'</script>");
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
                return;
            }
        }

        if (data != null && data.size() > 0) {

            for (MenuDTO menuDTO : data) {
                String skipURL = menuDTO.getSkipUrl();
                if (skipURL != null) {
                    if (uri.startsWith(skipURL)) {
                    	session.setAttribute("menuDTO", menuDTO);
                    }
                }
            }           
        }*/
    }
    /**
     * 需要不过拦截器的白名单
     * Administrator
     * 2017年3月6日
     *
     */
    public static boolean isWhite(String url){
        return white_list.contains(url);
    }

}
