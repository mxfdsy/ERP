package com.lmfun.service.menu.impl;

import java.util.*;
import java.util.stream.Collectors;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.lmfun.pojo.vo.menu.ChildMenu;
import com.lmfun.pojo.vo.menu.ModelMenu;
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
        if (CollectionUtils.isEmpty(permissions)) {
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
        if (CollectionUtils.isEmpty(moduleAndMenus)) {
            return Collections.emptyList();
        }

        List<MenuVO> resultMenu = new ArrayList<>();
        for (MenuPO moduleOrMenu : moduleAndMenus) {
            MenuVO menu = new MenuVO();
            BeanUtils.copyProperties(moduleOrMenu, menu);
            resultMenu.add(menu);
        }
        return resultMenu;
    }

    @Override
    public List<ModelMenu> listAllmenu() {
        //查询出所有的一级菜单
        List<MenuVO> parentsMenus = menuMapper.listParentMenu();

        //判断集合是否为空
        if (CollectionUtils.isEmpty(parentsMenus)) {
            return Collections.emptyList();
        }
        //为了让 我们不在循环中去查询数据库 我们先从数据库把数据查出来等用到时直接去缓存中去读取-这里我们得到了1级菜单的id
        List<Integer> parentIds = parentsMenus.stream().map(parentsMenu -> parentsMenu.getId()).collect(Collectors.toList());

        //获取到id 对应菜单的集合
        Map<Integer, List<ChildMenu>> childparets = mapChildMenu(parentIds);


        //往父级菜单中添加数据（子菜单的数据和自身的数据信息）
        ArrayList<ModelMenu> modelMenus = new ArrayList<>();
        for (MenuVO parentsMenu : parentsMenus
                ) {
            ModelMenu modelMenu = new ModelMenu();
            modelMenu.setId(parentsMenu.getId());
            modelMenu.setMenuIcon(parentsMenu.getMenuIcon());
            modelMenu.setSkipUrl(parentsMenu.getSkipUrl());
            //插入子菜单
            modelMenu.setChildMenuList(getChild(parentsMenu.getId()));
            modelMenus.add(modelMenu);
        }
        return modelMenus;
    }

    private Map<Integer, List<ChildMenu>> mapChildMenu(List<Integer> parentIds) {
        List<ChildMenu> childMenuList = menuMapper.listChildMenu(parentIds);
        Map<Integer, List<ChildMenu>> childMenus = childMenuList.stream().collect(Collectors.groupingBy(ChildMenu::getParent));
        return childMenus;
    }

    private List<ChildMenu> getChild(Integer id) {
        List<ChildMenu> childMenuList = menuMapper.getChild(id);
        return childMenuList;
    }
}
