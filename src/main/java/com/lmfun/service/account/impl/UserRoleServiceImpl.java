package com.lmfun.service.account.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lmfun.mapper.account.UserRoleMapper;
import com.lmfun.pojo.dto.user.LoginRoleDTO;
import com.lmfun.pojo.po.account.UserRolePO;
import com.lmfun.service.account.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    
    @Autowired
    private UserRoleMapper userRoleMapepr;

    @Override
    public List<LoginRoleDTO> listUserRoleInfo(String userUid) {
        List<UserRolePO> userRole = userRoleMapepr.listUserRole(userUid);
        if(CollectionUtils.isEmpty(userRole)){
            return Collections.emptyList();
        }
        Map<String, List<UserRolePO>> roleToUserRole = userRole.stream().collect(Collectors.groupingBy(UserRolePO::getRoleUid));
        
        List<LoginRoleDTO> roleInfo = new ArrayList<>(roleToUserRole.size());
        
        roleToUserRole.keySet().stream().forEach(roleUid -> {
            List<UserRolePO> roles = roleToUserRole.get(roleUid);
            LoginRoleDTO role = new LoginRoleDTO();
            role.setRoleUid(roleUid);
            role.setRoleKey(roles.get(0).getRoleKey());
            role.setMallUid(roles.stream().map(UserRolePO::getMallUid).collect(Collectors.toList()));
            roleInfo.add(role);
        });
        return roleInfo;
    }

}
