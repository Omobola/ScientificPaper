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
	
	public  TreeSet<String> union(TreeSet<String> setA, TreeSet<String> setB) {
	    TreeSet<String> tmp = new TreeSet<String>(setA);
	    tmp.addAll(setB);
	    return tmp;
	  }

public  TreeSet<String> intersection(TreeSet<String> setA, TreeSet<String> setB) {
	TreeSet<String> tmp = new TreeSet<String>();
	    for (String x : setA)
	      if (setB.contains(x))
	        tmp.add(x);
	    return tmp;
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



	  public  <T> Set<T> difference(Set<T> setA, Set<T> setB) {
	    Set<T> tmp = new TreeSet<T>(setA);
	    tmp.removeAll(setB);
	    return tmp;
	  }

	/*  public  <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
	    Set<T> tmpA;
	    Set<T> tmpB;

	    tmpA = union(setA, setB);
	    tmpB = intersection(setA, setB);
	    return difference(tmpA, tmpB);
	  }*/

	  public  <T> boolean isSubset(Set<T> setA, Set<T> setB) {
	    return setB.containsAll(setA);
	  }

	  public  <T> boolean isSuperset(Set<T> setA, Set<T> setB) {
	    return setA.containsAll(setB);
	  }

	  
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
