import java.util.Arrays;

public class MathArrUtils {

	static int sum(int[] arr) {
		int sum = 0;
		
		for(int element : arr) {
			sum += element;
		}
		
		return sum;
	}
	
	static double average(int[] arr) {
		return (double)sum(arr) / arr.length;
	}
	
	static int max(int[] arr) {
		int max = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}
	
	static int min(int[] arr) {
		int min = arr[0];
		
		for(int i = 1; i < arr.length; i++) {
			if(min > arr[i]) {
				min = arr[i];
			}
		}
		return min;
	}
	
	static int[] sort(int[] arr) {
		Arrays.sort(arr);
		return arr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 3, 5, 9, -3, 0 };
		
		System.out.println(sum(arr));
		System.out.println(MathArrUtils.average(arr));
		System.out.println(MathArrUtils.max(arr));
		System.out.println(MathArrUtils.min(arr));
		System.out.println(Arrays.toString(MathArrUtils.sort(arr)));
		
	}

}
