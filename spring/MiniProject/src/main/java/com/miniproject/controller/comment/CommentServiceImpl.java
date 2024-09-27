package com.miniproject.controller.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.CommentDTO;
import com.miniproject.domain.CommentVO;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.PagingInfoDTO;
import com.miniproject.domain.PointLogDTO;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private final CommentDAO cDao;

	@Inject
	private final PointLogDAO pDao;
	
	@Inject
	private final MemberDAO mDao;
	
	// 해당 게시글에 대한 전체 댓글 조회
	@Override
	public List<CommentVO> getAllComents(int boardNo) throws Exception {
		return cDao.getAllComents(boardNo);
	}

	// 댓글 저장
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean saveComment(CommentDTO newComment) throws Exception {
		
		boolean result = false;
		// 1) 댓글 저장 - insert
		if(cDao.insertNewComment(newComment) == 1) {
			// 2) 포인트 부여 - insert
			if(pDao.insertPointLog(new PointLogDTO(newComment.getCommenter(), "댓글작성")) == 1) {
				
				// 3) 멤버의 포인트 - update
				if(mDao.updateUserPointComment(newComment.getCommenter()) == 1) {
					result = true;
				}
				
			}
		}
		
		return result;
	}

	// 해당 게시글에 대한 전체 댓글 조회(with pagination)
	@Override
	public Map<String, Object> getAllComents(int boardNo, PagingInfoDTO pagingInfoDTO) throws Exception {
		
		PagingInfo pi = makePagingInfo(pagingInfoDTO, boardNo);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<CommentVO> list = cDao.getAllComents(boardNo, pi);
		
		result.put("pi", pi);
		result.put("list", list);
		
		return result;
	}
	
	private PagingInfo makePagingInfo(PagingInfoDTO dto, int boardNo) throws Exception {
		PagingInfo pi = new PagingInfo(dto);
		
		// setter 호출
		pi.setTotalPostCnt(cDao.getTotalPostCnt(boardNo));
		
		pi.setTotalPageCnt();
		pi.setStartRowIndex();
		
		// 페이징 블럭
		pi.setPageBlockNoCurPage();
		pi.setStartPageNoCurBloack();
		pi.setEndPageNoCurBlock();
		
		return pi;
	}

}
