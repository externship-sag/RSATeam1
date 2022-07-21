package fileManager;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;


public class DocxFileReader implements RSAFileReader {

	public String getFileData(File file) {
	System.out.println("Reading Docx File::"+file.getName());
        String text = "";
        try {
            FileInputStream fInput = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(fInput);
            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
            text = wordExtractor.getText();
            fInput.close();
            wordExtractor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}
