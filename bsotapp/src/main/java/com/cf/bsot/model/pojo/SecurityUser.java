package com.cf.bsot.model.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SpringSecurity的user类
 *
 * @author cf
 * @version 1.0
 * @date 2020/9/24 22:41
 */
public class SecurityUser implements UserDetails {

    private User user;

    private List<Menu> menuList;

    public SecurityUser(User user, List<Menu> menuList) {
        this.user = user;
        this.menuList = menuList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Menu menu: menuList) {
            authorities.add(new SimpleGrantedAuthority(menu.getPath()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return 1 == user.getStatus();
    }

    public User getUser() {
        return user;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
