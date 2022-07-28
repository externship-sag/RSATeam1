package resumeScreenApp;

public class ResumeScreeningApp {
	
	/*
	 * Application Main function
	 */
	public static void main(String[] args) {
		System.out.println("===Application Started...");
	
		RSAManager appMgr = new RSAManager();
		if(appMgr.analyzeFolderPath())
			System.out.println("====Screening Done!!");
		else
			System.err.println("====Screening Failed!!");

	}
}
