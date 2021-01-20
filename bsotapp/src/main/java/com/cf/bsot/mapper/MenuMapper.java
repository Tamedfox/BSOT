package com.cf.bsot.mapper;

import com.cf.bsot.model.pojo.Menu;

import java.util.List;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/17 23:06
 */
public interface MenuMapper {

    Menu getMenuByPrimaryKey(Long id);

    int deleteMenuByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu menu);

    int insertMenu(Menu menu);

    List<Menu> getAllUrlMenus();
}
