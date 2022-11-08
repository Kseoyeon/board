package com.spring.blog.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.blog.common.Search;
import com.spring.blog.dto.Board;
import com.spring.blog.dto.Reply;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<Board> getBoardList(Search search) throws Exception {
		return sqlSession.selectList("com.spring.blog.boardMapper.getBoardList", search);
	}
	
	@Override
	public Board getBoardContent(int bid) throws Exception {
		return sqlSession.selectOne("com.spring.blog.boardMapper.getBoardContent", bid);
	}
	
	@Override
	public int insertBoard(Board board) throws Exception {
		return sqlSession.insert("com.spring.blog.boardMapper.insertBoard", board);
	}
	
	@Override
	public int updateBoard(Board board) throws Exception {
		return sqlSession.update("com.spring.blog.boardMapper.updateBoard", board);
	}
	
	@Override
	public int deleteBoard(int bid) throws Exception {
		return sqlSession.insert("com.spring.blog.boardMapper.deleteBoard", bid);
	}
	
	@Override
	public int updateViewCnt(int bid) throws Exception {
		return sqlSession.update("com.spring.blog.boardMapper.updateViewCnt", bid);
	}
	
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return sqlSession.selectOne("com.spring.blog.boardMapper.getBoardListCnt", search);
	}
	
	@Override
	public List<Reply> getReplyList(int bid) throws Exception {
		return sqlSession.selectList("com.spring.blog.replyMapper.getReplyList", bid);
	}
	
	@Override
	public int saveReply(Reply reply) throws Exception {
		return sqlSession.insert("com.spring.blog.replyMapper.saveReply", reply);
	}
	
	@Override
	public int updateReply(Reply reply) throws Exception {
		return sqlSession.update("com.spring.blog.replyMapper.updateReply", reply);
	}
	
	@Override
	public int deleteReply(int rid) throws Exception {
		return sqlSession.delete("com.spring.blog.replyMapper.deleteReply", rid);
	}
	
}
