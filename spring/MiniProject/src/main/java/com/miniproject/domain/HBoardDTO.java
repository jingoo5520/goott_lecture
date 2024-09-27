package com.miniproject.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HBoardDTO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	
	private List<BoardUpFilesVODTO> fileList;
}