package com.spring.blog.service;

import java.util.List;

import com.spring.blog.dto.Board;

public interface BoardService {
	public List<Board> getBoardList() throws Exception;
}
