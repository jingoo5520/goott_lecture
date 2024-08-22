package webjingoo.queueex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueEx1Main {

	static int count = 0; // 대기인원 숫자
	static int MAX_SIZE = 10; // 최대 대기인원 숫자
	static int number = 0;
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("---------메뉴---------");
			System.out.print("1.대기번호 발급, 2.대기번호 호출, 9.종료 >> ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				getTicket();
				break;
			case 2:
				call();
				break;
			case 9:
				System.out.println("종료..");
				return;
			default:
				System.out.println("다시 입력.");
			}
		}
	}

	private static void getTicket() {
		if(count == MAX_SIZE) {
			System.out.println("대기인원이 많습니다. 잠시 후 다시 시도해주세요.");
		} else {
			queue.add(++number);
			count++;
			System.out.println(queue);
		}
		
	}

	private static void call() {
		if(count == 0) {
			System.out.println("대기인원이 없습니다.");
		} else {
			queue.poll();
			count--;
			System.out.println(queue);
		}
		
	}

}
