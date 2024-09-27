package com.miniproject.controller.comment;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.domain.CommentDTO;
import com.miniproject.domain.MyResponseWithData;
import com.miniproject.domain.MyResponseWithoutData;
import com.miniproject.domain.PagingInfoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

	@Inject
	private final CommentService cService;

	// 중괄호 안의 변수를 경로에서 읽어오기 위해
	// @PathVariable 사용
//	@GetMapping("all/{boardNo}")
//	public List<CommentVO> getAllCommentsByBoardNo(@PathVariable("boardNo") int boardNo) {
//		log.info(boardNo + "번의 모든 댓글 조회하기");
//
//		List<CommentVO> list = null;
//
//		try {
//			list = cService.getAllComents(boardNo);
//			System.out.println(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}
	
	@GetMapping("all/{boardNo}/{pageNo}")
	public ResponseEntity getAllCommentByBoardNo(@PathVariable("boardNo") int boardNo, @PathVariable("pageNo") int pageNo) {
		log.info(boardNo + "번의 모든 댓글을 조회하자(with pagination)");
		
		ResponseEntity result = null;
		Map<String, Object> data = null;
		
		try {
			data = cService.getAllComents(boardNo, new PagingInfoDTO(pageNo, 3));
			
			System.out.println("data:" + data);
			result = new ResponseEntity(MyResponseWithData.success(data), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity(MyResponseWithData.fail(), HttpStatus.BAD_GATEWAY);
		}
		
		return result;
	}
	
	@PostMapping("/{boardNo}")
	public ResponseEntity<MyResponseWithoutData> saveComment(@PathVariable("boardNo") int boardNo, @RequestBody CommentDTO newComment) {
		log.info(boardNo +"번 게시글에 새로운 댓글 " + newComment + "를 저장하자");
		ResponseEntity<MyResponseWithoutData> result = null;
		
		try {
			if(cService.saveComment(newComment)) {
				result = new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(200, "", "success"), HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<MyResponseWithoutData>(new MyResponseWithoutData(500, "", "fail"), HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return result; 
	}

}
