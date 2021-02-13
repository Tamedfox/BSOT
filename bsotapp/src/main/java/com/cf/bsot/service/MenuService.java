package com.cf.bsot.service;

import com.cf.bsot.common.utils.TreeUtils;
import com.cf.bsot.mapper.MenuMapper;
import com.cf.bsot.model.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/17 23:11
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public Integer addMenuInfo(Menu menu){
        return menuMapper.insertMenu(menu);
    }

    public Integer deleteMenuInfoById(Long id){
        return menuMapper.deleteMenuByPrimaryKey(id);
    }

    public Integer updateMenuInfo(Menu menu){
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    public Menu getMenuInfoById(Long id){
        return menuMapper.getMenuByPrimaryKey(id);
    }

    public List<Menu> getAllQueryMenus() {
        return menuMapper.getAllUrlMenus();
    }

    public Collection getTreeMenuListInfo() {
        List<Menu> menus = menuMapper.getAllMenus();
        Collections.sort(menus);
        return TreeUtils.toTree(menus,"id","parentId","children", Menu.class);
    }
}
