package com.spring.blog.menu.service;

import java.util.List;

import com.spring.blog.menu.dto.Menu;

public interface MenuService {
	public List<Menu> getMenuList() throws Exception;
	public void saveMenu(Menu menu) throws Exception;
	public void updateMenu(Menu menu) throws Exception;
	public void deleteMenu(String code) throws Exception;
}
