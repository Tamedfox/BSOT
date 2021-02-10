package com.cf.bsot.model.dto;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/10/8 22:20
 */
public class NavMenuDTO {

    private Long id;

    private Long parentId;

    private String name;

    /**
     * 数据库中存储path字段名为url
     */
    private String path;

    private String component;

    /**
     * 重定向url
     */
    private String redirect;

    /**
     * 是否缓存
     */
    private Boolean noCache;

    /**
     * 是否固定在tag-views中
     */
    private Boolean affix;

    /**
     * icon需要设置为前端反馈的meta.icon
     */
    private String icon;

    /**
     * title需要设置为前端反馈的meta.title
     */
    private String title;

    /**
     * 存储meta信息
     */
    private NavMenuMetaDTO meta;

    private List<NavMenuDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public Boolean getNoCache() {
        return noCache;
    }

    public void setNoCache(Boolean noCache) {
        this.noCache = noCache;
    }

    public Boolean getAffix() {
        return affix;
    }

    public void setAffix(Boolean affix) {
        this.affix = affix;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public NavMenuMetaDTO getMeta() {
        return meta;
    }

    public void setMeta(NavMenuMetaDTO meta) {
        this.meta = meta;
    }

    public List<NavMenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<NavMenuDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "NavMenuDTO{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", redirect='" + redirect + '\'' +
                ", noCache=" + noCache +
                ", affix=" + affix +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", meta=" + meta +
                ", children=" + children +
                '}';
    }
}
