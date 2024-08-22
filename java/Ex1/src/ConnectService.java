public class ConnectService {
	private static ConnectService instance = new ConnectService();

	private ConnectService() {
		
	}
	
	public static ConnectService getInstance() {
		return instance;
	}

}
