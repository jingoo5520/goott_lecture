package webjingoo.vocabularybook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VocabularyBook {
	private Map<String, Set<String>> words; 

	public VocabularyBook () {
		this.words = new HashMap<String, Set<String>>();
	}
	
	// 단어 추가
	public void addWord() {
		String word;
		Set<String> meanings = new HashSet<String>();

		Scanner sc = new Scanner(System.in);

		System.out.print("영어단어 입력 >> ");
		word = sc.nextLine();
		System.out.print("뜻 입력(복수개인 경우 공백으로 구분) >> ");
		String tempMeanings = sc.nextLine();

		for (String meaning : tempMeanings.toLowerCase().split(" ")) {
			meanings.add(meaning);
		}

		// 중복 단어가 추가되는 경우 뜻 추가
		if (words.containsKey(word)) {

			Set existMeanings = words.get(word);

			existMeanings.addAll(meanings);

			System.out.println(word + "에 새로운 뜻 " + meanings + " 추가 완료");

			words.replace(word, existMeanings);
			System.out.print("단어 수정 완료: ");
			
		} else {
			words.put(word, meanings);
			System.out.print("단어 추가 완료: ");
		}

		System.out.println(word + " " + words.get(word));
	}

	// 단어 삭제
	public void deleteWord() {
		String word;
		Scanner sc = new Scanner(System.in);

		System.out.print("영어단어 입력 >> ");
		word = sc.nextLine();
		
		if(words.containsKey(word)) {
			System.out.println("삭제 완료: " + word + " " + words.get(word));
			words.remove(word);
		} else {
			System.out.println(word + " 이(가) 존재하지 않습니다.");
		}
		
	}

	// 모두 보기
	public void showWords() {
//		Set wordsSet = words.entrySet();
		Iterator iter = words.entrySet().iterator();

		System.out.println("------------------------------------단어목록------------------------------------");
		while (iter.hasNext()) {
			Map.Entry<String, Set<String>> es = (Map.Entry) iter.next();
			System.out.println(es.getKey() + " " + es.getValue());
		}
	}
	
	// 영어단어로 검색
	public void searchByWord() {
		Scanner sc = new Scanner(System.in);
		String word;

		System.out.print("검색 단어 입력 >> ");
		word = sc.nextLine();
		
		if(words.containsKey(word)) {
			System.out.println("검색 완료: " + word + " " + words.get(word));
		} else {
			System.out.println(word + " 이(가) 존재하지 않습니다.");
		}
	}
	
	// 뜻으로 검색
	public void searchByMeaning() {
		Scanner sc = new Scanner(System.in);
		String meaning;
		
		Map<String, Set<String>> searchedWords = new HashMap<String, Set<String>>();
		
		
		System.out.print("검색 뜻 입력 >> ");
		meaning = sc.nextLine();
		
		Set<String> keys = words.keySet();
		
		int count = 0;
		
		for(String key : keys) {
			Set meanings =  words.get(key);
			
			if(meanings.contains(meaning)) {
				searchedWords.put(key, words.get(key));
				
				count++;
			}
		}
		
		Set<String> searchedKeys = searchedWords.keySet();
		
		if(count != 0) {
			System.out.println("검색 완료------------------------------------------------------------------------");
			for(String searchedKey : searchedKeys) {
				System.out.println(searchedKey + " " + searchedWords.get(searchedKey));
			}
		} else {
			System.out.println(meaning + " 에 대한 검색 결과가 없습니다.");
		}
	}
}
