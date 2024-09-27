package com.miniproject.domain;

import java.sql.Timestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access =AccessLevel.PROTECTED )
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentVO {
	private int commentNo;
	private String commenter;
	private String content;
	private Timestamp regDate;
	private int boardNo;
	
	private String userImg;
}
