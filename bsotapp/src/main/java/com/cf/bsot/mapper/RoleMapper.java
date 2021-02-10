package com.cf.bsot.mapper;

import com.cf.bsot.model.pojo.Menu;
import com.cf.bsot.model.pojo.Role;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/17 22:56
 */
public interface RoleMapper {

    Role getRoleByPrimaryKey(Long id);

    int deleteRoleByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role role);

    int insertRole(Role role);

    List<Menu> getMenusByRoleId(Long roleId);

    List<Role> pageListRoleInfo(Role role);
}
