package com.lmfun.service.menu;

import java.util.List;
import java.util.Set;

import com.lmfun.pojo.vo.menu.MenuVO;
import com.lmfun.pojo.vo.menu.ModelMenu;

public interface MenuService {

    List<MenuVO> listMenuByPermissionKeys(Set<String> permissionKeys);

    List<ModelMenu> listAllmenu();
}
