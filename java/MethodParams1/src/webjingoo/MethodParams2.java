package webjingoo;

import java.util.Arrays;

public class MethodParams2 {

	public static void main(String[] args) {
		Data dataA = new Data();
		// 객체는 참조타입이기 때문에 value에 0이 초기화됨
		System.out.println(dataA.value);

		dataA.value = 10;
		System.out.println("메서드 호출 전: " + dataA.value);
		
		callPrimitiveParams(dataA);
		
		System.out.println("메서드 호출 후: " + dataA.value);
		
		int[] aArr = {10};
		System.out.println(Arrays.toString(aArr));
		
		changeParams(aArr);
		
		System.out.println(Arrays.toString(aArr));
		
	}
	
	static void callPrimitiveParams(Data dataX) {
		dataX.value = 20;
	}
	
	static void changeParams(int[] x) {
		x[0] = 100;
	}

}
