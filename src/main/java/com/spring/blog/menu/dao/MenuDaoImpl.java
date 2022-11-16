package com.spring.blog.menu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.blog.menu.dto.Menu;

@Repository
public class MenuDaoImpl implements MenuDao {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<Menu> getMenuList() throws Exception {
		return sqlSession.selectList("com.spring.blog.menu.menuMapper.getMenuList");
	}
	
	@Override
	public int saveMenu(Menu menu) throws Exception {
		return sqlSession.insert("com.spring.blog.menu.menuMapper.insertMenu", menu);
	}
	
	@Override
	public int updateMenu(Menu menu) throws Exception {
		return sqlSession.update("com.spring.blog.menu.menuMapper.updateMenu", menu);
	}
	
	@Override
	public int deleteMenu(String code) throws Exception {
		return sqlSession.delete("com.spring.blog.menu.menuMapper.deleteMenu", code);
	}
}
