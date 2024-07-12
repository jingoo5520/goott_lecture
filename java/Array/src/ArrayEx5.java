import java.util.Scanner;

public class ArrayEx5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// int 배열에 1 ~ 100의 난수 20개를 넣고
		// 유저에게 숫자를 입력받아 배열에서 해당 숫자를 찾고
		// 몇 번째 위치에 있는지 탐색
		// 찾으시는 값은 n 번 째에 있습니다. 출력

		int[] array = new int[20];

		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 100 + 1);
			if(i == 0) {
				System.out.print("[" + array[i] + ", ");
			} else if (i == array.length - 1) {
				System.out.println(array[i] + "]");
			} else {
				System.out.print(array[i] + ", ");
			}
			
		}
		
		System.out.print("숫자 입력 >> ");
		int number = sc.nextInt();
		boolean isFind = false;
		
		for (int i = 0; i < array.length; i++) {
			if(array[i] == number) {
				isFind = true;
				System.out.println("찾으시는 값은 " + (i + 1) + " 번째에 있습니다.");
			}
		}
		
		if(!isFind) {
			System.out.println("찾으시는 값이 없습니다.");
		}
		
		sc.close();
	}

}
