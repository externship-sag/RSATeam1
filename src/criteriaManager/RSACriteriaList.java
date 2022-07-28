package criteriaManager;

import java.util.ArrayList;
/*
 * Interface class to extend implement criteria types.
 */
public interface RSACriteriaList{	
	public ArrayList<String> getValueList();
	public void setValueList(String ValueList);
	public String getTagValue();
	public int getSearchStatus();
	public String searchCriteria(String fileData);
	public void resetSearchStatus();
}
