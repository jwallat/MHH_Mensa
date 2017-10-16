import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		
		String link = "https://www.mh-hannover.de/fileadmin/organisation/logistik_gbIV/zentralkueche/downloads/speisepl_akt.pdf";
		String fileLocation = "C:\\tmp\\Speiseplan.pdf";
		FileHandler fileHandler = new FileHandler(link, fileLocation);
		
		fileHandler.download();
		
		TextHandler textHandler = new TextHandler(fileHandler.getPDFText());
		textHandler.cleanText();
		textHandler.printMeals();
	}
}
