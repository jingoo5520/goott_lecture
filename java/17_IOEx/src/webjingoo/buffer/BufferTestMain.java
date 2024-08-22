package webjingoo.buffer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferTestMain {

	public static void main(String[] args) throws IOException {
		// 기존 경로
		String originalFilePath = "D:\\lecture\\java\\17_IOEx\\temp\\tiger.jpg";
		
		// 복사할 경로
		String copyFilePath = "D:\\lecture\\java\\17_IOEx\\temp\\copied\\tiger.jpg";
		
		String originalFilePath2 = "D:\\lecture\\java\\17_IOEx\\temp\\tiger.jpg";
		String copyFilePath2 = "D:\\lecture\\java\\17_IOEx\\temp\\copied\\tiger2.jpg";
		
		FileInputStream fis = new FileInputStream(originalFilePath);
		FileOutputStream fos = new FileOutputStream(copyFilePath);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		// 입출력스트림 생성 + 버퍼스트림
		
		long start = System.nanoTime();
		
		while(true) {
			int data = fis.read();
			if(data == -1) {
				break;
			}
			fos.write(data);
		}
		
		long end = System.nanoTime();
		
		System.out.println(end - start);
	}

}
