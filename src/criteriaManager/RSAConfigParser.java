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

  	//List of Criteria objects: Skill, Qualification, years of work Experience
	private static ArrayList<RSACriteriaList> criteriaList;
	
	private static final String[] criteriaXMLTag = {"Skill", "Qualification","MinExperience","MaxExperience"};
	//Criteria field count to mark the resume file as eligible candidate.
	private int criteriaCnt = 0;
	private static File configFile;
	
	/*
	 * Constructor method that builds the criteria data from 
	 * the input configuration file : RSAConfig.xml
	 */
	public RSAConfigParser() {
		checkConfigFile();
		criteriaList = new ArrayList<RSACriteriaList>();
		addCriteriaList();
		CriteriaBuilder();
	}
	/*
	 * Get method for criteria count  
	 */
	public int getcriteriaCnt() {
		return criteriaCnt;
	}
	/*
	 * Get method for criteria object List  
	 */
	public ArrayList<RSACriteriaList> getcriteriaList() {
		return criteriaList;
	}
	/*
	 * Method to validate the input config file
	 */
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
	/*
	 * Method to fill the criteria values into the criteria object list.
	 */
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
	/*
	 * Xml configuration file reader 
	 */
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
	/*
	 * create instances for the criteria objects.
	 */
    private void addCriteriaList() {	
    	criteriaList.add(new SkillCriteria());
    	criteriaList.add(new QualificationCriteria());
    	criteriaList.add(new ExperienceCriteria());
    }

}//close class
