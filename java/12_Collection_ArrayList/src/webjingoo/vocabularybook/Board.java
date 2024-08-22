package webjingoo.vocabularybook;

import webjingoo.hashmapmember.Member;

public class Board {
	private int boardNo;
	private String writter;
	private String title;
	private String content;

	public Board(int boardNo, String writter, String title, String content) {
		super();
		this.boardNo = boardNo;
		this.writter = writter;
		this.title = title;
		this.content = content;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public String getWritter() {
		return writter;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", writter=" + writter + ", title=" + title + ", content=" + content + "]";
	}

}
