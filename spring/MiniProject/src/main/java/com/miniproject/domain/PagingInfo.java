package com.miniproject.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingInfo {

	// 기본 페이징 출력에 필요한 변수들
	
	private int pageNo; // 페이지 번호
	private int viewPostCntPerPage; // 페이지당 보여질 글의 개수
	
	private int totalPostCnt; // 전체 글 개수
	private int totalPageCnt; // 전체 페이지 수
	private int startRowIndex; // 현재 페이지에서 보여지는 첫 글의 index
	
	// 페이징 블럭 만들 때 필요한 변수들
	private int pageCntPerBlock = 10; // 1개의 페이징 블럭에서 보여줄 페이지 수
	private int pageBlockNoCurPage; // 현재 페이지가 속한 페이징 블럭 번호
	private int startPageNoCurBloack; // 현재 페이징 블럭의 시작 페이지 번호
	private int endPageNoCurBlock; // 현재 페이징 블럭의 마지막 페이지 번호
	
	public PagingInfo(PagingInfoDTO dto) {
		this.pageNo = dto.getPageNo();
		this.viewPostCntPerPage = dto.getPagingSize();
		
	}
	
	public void setTotalPostCnt(int totalPostCnt) {
		this.totalPostCnt = totalPostCnt;
	}
	
	public void setTotalPageCnt() {
		// (전체 페이지수 / 페이지당 보여질 글의 개수)
		// 나머지가 있다면 몫 + 1
		// 나머지가 없다면 몫
		if(this.totalPostCnt % this.viewPostCntPerPage == 0) {
			this.totalPageCnt = this.totalPostCnt / this.viewPostCntPerPage;
		} else {
			this.totalPageCnt = this.totalPostCnt / this.viewPostCntPerPage + 1;
		}
	}
	
	public void setStartRowIndex() {
		// (페이지번호 - 1) * (페이지당 보여질 글의 개수)
		this.startRowIndex = (this.pageNo - 1) * this.viewPostCntPerPage; 
	}
	
	public void setPageBlockNoCurPage() {
		// (현재 페이지번호) / (1개 페이징 블럭에서 보여줄 페이지 수)
		// 나머지가 있다면 몫 + 1
		// 나머지가 없다면 몫
		if(this.pageNo % this.pageCntPerBlock == 0) {
			this.pageBlockNoCurPage = this.pageNo / this.pageCntPerBlock;
		} else {
			this.pageBlockNoCurPage = this.pageNo / this.pageCntPerBlock + 1; 
		}
	}
	
	public void setStartPageNoCurBloack() {
		// (현재 페이징 블럭 번호 - 1) * (1개 페이징 블럭에서 보여줄 페이지 수) + 1
		this.startPageNoCurBloack = (this.pageBlockNoCurPage - 1 ) * this.pageCntPerBlock + 1;
	}
	
	public void setEndPageNoCurBlock() {
		this.endPageNoCurBlock = this.startPageNoCurBloack + (this.pageCntPerBlock - 1);
		
		// 데이터가 없는 페이지가 나오지 않도록 처리
		if(this.totalPageCnt < this.endPageNoCurBlock) {
			
			this.endPageNoCurBlock = this.totalPageCnt;
			
		}
	}
}
