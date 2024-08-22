package webjingoo.hashmapex;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapMain {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		map.put(1, "홍길동");
		map.put(2, "홍길서");
		map.put(3, "홍길남");
		map.put(4, "홍길북");
		map.put(4, "둘리"); // 중복된 키 삽입시 데이터 덮어쓰기, 중복 에러 따로 없음
		
		System.out.println(map);
		
		// 키를 포함하고 있는지 확인
		if(map.containsKey(4)) {
			// 키에 맞는 값 가져오기
			System.out.println(map.get(4));
		} else {
			System.out.println("없는 키 입니다.");
		}
		
		// Map의 모든 키를 가져와서 Set으로 반환
		Set<Integer> keys = map.keySet();
		
		for(Integer key : keys) {
			System.out.println(key);
		}
		
		
		// Map에 있는 모든 값을 가져와서 Collection 타입으로 반환
		Collection<String> values = map.values();
		
		// 에러 발생 (Collection > List) 
		// List<String> listValues = map.values();
		
		for(String value : values) {
			System.out.println(value);
		}
		
		// Map에 요소가 없냐?
		System.out.println(map.isEmpty());
		
		// Map에 해당 value가 있냐?
		System.out.println(map.containsValue("홍길동"));
		
		
		// Map에 key가 있다면 value값 반환
		// key가 없다면 defalut값 반환
		System.out.println(map.getOrDefault(4, "또치")); // "둘리"
		System.out.println(map.getOrDefault(5, "또치")); // "또치"
		
		// Ma에 key가 없으면 추가, 있으면 덮어씌움
		// 없는 경우 null 반환 후 해당 데이터 저장
		// 있는 경우 value 값 반환
		System.out.println(map.putIfAbsent(5, "또치")); 
		System.out.println(map.putIfAbsent(5, "또치"));		
		
		// Map 키에 대한 값 수정
		map.replace(5, "물고기"); // key(5) 에 대한 value 수정
		System.out.println(map);
		
		// Map 데이터 삭제
		map.put(9, "희동");
		System.out.println(map);
		
		// Key로 삭제
		map.remove(9);
		System.out.println(map);
		
		// Map 데이터 개수 확인 
		System.out.println(map.size());
		
		
		// ex) 
		// 맵생성 key = 학번, value = 성적
		// 5명 저장
		// 총 학생 목록 출력
		// 총점, 평균 출력
		
		System.out.println("연습문제ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		Map<String, Integer> students = new HashMap<String, Integer>();
		int total = 0;
		double avg;
		
		students.put("0001", 95);
		students.put("0002", 90);
		students.put("0003", 82);
		students.put("0004", 100);
		students.put("0005", 78);
		
		Set stuNoes = students.entrySet();
		System.out.println(stuNoes);
		
		Iterator iter = stuNoes.iterator();
		
		while(iter.hasNext()) {
			Map.Entry<String, Integer> es = (Map.Entry) iter.next();
			System.out.println("학번: " + es.getKey() + ", 성적: " + es.getValue());
		}
		
		
		Collection<Integer> scores = students.values();
		Iterator iter1 = scores.iterator();
		
		while(iter1.hasNext()) {
			total += (Integer) iter1.next();
		}
		
		avg = (double) total / students.size();
		
		System.out.println("total: " + total);
		System.out.println("avg: " + avg);
	}

}
