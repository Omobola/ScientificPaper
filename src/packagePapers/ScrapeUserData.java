package packagePapers;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapeUserData {

	public ScrapeUserData() {
		// TODO Auto-generated constructor stub
	}

	
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub

		/*
		 *  CONNECTION TO DATABASE */
		//local connection to the database
		String url = "jdbc:mysql://localhost:3307/cspmaidb";
		String uname = "root";
		String pw = "bolanle"; 
		Map<String, String> linkMap = new HashMap<String, String>();
		Map<String, String> getMap = new HashMap<String, String>();
		Map<String, String> userMap = new HashMap<String, String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		List<String> listName = new ArrayList<String>();
		List<String> listLinks = new ArrayList<String>();
		
		
		/* remote connection db
		 * String url = "jdbc:mysql://127.0.0.1:33006/cspmaidb";
		String uname = "username";
		String pw = "password";*/
		
		/* DECLARATION AND INITIALISATION */
		
		CodeBank cb = new CodeBank();   //Class codebank
		ConnectionClass cc = new ConnectionClass();  //Class connection class-takes care of all connection
		
        String userLink = "http://www2.warwick.ac.uk/fac/sci/dcs/people";
		
		/* Connecting to the database - calls class connect to  Database from connection class*/
		cc.connectToDatabase(url, uname, pw);
		
		/* DOWNLOAD LINKS FROM DCS PAGE */    
		
		linkMap = cb.downloadLink(userLink);       
		
		/*
		 * 1. Gets name and link of each user from the downloaded HTML
		 * 2.  The name and Link is the used to scrap each users' page
		 * */
		 selectMap=cc.select(url, uname, pw);
		
		 Iterator select = selectMap.entrySet().iterator();

		 while (select.hasNext()) {
			Map.Entry selectOut = (Map.Entry) select.next();
			listName.add(selectOut.getKey().toString());
			listLinks.add(selectOut.getValue().toString());
	
		 } 
			
		/* 
		 * 1. To add more user data to user's table, This segment of the code find out if user is not in the table already
		 *  */
		 
		
		 	Iterator iter = linkMap.entrySet().iterator();
		 
		 	while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			if(listName.contains(mEntry.getKey().toString()))
			{
				System.out.println("Data already exists!!!");
			}
			else
			{
			 System.out.println(cc.UserLinks(url, uname, pw, mEntry.getKey().toString(), mEntry.getValue().toString()));
			}
		}  
		 
		
		/*
		  1. Retrieve user email
		  2. Retrieve other user's information by using the link saved in the database already
		 */
		
		for(int cnt = 0; cnt< listLinks.size(); cnt++)
		{
			String Email = cb.returnEmail(listLinks.get(cnt));
			String editedEmail = Email.replaceAll("mailto:", "");
			String Name =listName.get(cnt);
			
			String profile = cb.scrapeData(String.valueOf(listLinks.get(cnt)), "H3", "RESEARCH PROFILE"); 
		    String editedProfile = profile.replaceAll("'", "");
			
		    String groups = cb.scrapeData(String.valueOf(listLinks.get(cnt)), "H3", "RESEARCH GROUPS");
		    String publications = cb.scrapeData(String.valueOf(listLinks.get(cnt)), "H3", "SELECTED PUBLICATIONS");
		    String editedPub = publications.replaceAll("'", "");
		   
		   
		   //Update User table with all the retrieved information 
		  			
		    cc.update(url, uname, pw, "Email", editedEmail, Name);
		    cc.update(url, uname, pw, "Research_Profile", editedProfile, Name);
		    cc.update(url, uname, pw, "Research_Groups", groups, Name);
		    cc.update(url, uname, pw, "Selected_Publications", editedPub, Name);
		
		}
		
		// Updating User topic Model
		
					List userModel = new ArrayList();
						
						File folder = new File("C:\\Users\\Omobola\\Desktop\\UserTopics");
						File[] listOfFiles = folder.listFiles();

						for (File file : listOfFiles) {
						    if (file.isFile()) {
						        userModel.add("C:\\Users\\Omobola\\Desktop\\UserTopics\\"+file.getName());
						    }
						}


						for(int r = 0; r< userModel.size(); r++)
							
						{
							String key1 = userModel.get(r).toString().replace("C:\\Users\\Omobola\\Desktop\\UserTopics\\", "");
							String key =key1.replace(".txt", "");
							
							System.out.println(userModel.get(r));
							
							String data = cb.convertFileToString(userModel.get(r).toString());
						
							//cc.updateUserModel(url, uname, pw, key, data);
						}
					
					
		
	}

}
