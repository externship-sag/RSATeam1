package resumeScreenApp;

import fileManager.RSAFileManager;
import fileManager.RSAFileUtils;

import java.io.File;

import criteriaManager.RSAConfigParser;
import criteriaManager.RSACriteriaManager;
import criteriaManager.RSAOutputWriter;

public class RSAManager
{
	private File filesList[];
	RSAConfigParser rsaconfig;
	/*
	 * Constructor method that works with the data folders and read the 
	 * input configuration file, build search criteria information 
	 * before screening the resume files.
	 */
	public RSAManager()
	{	
		//Clear the output folder, if exists already or create one.
		RSAOutputWriter.createOutputFile();
		
		//Parse the input config file and build criteria data list.
		rsaconfig = new RSAConfigParser();
		if(rsaconfig.getcriteriaCnt() == 0){
			System.err.println("Failed to build criteria");
		}
		//Check whether the resume folder has resume files.
		try {
			File directoryPath = new File(RSAFileUtils.getFolderPath("/data/resumes"));
			if (!directoryPath.exists()){
				System.err.println("Failed to locate the resume folder");
				java.lang.System.exit(1);
			}
			filesList = directoryPath.listFiles();
		}
		catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("Failed to locate the resume folder, check your system!!");
        }
		
		
	}
	/*
	 * This method pick each resume file from the input folder
	 * and read the file data using file manager module and pass the
	 * file content to the criteria manager module to search/match 
	 * the criteria data.
	 */
	public boolean analyzeFolderPath()
	{
		if(filesList.length == 0) {
			System.err.println("No Files in the resume folder");
			return false;
		}
		for(File file : filesList) {			
			
			RSAFileManager fileManager = new RSAFileManager(file);
			if(!fileManager.getFileData().isBlank())
			{
				RSACriteriaManager rsaCriteria = new RSACriteriaManager();
				rsaCriteria.CheckCriteriaEligibilty(rsaconfig.getcriteriaList(),fileManager);
			}
			else
				System.out.println("Skipped File :"+file.getName());
		}
		return true;
	}

}
		
