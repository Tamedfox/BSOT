package com.cf.bsot.mapper;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/20 22:30
 */
public interface RoleMenuMapper {

    void deleteMenuByRoleId(Long id);

    Integer addRoleMenuRel(Long roleId, Long[] menuIds);

}
