package fileManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import debugprint.rsaDebug;

public class RSAFileUtils
{
	public static final int fileMaxSize = 2097152;//2MB
	public static String getFolderPath(String folderName)
	{
		StringBuilder folderPath;  
		Path currentRelativePath = Paths.get("");
		folderPath = new StringBuilder(currentRelativePath.toAbsolutePath().toString());
		folderPath.append(folderName);
		rsaDebug.print("Current Folder path is: " + folderPath);
		return folderPath.toString();
	}
	
    public static boolean validateFile(File file)
    {
    	if((file.length()>0 && file.length()<fileMaxSize)  || file.canRead())
    		return true;
    	else
    		return false;
    }
	
}