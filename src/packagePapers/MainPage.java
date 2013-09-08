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
		/*
		 *  CONNECTION TO DATABASE */
		//local connection to the database
		String url = "jdbc:mysql://localhost:3307/cspmaidb";
		String uname = "root";
		String pw = "bolanle"; 
		
		/* remote connection db
		 * String url = "jdbc:mysql://127.0.0.1:33006/cspmaidb";
		String uname = "username";
		String pw = "password";*/
		
		/* DECLARATION AND INITIALISATION */
		
		CodeBank cb = new CodeBank();   //Class codebank
		ConnectionClass cc = new ConnectionClass();  //Class connection class-takes care of all connection
		
		
		
	    
	   
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
		
		 cb.createFile("C:\\Users\\Downloads\\TEST");
		   

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
		 String output ="";
		 
		    
		 ClassSimilar c1 = new ClassSimilar();
		 Double ind =0.00;
		
		 Double indTop =0.00;
		    
      /*
       * Read research profile of users
       * 
       * */
		  
	
		/*    Map<String,String> userTopics = new HashMap();
			
			File users = new File("C:\\Users\\Omobola\\Desktop\\UserTopics");
			File[] userFiles = users.listFiles();

			for (File file : userFiles) {
			    if (file.isFile()) {
			    	userTopics.put(file.getName().toString(),"C:\\Users\\Omobola\\Desktop\\UserTopics\\"+file.getName());
			    }
			}
			
			Iterator iterUser = userTopics.entrySet().iterator();
          
			
			while (iterUser.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iterUser.next();
				{
				//for(int x=0; x < userTopics.size(); x++)
			
		  // String s = c1.convertFileToString(userTopics.get(x).toString());
				//	System.out.println(mEntry.getKey());
					String s = c1.convertFileToString(mEntry.getValue().toString());
					String[] words = s.split("\\s+");
		   
					output=output+mEntry.getKey().toString();
		   /*
		    * 
		    * Create a set of words to represent user research profile
		    * 
		    *  */
		 String s = c1.convertFileToString("C\\Users\\Omobola\\Desktop\\freq.txt");
		 String[] words = s.split("\\s+");
		   
	
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
			
			
			//(int r = 0; r< topicAddy.size(); r++)
				for(int r = 0; r< 100 ; r++)
							
			{
			
		       // System.out.println(r);
		        String s3 = c1.convertFileToString(topicAddy.get(r).toString());
		       System.out.println(r+" : "+s3);
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
			    System.out.println("SET 1 : "+set1);
			    System.out.println("TOPIC : "+topic);
			   
			    indTop = (double) (Double.valueOf(inTop.size())/Double.valueOf(unTop.size()));
				    
			    System.out.println("Jaccard coefficient : "+in.size()+"/"+un.size()+"="+ind);
			    
			   jkd_1.put(topicAddy.get(r).toString(), indTop);
			    
			}
			 List sorted = new ArrayList();
			 List result = new ArrayList();
			 
			 sorted = c1.entriesSortedByValues(jkd_1);
		//	 String key = mEntry.getKey().toString().replace(".txt", "");
			String allsol = "";	
			 //System.out.println("DONE");
			for(int c =0; c< 15; c++)
			{
				String sol = sorted.get(c).toString();
				//String sol1 = sol.replace("C:\\Users\\Omobola\\Desktop\\Topics\\", mEntry.getKey().toString()+"-");
				System.out.println(sol);
				/*String replace = sol.substring(0,sol.lastIndexOf("\\"));
			    String replace1 = sol.replace(replace, "");
			    String replace2 = replace1.substring(0, replace1.indexOf("."));
			    result.add(replace2);*/
			//	allsol=allsol+sol1+"\n";
				
				
			}
			 output = output+allsol;
			//System.out.println(cb.writetoFIle(allsol,"C:\\Users\\Omobola\\Desktop\\top\\"+key+".txt"));
		    
			 // cc.updateRec(url, uname, pw, key, result);
			
		//}   
			 System.out.println(output);
	
}
		// cb.writetoFIle(output, "C:\\Users\\Omobola\\Desktop\\Histogram\\output1.txt");
		 
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
			//	  String[] words = mEntry.getValue().toString().split("\\s+");
	  
				  	{
						
				  System.out.println(name);
				  String email = cc.selectEmail(url, uname, pw, name);
				  System.out.println(email);
				  String feedback = "http://www.surveymonkey.com/s/87YYNTV";
				  mailBody="";
				 /* for(int w=0; w<words.length; w++)
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
				    *//*  if(!email.equals(""))
				      {
				      String response = mc.sendEmail(email, "Dear "+name+"\n\nMy name is Bolanle Oladapo, an MSc. Student of Department of computer Science, University of warwick.\nI am testing my dissertation project titled - A content based recommendation system for scientific papers.\nBelow is a list of papers that might be relevant to your research profile,please have a look and give feedback in the link below\n"+feedback+"\n\nThank You.\n\n\n Regards\nBolanle Oladapo.\n"+mailBody);
				      System.out.println(response);
				      }*/
				    
				/*    if(!email.equals(""))
				      {
				      //String response = mc.sendEmail(email,"Dear "+name+"\n\nMy name is Bolanle Oladapo,MSc. Student of Department of computer Science, University of warwick.\nI sent you an email couple of weeks ago as regards evaluating my dissertation project, Content-based recommendation system for scientific papers.\nThank you for your prompt response, but to analyse my research work, I would need more help from you.\nCould you please go to the link below and let me know the papers that were bad(irrelevant), good(relevant) and average (averagely-relevant) match to your research interest.\n"+feedback+"\n\nThank You.\n\n\n Regards\nBolanle Oladapo.\n");
				     // System.out.println(response);
				      }
				  }
				}
				
				  
				}
         	}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}*/
	}	
//}



