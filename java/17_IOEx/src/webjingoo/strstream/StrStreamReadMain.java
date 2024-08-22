package webjingoo.strstream;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class StrStreamReadMain {

	public static void main(String[] args) {
		try {
			InputStream is = new FileInputStream("D:\\lecture\\java\\17_IOEx\\temp\\stringtest.dat");
			
			Reader reader = new InputStreamReader(is);
			
			while(true) {
				int data = reader.read();
				
				if(data == -1) break;
				System.out.println((char)data);
			}
			
			reader.close();
			
//			Reader reader1 = new FileReader("D:\\lecture\\java\\17_IOEx\\temp\\stringtest.dat");
//			char[] data = new char[50];
//			
//			while(true) {
//				int num = reader.read(data);
//				
//				
//				if(num == -1) {
//					break;
//				}
//				
//				for()
//			}
			
			
			
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
