package fileManager;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocFileReader implements RSAFileReader {
	/*
	 * Read method for .doc file.
	 */
	public String getFileData(File file) {
		 System.out.println("Reading Doc File::"+file.getName());
		 String text = "";
	        try {
	            FileInputStream fInput= new FileInputStream(file);
	            HWPFDocument document = new HWPFDocument(fInput);
	            WordExtractor wordExtractor = new WordExtractor(document);
	            text = wordExtractor.getText();
	            fInput.close();
	            wordExtractor.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return text;
	}
	
}