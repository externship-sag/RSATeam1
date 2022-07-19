# **Resume Screening Application**

This is a console based basic Java application.

The application is capable of reading the resume files in PDF or Doc file format stored under ./data/resumes directory path, and search for the keywords defined in the "RSAConfig.xml" file.

The Application will generate results in the "Result.csv" file, stored under ./data/output directory path.

## **Input Configuration**
The Application input can be configured through the RSAConfig.xml file.

Currently, the Application is capable to filter the Skill, Qualification and Experience range as the eligibility criteria.
Hence, the user can modify/add the values for the above mentioned criteria types in the XML file.

Copy all the resume files that need to be screened, under ./data/resumes directory path.


## **Execution Steps**

#### 1.Clone the project repository to the local machine

    git clone https://github.com/externship-sag/RSATeam1.git
    
#### 2.Import the project source code from the File system to eclipse
  
    Open eclipse ->File ->Open projects from file system -> select Directory beside "Import Source" text field. (Browse the git cloned project directory)->Finish

    Now the project code is added to Project Explorer pane.

#### 3.Execute the application as Java application

    Select Run as Java application. 
    For the first time, a window may pop with list of libraries linked to this app, requesting to add filter.
    Search for resumeScreenApp and select it then click ok.
    At this stage, The Application should start execution and generate results in the output folder.
    
