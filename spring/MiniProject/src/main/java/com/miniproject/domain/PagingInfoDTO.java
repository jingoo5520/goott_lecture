package com.miniproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PagingInfoDTO {
	private int pageNo;
	private int pagingSize; // 한 페이지에서 보여질 글의 개수
	
}
