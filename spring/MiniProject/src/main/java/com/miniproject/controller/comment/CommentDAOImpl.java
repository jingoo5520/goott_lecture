package com.miniproject.controller.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.CommentDTO;
import com.miniproject.domain.CommentVO;
import com.miniproject.domain.PagingInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CommentDAOImpl implements CommentDAO {

	@Inject
	private SqlSession ses;
	
	private static final String ns = "com.miniproject.mappers.commentMapper.";

	
	
	// 해당 게시글에 대한 전체 댓글 조회
	@Override
	public List<CommentVO> getAllComents(int boardNo) throws Exception {
		return ses.selectList(ns + "getComments", boardNo);
	}

	// 댓글 저장
	@Override
	public int insertNewComment(CommentDTO newComment) {
		
		return ses.insert(ns + "saveComment", newComment);
		
	}

	// boardNo번의 전체 댓글 수 조회
	@Override
	public int getTotalPostCnt(int boardNo) throws Exception {
		
		return ses.selectOne(ns + "getCommentCount", boardNo);
	}

	@Override
	public List<CommentVO> getAllComents(int boardNo, PagingInfo pi) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("boardNo", boardNo);
		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + "getComments", params);
	}

}
