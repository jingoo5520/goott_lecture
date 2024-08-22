package teacher.stackex;

public class WebHistoryMain {

	public static void main(String[] args) {
		WebHistory webHistory = new WebHistory();
		
		webHistory.visitWebPage("홈페이지");
		webHistory.visitWebPage("네이버");
		webHistory.visitWebPage("다음");
		webHistory.visitWebPage("쿠팡");
		webHistory.visitWebPage("자바API");
		
		webHistory.goBack();
		webHistory.goBack();
	}

}
