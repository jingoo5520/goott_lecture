import java.util.Arrays;

public class ArrayEx7 {

	public static void main(String[] args) {
		// 1~9 까지의 수를 shuffle 한다.
		// 버블 정렬 -> 오름차순 정렬

		int[] arr = new int[9];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}

		for (int i = 0; i < arr.length; i++) {
			int randumIndex = (int) (Math.random() * 9);

			int temp = arr[i];
			arr[i] = arr[randumIndex];
			arr[randumIndex] = temp;
		}

		System.out.println(Arrays.toString(arr));

		// 버블 정렬
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.println(i + 1 + "번째 정렬시작");
			boolean isSwapped = false;
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					isSwapped = true;
				}
			}
			
			// 정렬이 다 된 경우 더이상 정렬을 안한다.
			if(!isSwapped) {
				break;
			}
			
			System.out.println(Arrays.toString(arr));
		}
		
//		System.out.println(Arrays.toString(arr));
	}
}


