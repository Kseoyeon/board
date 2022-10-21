package com.spring.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	@Override
	public Board getBoardContent(int bid) throws Exception {
		Board board = new Board();
		boardDao.updateViewCnt(bid);
		board = boardDao.getBoardContent(bid);
		return board;
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
