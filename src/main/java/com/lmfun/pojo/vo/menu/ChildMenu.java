package com.lmfun.pojo.vo.menu;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by 没想法的岁月 on 2018/5/31.
 */
public class ChildMenu {

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
}
