package teacher.hashmapex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HashMapVocaMain {

	static HashMap<String, ArrayList<String>> vocaBook = new HashMap();;
	static Scanner sc = new Scanner(System.in);

	private static void showMenu() {
		System.out.println("-------------------------------------암기장-------------------------------------");
		System.out.print("1. 영어단어 등록 / ");
		System.out.print("2. 영어단어 삭제 / ");
		System.out.print("3. 영어단어로 검색 / ");
		System.out.print("4. 뜻으로 검색 / ");
		System.out.print("5. 모두 보기 / ");
		System.out.println("9. 종료");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("메뉴 선택 >> ");
	}

	public static void main(String[] args) {

		vocaBook.put("apple", new ArrayList<>());

		while (true) {
			showMenu();

			int num = 0;

			try {
				num = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(e);
			}

			switch (num) {
			case 1:
				addWord();
				break;
			case 2:
//				deleteWord();
				break;
			case 3:
//				searchByWord();
				break;
			case 4:
//				searchByMeaning();
				break;
			case 5:
				outputVocaBook();
				break;
			case 9:
				System.out.println("종료합니다..");
				System.exit(0);
			default:
				System.out.println("다시 입력해주세요.");
			}

		}
	}

	private static void addWord() {
		String word;
		ArrayList<String> meanings = new ArrayList<String>();

		System.out.print("단어 입력: ");
		word = sc.nextLine();

		while (true) {
			System.out.print("뜻 입력(모두 입력시 q 입력): ");
			String meaning = sc.nextLine();

			if (meaning.equals("q")) {
				break;
			}

			meanings.add(meaning);
		}

		if (vocaBook.containsKey(word)) {
			ArrayList<String> temp = vocaBook.get(word);
			temp.addAll(meanings);
			vocaBook.put(word, meanings);
		} else {
			vocaBook.put(word, meanings);
		}
	}

	private static void searchWord() {
		System.out.print("조회할 단어 입력 >> ");
		String wordToSearch = sc.nextLine();

		System.out.println(wordToSearch + "의 뜻을 검색합니다.");
		ArrayList<String> meanings = vocaBook.get(wordToSearch);
		System.out.println(meanings);
	}

	private static void searchByMeaning() {
		System.out.print("조회할 뜻 입력 >> ");
		String meaning = sc.nextLine();

		System.out.println(meaning + " 의 뜻을 가진 단어 검색");

		boolean isFind = false;

		for (Map.Entry<String, ArrayList<String>> entry : vocaBook.entrySet()) {
			String word = entry.getKey();
			ArrayList<String> meanings = entry.getValue();

			if (meanings.contains(meaning)) {
				isFind = true;
				System.out.println(word);
			}

		}

		if (!isFind)
			System.out.println("검색된 단어가 없습니다.");
	}
	
	private static void deleteWord() {
		System.out.print("삭제할 단어 입력 >> ");
		String wordToDelete = sc.nextLine();
	}

	private static void outputVocaBook() {
		Set<String> words = vocaBook.keySet();

		for (String word : words) {
			List<String> meanings = vocaBook.get(word);
			for (String meaning : meanings) {
				System.out.print(" " + meaning);
			}
			System.out.println();
		}

		for (Map.Entry<String, ArrayList<String>> entry : vocaBook.entrySet()) {
			String word = entry.getKey();
			ArrayList<String> meanings = entry.getValue();

			System.out.println(word + ": " + meanings);
		}
	}
}
