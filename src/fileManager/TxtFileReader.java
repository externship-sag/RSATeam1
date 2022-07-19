package fileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TxtFileReader implements RSAFileReader{
	
    public String getFileData(File file) {
    	
    	StringBuilder text = new StringBuilder();
    	String fileData;
    	if(!file.canRead()) {
    		System.err.println("Cannot read the file");
    		return "";
    	}
    	System.out.println("Reading text File::"+file.getName());
    	
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"))) 
		{
			String line;
			while ((line = br.readLine()) != null) 
			{
				text.append(line).append(System.getProperty("line.separator"));
			}
			fileData = new String (text.toString());
			
		} 
		catch (FileNotFoundException exception) 
		{
			System.err.println("Caught Exception"+ exception);
			exception.printStackTrace();
			return "";
		}
		catch (IOException exception) 
		{
			System.err.println("Caught Exception"+ exception);
			exception.printStackTrace();
			return "";
		}
		catch(OutOfMemoryError error)
		{
			System.err.println("Large File Size!!Error Caught"+ error);
			return "";
		}
		return fileData;
    }
}
