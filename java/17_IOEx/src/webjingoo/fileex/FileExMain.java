package webjingoo.fileex;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FileExMain {

	public static void main(String[] args) {
		File curDirectory = new File(".");
		
		File[] files = curDirectory.listFiles();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		DecimalFormat df = new DecimalFormat("#,###");
		
		System.out.println(files[1].isFile());
		
		long totalSize = 0;
		int fileCount = 0;
		int dirCount = 2;
		String isFile;
		
		for(File file : files) {
			String modifiedDate = sdf.format(file.lastModified());
			
			
			if(file.isFile()) {
				fileCount++;
				isFile = " ";
			} else {
				dirCount++;
//				System.out.println(file.getFreeSpace());
				isFile = "<DIR>";
			}
			
			long fileSize = file.length();
			totalSize += fileSize;
			 
//			System.out.println(curDirectory.getFreeSpace());
			
			System.out.println(modifiedDate + "\t" + isFile + "\t" + fileSize + "\t" + file);
		}
		
		System.out.println("\t\t" + fileCount + "개 파일\t\t" + totalSize + "바이트" );
		System.out.println("\t\t" + dirCount + "개 디렉터리\t\t");
		System.out.println(curDirectory.getFreeSpace());
		
	}

}
