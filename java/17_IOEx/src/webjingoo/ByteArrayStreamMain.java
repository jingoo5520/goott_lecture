package webjingoo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ByteArrayStreamMain {

	public static void main(String[] args) {
		byte[] inScr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // 원본
		byte[] outSrc = null; // 목적지
		
		// 입출력 스트림 객체 생성
		InputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inScr);
		output = new ByteArrayOutputStream();
		
		int data = 0;
		
		try {
			while ((data = input.read()) != -1) {
				System.out.println(data);
				output.write(data);
			}
			
		} catch (IOException e) {
			
		}
		
		//output = output.toByteArray();
		
		System.out.println(Arrays.toString(outSrc));
	}

}
