package com.spring.blog.service;

import java.util.List;

import com.spring.blog.dto.Board;

public interface BoardService {
	public List<Board> getBoardList() throws Exception;
	public void insertBoard(Board board) throws Exception;
	public Board getBoardContent(int bid) throws Exception;
	public void updateBoard(Board board) throws Exception;
	public void deleteBoard(int bid) throws Exception;
}
