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
	
	public RSAManager()
	{	
		RSAOutputWriter.createOutputFile();
		rsaconfig = new RSAConfigParser();
		if(rsaconfig.getcriteriaCnt() == 0){
			System.err.println("Failed to build criteria");
		}
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
		
