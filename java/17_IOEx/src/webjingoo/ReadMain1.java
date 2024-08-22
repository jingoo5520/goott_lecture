package webjingoo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadMain1 {

	public static void main(String[] args) {
		
		InputStream is = null;
		
		try  {
			
			is = new FileInputStream("D:\\lecture\\java\\17_IOEx\\temp\\test.dat");
	
//			int data = is.read();
//			System.out.println(data);
//			
//			int data2 = is.read();
//			System.out.println(data);
//			
//			int data3 = is.read();
//			System.out.println(data);
			
			while(true) {
				int data = is.read();
				if(data == -1) break;
				System.out.println(data);
			}
		}		
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}
