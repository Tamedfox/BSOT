package com.cf.bsot.mapper;

import com.cf.bsot.model.dto.NavMenuDTO;
import com.cf.bsot.model.pojo.Menu;
import com.cf.bsot.model.pojo.Role;
import com.cf.bsot.model.pojo.User;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/17 21:01
 */
public interface UserMapper {

    List<User> getAllUsers();

    User getUserByPrimaryKey(Long id);

    User loadUserByUsername(String username);

    Integer deleteByPrimaryKey(Long id);

    Integer updateUserByPrimaryKey(User user);

    /**
     * 增加用户信息
     * @param user
     * @return
     */
    Integer addUserInfo(User user);

    /**
     * 通过用户id获取对应角色
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(Long userId);

    /**
     * 通过用户id获取对应权限
     * @param id
     * @return
     */
    List<Menu> getMenusByUserId(Long id);

    /**
     * 更新指定username的登录时间
     * @param username
     */
    void updateLoginTime(String username);

    /**
     * 带搜索条件查找用户信息
     * @param searchUser 搜索条件
     * @return
     */
    List<User> pageListUserInfo(User searchUser);

    /**
     * 通过用户名查询对应的菜单列表
     * @param userId
     * @return
     */
    List<NavMenuDTO> getNavListByUsername(Long userId);
}
