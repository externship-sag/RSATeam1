package fileManager;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PdfFileReader implements RSAFileReader {
	
	public String getFileData(File file) { 
        String text = "";
        System.out.println("Reading PDF File::"+file.getName());
        try {
            PDDocument document = PDDocument.load(file);
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripper textStripper = new PDFTextStripper();
                text = textStripper.getText(document).trim();
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
	
}
