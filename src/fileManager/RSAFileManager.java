package fileManager;

import java.io.File;

import debugprint.rsaDebug;

public class RSAFileManager {
	
	private String fileData;
	private String fileName;
	
	public RSAFileManager(File file) {
		fetchFileData(getFileExtension(file),file);
	}
	private String fetchFileData(String ftype, File fileptr)
	{
		rsaDebug.print("*****************************");
		if(!RSAFileUtils.validateFile(fileptr))
			return fileData = "";
        switch(ftype) {
        case "txt":
        	fileData = (new TxtFileReader()).getFileData(fileptr);
        	break;
        case "pdf":
        	fileData = (new PdfFileReader()).getFileData(fileptr);
        	break;
        case "doc":
        	fileData = (new DocFileReader()).getFileData(fileptr);
        	break;
        case "docx":
        	fileData = (new DocxFileReader()).getFileData(fileptr);
        	break;	
        default:
        	System.err.println("Unsupported file::"+fileptr.getName());
        	fileData = "";
        	break;
        }       
        return fileData;
	}
	
    private String getFileExtension(File fileptr) {
    	fileName = fileptr.getName();
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getFileName()
    {
    	 try {
             return fileName.substring(0,fileName.lastIndexOf("."));
         } catch (Exception e) {
             return "";
         }
    }
    
    public String getFileData()
    {
    	return this.fileData;
    }
}
