package com.cf.bsot.mapper;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/20 21:46
 */
public interface UserRoleMapper {

    void deleteRoleByUserId(Long id);

    Integer addUserRoleRel(Long userId, Long[] roleIds);

}
