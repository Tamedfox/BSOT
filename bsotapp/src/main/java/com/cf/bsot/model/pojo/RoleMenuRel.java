package com.cf.bsot.model.pojo;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/20 21:51
 */
public class RoleMenuRel {

    private Long roleId;

    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuRel{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
