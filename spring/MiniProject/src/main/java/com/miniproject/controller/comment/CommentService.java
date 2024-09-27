package com.miniproject.controller.comment;

import java.util.List;
import java.util.Map;

import com.miniproject.domain.CommentDTO;
import com.miniproject.domain.CommentVO;
import com.miniproject.domain.PagingInfoDTO;

public interface CommentService {
	// 해당 게시글에 대한 전체 댓글 조회
	List<CommentVO> getAllComents(int boardNo) throws Exception;
	
	// 댓글 저장
	boolean saveComment(CommentDTO newCommnet) throws Exception;

	// 전체 댓글 조회(with pagination)
	Map<String, Object> getAllComents(int boardNo, PagingInfoDTO pagingInfoDTO) throws Exception;
}
