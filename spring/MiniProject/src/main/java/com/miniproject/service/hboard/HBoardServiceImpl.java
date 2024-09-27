package com.miniproject.service.hboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.BoardUpFilesVODTO;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.PagingInfoDTO;
import com.miniproject.domain.PointLogDTO;
import com.miniproject.domain.SearchCriteriaDTO;
import com.miniproject.persistence.HBoardDAO;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;
import com.miniproject.util.FileProcess;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 아래의 클래스가 서비스 객체임을 컴파일러에게 공지
public class HBoardServiceImpl implements HBoardService {

	@Inject
	private FileProcess fileProcess; // FileProcess 객체 주입

	@Autowired
	private HBoardDAO bDao;
	@Autowired
	private PointLogDAO pDao;
	@Autowired
	private MemberDAO mDao;

	// Service 단에서 해야할 작업
	// 1) Controller에서 넘겨진 파라미터 처리
	// 2) DB작업이 필요하다면 DAO단 호출
	// 3) DAO단에서 반환된 값을 Controller 단으로 넘겨줌

	// 게시판 전체 가져오기(초기)
	@Override
	public List<HBoardVO> getAllBoard() throws Exception {

		List<HBoardVO> list = bDao.selectAllBoard();

		return list;
	}

	// 게시판 전체 가져오기(페이징)
	@Override
	public Map<String, Object> getAllBoard(PagingInfoDTO dto) throws Exception {
		PagingInfo pi = makePagingInfo(dto);
		
		List<HBoardVO> list = bDao.selectAllBoard(pi);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("pagingInfo", pi);
		resultMap.put("boardList", list);
		
		return resultMap;
	}
	
	// 게시판 목록 가져오기(검색)
	@Override
	public Map<String, Object> getAllBoard(PagingInfoDTO dto, SearchCriteriaDTO searchCriteriaDTO) throws Exception {
		
//		searchCriteriaDTO.setSearchWord("%" + searchCriteriaDTO.getSearchWord() + "%");
		
		PagingInfo pi = makePagingInfo(dto, searchCriteriaDTO);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<HBoardVO> list = bDao.selectAllBoard(pi, searchCriteriaDTO); 
		
		resultMap.put("pagingInfo", pi);
		resultMap.put("boardList", list);
		
		return resultMap;
	}
	

	private PagingInfo makePagingInfo(PagingInfoDTO dto) throws Exception {
		PagingInfo pi = new PagingInfo(dto);
		
		// setter 호출
		pi.setTotalPostCnt(bDao.getTotalPostCnt());
		
		pi.setTotalPageCnt();
		pi.setStartRowIndex();
		
		// 페이징 블럭
		pi.setPageBlockNoCurPage();
		pi.setStartPageNoCurBloack();
		pi.setEndPageNoCurBlock();

		System.out.println("service pagingInfo: " + pi.toString());
		
		return pi;
	}
	
	private PagingInfo makePagingInfo(PagingInfoDTO dto, SearchCriteriaDTO sc) throws Exception {
		PagingInfo pi = new PagingInfo(dto);
		
		// setter 호출
		pi.setTotalPostCnt(bDao.getTotalPostCnt(sc));
		
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
	@Transactional(rollbackFor = Exception.class)
	public boolean saveBoard(HBoardDTO hBoard) throws Exception {
		boolean result = false;

		// 1) newBoard를 DAO단을 통해서 insert한다.
		if (bDao.insertNewBoard(hBoard) == 1) {

			// 첨부파일이 있다면 함께 저장
			// 1-1) 방금 저장된 게시글의 번호 가져오기
			// 1-2) 방금 저장된 게시글의 번호를 참조하는 첨부파일 정보를 insert

			int newBoardNo = bDao.selectMaxBoardNo();

			bDao.updateBoardRef(newBoardNo);

			for (BoardUpFilesVODTO file : hBoard.getFileList()) {
				file.setBoardNo(newBoardNo);
				bDao.insertBoardUpFile(file);
			}

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
//	public HBoardVO getBoard(int boardNo) throws Exception {
//		HBoardVO board = bDao.selectBoard(boardNo);
//		return board;
//	}

//	@Override
//	public List<BoardUpFilesVODTO> getBoardUpFiles(int boardNo) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public HBoardDTO getBoard(int boardNo) throws Exception {

		HBoardDTO hboard = null;

		HBoardVO board = bDao.selectBoard(boardNo);

		List<BoardUpFilesVODTO> fileList = bDao.selectBoardUpFiles(boardNo);

		System.out.println("getBoard-board: " + board);
		System.out.println("getBoard-fileList: " + fileList);

		hboard = new HBoardDTO(boardNo, board.getTitle(), board.getContent(), board.getWriter(), fileList);

		return hboard;
	}

	@Override
	public HBoardDTO testResultMap(int boardNo) throws Exception {
		HBoardDTO dto = bDao.testResultMap(boardNo);
		System.out.println("service: " + dto);

		return dto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<BoardDetailInfo> read(int boardNo, String ipAddr) throws Exception {

		List<BoardDetailInfo> list = bDao.selectBoardDetailByBoardNo(boardNo);

		// 조회수 증가
		// boardreadlog 테이블에 ipAddr, boardNo가 동일한 행을 가져옴
		// 행이 없거나, 해당 데이터의 시간과 현재 시간을 계산해 24시간이 지났다면
		// 조회수를 1 증가시키고 시간을 현재 시간으로 업데이트 후 게시글을 가져옴

		int dateDiff = bDao.selectDateDiff(ipAddr, boardNo);


		if (dateDiff == -1) {
			// 최초 조회라면
			// 조회 정보 insert
			if (bDao.insertBoardReadLog(ipAddr, boardNo) == 1) {
				// insert 성공
				// 조회수 증가
				updateReadCount(boardNo, list);
				// 해당 글 불러오기
			}
		} else if (dateDiff >= 1) {
			// 최조 조회가 아니고, 24시간이 지난 경우

			// 조회 시간 업데이트
			bDao.updateReadWhen(ipAddr, boardNo);
			// 조회수 증가
			updateReadCount(boardNo, list);
			// 해당 글 불러오기
		}

		// 24시간이 지나지 않았다면
		// 조회수 증가 없이 게시글을 가져옴

		System.out.println("read service: " + list);
		return list;
	}

	private void updateReadCount(int boardNo, List<BoardDetailInfo> list) throws Exception {
		if (bDao.updateReadCount(boardNo) == 1) {
			for (BoardDetailInfo b : list) {
				b.setReadCount(b.getReadCount() + 1);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean saveReply(HBoardReplyDTO replyBoardDTO) throws Exception {

		boolean result = false;

//		-- 2-2) 부모글에 대한 다른 답을이 있는 상태에서, 부모글의 답글이 추가되는 경우,
//	-- (자리 확보를 위해) 기존의 답글의 refOrder값을 수정(+1) 해야함.

		bDao.updateRefOrder(replyBoardDTO.getRef(), replyBoardDTO.getRefOrder());

		replyBoardDTO.setStep(replyBoardDTO.getStep() + 1);
		replyBoardDTO.setRefOrder(replyBoardDTO.getRefOrder() + 1);

		if (bDao.insertReplyBoard(replyBoardDTO) == 1) {
			result = true;
		}

		return result;

//	-- 2-1) 답글을 입력받아 저장
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean deleteBoard(int boardNo, String realPath) throws Exception {

		boolean result = false;

		List<BoardUpFilesVODTO> list = bDao.selectBoardUpFiles(boardNo);

		System.out.println("삭제해야 할 파일 목록: ");
		for (BoardUpFilesVODTO v : list) {
			System.out.println(realPath + v.getNewFileName());
			if (v.getThumbFileName() != null) {
				// 이미지 파일이라면
				fileProcess.removeFile(realPath + v.getThumbFileName());
				fileProcess.removeFile(realPath + v.getNewFileName());
			} else {
				// 이미지 파일이 아니라면
				fileProcess.removeFile(realPath + v.getNewFileName());
			}
		}

		// isDelete = 'Y'
		// 파일들 DB에서 삭제
		if (bDao.updateBoardIsDelete(boardNo) == 1 && bDao.deleteBoardUpFiles(boardNo) == 1) {
			result = true;
		}

		return result;
	}

	@Override
	public List<BoardDetailInfo> read(int boardNo) throws Exception {

		System.out.println("수정 read 함수 호출");
		List<BoardDetailInfo> list = bDao.selectBoardDetailByBoardNo(boardNo);

		return list;
	}

	// 게시판 수정
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public boolean modifyBoard(HBoardDTO modifyBoard, List<Integer> removeFileNoList, List<BoardUpFilesVODTO> newFileList)
			throws Exception {
		boolean result = false;
		
		// 게시판 수정
		bDao.updateBoard(modifyBoard);

		// 게시판 기존 파일 일부 삭제
		for(int i = 0; i < removeFileNoList.size(); i++) {
			bDao.deleteBoardUpSomeFile(removeFileNoList.get(i));
		}

		// 게시판 새로운 파일 추가 추가
		for (int i = 0; i < newFileList.size(); i++) {
			System.out.println("here------------------------");
			System.out.println(newFileList.get(i));
			
			newFileList.get(i).setBoardNo(modifyBoard.getBoardNo());
			if(bDao.insertBoardUpFile(newFileList.get(i)) == 1) {
				result = true;
			} else {
				result = false;
				break;
			}
		}
		
		return result;
	}

	

	
}
