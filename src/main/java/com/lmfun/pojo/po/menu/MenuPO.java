package com.lmfun.pojo.po.menu;

import com.lmfun.pojo.po.BasePO;

public class MenuPO extends BasePO{
    private Integer id;
    
    private String menuUid;
    
    private String name;
    
    private Integer parent;
    
    private Integer layer;
    
    private Integer sort;

    private String menuIcon;
    
    private String menuIconChecked;
    
    private String skipUrl;
    
    private String code;
    
    private String permissionKeys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuUid() {
        return menuUid;
    }

    public void setMenuUid(String menuUid) {
        this.menuUid = menuUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPermissionKeys() {
        return permissionKeys;
    }

    public void setPermissionKeys(String permissionKeys) {
        this.permissionKeys = permissionKeys;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuPO [id=");
        builder.append(id);
        builder.append(", menuUid=");
        builder.append(menuUid);
        builder.append(", name=");
        builder.append(name);
        builder.append(", parent=");
        builder.append(parent);
        builder.append(", layer=");
        builder.append(layer);
        builder.append(", sort=");
        builder.append(sort);
        builder.append(", menuIcon=");
        builder.append(menuIcon);
        builder.append(", menuIconChecked=");
        builder.append(menuIconChecked);
        builder.append(", skipUrl=");
        builder.append(skipUrl);
        builder.append(", code=");
        builder.append(code);
        builder.append(", permissionKeys=");
        builder.append(permissionKeys);
        builder.append("]");
        return builder.toString();
    }
}
