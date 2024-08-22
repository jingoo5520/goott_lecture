package webjingoo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteMain1 {

	public static void main(String[] args) {
		byte a = 10;
		byte b = 20;
		byte c = 30;
		
		try  {
			
			OutputStream os = new FileOutputStream("D:\\lecture\\java\\17_IOEx\\temp\\test.dat");
			
			os.write(a);
			os.write(b);
			os.write(c);
			
//			os.flush();
	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
