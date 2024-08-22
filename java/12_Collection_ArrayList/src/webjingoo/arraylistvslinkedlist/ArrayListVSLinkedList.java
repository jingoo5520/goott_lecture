package webjingoo.arraylistvslinkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVSLinkedList {

	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		LinkedList ll = new LinkedList();
		
		System.out.println("원소 추가 시간 측정");
		System.out.println(add1(al)); // 18
		System.out.println(add1(ll)); // 123
		
		System.out.println("모든 원소 접근 시간 측정");
		System.out.println(access(al)); // 3
		System.out.println(access(ll)); // 3990
		
		System.out.println("중간에 원소 추가하기");
		addMid(al);
		
		System.out.println("앞에서부터 순서대로 삭제하기");
		remove0(al);
		
		System.out.println("끝에서부터 순서대로 삭제하기");
		remove1(al);
		
	}

	private static long remove1(List list) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}

	private static long remove0(List list) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}

	private static long addMid(List list) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}

	private static long add1(List list) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 1000000; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}

	private static long access(List list) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 50000; i++) {
			list.get(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	
}
