package com.cf.bsot.model.dto;

/**
 * @author cf
 * @version 1.0
 * @date 2021/1/10 14:40
 */
public class LoginUserInfoDTO {

    private Long id;

    private String name;

    private String avatar;

    private String roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "LoginUserInfoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
