package com.lmfun.service.menu.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lmfun.mapper.menu.MenuMapper;
import com.lmfun.pojo.po.menu.MenuPO;
import com.lmfun.pojo.po.menu.PermissionPO;
import com.lmfun.pojo.vo.menu.MenuVO;
import com.lmfun.service.menu.MenuService;
import com.lmfun.service.menu.PermissionService;

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private PermissionService permissionService;
    
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> listMenuByPermissionKeys(Set<String> permissionKeys) {
        List<PermissionPO> permissions = permissionService.listPermissionByPermissionKeys(permissionKeys);
        if(CollectionUtils.isEmpty(permissions)){
            return Collections.emptyList();
        }
        
        List<Integer> moduleIds = permissions
                .stream()
                .map(permission -> permission.getParentMenuId())
                .distinct()
                .collect(Collectors.toList());
        List<Integer> menuIds = permissions
                .stream()
                .map(permission -> permission.getMenuId())
                .distinct()
                .collect(Collectors.toList());
        moduleIds.addAll(menuIds);
        
        List<MenuPO> moduleAndMenus = menuMapper.listMenuByIds(moduleIds); 
        if(CollectionUtils.isEmpty(moduleAndMenus)){
            return Collections.emptyList();
        }
        
        List<MenuVO> resultMenu = new ArrayList<>();
        for(MenuPO moduleOrMenu : moduleAndMenus){
            MenuVO menu = new MenuVO();
            BeanUtils.copyProperties(moduleOrMenu, menu);
            resultMenu.add(menu);
        }
        return resultMenu;
    }

}
