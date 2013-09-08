package packagePapers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ScrapePaperData {

	public ScrapePaperData() {
	}

	public static void main(String[] args) throws SQLException, IOException {
		
		/* Paper information are saved on Partridge as JSON. This code segment 
		 * download JSON file from partridge, open this file to fetch doi. The doi is used 
		 * to open the paper on the original sources (PLOSONE and PUBMED).
		 * Papers are downloaded from the original sources in form of XML.
		 * Using XML parser, I retrieved some information about each paper
		 * */
		
	 	//Declaration and Initialisation
		
		String directory ="C:\\Users\\Omobola\\Desktop"; // The location you want to download the papers to from PLOSONE and PubMed
		//String directoryPath ="C:\\-------\\PAPERXML"; /* The location of the downloaded XML papers.... You can copy the xml papers from the Report CD 
		                                                 // place it in any location of your choice. PUt the location into directoryPath1				
		String directoryPath1 ="C:\\-------\\PAPERJSON"; /* The location of the downloaded JSON papers.... You can copy the xml papers from the Report CD 
		                                                    place it in any location of your choice. PUt the location into directoryPath1*/				
        
		/*String directoryPath ="C:\\Users\\Omobola\\Desktop"; */
		String directoryPath ="C:\\Users\\Omobola\\Desktop\\NEWXML\\";
		
	    List<String> listPart = new ArrayList<String>();
	    List<String> getPart = new ArrayList<String>();
	    List<String> getTitle = new ArrayList<String>();
	    List<String> addTitle = new ArrayList<String>();
	    ConnectionClass cc = new ConnectionClass();
	    CodeBank cb = new CodeBank();
	    String path = "http://farnsworth.papro.org.uk/api/papers/";
		
	    /*  CONNECTION TO DATABASE */
		//local connection to the database
		String url = "jdbc:mysql://localhost:3307/cspmaidb";
		String uname = "root";
		String pw = "bolanle"; 
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
		
		
		/* remote connection db
		 * String url = "jdbc:mysql://127.0.0.1:33006/cspmaidb";
		   String uname = "username";
		   String pw = "password";*/
		cc.connectToDatabase(url, uname, pw);
		
		
	   // Create address  of partridge
	     listPart = cb.createAddress();
	    
	  // Select all json Links: this links are used to check if links does not already exist when saving new links into the table  
		 getPart = cc.fromPatridge(url, uname, pw);
		
		/*for(int j = 0 ; j< listPart.size(); j++)
		{
			if(getPart.contains(listPart.get(j).toString()))
			{
				System.out.println(listPart.get(j).toString()+"exists!!!");
				
			}
			else
			{
			String link = listPart.get(j).toString();
			     //cc.patridgeLinks(url, uname, pw, link);
			}
		}
	   */
		
		/* Download JSON files from partridge API */ 
		
	/*	for(int cnt = 0; cnt<listPart.size(); cnt++)
		{
			System.out.println(listPart.get(cnt).toString());
		}
		
		String msg = cb.saveJSON(listPart, directory);
		System.out.println(msg);
		*/
		
		/* Open all json files and extract needed Data */
		
		
	/*	for(int json = 1; json<=55; json++)
		{
			newAddress.add(jsonPath+String.valueOf(json)+".json");
		}
		
		
	    int getCnt =0;
		
	    for(int k =0; k<newAddress.size(); k++)
	    {
		  
	    	String newFile =cb.convertFileToString(newAddress.get(k).toString());
		   
	    	fetchTitle = cb.getTitle(newFile); // Get Title of the paper
		    getURL = cb.originalLink(newFile); // Get original source of the paper (doi)
		    getAbstract = cb.getAbstract(newFile); // Get Abstract of the paper
		    urlFetch = cb.getURL(newFile);  // Address of the paper on Partridge
		    
		    for(int tit = 0; tit< fetchTitle.size(); tit++)
			   { 
				  //Add all the title of every paper to a List
				   getCnt++;
			   
				   listTitleFetch.add(fetchTitle.get(tit).toString());
				   
				  System.out.println(getCnt+": "+fetchTitle.get(tit).toString());
			   }
			   
		   
		   for(int tit = 0; tit< getURL.size(); tit++)
		   { 
			 //Add all the URL of every paper to a List
			   getCnt++;
		   
			   listURL.add(getURL.get(tit).toString());
			   
			  System.out.println(getCnt+": "+getURL.get(tit).toString());
		   }
		   
		   for(int tit = 0; tit< getAbstract.size(); tit++)
		   { 
			 //Add all the Abstract of every paper to a List
			   getCnt++;
		   
			   listAbstract.add(getAbstract.get(tit).toString());
			   
			  System.out.println(getCnt+": "+getAbstract.get(tit).toString());
		   }
		   
		   
		   for(int tit = 0; tit< urlFetch.size(); tit++)
		   { 
			 //Add all the Partridge Link of every paper to a List
			   getCnt++;
		   
			   listURLFetch.add(urlFetch.get(tit).toString());
			   
			  System.out.println(getCnt+": "+urlFetch.get(tit).toString());
		   }
		   
		   
	    }
	  
	         cc.paper_information(url, uname, pw, listURLFetch);
	         cc.updatePaper(url, uname, pw, "Original_Url", listURL,listURLFetch);
	         cc.updatePaper(url, uname, pw, "Title", listTitleFetch,listURLFetch );
	         cc.updatePaper(url, uname, pw, "Abstract", listAbstract,listURLFetch );
	  
		*/
		
		      /*Get the whole document and save*/
		
	      //    cc.updateDoc(url, uname, pw, directoryPath);
		
		// Get the whole document for text Mining 
		    
		                	
		/*    Map<String, String> getMap = cc.selectAll(url, uname, pw);
		    Iterator iterAll = getMap.entrySet().iterator();
		 
	 	         while (iterAll.hasNext()) {
		              Map.Entry mapAll = (Map.Entry) iterAll.next();
		                 {
		                	 
		                     String address1 = mapAll.getKey().toString();
		                     String writeFile = cb.createNewFile(address1, "C:\\Users\\Desktop");
	                         
                             String paths = cb.createNewFile(mapAll.getKey().toString(),"C:\\Users\\Desktop");
			                 System.out.println(paths);
			
		                     String fileName2 = mapAll.getKey().toString().replaceAll("http://farnsworth.papro.org.uk/file/", "");
                             String writeFile1 =  cb.writetoFIle(mapAll.getValue().toString(),"C:\\Users\\Desktop\\DOCUMENTS\\"+fileName2+".txt");
			                 System.out.println(writeFile1);
		
		                  }
	     
	 	         }      
		// Get the Link of each paper on farnsworth
  /*      List<String> farnsLink = new ArrayList<String>();
        farnsLink = cc.selectLink(url, uname, pw);
    
	// Download all papers in form of XML from PLOSONE and PUBMED
       cb.saveXML(farnsLink, directoryPath); */
		 
		 /* Updating Topic Model */
		/*	List retrieveAddress = new ArrayList();
			
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
			    System.out.print(data);
				cc.updateModel(url, uname, pw, key, data);
			}
			*/
		 
		 
		
	}

}
