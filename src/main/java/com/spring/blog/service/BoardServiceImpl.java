package com.spring.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.blog.dao.BoardDao;
import com.spring.blog.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDao boardDao;

	@Override
	public List<Board> getBoardList() throws Exception {
		return boardDao.getBoardList();
	}

}
