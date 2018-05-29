package com.lmfun.service.menu.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmfun.mapper.menu.PermissionMapper;
import com.lmfun.pojo.po.menu.PermissionPO;
import com.lmfun.service.menu.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
    
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionPO> listPermissionByPermissionKeys(Set<String> permissionKeys) {
        return permissionMapper.listPermissionByPermissionKeys(permissionKeys);
    }
    
    

}
