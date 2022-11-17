package com.spring.blog.menu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.blog.menu.dao.MenuDao;
import com.spring.blog.menu.dto.Menu;

@Service
public class MenuServiceImpl implements MenuService {
	@Inject
	private MenuDao menuDao;
	
	@Override
	public List<Menu> getMenuList() throws Exception {
		return menuDao.getMenuList();
	}
	
	@Override
	public void saveMenu(Menu menu) throws Exception {
		menuDao.saveMenu(menu);
	}
	
	@Override
	public void updateMenu(Menu menu) throws Exception {
		menuDao.updateMenu(menu);
	}
	
	@Override
	public void deleteMenu(String code) throws Exception {
		menuDao.deleteMenu(code);
	}
}
