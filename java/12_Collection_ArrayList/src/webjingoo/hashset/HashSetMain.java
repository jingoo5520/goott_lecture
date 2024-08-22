package webjingoo.hashset;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetMain {

	public static void main(String[] args) {
		HashSet setA = new HashSet();

		setA.add("1");
		setA.add("2");
		setA.add("3");
		setA.add("4");
		setA.add("5");
		// setA.add("3"); // 중복 허용하지 않음

		System.out.println(setA);

		HashSet setB = new HashSet();

		setB.add("4");
		setB.add("5");
		setB.add("6");
		setB.add("7");
		setB.add("8");

		System.out.println(setB);

		// 두 HashSet의 중복요소 찾기, 교집합 찾기
		HashSet setInter = new HashSet();

		// 모든 요소 순회
		Iterator iter = setB.iterator();

		// hasNext(): 다음 요소가 있는지 확인
		// next(): 요소 반환
		while (iter.hasNext()) {
			Object tmp = iter.next();
			if (setA.contains(tmp)) {
				setInter.add(tmp);
			}
		}

		System.out.println("intersection: " + setInter);

		// 차집합
		HashSet setDif = new HashSet();
		iter = setA.iterator();

		while (iter.hasNext()) {
			Object tmp = iter.next();
			System.out.println(tmp);
			if (!setB.contains(tmp)) {
				setDif.add(tmp);
			}
		}

		System.out.println("difference: " + setDif);

		// 합집합
		HashSet setUni = new HashSet();
		iter = setB.iterator();

		setUni = (HashSet) setA.clone();
		System.out.println(setUni);

		while (iter.hasNext()) {
			Object tmp = iter.next();
			setUni.add(tmp);

		}

		System.out.println("Union: " + setUni);

	}

}
