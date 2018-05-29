package com.lmfun.pojo.po.menu;

import com.lmfun.pojo.po.BasePO;

public class PermissionPO extends BasePO{
    
    private Integer id;
    
    private String permissionUid;
    
    private String permissionKey;
    
    private String permissionName;
    
    private String permissionDesc;
    
    private String permissionModule;
    
    private Integer menuId;
    
    private Integer parentMenuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionUid() {
        return permissionUid;
    }

    public void setPermissionUid(String permissionUid) {
        this.permissionUid = permissionUid;
    }

    public String getPermissionKey() {
        return permissionKey;
    }

    public void setPermissionKey(String permissionKey) {
        this.permissionKey = permissionKey;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getPermissionModule() {
        return permissionModule;
    }

    public void setPermissionModule(String permissionModule) {
        this.permissionModule = permissionModule;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PermissionPO [id=");
        builder.append(id);
        builder.append(", permissionUid=");
        builder.append(permissionUid);
        builder.append(", permissionKey=");
        builder.append(permissionKey);
        builder.append(", permissionName=");
        builder.append(permissionName);
        builder.append(", permissionDesc=");
        builder.append(permissionDesc);
        builder.append(", permissionModule=");
        builder.append(permissionModule);
        builder.append(", menuId=");
        builder.append(menuId);
        builder.append(", parentMenuId=");
        builder.append(parentMenuId);
        builder.append("]");
        return builder.toString();
    }
}
