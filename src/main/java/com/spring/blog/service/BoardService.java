package com.spring.blog.service;

import java.util.List;

import com.spring.blog.common.Pagination;
import com.spring.blog.dto.Board;

public interface BoardService {
	public List<Board> getBoardList(Pagination pagination) throws Exception;
	public void insertBoard(Board board) throws Exception;
	public Board getBoardContent(int bid) throws Exception;
	public void updateBoard(Board board) throws Exception;
	public void deleteBoard(int bid) throws Exception;
	public int getBoardListCnt() throws Exception;
}
