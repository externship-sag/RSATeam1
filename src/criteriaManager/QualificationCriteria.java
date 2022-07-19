package criteriaManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import debugprint.rsaDebug;

public class QualificationCriteria implements RSACriteriaList{

	private ArrayList<String> ValueList;
	private int searchStatus = 0;
	
	public QualificationCriteria() {
		ValueList = new ArrayList<String>();
	}
	
	public ArrayList<String> getValueList() {
		return ValueList;
	}

	public void setValueList(String skillValue) {
		ValueList.add(skillValue);
	}
	public String getTagValue() {
		return "Qualification";
	}
	public void resetSearchStatus() {
		searchStatus = 0;
	}
	public int getSearchStatus() {
		return searchStatus;
	}
	public String searchCriteria(String fileData)
	{
		for(String keyValue : ValueList)
		{
			String strRegex = keyValue;
	        Pattern pattern = Pattern.compile(strRegex);
	        Matcher matcher = pattern.matcher(fileData);
	        rsaDebug.print(keyValue+"::"+strRegex);
	        while(matcher.find()){
	        	++searchStatus;
	            rsaDebug.print("Found at " + matcher.group());
	            return keyValue;
	        }
		}
		return "****";
	}
}
