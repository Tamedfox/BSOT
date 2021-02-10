package com.cf.bsot.common.security;

import com.cf.bsot.model.pojo.Menu;
import com.cf.bsot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/26 14:26
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    private static Map<String, ConfigAttribute> configAttributeMap = null;

    public void clearDataSource(){
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    private void loadDataSource(){
        List<Menu> menus = menuService.getAllQueryMenus();
        configAttributeMap = new HashMap<>(16);
        for (Menu menu : menus) {
            configAttributeMap.put(menu.getPath(),new SecurityConfig(menu.getPath()));
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if(configAttributeMap == null){
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问路径
        String requestUrl = ((FilterInvocation) o).getRequest().getRequestURI();
        String requestMethod = ((FilterInvocation) o).getRequest().getMethod();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        Iterator<String> menusIterator = configAttributeMap.keySet().iterator();
        while(menusIterator.hasNext()){
            String menuUrl = menusIterator.next();
            //url匹配&请求方法匹配
            if(antPathMatcher.match(menuUrl,requestUrl)){
                configAttributes.add(new SecurityConfig(menuUrl));
            }
        }
        //如果为空随便赋值，不允许匿名访问
        if(configAttributes.isEmpty()){
            configAttributes.add(new SecurityConfig("null"));
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
