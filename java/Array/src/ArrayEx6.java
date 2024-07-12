import java.util.Arrays;

public class ArrayEx6 {

	public static void main(String[] args) {
		// 로또 추첨기
		// 1~45 사이의 정수를 6개 뽑아서 int 배열에 넣는다
		// 1 ~ 45가 들어있는 배열 생성
		// index번호를 통해 6번 랜덤으로 추출
		// 추출된 index의 원소값은 0으로 변경? 배열자르기?

		int[] lottoNumbers = new int[6];		
		
		int count = 0;
		while(count < 6) {
			int num = (int) (Math.random() * 45); // 1 ~ 45 숫자
			
			boolean isExist = false;
			for(int i = 0; i < lottoNumbers.length; i++) {
				if(num == lottoNumbers[i]) {
					isExist = true;
				}
			}
			
			if(!isExist) {
				lottoNumbers[count] = num;
				count++;
			}
			
		}
		
		System.out.println(Arrays.toString(lottoNumbers));
		
		// 1 ~ 45가 들어있는 배열 생성 방법?
		
	}
	
	

}
