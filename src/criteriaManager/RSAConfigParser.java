package criteriaManager;

import debugprint.*;
import fileManager.RSAFileUtils;

import java.io.File;
import java.util.*;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RSAConfigParser {

	private static ArrayList<RSACriteriaList> criteriaList;
	
	private static final String[] criteriaXMLTag = {"Skill", "Qualification","MinExperience","MaxExperience"};
	private int criteriaCnt = 0;
	private static File configFile;
	
	public RSAConfigParser() {
		checkConfigFile();
		criteriaList = new ArrayList<RSACriteriaList>();
		addCriteriaList();
		CriteriaBuilder();
	}
	public int getcriteriaCnt() {
		return criteriaCnt;
	}
	
	public ArrayList<RSACriteriaList> getcriteriaList() {
		return criteriaList;
	}
	
	public void checkConfigFile() {
		try {
			configFile = new File(RSAFileUtils.getFolderPath("/data/"),"RSAConfig.xml");
			if(!RSAFileUtils.validateFile(configFile))
			{
				System.err.println("Failed to locate/read Config file, check your system!!"); 
				java.lang.System.exit(1);
			}
		}
		catch (Exception e) {
            System.err.println(e.getMessage());
	    }
	}
	
	private static void fillCriteriaCollection(String criteriaType, String strVal) 
	{
		for(RSACriteriaList list: criteriaList)
		{
			if(criteriaType.contains(list.getTagValue()))
			{
				list.setValueList(strVal);
			}
		}
		
	}

	private boolean CriteriaBuilder() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = null;

	    try {
	    	builder = factory.newDocumentBuilder();
	    } 
	    catch (ParserConfigurationException e) {
	    	e.printStackTrace();
	    }
	    Document doc = null;
	    try {
	    	doc = builder.parse(configFile);
	    } 
	    catch (SAXException e) {
	    	e.printStackTrace();
	    	return false;
	    } 
	    catch (IOException e) {
	    	e.printStackTrace();
	    	return false;
	    }
	    doc.getDocumentElement().normalize();

	    for(String cTag:criteriaXMLTag)
	    {
		    NodeList nList = doc.getElementsByTagName(cTag);		  
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if(nNode.hasChildNodes()){
					NodeList list = nNode.getChildNodes();
					String data = list.item(0).getNodeValue();
					rsaDebug.print("value :: "+ data);
					fillCriteriaCollection(cTag, data);
		        }
		        ++criteriaCnt;
		    }
	       
	    }
	    return true;
	}
    private void addCriteriaList() {	
    	criteriaList.add(new SkillCriteria());
    	criteriaList.add(new QualificationCriteria());
    	criteriaList.add(new ExperienceCriteria());
    }

}//close class