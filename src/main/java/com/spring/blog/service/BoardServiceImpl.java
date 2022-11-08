package com.spring.blog.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.blog.common.Search;
import com.spring.blog.dao.BoardDao;
import com.spring.blog.dto.Board;
import com.spring.blog.dto.Reply;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDao boardDao;

	@Override
	public List<Board> getBoardList(Search search) throws Exception {
		return boardDao.getBoardList(search);
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
	
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return boardDao.getBoardListCnt(search);
	}
	
	@Override
	public List<Reply> getReplyList(int bid) throws Exception {
		return boardDao.getReplyList(bid);
	}
	
	@Override
	public int saveReply(Reply reply) throws Exception {
		return boardDao.saveReply(reply);
	}
	
	@Override
	public int updateReply(Reply reply) throws Exception {
		return boardDao.updateReply(reply);
	}
	
	@Override
	public int deleteReply(int rid) throws Exception {
		return boardDao.deleteReply(rid);
	}
}
