package webjingoo.ex_0;

public class ParseService {

	public void processParse(String fileName) {
		Parser parser = null;

		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);

		
		switch (ext) {
		case "json":
			parser = new JsonParser();
			break;
		case "xml":
			parser = new XMLParser();
			break;
		default:
			parser = null;
		}


		if(parser != null) {
			parser.parse();
		}
		
	}
}
