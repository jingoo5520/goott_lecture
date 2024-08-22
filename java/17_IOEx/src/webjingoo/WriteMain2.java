package webjingoo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteMain2 {

	public static void main(String[] args) {
		try (OutputStream os = new FileOutputStream("D:\\lecture\\java\\17_IOEx\\temp\\test2.dat")) {
			
			byte[] array = {10, 20, 30, 40, 50};
			
			os.write(array);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
