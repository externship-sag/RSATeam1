package criteriaManager;

import java.util.ArrayList;

import debugprint.rsaDebug;
import fileManager.RSAFileManager;

public class RSACriteriaManager{

	private ArrayList<String> outputString;
	
	public RSACriteriaManager() {
		outputString = new ArrayList<>();
	}
	
	public ArrayList<String> getOutputString() {
		return outputString;
	}
	public void setOutputString(String outString) {
		outputString.add(outString);
	}
	
	public void CheckCriteriaEligibilty(ArrayList<RSACriteriaList> criteriaList, RSAFileManager fileManager)
	{
		int eligibleCnt = 0;
		
		setOutputString(fileManager.getFileName());
		
		for(RSACriteriaList list: criteriaList) {
			list.resetSearchStatus();
			setOutputString(list.searchCriteria(fileManager.getFileData()));
			eligibleCnt += list.getSearchStatus();
		}
		
		if(eligibleCnt == criteriaList.size())
			setOutputString("yes");
	    else
	    	setOutputString("no");
		rsaDebug.print("print lists:"+criteriaList);
		
		String[] outStr = outputString.toArray(new String[0]);
		RSAOutputWriter.updateOutput(outStr);

	}

}
