package com.cf.bsot.service;

import com.cf.bsot.mapper.RoleMapper;
import com.cf.bsot.mapper.RoleMenuMapper;
import com.cf.bsot.model.pojo.Menu;
import com.cf.bsot.model.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/17 23:11
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    public Integer insertRoleInfo(Role role){
        return roleMapper.insertRole(role);
    }

    public Integer deleteRoleInfoById(Long id){
        return roleMapper.deleteRoleByPrimaryKey(id);
    }

    public Integer updateRoleInfo(Role role){
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public Role getRoleInfoById(Long id){
        return roleMapper.getRoleByPrimaryKey(id);
    }

    public boolean updateRoleMenuRelInfo(Long roleId, Long[] menuIds){
        roleMenuMapper.deleteMenuByRoleId(roleId);
        return roleMenuMapper.addRoleMenuRel(roleId,menuIds) == menuIds.length;
    }

    public List<Menu> getMenusByRoleId(Long roleId) {
        return roleMapper.getMenusByRoleId(roleId);
    }
}