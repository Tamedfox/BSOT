package com.cf.bsot.model.dto;

import com.cf.bsot.model.pojo.Role;

/**
 * @author cf
 * @version 1.0
 * @date 2021/2/9 21:52
 */
public class RoleDTO extends Role {

    private Long[] menuIds;

    public Long[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Long[] menuIds) {
        this.menuIds = menuIds;
    }
}
