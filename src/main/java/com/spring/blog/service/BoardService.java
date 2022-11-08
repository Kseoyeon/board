package com.spring.blog.service;

import java.util.List;

import com.spring.blog.common.Search;
import com.spring.blog.dto.Board;
import com.spring.blog.dto.Reply;

public interface BoardService {
	public List<Board> getBoardList(Search search) throws Exception;
	public void insertBoard(Board board) throws Exception;
	public Board getBoardContent(int bid) throws Exception;
	public void updateBoard(Board board) throws Exception;
	public void deleteBoard(int bid) throws Exception;
	public int getBoardListCnt(Search search) throws Exception;
	
	public List<Reply> getReplyList(int bid) throws Exception;
	public int saveReply(Reply reply) throws Exception;
	public int updateReply(Reply reply) throws Exception;
	public int deleteReply(int rid) throws Exception;
}
