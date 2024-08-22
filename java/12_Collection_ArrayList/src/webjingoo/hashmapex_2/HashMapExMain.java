package webjingoo.hashmapex_2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HashMapExMain {

	public static void main(String[] args) {
		String words = "Hash table based implementation of the Map interface based Map Hash Map";
		
		
		String[] strArr = words.toLowerCase().split(" ");
		System.out.println(Arrays.toString(strArr));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(String str : strArr) {
			if(map.containsKey(str)) {
				int value = map.get(str);
				map.put(str, ++value);
			} else {
				map.put(str, 1);
			}
		}
		
		Set strSet = map.entrySet();
		Iterator iter = strSet.iterator();
		
		while(iter.hasNext()) {
			Map.Entry<String, Integer> es = (Map.Entry) iter.next();
			System.out.println(es.getKey() + ": " + es.getValue());
		}
		
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		TreeMap<String, Integer> tmap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
		tmap.putAll(map);
		
		Iterator iter2 = tmap.entrySet().iterator();
		
		
		while(iter2.hasNext()) {
			Map.Entry<String, Integer> es = (Map.Entry )iter2.next();
			System.out.println(es.getKey() + ": " + es.getValue());
			
		}
		
		
		
	}

}
