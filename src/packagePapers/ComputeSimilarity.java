package packagePapers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class ComputeSimilarity {

	public ComputeSimilarity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

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
		  
	
		   Map<String,String> userTopics = new HashMap();
			
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
				
					for(int y=0; y < userTopics.size(); y++){
			
		                  String s = c1.convertFileToString(userTopics.get(y).toString());
				       
				       }
				System.out.println(mEntry.getKey());
				String s = c1.convertFileToString(mEntry.getValue().toString());
				String[] words = s.split("\\s+");
		   
					output=output+mEntry.getKey().toString();
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
}
}