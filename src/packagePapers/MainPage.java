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


public class MainPage {

	public MainPage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws SQLException, IOException, AddressException, MessagingException {
		// TODO Auto-generated method stub

		//Declaration and initialisation
		
		CodeBank cb = new CodeBank();
		ConnectionClass cc = new ConnectionClass();

		/*
		 * LOCAL CONNECTION TO DATABASE */
	/*	String url = "jdbc:mysql://localhost:3307/cspmaidb";
		String uname = "root";
		String pw = "bolanle"; 
		*/
		
		String directoryPath ="C:\\Users\\Omobola\\Desktop";
		String directoryPath1 ="C:\\Users\\Omobola\\Desktop\\NEWXML\\";
		/*
		*
		*/
		
		/*
		 * REMOTE CONNECTION TO DATABASE 
		 */
		
		/* DECLARATION AND INITIALISATION */
		
		
		String url = "jdbc:mysql://127.0.0.1:33006/cspmaidb";
		String uname = "cspmaiweb";
		String pw = "yT9B3rfxli";

		Map<String, String> linkMap = new HashMap<String, String>();
		Map<String, String> getMap = new HashMap<String, String>();
		Map<String, String> userMap = new HashMap<String, String>();
		Map<String, String> selectMap = new HashMap<String, String>();
		List<String> listName = new ArrayList<String>();
		List<String> listLinks = new ArrayList<String>();
		
		String userLink = "http://www2.warwick.ac.uk/fac/sci/dcs/people";
		
		
		/* CONNECT TO REMOTE DATABASE */
		cc.connectToDatabase(url, uname, pw);
		
		/* INSERT USER NAME AND USER LINKS INTO DATABASE */
		
		/* DOWNLOAD LINKS FROM DCS PAGE */      linkMap = cb.downloadLink(userLink);       /*END OF DOWNLOAD*/
		
		/*CHECK IF DATA DOESNT ALREADY EXIST-----RETRIEVE ALL PRIMARY KEYS FROM TABLE*/
		 selectMap=cc.select(url, uname, pw);
		
		 Iterator select = selectMap.entrySet().iterator();

		 while (select.hasNext()) {
			Map.Entry selectOut = (Map.Entry) select.next();
			listName.add(selectOut.getKey().toString());
			listLinks.add(selectOut.getValue().toString());
	
		 }   /*END OF RETRIEVE */
			
		/* ADD NEW LINKS TO TABLE USER_LINKS */
		 
		
		 /*	Iterator iter = linkMap.entrySet().iterator();
		 
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
		}  */   /*END OF ADDING NEW LINKS TO TABLE USER_LINKS */
		
		/*
		  ADD EMAILS TO THE TABLE
		 */
		
		/*for(int cnt = 0; cnt< listLinks.size(); cnt++)
		{
			String Email = cb.returnEmail(listLinks.get(cnt));
			String editedEmail = Email.replaceAll("mailto:", "");
			String Name =listName.get(cnt);
			
			String profile = cb.scrapeData(String.valueOf(listLinks.get(cnt)), "H3", "RESEARCH PROFILE"); 
		    String editedProfile = profile.replaceAll("'", "");
			
		    String groups = cb.scrapeData(String.valueOf(listLinks.get(cnt)), "H3", "RESEARCH GROUPS");
		    String publications = cb.scrapeData(String.valueOf(listLinks.get(cnt)), "H3", "SELECTED PUBLICATIONS");
		    String editedPub = publications.replaceAll("'", "");
		   
		    /*ADD AND UPDATE USER TABLE 
		  // System.out.println(Name+": "+editedPub);
	    	  
		    
		    //System.out.println(Name+": "+groups);
	    	
			//System.out.println(Name+": "+editedProfile);
	    	 //System.out.println(Name + " : "+editedEmail);
			
		  //cc.update(url, uname, pw, "Email", editedEmail, Name);
		  //cc.update(url, uname, pw, "Research_Profile", editedProfile, Name);
		   // cc.update(url, uname, pw, "Research_Groups", groups, Name);
		    //cc.update(url, uname, pw, "Selected_Publications", editedPub, Name);
			
			
		   
		}*/
		 
	    
	    /* GET JSON ADDRESS FROM PARTRIDGE AND SAVE INTO PATRIDGE TABLE */
	    
	    
	    List<String> listPart = new ArrayList<String>();
	    List<String> getPart = new ArrayList<String>();
	    List<String> getTitle = new ArrayList<String>();
	    List<String> addTitle = new ArrayList<String>();
	    
	    
	   // getPart = cc.fromPatridge(url, uname, pw);
	    
	    
	   // listPart = cb.createAddress();
		
		/*for(int j = 0 ; j< listPart.size(); j++)
		{
			if(getPart.contains(listPart.get(j).toString()))
			{
				//System.out.println(listPart.get(j).toString()+"exists!!!");
				
			}
			else
			{
			String link = listPart.get(j).toString();
			//System.out.println(cc.patridgeLinks(url, uname, pw, link));
			}
		}
	     System.out.println(listPart.size()); */
	    
	    
		/*END OF GET ADDRESS */
		
	    List<String> farnsLink = new ArrayList<String>();
	   // farnsLink = cc.selectLink(url, uname, pw);
	    
	    System.out.println(farnsLink.size());
		
	  //  System.out.println(cb.saveXML(farnsLink, directoryPath));
		
		/* OPEN JSON FILE AND SCRAP NEEDED DATA */
		
		String jsonPath = "C:\\Users\\Omobola\\Desktop\\PAPERJSON\\";
		List<String> newAddress = new ArrayList<String>();
		List<String> listURL = new ArrayList<String>();
		List<String> getURL = new ArrayList<String>();
		List<String> getAbstract = new ArrayList<String>();
		List<String> listAbstract = new ArrayList<String>();
		List<String> getAuthor = new ArrayList<String>();
		List<String> listAuthor = new ArrayList<String>();
		List<String> getDoc = new ArrayList<String>();
		List<String> listDoc = new ArrayList<String>();
		List<String> urlFetch = new ArrayList<String>();
		List<String> listURLFetch = new ArrayList<String>();
		List<String> fetchTitle = new ArrayList<String>();
		List<String> listTitleFetch = new ArrayList<String>();
		
		for(int json = 1; json<=55; json++)
		{
			newAddress.add(jsonPath+String.valueOf(json)+".json");
		}
		
		
	 /*int getCnt =0;
		
	  for(int k =0; k<newAddress.size(); k++)
	    {
		  String newFile =cb.convertFileToString(newAddress.get(k).toString());
		  // System.out.println(newFile);
		   
	    	fetchTitle = cb.getTitle(newFile);
		    getURL = cb.originalLink(newFile);
		    getAbstract = cb.getAbstract(newFile);
		    getAuthor = cb.getAuthor(newFile);
			urlFetch = cb.getURL(newFile);  
		    
		    
		    
		    
			  for(int tit = 0; tit< fetchTitle.size(); tit++)
			   { 
				   
				   getCnt++;
			   
				   listTitleFetch.add(fetchTitle.get(tit).toString());
				   
				  System.out.println(getCnt+": "+fetchTitle.get(tit).toString());
			   }
			   
		   
		   for(int tit = 0; tit< getURL.size(); tit++)
		   { 
			   
			   getCnt++;
		   
			   listURL.add(getURL.get(tit).toString());
			   
			  System.out.println(getCnt+": "+getURL.get(tit).toString());
		   }
		   
		   for(int tit = 0; tit< getAbstract.size(); tit++)
		   { 
			   
			   //getCnt++;
		   
			   listAbstract.add(getAbstract.get(tit).toString());
			   
			  System.out.println(getCnt+": "+getAbstract.get(tit).toString());
		   }
		   
		   
		   for(int tit = 0; tit< urlFetch.size(); tit++)
		   { 
			   
			   getCnt++;
		   
			   listURLFetch.add(urlFetch.get(tit).toString());
			   
			  System.out.println(getCnt+": "+urlFetch.get(tit).toString());
		   }
		   
		   
	    }
	  
	  
	  for(int cntr =0 ; cntr < 2729 ; cntr++)
	  {
		 // System.out.println(listURLFetch.get(cntr));
		 // System.out.println(listTitleFetch.get(cntr));
		   
		  
	  }
	  */
	  
	  //cc.paper_information(url, uname, pw, listURLFetch);
	 
	  
	  //cc.updatePaper(url, uname, pw, "Original_Url", listURL, );
	 
	 // cc.updatePaper(url, uname, pw, "Title", listTitleFetch,listURLFetch );
	 // cc.updatePaper(url, uname, pw, "Abstract", listAbstract,listURLFetch );
	  
	/*	
		List<String> listpaperLinks = new ArrayList<String>();
		List<String> listXML = new ArrayList<String>();
		String directoryPath = "C:\\Users\\Omobola\\Desktop\\PAPERXML\\";
		String check = "http://farnsworth.papro.org.uk/file/";
		List<String> listCheck = new ArrayList<String>();
		List<String> getBody = new ArrayList<String>();
		List<String> listbody = new ArrayList<String>();
		
		//String xmlfile = cb.convertFileToString("C:\\Users\\Omobola\\Desktop\\PAPERXML\\23.xml");
		
		
		//String printxml = cb.extractData(xmlfile);
		
		//System.out.println(printxml);
		
		*/
		
	    // cc.updateDoc(url, uname, pw, directoryPath1);
		
		// Get the whole document for text Mining 
		
		
		/*getMap = cc.selectAll(url, uname, pw);
		Iterator iterAll = getMap.entrySet().iterator();
		 
	 	while (iterAll.hasNext()) {
		Map.Entry mapAll = (Map.Entry) iterAll.next();
		{
		 //System.out.println(mapAll.getKey().toString()+" : "+mapAll.getValue().toString());

			String paths = cb.createNewFile(mapAll.getKey().toString(),directoryPath);
			System.out.println(paths);
			
			String fileName2 = mapAll.getKey().toString().replaceAll("http://farnsworth.papro.org.uk/file/", "");

			
			String writeFile =  cb.writetoFIle(mapAll.getValue().toString(),"C:\\Users\\Omobola\\Desktop\\DOCUMENTS\\"+fileName2+".txt");
			System.out.println(writeFile);
		
		}
		
		
		System.out.println(getMap.size());
		
		*/		
		
	//}  
	
		/* Updating Topic Model
		List retrieveAddress = new ArrayList();
		
		File folder = new File("C:\\Users\\Omobola\\Desktop\\Topics");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        retrieveAddress.add("C:\\Users\\Omobola\\Desktop\\Topics\\"+file.getName());
		    }
		}


		for(int r = 0; r< retrieveAddress.size(); r++)
			
		{
			String key1 = retrieveAddress.get(r).toString().replace("C:\\Users\\Omobola\\Desktop\\Topics\\", "");
			String key =key1.replace(".txt", "");
			
			System.out.println(retrieveAddress.get(r));
			
			String data = cb.convertFileToString(retrieveAddress.get(r).toString());
		
			cc.updateModel(url, uname, pw, key, data);
		}
		*/
		

		/* UPDATE TERM FREQUENCY
		List termFreq = new ArrayList();
		
		File folder = new File("C:\\Users\\Omobola\\Desktop\\TermFreq");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        termFreq.add("C:\\Users\\Omobola\\Desktop\\TermFreq\\"+file.getName());
		    }
		}


		for(int r = 0; r< termFreq.size(); r++)
			
		{
			String key1 = termFreq.get(r).toString().replace("C:\\Users\\Omobola\\Desktop\\TermFreq\\", "");
			String key =key1.replace(".txt", "");
			
			System.out.println(termFreq.get(r));
			
			String data = cb.convertFileToString(termFreq.get(r).toString());
		
			cc.updateFreq(url, uname, pw, key, data);
		}
		*/
		
		/*  Writing user data from database to file  */
		
		
/*
				userMap = cc.userAll(url, uname, pw);
				Iterator iterUser = userMap.entrySet().iterator();
		
				while (iterUser.hasNext()) {
		
					Map.Entry mapUser = (Map.Entry) iterUser.next();
					{
			
				//String fileName3 = mapUser.getKey().toString().replace("ń", "n");
				//String fileName2 = fileName3.replace("ć", "c");
				
				String fileName2 = mapUser.getKey().toString(); 
				//String paths = cb.createNewFile(fileName2,directoryPath);
				
				String content = mapUser.getValue().toString();

				System.out.println(fileName2+" : "+ content);

				String writeFile =  cb.writetoFIle(content,"C:\\Users\\Omobola\\Desktop\\Users\\"+fileName2+".txt");
				//System.out.println(writeFile);
			}
			
			
			System.out.println(userMap.size());
			
		}*/
		
		

		// Updating User topic Model
	/*
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
		
			cc.updateUserModel(url, uname, pw, key, data);
		}*/
		
		
		
		/** Compute similarities **/
		
	

		TreeSet<String> set1 = new TreeSet<String>();
		 TreeSet<String> un = new TreeSet<String>();
		 TreeSet<String> in = new TreeSet<String>();
		 TreeSet<String> unTop = new TreeSet<String>();
		 TreeSet<String> inTop = new TreeSet<String>();
		 Map<String, Double> jkd = new HashMap<String, Double>();
		 Map<String, Double> jkd_1 = new HashMap<String, Double>();
		 
		    
		 ClassSimilar c1 = new ClassSimilar();
		 Double ind =0.00;
		
		 Double indTop =0.00;
		    
      /*
       * Read research profile of users
       * 
       * */
		  
	
		    Map<String,String> userTopics = new HashMap();
			
			File users = new File("C:\\Users\\Omobola\\Desktop\\UserTopics1");
			File[] userFiles = users.listFiles();

			for (File file : userFiles) {
			    if (file.isFile()) {
			    	userTopics.put(file.getName().toString(),"C:\\Users\\Omobola\\Desktop\\UserTopics1\\"+file.getName());
			    }
			}
			
			Iterator iterUser = userTopics.entrySet().iterator();
            
			
			while (iterUser.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iterUser.next();
				{
				//for(int x=0; x < userTopics.size(); x++)
			
		  // String s = c1.convertFileToString(userTopics.get(x).toString());
					String s = c1.convertFileToString(mEntry.getValue().toString());
					String[] words = s.split("\\s+");
		   
		   /*
		    * 
		    * Create a set of words to represent user research profile
		    * 
		    *  */
		
					for(int i = 0; i < words.length; i++)
		    {
		      set1.add(words[i].toString());
		    }
		  
		  
		   /*
		    * 
		    * Get address of all papers and also topics
		    */
		  
		   	
		   List topicAddy = new ArrayList();
				
				File topics = new File("C:\\Users\\Omobola\\Desktop\\Topics");
				File[] fileTopics = topics.listFiles();

				for (File file : fileTopics) {
				    if (file.isFile()) {
				    	topicAddy.add("C:\\Users\\Omobola\\Desktop\\Topics\\"+file.getName());
				    }
				}

				System.out.println(topicAddy.size());
			/*
			 * 1. convert the frequency to string
			 * 2. create set of words to represent all papers
			 * */
			
			
			 for(int r = 0; r< topicAddy.size(); r++)
				//for(int r = 0; r< 100 ; r++)
							
			{
			
		       // System.out.println(r);
		        String s3 = c1.convertFileToString(topicAddy.get(r).toString());
		       // System.out.println(r+" : "+s3);
		        String[] top = s3.split("\\s+");
		       
		        TreeSet<String> terms = new TreeSet<String>();
		        TreeSet<String> topic = new TreeSet<String>();
				
		        for(int i = 0; i < top.length; i++)
			    {
			      topic.add(top[i].toString());
			    }
		       
		        
		        
			  /*
			   * Calculate intersection and union  between user, topics and termfreq  
			   */
		        
		      unTop = c1.union(set1, topic);
			    inTop = c1.intersection(set1, topic);
			   // System.out.println("SET 1 : "+set1);
			   // System.out.println("TOPIC : "+topic);
			   
			    indTop = (double) (Double.valueOf(inTop.size())/Double.valueOf(unTop.size()));
				    
			   // System.out.println("Jaccard coefficient : "+in.size()+"/"+un.size()+"="+ind);
			    
			   jkd_1.put(topicAddy.get(r).toString(), indTop);
			    
			}
			 List sorted = new ArrayList();
			 List result = new ArrayList();
			 
			 sorted = c1.entriesSortedByValues(jkd_1);
			 String key = mEntry.getKey().toString().replace(".txt", "");
			String allsol = "";	
			 //System.out.println("DONE");
			for(int c =0; c< 15; c++)
			{
				String sol = sorted.get(c).toString();
				String sol1 = sol.replace("C:\\Users\\Omobola\\Desktop\\Topics\\", "");
				//System.out.println(sol);
				/*String replace = sol.substring(0,sol.lastIndexOf("\\"));
			    String replace1 = sol.replace(replace, "");
			    String replace2 = replace1.substring(0, replace1.indexOf("."));
			    result.add(replace2);*/
				allsol=allsol+sol1+"\n";
				
				
			}
			//System.out.println(cb.writetoFIle(allsol,"C:\\Users\\Omobola\\Desktop\\top\\"+key+".txt"));
		    
			 // cc.updateRec(url, uname, pw, key, result);
			
		}   

	}
		 
		 mailClass mc = new mailClass();
	
		 
		 // SEND RECOMMENDATION
		 
	/*	try{ Map<String,String> sendRec = new HashMap<String,String>();
		 sendRec = cc.selectRec(url, uname, pw);
		
		 Iterator iterRec = sendRec.entrySet().iterator();
         	while (iterRec.hasNext()) {
         		String mailBody ="";
         		Map.Entry mEntry = (Map.Entry) iterRec.next();
				{
					
				  String name = mEntry.getKey().toString();
				  String[] words = mEntry.getValue().toString().split("\\s+");
	  
				  	{
						
				  System.out.println(name);
				  String email = cc.selectEmail(url, uname, pw, name);
				  System.out.println(email);
				  String feedback = "http://www.surveymonkey.com/s/QKZKRN9";
				  
				  for(int w=0; w<words.length; w++)
				  {
					  String rec = words[w].toString().replace(",", "");
					  String rec1 = rec.replace("]", "");
					  String rec2 = rec1.replace("[", "");
					 
					  String key = "http://farnsworth.papro.org.uk/file/"+rec2;
		
					  Map<String, String> mailItem = new HashMap<String,String>();
					  mailItem = cc.selectMail(url, uname, pw, key);
					  Iterator iterMail = mailItem.entrySet().iterator();
			         	while (iterMail.hasNext()) {
							Map.Entry mntry = (Map.Entry) iterMail.next();
							{
							  String ourl = mntry.getKey().toString();
							 // System.out.println(ourl);
							  String body = mntry.getValue().toString();
							 // System.out.println(body);
							  mailBody = mailBody+"\n"+(w+1)+"\n" + ourl +"\n" +body; 
							}
			         	}
			         	}
				      System.out.println(mailBody);
				      if(!email.equals(""))
				      {
				      String response = mc.sendEmail(email, "Dear "+name+"\n\nMy name is Bolanle Oladapo, an MSc. Student of Department of computer Science, University of warwick.\nI am testing my dissertation project titled - A content based recommendation system for scientific papers.\nBelow is a list of papers that might be relevant to your research profile,please have a look and give feedback in the link below\n"+feedback+"\n\nThank You.\n\n\n Regards\nBolanle Oladapo.\n"+mailBody);
				      System.out.println(response);
				      }
				  }
				}
				
				  
				}
         	}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}*/
	}	
}



