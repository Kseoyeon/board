package com.spring.blog;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.blog.dao.BoardDao;
import com.spring.blog.dto.Board;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/root-context.xml", "classpath:spring/dataSource-context.xml","classpath:servlet-context.xml" })
public class BoardDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	
	@Inject
	private BoardDao boardDao;
	
//	@Test @Ignore
//	public void testGetBoardList() throws Exception {
//		List<Board> boardList = boardDao.getBoardList();
//		logger.info("\n Board List \n");
//		if(boardList.size() > 0) {
//			for(Board list : boardList) {
//				logger.info(list.title);
//			}
//		} else {
//			logger.info("데이터가 없습니다.");
//		}
//		
//	}
	
	@Test @Ignore
	public void testGetBoardContent() throws Exception {
		Board board = boardDao.getBoardContent(2);
		logger.info("\n Board List \n");
		if(board != null) {
			logger.info("글번호 : " + board.getBid());
			logger.info("카테고리 : " + board.getCate_cd());
			logger.info("글제목 : " + board.getTitle());
			logger.info("글내용 : " + board.getContent());
			logger.info("글태그 : " + board.getTag());
			logger.info("조회수 : " + board.getView_cnt());
			logger.info("작성자 : " + board.getReg_id());
			logger.info("작성일 : " + board.getReg_dt());
			logger.info("수정일 : " + board.getEdit_dt());
		} else {
			logger.info("데이터가 없습니다.");
		}
	}
	
	@Test 
	public void testInsertBoard() throws Exception {
		Board board = new Board();
		board.setCate_cd("1");
		//board.setTitle("첫번째 게시물 입니다.");
		//board.setContent("첫번째 게시물입니다.");
		board.setTag("1");
		board.setReg_id("1");
		
		for(int i = 1; i < 500; i++) {
			board.setTitle(i + " 번째 게시물 입니다.");
			board.setContent(i + " 번째 게시물 입니다.");
			int result = boardDao.insertBoard(board);
			logger.info("\n Insert Board Result " + result);
			if(result == 1) {
				logger.info("\n 게시물 등록 성공");
			} else {
				logger.info("\n 게시물 등록 실패");
			}
		}
	}
	
	@Test @Ignore
	public void testUpdateBoard() throws Exception {
		Board board = new Board();
		board.setBid(1);
		board.setCate_cd("1");
		board.setTitle("첫번째 게시물 입니다 - 수정 합니다.");
		board.setContent("첫번째 게시물입니다 - 수정합니다.");
		board.setTag("1-1");
		
		int result = boardDao.updateBoard(board);
		logger.info("\n Update Board Result \n");
		if(result > 0) {
			logger.info("\n 게시물 수정 성공");
		} else {
			logger.info("\n 게시물 수정 실패");
		}
	}

	@Test @Ignore
	public void testDeleteBoard() throws Exception {
		int result = boardDao.deleteBoard(1);
		logger.info("\n Delete Board Result \n");
		if(result > 0) {
			logger.info("\n 게시물 삭제 성공");
		} else {
			logger.info("\n 게시물 삭제 실패");
		}
	}
	
	@Test @Ignore
	public void testUpdateViewCnt() throws Exception {
		int result = boardDao.updateViewCnt(1);
		logger.info("\n Update View Count Result \n");
		if(result > 0) {
			logger.info("\n 게시물 조회수 업데이트 성공");
		} else {
			logger.info("\n 게시물 조회수 업데이트 실패");
		}
	}
}
