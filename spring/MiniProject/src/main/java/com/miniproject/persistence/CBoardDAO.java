package com.miniproject.persistence;

import java.util.List;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.BoardUpFilesVODTO;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.SearchCriteriaDTO;

public interface CBoardDAO {
	List<HBoardVO> selectAllBoard() throws Exception;
	
	int insertNewBoard(HBoardDTO hBoard) throws Exception;
	
	// 최근 저장된 글의 번호를 얻어오는 메서드
	int selectMaxBoardNo() throws Exception;
	
	int insertBoardUpFile(BoardUpFilesVODTO file) throws Exception;
	
	HBoardVO selectBoard(int boardNo) throws Exception;
	
	List<BoardUpFilesVODTO> selectBoardUpFiles(int boardNo) throws Exception;
	
	// resultMap 테스트
	HBoardDTO testResultMap(int boardNo) throws Exception;
	
	// 게시글 상세 정보(게시글, 첨부파일, 유저 정보)
	BoardDetailInfo selectBoardDetailByBoardNo(int boardNo) throws Exception;

	// 시간 차이 가져오기
	int selectDateDiff(String ipAddr, int boardNo) throws Exception;

	// 게시판 조회 기록 추가
	int insertBoardReadLog(String ipAddr, int boardNo) throws Exception;

	// 조회 시간 업데이트
	int updateReadWhen(String ipAddr, int boardNo) throws Exception;

	// 조회수 증가
	int updateReadCount(int boardNo) throws Exception;
	
	void updateBoardRef(int boardNo) throws Exception;

	void updateRefOrder(int ref, int refOrder) throws Exception;

	int insertReplyBoard(HBoardReplyDTO replyBoardDTO) throws Exception;

	// 게시판 삭제처리 isDelete = 'Y'
	int updateBoardIsDelete(int boardNo) throws Exception;
	
	// 업로드된 파일 삭제
	int deleteBoardUpFiles(int boardNo) throws Exception;

	// 게시판 수정
	int updateBoard(HBoardDTO modifyBoard) throws Exception;

	// 게시판 파일 수정, 기존에 업로드되어있던 파일 삭제
	int deleteBoardUpSomeFile(int fileNo) throws Exception;

	// 전체 글(데이터의 수)
	int getTotalPostCnt() throws Exception;

	// 게시글 목록 조회 -- 페이징
	List<HBoardVO> selectAllBoard(PagingInfo pi)  throws Exception;

	// 게시글 목록 조회 - 검색
	List<HBoardVO> selectAllBoard(SearchCriteriaDTO searchCriteriaDTO);
	
	// 검색된 게시글 수
	int getTotalPostCnt(SearchCriteriaDTO sc) throws Exception;
	
	List<HBoardVO> selectAllBoard(PagingInfo pi, SearchCriteriaDTO searchCriteriaDTO)  throws Exception;

	// 좋아요
	int likeBoard(int boardNo, String who) throws Exception;

	// 좋아요 수 업데이트
	int updateBoardLikeCount(int i, int boardNo) throws Exception;

	int disLikeBoard(int boardNo, String who) throws Exception;

	List<String> selectPeopleWhoLikeBoard(int boardNo) throws Exception;
}
