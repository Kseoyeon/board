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
	
	@Override
	public void insertBoard(Board board) throws Exception {
		boardDao.insertBoard(board);
	}
	@Override
	public Board getBoardContent(int bid) throws Exception {
		boardDao.updateViewCnt(bid);
		return boardDao.getBoardContent(bid);
	}
	
	@Override
	public void updateBoard(Board board) throws Exception {
		boardDao.updateBoard(board);
	}
	@Override
	public void deleteBoard(int bid) throws Exception {
		 boardDao.deleteBoard(bid);
	}		
}
