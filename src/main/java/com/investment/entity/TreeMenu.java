package com.investment.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单实体类
 *
 * @author Sun
 */
public class TreeMenu {
    /**
     * 唯一标识ID
     */
    private Integer id;
    /**
     * 父菜单ID
     */
    private Integer pid;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单访问Url
     */
    private String url;
    private Boolean checked = false;

    /**
     * 添加元素
     */
    private List<TreeMenu> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TreeMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TreeMenu> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
