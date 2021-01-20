package com.cf.bsot.model.dto;

/**
 * @author cf
 * @version 1.0
 * @date 2021/1/7 22:29
 */
public class NavMenuMetaDTO {

    public NavMenuMetaDTO(String icon, String title, Boolean affix) {
        this.icon = icon;
        this.title = title;
        this.affix = affix;
    }

    private String icon;

    private String title;

    private Boolean affix;

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

    public Boolean getAffix() {
        return affix;
    }

    public void setAffix(Boolean affix) {
        this.affix = affix;
    }

    @Override
    public String toString() {
        return "NavMenuMetaDTO{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", affix=" + affix +
                '}';
    }
}
