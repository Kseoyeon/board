package com.spring.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.dto.Reply;
import com.spring.blog.service.BoardService;

@RestController
@RequestMapping(value = "/restBoard")
public class RestBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/getReplyList", method = RequestMethod.POST)
	public List<Reply> getReplyList(@RequestParam("bid") int bid) throws Exception {
		return boardService.getReplyList(bid);
	}
	
	@RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	public Map<String, Object> saveReply(@RequestBody Reply reply) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.saveReply(reply);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public Map<String, Object> updateReply(@RequestBody Reply reply) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			boardService.updateReply(reply);
			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "False");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public String deleteReply(@RequestParam("rid") int rid) throws Exception {
		boardService.deleteReply(rid);
		return "board/boardContent";
	}
}
