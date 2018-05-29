package com.lmfun.pojo.vo.menu;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MenuVO implements Serializable {

    private static final long serialVersionUID = 2829751891025969708L;

    @JSONField(name = "parent")
    private Integer parent;
    
    @JSONField(name = "sort")
    private Integer sort;
    
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "code")
    private String code;
    
    @JSONField(name = "layer")
    private Integer layer;
    
    @JSONField(name = "id")
    private Integer id;
    
    @JSONField(name = "skip_url")
    private String skipUrl;
    
    @JSONField(name = "menu_icon")
    private String menuIcon;
    
    @JSONField(name = "menu_icon_checked")
    private String menuIconChecked;
    
    @JSONField(name = "code_list")
    private String[] codeList;

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
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
    
    public String[] getCodeList() {
        codeList=code.split(",");
        return codeList;
    }
    
    public void setCodeList(String[] codeList) {
        this.codeList = codeList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MenuDTO [parent=");
        builder.append(parent);
        builder.append(", sort=");
        builder.append(sort);
        builder.append(", name=");
        builder.append(name);
        builder.append(", code=");
        builder.append(code);
        builder.append(", layer=");
        builder.append(layer);
        builder.append(", id=");
        builder.append(id);
        builder.append(", skipUrl=");
        builder.append(skipUrl);
        builder.append(", menuIcon=");
        builder.append(menuIcon);
        builder.append(", menuIconChecked=");
        builder.append(menuIconChecked);
        builder.append("]");
        return builder.toString();
    }

}
