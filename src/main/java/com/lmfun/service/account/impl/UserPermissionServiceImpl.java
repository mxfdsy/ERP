package com.lmfun.service.account.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lmfun.mapper.account.RolePermissionMapper;
import com.lmfun.service.account.UserPermissionService;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<String> listUserRolePermissionKey(List<String> roleUids) {
        if(CollectionUtils.isEmpty(roleUids)){
            return Collections.emptySet();
        }
        return rolePermissionMapper.listUserRolePermissionKey(roleUids);
    }

}
