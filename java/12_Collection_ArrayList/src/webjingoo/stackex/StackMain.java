package webjingoo.stackex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackMain {

	public static void main(String[] args) {
		System.out.println("---stack---");
		Stack<String> stack = new Stack<String>();
		
		stack.push("홍길동1");
		stack.push("홍길서2");
		stack.push("홍길남3");
		
		System.out.println(stack);
		System.out.println(stack.size());
		
		stack.pop();
		System.out.println(stack);
		
		// 가장 마지막에 들어온 원소 보기
		System.out.println(stack.peek());
		
		stack.pop();
		System.out.println(stack);
		
		System.out.println(stack.peek());
		
		stack.pop();
		System.out.println(stack);
		// System.out.println(stack.peek());
		
		System.out.println("---queue---");
		
		Queue<String> queue = new LinkedList<String>();
		queue.offer("홍길동1");
		queue.offer("홍길서2");
		queue.offer("홍길남3");
		
		System.out.println(queue);
		System.out.println(queue.poll());
		System.out.println(queue);
		System.out.println(queue.peek());
		
		
		try {
		
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Exception caught: " + e.getMessage());
        }
	}

}
