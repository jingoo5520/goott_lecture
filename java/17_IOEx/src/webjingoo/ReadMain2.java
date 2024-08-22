package webjingoo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ReadMain2 {

	public static void main(String[] args) {
		try (InputStream is = new FileInputStream("D:\\lecture\\java\\17_IOEx\\temp\\test2.dat")) {

//			byte[] data = new byte[3];
//			
//			int num = is.read(data, 0, 3);
//			System.out.println(num);
//			System.out.println(Arrays.toString(data));
			
			byte[] data = new byte[50];
			
			while(true) {
				int num = is.read(data);
				
				if(num == -1) {
					break;
				}
				
				System.out.println(num);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
