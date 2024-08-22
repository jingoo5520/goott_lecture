package webjingoo.stackEx1;

import java.util.Scanner;
import java.util.Stack;

public class StackEx1Main {
	static Stack<String> stack;
	static String[] sites = { "네이버", "다음", "쿠팡", "자바" };
	static int index = sites.length - 1;
	static int MAX_INDEX = sites.length - 1;
	
	public static void main(String[] args) {
		stack = new Stack<String>();

		stack.push(sites[0]);
		stack.push(sites[1]);
		stack.push(sites[2]);
		stack.push(sites[3]);
		
		System.out.print(stack);

		Scanner sc = new Scanner(System.in);

		
		while (true) {
			System.out.println(" 현재위치: " + stack.peek());
			System.out.print("1.뒤로가기, 2.앞으로가기, 3.종료 >> ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				back();
				break;
			case 2:
				go();
				break;
			case 3:
				System.out.println("종료..");
				return;
			default:
				System.out.println("다시 입력.");
			}
		}

	}

	private static void go() {
		if(index == MAX_INDEX) {
		} else {
			stack.push(sites[++index]);
		}
		
		System.out.print(stack);
	}

	private static void back() {
		if(index == 0) {
		} else {
			stack.pop();
			index--;
		}
		System.out.print(stack);
	}

}
