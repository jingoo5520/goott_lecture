package com.miniproject.service.hboard;

import java.util.List;
import java.util.Map;
import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.BoardUpFilesVODTO;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.PagingInfoDTO;
import com.miniproject.domain.SearchCriteriaDTO;

public interface CBoardService {
	// 게시판 전체 리스트 조회
	List<HBoardVO> getAllBoard() throws Exception;

	// 게시판 리스트 조회 - 페이징
	Map<String, Object> getAllBoard(PagingInfoDTO dto) throws Exception;

	// 게시판 리스트 조회 - 검색
	Map<String, Object> getAllBoard(PagingInfoDTO dto, SearchCriteriaDTO searchCriteriaDTO) throws Exception;

	// 게시판 글 쓰기
	boolean saveBoard(HBoardDTO hBoard) throws Exception;

	// 게시판 상세보기
	HBoardDTO getBoard(int boardNo) throws Exception;

	// 게시판 업로드 파일 가져오기
	// List<BoardUpFilesVODTO> getBoardUpFiles(int boardNo) throws Exception;

	HBoardDTO testResultMap(int boardNo) throws Exception;

	// 게시글 상세 조회
	BoardDetailInfo read(int boardNo, String ipAddr) throws Exception;

	// 답글 저장
	boolean saveReply(HBoardReplyDTO replyBoardDTO) throws Exception;

	boolean deleteBoard(int boardNo, String realPath) throws Exception;

	BoardDetailInfo read(int boardNo) throws Exception;

	// 게시판 수정
	boolean modifyBoard(HBoardDTO modifyBoard) throws Exception;

	// 좋아요
	boolean likeBoard(int boardNo, String who) throws Exception;

	// 좋아요 취소
	boolean disLikeBoard(int boardNo, String who) throws Exception;

	// 해당 게시글을 좋아요 한 사람들 조회
	List<String> selectPeopleWhoLike(int boardNo) throws Exception;

}
