package com.lmfun.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lmfun.common.constant.Constants;
import com.lmfun.common.util.ResultBuilderUtils;
import com.lmfun.mapper.account.UserAccountMapper;
import com.lmfun.pojo.dto.user.LoginRoleDTO;
import com.lmfun.pojo.dto.user.LoginUserDTO;
import com.lmfun.pojo.po.account.UserAccountPO;
import com.lmfun.pojo.vo.menu.MenuVO;
import com.lmfun.service.account.UserAccountService;
import com.lmfun.service.account.UserPermissionService;
import com.lmfun.service.account.UserRoleService;
import com.lmfun.service.menu.MenuService;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);
    
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private UserPermissionService userPermissionService;
    
    @Autowired
    private MenuService menuService;
    
    @Override
    public boolean isLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginUserDTO loginUser = (LoginUserDTO) session.getAttribute(Constants.LOGIN_USER);
        return loginUser != null;
    }

    @Override
    public String login(HttpServletRequest request, String loginName, String password) {
        LoginUserDTO loginUser = getLoginUserInfo(loginName, password);
        if(null == loginUser){
            logger.error("用户{}登录，用户名或密码错误，密码：{}", loginName, password);
            return ResultBuilderUtils.buildError("用户名或密码错误");
        }
        if(CollectionUtils.isEmpty(loginUser.getPermissionInfo())){
            logger.error("用户{}-{}无权限登录", loginUser.getName(), loginUser.getPhone());
            return ResultBuilderUtils.buildError("用户无权限");
        }
        
        List<MenuVO> menu = menuService.listMenuByPermissionKeys(loginUser.getPermissionInfo());
        // TODO Auto-generated method stub
        return null;
    }
    
    private LoginUserDTO getLoginUserInfo(String loginName, String password){
        UserAccountPO userAccountInfo = userAccountMapper.getUserAccountByLoginNameAndPassword(loginName, password);
        if(null == userAccountInfo){
            return null;
        }
        
        List<LoginRoleDTO> userRoleInfo = userRoleService.listUserRoleInfo(userAccountInfo.getUserUid());
        
        LoginUserDTO userInfo = new LoginUserDTO();
        userInfo.setUserUid(userAccountInfo.getUserUid());
        userInfo.setName(userAccountInfo.getName());
        userInfo.setLoginName(userAccountInfo.getLoginName());
        userInfo.setPhone(userAccountInfo.getMemo());
        
        userInfo.setRoleInfo(userRoleInfo);
        userInfo.setPermissionInfo(
                userPermissionService.listUserRolePermissionKey(
                        userRoleInfo.stream().map(LoginRoleDTO::getRoleUid).collect(Collectors.toList())));
        return userInfo;
    }

}
