import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;

public class FileHandler {

	private URL link;
	private String toLocation;
	
	public FileHandler(String link, String toLocation) {
		try {
			this.link = new URL(link);
			this.toLocation = toLocation;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void download() throws MalformedURLException, IOException {
		try(ReadableByteChannel in = Channels.newChannel(link.openStream());
			@SuppressWarnings("resource")
			FileChannel out = new FileOutputStream(toLocation).getChannel() ) {
					out.transferFrom(in, 0, Long.MAX_VALUE);
					//out.close();
				}			
	}
	
	public String getPDFText() {
		try {
			String pdfFilePath = toLocation;
			Document pdf = PDF.open(pdfFilePath);
			StringBuilder text = new StringBuilder(1024);
			pdf.pipe(new OutputTarget(text));
			pdf.close();
			return text.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
