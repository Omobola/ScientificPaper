package packagePapers;

import java.io.BufferedInputStream;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;


public class CodeBank {

	public CodeBank() {
		// TODO Auto-generated constructor stub
	}
	
	public Map<String, String> downloadLink(String url)
	{
		/* Initialisation */
		/*
		  */
		 
		Document doc;
		Map<String, String> linkMap = new HashMap<String, String>();
        
		Boolean response=null;
		String outputs="";
		
		try {
			doc = Jsoup.connect(url).get();
			Element divs = doc.select("div.people").get(0);
			Elements links = divs.select("a[href]");
			
			 for(int x=0; x<links.size(); x++)
		   	  {
		   	   		response =links.get(x).attr("abs:href").contains("http");
		   	  if(response==true){
		   	    outputs = links.get(x).attr("abs:href").toString();
		   	    String textlink= links.get(x).text();
		        String urllink= outputs; 
		       
		        //System.out.println(textlink);
		        //System.out.println(urllink);
			    
		        if(!textlink.equals(""))
		        {
		         linkMap.put(String.valueOf(textlink),String.valueOf(urllink));
		        }
		       }	
			}System.out.println(links.size());
			
		        
		} catch (IOException e) {
			/* TODO Auto-generated catch block*/
			e.printStackTrace();
		}
		return linkMap;
	}
	public String writetoFIle(String data, String path)
	{
		File file = new File(path);
		//File file = new File(path);
		 String msg="Writing Successful!";
		//if file doesn't exists, then create it
		
		FileWriter fw;
		try {
			
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
		
	}
	
	public String scrapeData(String newAddy,String parentTag,String childTag)
	{
		 String output="";
		try {
			 Document doc;
	    	 doc = Jsoup.connect(newAddy).get();
	    	 Elements headings = doc.getElementsByTag(parentTag);
	    	 
	    	 for (Element heading : headings) {
	    		 
	    		 if(String.valueOf(heading.text()).equals(childTag)){
				    	
	    			 int size = heading.siblingElements().size();
	    	     
	    			 	//output = output+ childTag +"\n";
	    	     
	    	     for(int cntr =0; cntr<size; cntr++)
	    	     {
	    	    	 Element text=heading.siblingElements().get(cntr);
	    	    	 TextNode tn = new TextNode(text.text(),"");
			    	 output = output+tn.getWholeText()+ "\n";
	    	     }
	    	  }
	    	 }
	    	 
	    	    	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 return output;
	}
	
	public String returnEmail(String newAddy)
	{
	 Document doc;
     String outputs="";
     Boolean response = false;
     
	    try {
	    	  doc = Jsoup.connect(newAddy).get();
	    	  Elements links = doc.select("a[href]");
	    	  for(int x=0; x<links.size(); x++)
	    	  {
	    	   response =links.get(x).attr("abs:href").contains("mailto");
	    	  if(response==true){
	    	    outputs = links.get(x).attr("abs:href").toString();
	    	  }
	    		 
	    	  }
	    	  
	    	  
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return outputs;
	}

	public String returnName(String newAddy)
	{
		String outputs="";
		Document doc;
	    try {
	    	  doc = Jsoup.connect(newAddy).get();
	    	  Elements links = doc.select("title");
	    	  outputs = links.text();
	    	  
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return outputs;
	}

	public String getResearch(String newAddy)
	{
		Document doc;
		String x="";
		
		try {
		 doc = Jsoup.connect(newAddy).get();
		 Elements headings = doc.getElementsByTag("h3");
   	 
		 for (Element heading : headings) {
   		 
   		 if(String.valueOf(heading.text()).equals("RESEARCH")){
		         	x = x+ "RESEARCH" +"\n";
   	     
		         	Element text=heading.nextElementSibling();
		         	TextNode tn = new TextNode(text.text(),"");
		         	x = x+tn.getWholeText()+ "\n";
   	             }
   	          }
   	 
			} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	return x;
	}

	public void readFromApi(String address,String filePath) throws ClientProtocolException, IOException
	{
		//  READ FROM API ----works well
		HttpClient httpclient = new DefaultHttpClient();
		 HttpGet httpget = new HttpGet(address);
		 HttpResponse response = httpclient.execute(httpget);
		// System.out.println(response.getStatusLine());
		 HttpEntity entity = response.getEntity();
		 if (entity != null) {
		     InputStream instream = entity.getContent();
		     try {
		         BufferedInputStream bis = new BufferedInputStream(instream);
		         BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		         int inByte;
		         while ((inByte = bis.read()) != -1 ) {
		             bos.write(inByte);
		         }
		         bis.close();
		         bos.close();
		     } catch (IOException ex) {
		         throw ex;
		     } catch (RuntimeException ex) {
		         httpget.abort();
		         throw ex;
		     } finally {
		         instream.close();
		     }
		     httpclient.getConnectionManager().shutdown();
		 }

		
	}
	
	
	public String createDirectory(String directoryPath)
	{
		String msg="";
		File theDir = new File(directoryPath);
		// if the directory does not exist, create it
		  if (!theDir.exists())
		  {
		    System.out.println("creating directory: " + directoryPath);
		    boolean result = theDir.mkdir();  
		    if(result){    
		       msg="create";  
		     }

		  }
		  else
		  {
			  msg="exists";
		  }
		return msg;
	}

	public void createFile(String path) throws IOException
	{
		//(use relative path for Unix systems)
		File f = new File(path);
		f.createNewFile();
	}
	
	
	public List<String> createAddress()
	{
		List<String> address = new ArrayList<String>();
		int start = 1;
		int n = 2729;
		String link="http://farnsworth.papro.org.uk/api/papers/";
		int size = 50;
		
		while (n > 1 )
		{
			if(start==1){
			address.add(link+"50/"+start+"/");
			n=n-size;
			start = start+49;}
			else
			{
				address.add(link+"50/"+start+"/");
				n=n-size;
				start = start+50;
			}
			
		}
		
		return address;
		
	}
	
	
	public String saveJSON(List<String> address,String directoryPath) throws IOException
	{
		
		
		String path1 = directoryPath+"\\PAPERJSON";
	    String path2 = "";

        String msg = this.createDirectory(path1);

        int cntr = 1;

        if(msg=="create")
        {
            cntr = 1;
           
        }
        else
        {
            // Put all file names in root directory into array.
             cntr = new File(path1).list().length;

        }

        for (int i = 0; i < address.size() ; i++)
        {

            String address1 = String.valueOf(address.get(i));

            String fileName = "\\" + String.valueOf(cntr) + ".json";
            
            cntr = cntr + 1;
            
            System.out.println(address1);
            path2 = path1 + fileName;
            this.createFile(path2);
            this.readFromApi(address1, path2);
            
        }

        msg = "Download Complete!";
        return msg;
	}


	public String saveXML(List<String> address,String directoryPath) throws IOException
	{
		
		
		String path1 = directoryPath+"\\NEWXML";
	    String path2 = "";

        String msg = this.createDirectory(path1);

       for (int i = 0; i < address.size() ; i++)
        {

            String address1 = String.valueOf(address.get(i));
            
            String fileName2 = address1.replaceAll("http://farnsworth.papro.org.uk/file/", "");

            String fileName = "\\" + fileName2 + ".xml";
            
           path2 = path1 + fileName;
           this.createFile(path2);
           this.readFromApi(address1, path2);
            System.out.println(i+":"+address1);
            
        }

        msg = "Download Complete!";
        return msg;
	}


	public List<String> fileNAMES(String path)
	{
		List<String> listFiles = new ArrayList<String>();
		
		File folder = new File(path);
	
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) 
	    {
	        listFiles.add(listOfFiles[i].getName());
	      
	    }
	    
	    return listFiles;
	}
	
	public List<String> getTitle(String body) throws FileNotFoundException, IOException
	{
		List<String> listArray = new ArrayList<String>();
		String title = "";
		
		//READ FROM A JSON FILE-----successful
		JSONParser parser = new JSONParser();
		try {
			/*URL uri = new URL(path);
			URLConnection con = uri.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);
			*/
			Object obj = parser.parse(body);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray papers = (JSONArray) jsonObject.get("papers");

			for(int i =0; i<papers.size();i++)
		    {
				JSONObject paper = (JSONObject) papers.get(i); // Get row object
                
				title = (String) paper.get("title");
				listArray.add(title);
		    }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return listArray;
	}

	
	public String extractData(String xml) throws IOException
	{
		Document xmlDoc = Jsoup.parse(xml, "", Parser.xmlParser());

		Elements links = xmlDoc.select("body");
	
		String output = links.text();
	  
		return output;  
	   
	}

	public List<String> getAbstract(String body) throws FileNotFoundException, IOException
	{
		String abstracts = "";
		List<String> listArray = new ArrayList<String>();
		
		
		//READ FROM A JSON FILE-----successful
		JSONParser parser = new JSONParser();
		try {
			/*URL uri = new URL(path);
			URLConnection con = uri.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);
			*/
			
			Object obj = parser.parse(body);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray papers = (JSONArray) jsonObject.get("papers");

			for(int i =0; i<papers.size();i++)
		    {
				JSONObject paper = (JSONObject) papers.get(i); // Get row object
                
				abstracts = (String) paper.get("abstract");
				listArray.add(abstracts);
		    		
		    }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return listArray;
	}

	public List<String> getURL(String body) throws FileNotFoundException, IOException
	{
		String url = "";
		List<String> listArray = new ArrayList<String>();
		
		
		//READ FROM A JSON FILE-----successful
		JSONParser parser = new JSONParser();
		try {
			/*URL uri = new URL(path);
			URLConnection con = uri.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);*/
			
			Object obj = parser.parse(body);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray papers = (JSONArray) jsonObject.get("papers");

			for(int i =0; i<papers.size();i++)
		    {
				JSONObject paper = (JSONObject) papers.get(i); // Get row object
                
				JSONArray files = (JSONArray) paper.get("files");
				
				JSONObject p = (JSONObject)files.get(0);
				
				url = (String) p.get("url");
				listArray.add(url);
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return listArray;
	}

	public List<String> getAuthor(String body) throws FileNotFoundException, IOException
	{
		String thor = "";
		List<String> thorList = new ArrayList<String>();
		
		
		//READ FROM A JSON FILE-----successful
		JSONParser parser = new JSONParser();
		try {
			/*URL uri = new URL(path);
			URLConnection con = uri.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding); */
			
			Object obj = parser.parse(body);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray papers = (JSONArray) jsonObject.get("papers");

			for(int i =0; i<papers.size();i++)
		    {
				JSONObject paper = (JSONObject) papers.get(i); // Get row object
                JSONArray authors = (JSONArray) paper.get("authors");
				for(int j = 0; j < authors.size(); j++ )
				{
					JSONObject author = (JSONObject)authors.get(j);
					
					String sn = (String) author.get("surname");
					String fn = (String) author.get("forenames");
					thor =  sn+" "+fn;
					thorList.add(thor);
					
				}

				
		    }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return thorList;
	}

	public List<String> originalLink(String body) throws FileNotFoundException, IOException
	{
		String link = "http://dx.doi.org/";
		String link1="";
		List<String> linkArray = new ArrayList<String>();
		
		//READ FROM A JSON FILE-----successful
		JSONParser parser = new JSONParser();
		try {
			/*URL uri = new URL(path);
			URLConnection con = uri.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);
			*/
			Object obj = parser.parse(body);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray papers = (JSONArray) jsonObject.get("papers");

			for(int i =0; i<papers.size();i++)
		    {
				JSONObject paper = (JSONObject) papers.get(i); // Get row object
                
				link1 =link+ (String) paper.get("doi");
				linkArray.add(link1);	
		    }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return linkArray;
	}
	
	public String convertFileToString(String filePath) throws IOException
	{
		/*String content = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
		//System.out.println(content);*/
		File myFile = new File(filePath);
		String content="";
	    try{
	        content = FileUtils.readFileToString(myFile);
	    }catch(IOException e){
	        e.printStackTrace();
	    }  
	    
	    return content;
    }


	public String createNewFile(String address1,String directoryPath) throws IOException
	{
		
		
		String path1 = directoryPath+"\\DOCUMENTS";
	    String path2 = "";

        String msg = this.createDirectory(path1);
        String fileName2 = address1.replaceAll("http://farnsworth.papro.org.uk/file/", "");

        String fileName = "\\" + fileName2 + ".txt";
            
        path2 = path1 + fileName;
        this.createFile(path2);
        
        msg = "Creation Successful!";
        
        return msg;
	}

    public void printoutvalues ()
    {
    	String source = "C:\\Users\\Omobola\\Desktop\\Histogram";
    	
    }


}
