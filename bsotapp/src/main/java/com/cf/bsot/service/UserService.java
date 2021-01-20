package com.cf.bsot.service;

import com.cf.bsot.common.api.CustomException;
import com.cf.bsot.common.api.ResultCode;
import com.cf.bsot.common.utils.JwtTokenUtil;
import com.cf.bsot.mapper.UserMapper;
import com.cf.bsot.mapper.UserRoleMapper;
import com.cf.bsot.model.dto.LoginUserInfoDTO;
import com.cf.bsot.model.dto.NavMenuDTO;
import com.cf.bsot.model.dto.NavMenuMetaDTO;
import com.cf.bsot.model.dto.UserLoginDTO;
import com.cf.bsot.model.pojo.Menu;
import com.cf.bsot.model.pojo.Role;
import com.cf.bsot.model.pojo.SecurityUser;
import com.cf.bsot.model.pojo.User;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/17 21:55
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User getUserById(Long id){
        return userMapper.getUserByPrimaryKey(id);
    }

    public Integer deleteUserById(Long id){
        return userMapper.deleteByPrimaryKey(id);
    }

    public Integer updateUserById(User user){
        return userMapper.updateUserByPrimaryKey(user);
    }

    public Integer addUserInfo(User user) {
        user.setCreateTime(new Date());
        user.setLastLoginTime(user.getCreateTime());
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.addUserInfo(user);
    }

    public List<Role> getRolesByUserId(Long id){
        return userMapper.getRolesByUserId(id);
    }

    public List<Menu> getMenusByUserId(Long id) {
        return userMapper.getMenusByUserId(id);
    }

    public boolean updateUserRoleRelInfo(Long userId, Long[] roleIds){
        userRoleMapper.deleteRoleByUserId(userId);
        return userRoleMapper.addUserRoleRel(userId,roleIds) == roleIds.length;
    }

    public String userLogin(UserLoginDTO userLoginDTO, HttpServletRequest request) throws CustomException {
        String token = "";
        UserDetails userDetails = this.loadUserByUsername(userLoginDTO.getUsername());
        if(!passwordEncoder.matches(userLoginDTO.getPassword(),userDetails.getPassword())){
            throw new CustomException(ResultCode.NAME_PASS_ERROR);
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userMapper.updateLoginTime(userDetails.getUsername());
        token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if(null != user){
            List<Menu> menus = this.getMenusByUserId(user.getId());
            return new SecurityUser(user,menus);
        }
        throw new CustomException(ResultCode.NAME_PASS_ERROR);
    }

    public List<User> pageListUserInfo(Integer page, Integer size, User searchUser) {
        PageHelper.startPage(page,size);
        return userMapper.pageListUserInfo(searchUser);
    }

    public List<NavMenuDTO> getNavListByUsername() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userMapper.loadUserByUsername(username);
        List<NavMenuDTO> navList = userMapper.getNavListByUsername(user.getId());
        return navListToTreeMenus(navList);
    }

    private List<NavMenuDTO> navListToTreeMenus(List<NavMenuDTO> navList) {
        List<NavMenuDTO> treeList = new ArrayList<>();
        for (NavMenuDTO menu : navList) {
            menu.setMeta(new NavMenuMetaDTO(menu.getIcon(),menu.getTitle(),menu.getAffix()));
            if(menu.getParentId() == 0L || menu.getParentId() == null){
                treeList.add(menu);
            }
        }
        this.addChildrenNav(navList,treeList);
        return treeList;
    }

    /**
     * 递归查找组装子节点
     * @param navList
     * @param treeList
     */
    private void addChildrenNav(List<NavMenuDTO> navList, List<NavMenuDTO> treeList) {
        for (NavMenuDTO rootMenu : treeList) {
            List<NavMenuDTO> childList = new ArrayList<>();
            for (NavMenuDTO navMenu : navList) {
                if(rootMenu.getId().equals(navMenu.getParentId())){
                    childList.add(navMenu);
                }
            }
            rootMenu.setChildren(childList);
            this.addChildrenNav(navList,childList);
        }
    }

    public LoginUserInfoDTO getLoginUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        User user = userMapper.loadUserByUsername(username);
        LoginUserInfoDTO userInfo = new LoginUserInfoDTO();
        userInfo.setName(user.getUsername());
        userInfo.setAvatar(user.getIcon());
        List<Role> roles = userMapper.getRolesByUserId(user.getId());
        List<String> rolesList = new ArrayList<>(16);
        String rolesStr = "";
        for (Role role : roles) {
            rolesList.add(role.getName());
        }
        userInfo.setRoles(StringUtils.join(rolesList, ","));
        return userInfo;
    }
}
