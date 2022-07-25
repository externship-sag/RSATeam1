package criteriaManager;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import debugprint.rsaDebug;

public class ExperienceCriteria implements RSACriteriaList{

	private ArrayList<String> ValueList;
	private int ExperienceValue = 0;
	private int searchStatus = 0;
	
	public ExperienceCriteria() {
		ValueList = new ArrayList<String>();
	}
	
	public ArrayList<String> getValueList() {
		return ValueList;
	}

	public void setValueList(String skillValue) {
		ValueList.add(skillValue);
	}
	
	public void resetSearchStatus() {
		searchStatus = 0;
	}
	
	public int getSearchStatus() {
		return searchStatus;
	}
	
	public String getTagValue() {
		return "Experience";
	}

	private String searchExp(String fileData, String regexStr)
	{
		Pattern tok_pattern = Pattern.compile(regexStr,Pattern.CASE_INSENSITIVE);
		Matcher tok_matcher = tok_pattern.matcher(fileData);

		while (tok_matcher.find())
		{
			Pattern year_pattern = Pattern.compile("\\d+(\\.\\d+)?");
		    Matcher year_matcher = year_pattern.matcher(tok_matcher.group().toString());
		    while (year_matcher.find())
		    {
		    	ExperienceValue = (int) Float.parseFloat(year_matcher.group().toString());
				rsaDebug.print("Experience:"+ExperienceValue);
				if(isExperienceEligible())
					++searchStatus;
				return(year_matcher.group().toString());
			}
		}
		return("**");
	}

	public String searchCriteria(String fileData)
	{
		String outStr = searchExp(fileData,"(?<=experience)(.*)(?=years)");
		if(outStr.contains("**"))
		{
			rsaDebug.print("Retrying with another pattern.........");
			outStr = searchExp(fileData,"(.*)(?<=years)");
		}
		return outStr;
	}
	
	private boolean isExperienceEligible()
	{
		rsaDebug.print("Experience:"+ExperienceValue);
		int minRange = Integer.parseInt(ValueList.get(0));
		int maxRange = Integer.parseInt(ValueList.get(1));
		if (ValueRange.of(minRange, maxRange).isValidIntValue(ExperienceValue)) 
			return true;
		else 
			return false;
	}
}
