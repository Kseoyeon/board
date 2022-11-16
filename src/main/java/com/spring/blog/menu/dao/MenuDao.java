package com.spring.blog.menu.dao;

import java.util.List;

import com.spring.blog.menu.dto.Menu;

public interface MenuDao {
	public List<Menu> getMenuList() throws Exception;
	public int saveMenu(Menu menu) throws Exception;
	public int updateMenu(Menu menu) throws Exception;
	public int deleteMenu(String code) throws Exception;	
}
