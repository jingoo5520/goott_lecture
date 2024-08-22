package webjingoo.exceptionex;

public class ExceptionEx1Main {

	public static void main(String[] args) {
		try {
			install();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("설치실패");
		} 

	}

	

	private static void install() throws InstallException, MemoryException {
		try {
			startInstall();
			copyFiles();
		} catch (SpaceException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("예외 발생: " + e.getMessage());
			System.out.println("공간을 확보한 후에 설치해주세요...");
			InstallException ie = new InstallException("설치중 예외 발생");
			ie.initCause(e); // 원인예외
			ie.getCause();
			throw ie;
		} catch (MemoryException e) {
			System.out.println("예외 발생: " + e.getMessage());
			System.out.println("메모리를 확보한 후에 설치해주세요...");
			InstallException ie = new InstallException("설치중 예외 발생");
			ie.initCause(e); // 원인예외
			ie.getCause();
			throw ie;
		}finally {
			deleteTmpFiles();
		}
	}

	

	private static void startInstall() throws SpaceException, MemoryException {
		System.out.println("설치를 시작합니다...");
		// 만약 설치공간이 없다면, 설치공간 확인 메서드 = false 인 경우
		if (!calSpace()) {
			// 예외 발생
			throw new SpaceException("디스크공간이 부족합니다.");
		}
		
		if(!carMemory()) {
//			throw new MemoryException("메모리가 부족합니다.");
			throw new RuntimeException(new MemoryException("메모리가 부족합니다."));
		}
	}

	

	private static boolean calSpace() {
		// 설치공간 충분: true, 불충분: false
		return false;
	}
	
	private static boolean carMemory() {
		return false;
	}
	
	private static void copyFiles() {
		System.out.println("파일 복사 시작합니다...");
	}
	
	private static void deleteTmpFiles() {
		System.out.println("임시파일을 삭제합니다...");
	}

}

class SpaceException extends Exception {
	SpaceException(String msg) {
		super(msg);
	}
}

class InstallException extends Exception {
	InstallException(String msg) {
		super(msg);
	}
}

class MemoryException extends Exception {
	MemoryException(String msg){
		super(msg);
	}
}
