package com.miniproject.service.hboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.PagingInfoDTO;
import com.miniproject.domain.PointLogDTO;
import com.miniproject.domain.SearchCriteriaDTO;
import com.miniproject.persistence.CBoardDAO;
import com.miniproject.persistence.HBoardDAO;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;

@Service
public class CBoardServiceImpl implements CBoardService {

	@Autowired
	private CBoardDAO cDao;

	@Autowired
	private HBoardDAO bDao;

	@Autowired
	private PointLogDAO pDao;
	@Autowired
	private MemberDAO mDao;

	@Override
	public List<HBoardVO> getAllBoard() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAllBoard(PagingInfoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getAllBoard(PagingInfoDTO dto, SearchCriteriaDTO searchCriteriaDTO) throws Exception {

		PagingInfo pi = makePagingInfo(dto, searchCriteriaDTO);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<HBoardVO> list = cDao.selectAllBoard(pi, searchCriteriaDTO);

		resultMap.put("pagingInfo", pi);
		resultMap.put("boardList", list);

		return resultMap;
	}

	private PagingInfo makePagingInfo(PagingInfoDTO dto, SearchCriteriaDTO sc) throws Exception {
		PagingInfo pi = new PagingInfo(dto);

		// setter 호출
		pi.setTotalPostCnt(cDao.getTotalPostCnt(sc));

		pi.setTotalPageCnt();
		pi.setStartRowIndex();

		// 페이징 블럭
		pi.setPageBlockNoCurPage();
		pi.setStartPageNoCurBloack();
		pi.setEndPageNoCurBlock();

		System.out.println("service pagingInfo: " + pi.toString());

		return pi;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean saveBoard(HBoardDTO hBoard) throws Exception {
		boolean result = false;

		// 1) newBoard를 DAO단을 통해서 insert한다.
		if (cDao.insertNewBoard(hBoard) == 1) {

			int newBoardNo = cDao.selectMaxBoardNo();
			cDao.updateBoardRef(newBoardNo);

			// 2) 1)에서 insert가 성공하면, pointlog 에 저장
			if (pDao.insertPointLog(new PointLogDTO(hBoard.getWriter(), "글작성")) == 1) {
				// 3) 작성자의 userPoint값을 update한다.
				if (mDao.updateUserPoint(hBoard.getWriter()) == 1) {
					result = true;
				}
			}
		}

		return result;
	}

	@Override
	public HBoardDTO getBoard(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HBoardDTO testResultMap(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDetailInfo read(int boardNo) throws Exception {
		return cDao.selectBoardDetailByBoardNo(boardNo);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BoardDetailInfo read(int boardNo, String ipAddr) throws Exception {
		BoardDetailInfo boardInfo = cDao.selectBoardDetailByBoardNo(boardNo);

		int dateDiff = bDao.selectDateDiff(ipAddr, boardNo);

		if (dateDiff == -1) {
			// 최초 조회라면
			// 조회 정보 insert
			if (bDao.insertBoardReadLog(ipAddr, boardNo) == 1) {
				// insert 성공
				// 조회수 증가
				updateReadCount(boardNo, boardInfo);
				// 해당 글 불러오기
			}
		} else if (dateDiff >= 1) {
			// 최조 조회가 아니고, 24시간이 지난 경우

			// 조회 시간 업데이트
			bDao.updateReadWhen(ipAddr, boardNo);
			// 조회수 증가
			updateReadCount(boardNo, boardInfo);
			// 해당 글 불러오기
		}
		
		
		
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        
        HttpSession ses = request.getSession();
        
        System.out.println("ses: " + ses);

		
		
		return boardInfo;
	}

	private void updateReadCount(int boardNo, BoardDetailInfo boardInfo) throws Exception {
		if (bDao.updateReadCount(boardNo) == 1) {

			boardInfo.setReadCount(boardInfo.getReadCount() + 1);
		}
	}
	
	@Override
	public boolean saveReply(HBoardReplyDTO replyBoardDTO) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBoard(int boardNo, String realPath) throws Exception {

		return false;
	}

	@Override
	public boolean modifyBoard(HBoardDTO modifyBoard) throws Exception {
		boolean result = false;

		// 게시판 수정
		if (cDao.updateBoard(modifyBoard) == 1) {
			result = true;
		}

		return result;
	}

	// 좋아요, 좋아요 취소
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean likeBoard(int boardNo, String who) throws Exception {
		boolean result = false;

		// 좋아요
		// 1) boardlike 테이블에 insert
		if (cDao.likeBoard(boardNo, who) == 1) {
			// 2) hboard 의 likecount 컬럼 업데이트
			if (cDao.updateBoardLikeCount(1, boardNo) == 1) {
				result = true;
			}
		}

		return false;
	}

	@Override
	public boolean disLikeBoard(int boardNo, String who) throws Exception {
		
		boolean result = false;

		// 좋아요 취소
		// 1) boardlike 테이블에서 delete
		if (cDao.disLikeBoard(boardNo, who) == 1) {
			// 2) hboard 의 likecount 컬럼 업데이트
			if (cDao.updateBoardLikeCount(-1, boardNo) == 1) {
				result = true;
			}
		}

		return false;
	}

	// 해당 게시글을 좋아요 한 사람들 조회
	@Override
	public List<String> selectPeopleWhoLike(int boardNo) throws Exception {
		return cDao.selectPeopleWhoLikeBoard(boardNo);
	}

}
