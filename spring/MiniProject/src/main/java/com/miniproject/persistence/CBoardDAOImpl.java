package com.miniproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.BoardDetailInfo;
import com.miniproject.domain.BoardUpFilesVODTO;
import com.miniproject.domain.HBoardDTO;
import com.miniproject.domain.HBoardReplyDTO;
import com.miniproject.domain.HBoardVO;
import com.miniproject.domain.PagingInfo;
import com.miniproject.domain.SearchCriteriaDTO;

@Repository
public class CBoardDAOImpl implements CBoardDAO {

	@Inject
	private SqlSession ses;

	private static String ns = "com.miniproject.mappers.cboardMapper.";

	@Override
	public List<HBoardVO> selectAllBoard() throws Exception {

		return null;
	}

	@Override
	public List<HBoardVO> selectAllBoard(PagingInfo pi) throws Exception {

		List<HBoardVO> list = ses.selectList(ns + "getAllBoard", pi);
		return null;
	}

	@Override
	public List<HBoardVO> selectAllBoard(SearchCriteriaDTO searchCriteriaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HBoardVO> selectAllBoard(PagingInfo pi, SearchCriteriaDTO sc) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("startRowIndex", pi.getStartRowIndex());
		params.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		params.put("searchType", sc.getSearchType());
		params.put("searchWord", "%" + sc.getSearchWord() + "%");

		return ses.selectList(ns + "getSearchBoard", params);
	}

	@Override
	public int insertNewBoard(HBoardDTO hBoard) throws Exception {
		// TODO Auto-generated method stub
		return ses.insert(ns + "saveNewBoard", hBoard);
	}

	@Override
	public int selectMaxBoardNo() throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("here" + ses.selectOne(ns + "getMaxNo"));
		return ses.selectOne(ns + "getMaxNo");

	}

	@Override
	public int insertBoardUpFile(BoardUpFilesVODTO file) throws Exception {
		// TODO Auto-generated method stub
		return ses.insert(ns + "saveUpFile", file);
	}

	@Override
	public HBoardVO selectBoard(int boardNo) throws Exception {
		return ses.selectOne(ns + "getBoard", boardNo);
	}

	@Override
	public List<BoardUpFilesVODTO> selectBoardUpFiles(int boardNo) throws Exception {
		return ses.selectList(ns + "getBoardUpFiles", boardNo);
	}

	@Override
	public HBoardDTO testResultMap(int boardNo) throws Exception {

		HBoardDTO dao = ses.selectOne(ns + "selectResultMapTest", boardNo);
		System.out.println(dao);

		return dao;
	}

	@Override
	public BoardDetailInfo selectBoardDetailByBoardNo(int boardNo) throws Exception {

		return ses.selectOne(ns + "selectBoardDetailInfoByBoardNo", boardNo);
	}

	@Override
	public int selectDateDiff(String ipAddr, int boardNo) {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("ipAddr", ipAddr);
		param.put("boardNo", boardNo);

		int diff = ses.selectOne(ns + "selectBoardDateDiff", param);

		System.out.println("selectDateDiff dao: " + diff);

		return diff;
	}

	@Override
	public int insertBoardReadLog(String ipAddr, int boardNo) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ipAddr", ipAddr);
		param.put("boardNo", boardNo);

		System.out.println("ipAddr: " + ipAddr);
		System.out.println("boardNo: " + boardNo);

		int result = ses.insert(ns + "saveBoardReadLog", param);

		System.out.println("insertBoardReadLog dao: " + result);
		return result;
	}

	@Override
	public int updateReadWhen(String ipAddr, int boardNo) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ipAddr", ipAddr);
		param.put("boardNo", boardNo);

		int result = ses.insert(ns + "updateBoardReadLog", param);

		System.out.println("dao: " + result);

		return result;
	}

	@Override
	public int updateReadCount(int boardNo) {
		int result = ses.insert(ns + "updateReadCount", boardNo);

		System.out.println("dao: " + result);

		return result;
	}

	@Override
	public void updateBoardRef(int boardNo) throws Exception {

		ses.update(ns + "updateBoardRef", boardNo);
	}

	@Override
	public void updateRefOrder(int ref, int refOrder) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("ref", ref);
		params.put("refOrder", refOrder);

		ses.update(ns + "updateBoardRefOrder", params);
	}

	@Override
	public int insertReplyBoard(HBoardReplyDTO replyDTO) throws Exception {

		return ses.insert(ns + "insertReplyBoard", replyDTO);
	}

	@Override
	public int updateBoardIsDelete(int boardNo) throws Exception {
		// TODO Auto-generated method stub

		return ses.update(ns + "updateBoardIsDelete", boardNo);

	}

	@Override
	public int deleteBoardUpFiles(int boardNo) throws Exception {

		return ses.delete(ns + "deleteBoardUpFiles", boardNo);
	}

	// 게시판 수정
	@Override
	public int updateBoard(HBoardDTO modifyBoard) throws Exception {
		return ses.update(ns + "updateBoard", modifyBoard);
	}

	@Override
	public int deleteBoardUpSomeFile(int fileNo) throws Exception {

		return ses.delete(ns + "deleteBoardUpSomeFile", fileNo);
	}

	// 전체 글 데이터의 수
	@Override
	public int getTotalPostCnt() throws Exception {

		return ses.selectOne(ns + "selectTotalCount");
	}

	@Override
	public int getTotalPostCnt(SearchCriteriaDTO sc) throws Exception {

		return ses.selectOne(ns + "selectTotalCntWithSearchCriteria", sc);
	}

	// 좋아요
	@Override
	public int likeBoard(int boardNo, String who) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("boardNo", boardNo);
		params.put("who", who);

		return ses.insert(ns + "like", params);
	}

	// 좋아요 수 업데이트
	@Override
	public int updateBoardLikeCount(int i, int boardNo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("n", i);
		params.put("boardNo", boardNo);

		return ses.update(ns + "updateLikeCount", params);
	}

	@Override
	public int disLikeBoard(int boardNo, String who) throws Exception {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("boardNo", boardNo);
		params.put("who", who);

		return ses.delete(ns + "disLike", params);
	}

}
