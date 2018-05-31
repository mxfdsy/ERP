package com.lmfun.pojo.vo.menu;

import com.alibaba.fastjson.annotation.JSONField;
import com.lmfun.pojo.po.menu.MenuPO;

import java.util.List;

/**
 * @author  小武
 * 一级菜单和其对应的子菜单
 */
public class ModelMenu {

    @JSONField(name = "menu_name")
    private String menuName;

    @JSONField(name = "menu_id")
    private Integer menuId;

    @JSONField(name = "menu_icon")
    private String menuIcon;

    @JSONField(name = "menu_icon_checked")
    private String menuIconChecked;

    @JSONField(name = "child_menu")
    private List<MenuPO> childMenuList;

    public void setChildMenuList(List<MenuPO> childMenuList) {
        this.childMenuList = childMenuList;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<MenuPO> getChildMenuList() {
        return childMenuList;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuIconChecked() {
        return menuIconChecked;
    }

    public void setMenuIconChecked(String menuIconChecked) {
        this.menuIconChecked = menuIconChecked;
    }



}
