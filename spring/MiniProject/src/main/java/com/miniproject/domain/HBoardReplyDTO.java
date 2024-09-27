package com.miniproject.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class HBoardReplyDTO {
	private String title;
	private String content;
	private String writer;
	
	private int ref;
	private int step;
	private int refOrder;
}
