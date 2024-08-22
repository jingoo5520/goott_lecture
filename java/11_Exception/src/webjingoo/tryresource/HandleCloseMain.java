package webjingoo.tryresource;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HandleCloseMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// D:\lecture\java\11_Exception\src\webjingoo\tryresource\score.dat
//		FileInputStream fis = null;
//		
//		try {
//			fis = new FileInputStream("D:\\lecture\\java\\11_Exception\\src\\webjingoo\\tryresource\\score.dat");
//			
//		System.out.println(fis);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			System.out.println("파일이 없습니다.");
//		} finally {
//			DataInputStream dis = new DataInputStream(fis);
//			try {
//				dis.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		// try-with-resouce
		// AutoCloseble 인터페이스를 구현한 것이라면, 자동으로 close() 호출.
		try (FileInputStream fis = new FileInputStream("D:\\lecture\\java\\11_Exception\\src\\webjingoo\\tryresource\\score.dat");
				DataInputStream dis = new DataInputStream(fis)) {
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
