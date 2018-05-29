package com.lmfun.controller.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmfun.common.constant.Constants;
import com.lmfun.common.util.ImageUtils;
import com.lmfun.common.util.RandomStrUtils;
import com.lmfun.common.util.ResultBuilderUtils;
import com.lmfun.controller.BaseController;
import com.lmfun.service.account.UserAccountService;

@Controller
public class LoginController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SessionRepository<?> sessionRepository;

    @Autowired
    private UserAccountService userService;

    @RequestMapping(value = {"/", "/login.html", "/login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (userService.isLogin(request)) {
            return "redirect:/index";
        }
        return "login/login";
    }

    @RequestMapping(value = "/login/imageCode.jpeg", method = RequestMethod.GET)
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        try {
            String imgCode = RandomStrUtils.getComplexRandomStr(4);
            request.getSession().setAttribute("validateCode", imgCode);
            ImageUtils.imageCodeGeneration(100, 40, imgCode, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login/authenticate", method = RequestMethod.POST, produces = JSON_UTF8)
    public String authenticate(
            @RequestParam(value = "login_name") String loginName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "validateCode") String validateCode, HttpServletRequest request) {

        if (StringUtils.isEmpty(loginName)) {
            return ResultBuilderUtils.buildError("请输入登录名");
        }

        if (StringUtils.isEmpty(password)) {
            return ResultBuilderUtils.buildError("请输入密码");
        }

        if (StringUtils.isEmpty(validateCode)) {
            return ResultBuilderUtils.buildError("请输入验证码");
        }

        String sessionVidateCode = (String) request.getSession().getAttribute(Constants.VALIDATE_CODE);
        if (StringUtils.isEmpty(sessionVidateCode)) {
            return ResultBuilderUtils.buildError("请输刷新页面再登录");
        }

        if (!sessionVidateCode.equalsIgnoreCase(validateCode)) {
            return ResultBuilderUtils.buildError("验证码错误");
        }

        return userService.login(request, loginName, password);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        request.getSession().invalidate();
        sessionRepository.delete(sessionId);

        return "redirect:/login";
    }
}
