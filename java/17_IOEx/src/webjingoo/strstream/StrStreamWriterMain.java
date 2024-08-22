package webjingoo.strstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class StrStreamWriterMain {

	public static void main(String[] args) {
		try {
			OutputStream os = new FileOutputStream("D:\\lecture\\java\\17_IOEx\\temp\\stringtest.dat");
			Writer writer = new OutputStreamWriter(os);
			
//			Writer writer = new FileWriter("D:\\lecture\\java\\17_IOEx\\temp\\stringtest.dat");
			
			
			
			char a = 'A';
			char b = 'B';
			char c = 'C';
			
			writer.write(a);
			writer.write(b);
			writer.write(c);
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
