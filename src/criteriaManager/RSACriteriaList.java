package criteriaManager;

import java.util.ArrayList;

public interface RSACriteriaList{	
	public ArrayList<String> getValueList();
	public void setValueList(String ValueList);
	public String getTagValue();
	public int getSearchStatus();
	public String searchCriteria(String fileData);
	public void resetSearchStatus();
}
