package fileManager;

import java.io.File;

import debugprint.rsaDebug;

public class RSAFileManager {
	
	private String fileData;
	private String fileName;
	
	public RSAFileManager(File file) {
		fetchFileData(file);
	}
	/*
	 * For each resume file, based on the file extension type
	 * corresponding file read methods will be invoked and store the
	 * file content in the private buffer variable:fileData.
	 */
	private void fetchFileData(File fileptr)
	{
		rsaDebug.print("*****************************");
		if(!RSAFileUtils.validateFile(fileptr)){
			fileData = "";
			return;
		}
        switch(getFileExtension(fileptr)) {
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
	}
	/*
	 * Local Method to identify the file extension type.
	 */
    private String getFileExtension(File fileptr) {
    	fileName = fileptr.getName();
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
    /*
     * Method to fetch the candidate name from the file name.
     */
    public String getFileName()
    {
    	 try {
             return fileName.substring(0,fileName.lastIndexOf("."));
         } catch (Exception e) {
             return "";
         }
    }
    /*
     * Get Method to read the private member: fileData
     */
    public String getFileData()
    {
    	return this.fileData;
    }
}
