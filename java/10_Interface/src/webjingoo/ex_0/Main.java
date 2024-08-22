package webjingoo.ex_0;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParseService service = new ParseService();
		
		service.processParse("data.xml");
		service.processParse("data.json");
		service.processParse("data.yaml");
		
		
	}

}
