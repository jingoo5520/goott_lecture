package webjingoo.vocabularybook;

import java.util.Scanner;

public class VocabularyBookMain {
	public static void main(String[] args) {
		// 암기장 구현(Map 이용)
		// 영어단어 등록
		// 영어단어의 의미 등록(하나의 단어에 여러개의 뜻이 있을 수 있음)
		// 영어 단어로 뜻을 검색
		// 뜻으로 단어 검색
		// 영어 단어 삭제 가능

		VocabularyBook vocaBook = new VocabularyBook();
		Scanner sc = new Scanner(System.in);

		while (true) {
			showMenu();
			int num = sc.nextInt();

			switch (num) {
			case 1:
				vocaBook.addWord();
				break;
			case 2:
				vocaBook.deleteWord();
				break;
			case 3:
				vocaBook.searchByWord();
				break;
			case 4:
				vocaBook.searchByMeaning();
				break;
			case 5:
				vocaBook.showWords();
				break;
			case 9:
				System.out.println("종료합니다..");
				System.exit(0);
			default:
				System.out.println("다시 입력해주세요.");
			}

		}

	}

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
}
