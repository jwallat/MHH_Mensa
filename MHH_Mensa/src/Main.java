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

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		try(
		ReadableByteChannel in=Channels.newChannel(
				new URL("https://www.mh-hannover.de/fileadmin/organisation/logistik_gbIV/zentralkueche/downloads/speisepl_akt.pdf").openStream());
				FileChannel out=new FileOutputStream("C:\\Users\\jwall\\Downloads\\Speiseplan.pdf").getChannel() ) {
					out.transferFrom(in, 0, Long.MAX_VALUE);
				}
		
		try {
			String pdfFilePath = "C:\\Users\\jwall\\Downloads\\Speiseplan.pdf";
			Document pdf = PDF.open(pdfFilePath);
			StringBuilder text = new StringBuilder(1024);
			pdf.pipe(new OutputTarget(text));
			pdf.close();
			System.out.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
