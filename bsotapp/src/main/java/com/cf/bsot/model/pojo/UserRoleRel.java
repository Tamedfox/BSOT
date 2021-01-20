package com.cf.bsot.model.pojo;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/20 21:50
 */
public class UserRoleRel {

    private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleRel{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
