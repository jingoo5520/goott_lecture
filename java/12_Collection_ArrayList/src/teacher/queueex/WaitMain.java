package teacher.queueex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class WaitMain {

	private static final int MAX_WAIT_NUM = 10;
	private static int issueNum = 0;

	public static void main(String[] args) {
		Queue<Integer> waitList = new LinkedList<Integer>();

		Scanner sc = new Scanner(System.in);

		System.out.println("1. 대기번호 발급");
		System.out.println("2. 대기번호 호출");
		System.out.println("9. 종료");

		int choice = sc.nextInt();

		switch (choice) {
		case 1:
//			issueWaitNumber();
			break;
		case 2:
//			callWaitNumber();
			break;
		case 9:
			System.out.println("종료.");
			System.exit(0);
		default:
			System.out.println("다시 입력하시오.");
			break;
		}

	}

}
