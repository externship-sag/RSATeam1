package criteriaManager;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

import debugprint.rsaDebug;
import fileManager.RSAFileUtils;

public class RSAOutputWriter {
	static File Path;
	
	public static void createOutputFile()
	{
		try {
			Path path = Paths.get(RSAFileUtils.getFolderPath("/data/output")+"/Result.csv");
			if(Files.exists(path)) {
				rsaDebug.print("Clearing output folder");
				Files.delete(path);
				Files.createFile(path);
				String[] ouputHeader = {"Name","Skill","Qualification","Experience","Eligible"};
				updateOutput(ouputHeader);
			}
		}
		catch (Exception e) {
	        System.err.println(e.getMessage());
	        System.err.println("Failed to locate the resume folder, check your system!!");
	    }
	}
	
	public static void updateOutput(String[] displayStr)
	{
		try {
			FileWriter outputfile = new FileWriter(new 
					 File(RSAFileUtils.getFolderPath("/data/output")+"/Result.csv"),true);
			 
			CSVWriter writer = new CSVWriter(outputfile);
			  
			writer.writeNext(displayStr);
			writer.close();
		}
		catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	

}
