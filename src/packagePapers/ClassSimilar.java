/** This class is used to compare the similarities between topic model of users and topic model of papers. It is used to compute
 * JACCARD INDEX
 */

package packagePapers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

public class ClassSimilar {

	public ClassSimilar() {
		// TODO Auto-generated constructor stub
	}
	
	public  TreeSet<String> union(TreeSet<String> set1, TreeSet<String> set2) {
	    TreeSet<String> tset = new TreeSet<String>(set1);
	    tset.addAll(set2);
	    return tset;
	  }

public  TreeSet<String> intersection(TreeSet<String> set1, TreeSet<String> set2) {
	TreeSet<String> tset = new TreeSet<String>();
	    for (String intset : set1)
	      if (set2.contains(intset))
	        tset.add(intset);
	    return tset;
	  }

public String convertFileToString(String filePath) throws IOException
{
	File myFile = new File(filePath);
	String content="";
    try{
        content = FileUtils.readFileToString(myFile);
    }catch(IOException e){
        e.printStackTrace();
    }  
    
    return content;
}

// Sort JACCARD in descending order

	  public <K,V extends Comparable<? super V>> 
      List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
		  List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
		  Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() {
          @Override
          public int compare(Entry<K,V> e1, Entry<K,V> e2) {
              return e2.getValue().compareTo(e1.getValue());
          	}
		  }
       );

      return sortedEntries;
    }


}
