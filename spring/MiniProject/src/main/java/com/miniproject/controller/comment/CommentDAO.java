package com.miniproject.controller.comment;

import java.util.List;

import com.miniproject.domain.CommentDTO;
import com.miniproject.domain.CommentVO;
import com.miniproject.domain.PagingInfo;

public interface CommentDAO {
	// 해당 게시글에 대한 전체 댓글 조회
	List<CommentVO> getAllComents(int boardNo) throws Exception;

	// 댓글 저장
	int insertNewComment(CommentDTO newComment) throws Exception;

	// boardNo번의 전체 댓글 수 조회
	int getTotalPostCnt(int boardNo) throws Exception;
	
	List<CommentVO> getAllComents(int boardNo, PagingInfo pi) throws Exception;
}
