package teacher.stackex;

import java.util.Stack;

public class WebHistory {
	
	private Stack<String> history = new Stack<String>();
	private String currentPage = null;
	
	public void visitWebPage(String webPage) {
		if(currentPage != null) {
			history.push(webPage);
		}
		
		currentPage = webPage;
		System.out.println("방문한 웹페이지: " + webPage);
	}
	
	public void goBack() {
		// 뒤로가기 버튼 클릭
		if(!history.isEmpty()) {
			currentPage = history.pop();
			System.out.print("뒤로가기 클릭!");
			System.out.println(" 현재 페이지: " + currentPage);
		} else {
			System.out.println("첫 페이지 입니다.");
		}
		
		
	}
}
