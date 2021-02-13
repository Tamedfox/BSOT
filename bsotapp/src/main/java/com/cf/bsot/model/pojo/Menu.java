package com.cf.bsot.model.pojo;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/15 23:25
 */
public class Menu implements Comparable<Menu> {

    private Long id;

    private Long parentId;

    private String name;

    private String description;

    private String path;

    private String method;

    private String type;

    private String component;

    private String redirect;

    private Integer affix;

    private Integer noCache;

    private Integer hidden;

    private String icon;

    private String title;

    private Integer status;

    private Integer orderSeq;

    private List<Menu> children;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getAffix() {
        return affix;
    }

    public void setAffix(Integer affix) {
        this.affix = affix;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Integer getNoCache() {
        return noCache;
    }

    public void setNoCache(Integer noCache) {
        this.noCache = noCache;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", component='" + component + '\'' +
                ", redirect='" + redirect + '\'' +
                ", affix=" + affix +
                ", noCache=" + noCache +
                ", hidden=" + hidden +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", orderSeq=" + orderSeq +
                ", children=" + children +
                '}';
    }

    @Override
    public int compareTo(Menu menu) {
        return this.orderSeq - menu.orderSeq;
    }
}
