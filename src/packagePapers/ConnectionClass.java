package packagePapers;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ConnectionClass {

	public ConnectionClass() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	
	
	
	public void portForwarding(String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException 
	{
		final JSch jsch = new JSch();
	    Session session = jsch.getSession( strSshUser, strSshHost, 22);
	    session.setPassword( strSshPassword );

	    final Properties config = new Properties();
	    config.put( "StrictHostKeyChecking", "no" );
	    session.setConfig( config );

	    session.connect();
	    System.out.println("Connected");
	    int assinged_port=session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
	    System.out.println("localhost:"+assinged_port+" -> "+strRemoteHost+":"+nRemotePort);
	    System.out.println("Port Forwarded");
	    
	    
    }
 
	public void forwarding() throws SQLException
	{
		
		int lport=3307;
        String rhost="mysql5.dcs.warwick.ac.uk";
        String host="joshua.dcs.warwick.ac.uk";
        int rport=3306;
        String user="cspmaiweb";
        String password="1Adefowope";
        String dbuserName = "cspmaiweb";
        String dbpassword = "yT9B3rfxli";
     
       // String url = "jdbc:mysql://localhost:"+lport+"/test";
        
        String url = "jdbc:mysql://joshua.dcs.warwick.ac.uk:"+rport+"/test";
        
        String driverName="com.mysql.jdbc.Driver";
        Connection conn = null;
        Session session= null;
        try{
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
           // int assinged_port=session.setPortForwardingL(lport, rhost, rport);
           // System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            System.out.println("Port Forwarded");
             
            //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(conn != null && !conn.isClosed()){
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }
    }
	
	public void connectToDatabase(String url, String user, String password) throws SQLException
	{
		System.out.println("-------- MySQL JDBC Connection Testing ------------");
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
	 
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;
	 
		try {
			connection = DriverManager
			.getConnection(url,user,password);
	 
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
	 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	  }	

	public void insertToTable(String url, String user, String password) throws SQLException
	{
		String sql = "INSERT INTO trial (name, address, pnumber) VALUES ('Disneyland','Mickey Mouse', 'North America')";
        
		Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = (Statement) con.createStatement(); 
	    
	    st.executeUpdate(sql);

	}


	public String patridgeLinks(String url, String user, String password,String link) throws SQLException
	{
		String msg="Insertion Successful";
		String sql = "INSERT INTO patridge_links(Links) VALUES ('"+link+"')";
        
		Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = (Statement) con.createStatement(); 
	    
	    st.executeUpdate(sql);
		return msg;

	}

	public String paper_information(String url, String user, String password,List<String> link) throws SQLException
	{
		String msg="Insertion Successful";
		Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = (Statement) con.createStatement(); 
	    
	    for(int i =0; i< link.size(); i++)
	    {
	    String sql = "INSERT INTO paper_information(Link) VALUES ('"+link.get(i).toString()+"')";
        st.executeUpdate(sql);
	    }
	    
	    System.out.println("Insertion Successful");
	    
		return msg;

	}

	public String UserLinks(String url, String user, String password,String Name, String link) throws SQLException
	{
		String msg="Insertion Successful";
		//String name1= Name.replaceAll("?", "");
		
		String sql = "INSERT INTO user_links (Name,Link) VALUES ('"+Name+"','"+link+"')";
        
		Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = (Statement) con.createStatement(); 
	    
	    st.executeUpdate(sql);
		return msg;

	}
	
	public void update(String url, String uname, String pw,String Field,String value, String name) throws SQLException
	{
		/* url = "jdbc:mysql://localhost:3307/ScientificPaper";
		 uname = "root";
		 pw = "bolanle";*/
		
		 String msg="Insertion Successful";
		 Connection con = null;
	        Statement st = null;
	        
	        con = DriverManager.getConnection(url, uname, pw);
           
	        st = con.createStatement();

            st.executeUpdate("UPDATE user_links SET "+String.valueOf(Field)+" = '"+String.valueOf(value)+"' "
                    + "WHERE Name = '"+String.valueOf(name)+"'");
           
            con.close();
            
		 System.out.println("Update");
	}
	

	public void updatePaper(String url, String uname, String pw,String Field,List<String> value, List<String> name) throws SQLException
	{
		 url = "jdbc:mysql://localhost:3307/ScientificPaper";
		 uname = "root";
		 pw = "bolanle";
		 String msg="Insertion Successful";
		 Connection con = null;
	        Statement st = null;
	        
	        con = DriverManager.getConnection(url, uname, pw);
           
	        st = con.createStatement();

	        for(int i =0; i<value.size(); i++)
	        {
	        	String newValue = String.valueOf(value.get(i)).replaceAll("'", "");
	        	System.out.println(newValue);
	        	System.out.println(String.valueOf(name.get(i)));
	            
	        	st.executeUpdate("UPDATE paper_information SET "+String.valueOf(Field)+" = '"+String.valueOf(newValue)+"' "
                   + "WHERE Link = '"+String.valueOf(name.get(i))+"'");
	        	
	        }
            con.close();
            
		 System.out.println("Update");
	}

	public Map<String, String> select(String url, String uname, String pw)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM user_links";
	      Map<String, String> linkMap = new HashMap<String, String>();
	         
	      try{
			
	    	  
			 Connection conn = DriverManager.getConnection(url, uname, pw);
		     
	      
	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String name = rs.getString("Name");
	        String link = rs.getString("Link");
	        
	        linkMap.put(String.valueOf(name),String.valueOf(link));
	        
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}
	
	public List<String> fromPatridge(String url, String uname, String pw)
	{
		   String query = "SELECT * FROM patridge_links";
	      List<String> linkMap = new ArrayList<String>();
	         
	      try{
	    	  Connection conn = DriverManager.getConnection(url, uname, pw);
	    	  // create the java statement
	    	  Statement st = conn.createStatement();
	      
	            ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	         while (rs.next())
	           {
	              String link = rs.getString("Links");
	              linkMap.add(String.valueOf(link));
	            }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}
	

	
	public List<String> selectLink(String url, String uname, String pw)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT Link FROM paper_information";
	      List<String> linkMap = new ArrayList<String>();
	         
	      try{
	    	  Connection conn = DriverManager.getConnection(url, uname, pw);
	    	  // create the java statement
	    	  Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String link = rs.getString("Link");
	        
	        linkMap.add(String.valueOf(link));
	        
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}
	

	
	public String UserData(String url, String user, String password,String name,String profile, String groups, String email, String publication) throws SQLException
	{
		String msg="Insertion Successful";
		
		String sql = "INSERT INTO User_Data(Name,Email,Research_Profile,Research_Groups,Selected_Publications) VALUES ('"+name+"','"+email+"','"+profile+"','"+groups+"','"+publication+"')";
        
		Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = (Statement) con.createStatement(); 
	    
	    st.executeUpdate(sql);
		return msg;

	}

	public String PaperData(String url, String user, String password,String uri,String title, String abstracts, String authors, String keywords, String Topicmodel,String wholePaper) throws SQLException
	{
		String msg="Insertion Successful";
		
		String sql = "INSERT INTO Paper_Data(URL,Title,Abstract,Authors,Keywords,Topic_Model,Whole_Document) VALUES ('"+uri+"','"+title+"','"+abstracts+"','"+authors+"','"+keywords+"','"+Topicmodel+"','"+wholePaper+"')";
        Connection con = DriverManager.getConnection(url, user, password);
	    Statement st = (Statement) con.createStatement(); 
	    
	    st.executeUpdate(sql);
		return msg;

	}

	
	public void updateDoc(String url, String uname, String pw,String directoryPath) throws SQLException, IOException
	{
		 String msg="Insertion Successful";
		 Connection con = null;
	     Statement st = null;
	     CodeBank cb = new CodeBank();
	        
	     con = DriverManager.getConnection(url, uname, pw);
           
         List<String> listFiles = new ArrayList<String>();
			
         listFiles = cb.fileNAMES(directoryPath);
         String val ="http://farnsworth.papro.org.uk/file/";
         String pathLink = "C:\\Users\\Omobola\\Desktop\\NEWXML\\";
			
         String Field = "Whole_Document";
			
         List<String> urlFetch = new ArrayList<String>();
		 List<String> listIndex = new ArrayList<String>();
 		
         urlFetch = this.selectLink(url, uname, pw) ; 
		    
         for(int cntr = 0; cntr < urlFetch.size(); cntr++)
         {
        	 String num = urlFetch.get(cntr).toString().replaceAll("http://farnsworth.papro.org.uk/file/", "");
        	 listIndex.add(num);
         }
			   
		for (int xml =0; xml < listIndex.size(); xml++)
			{
				String xmlpath = pathLink+String.valueOf(listIndex.get(xml))+".xml";
				
				String xmlfile = cb.convertFileToString(xmlpath);
				String printxml = cb.extractData(xmlfile);
				
				st = con.createStatement();

				String newValue = printxml.replaceAll("'", "");
		        //System.out.println(newValue);
		        	
				String nameLink = val+String.valueOf(listIndex.get(xml));
		        	
				System.out.println(nameLink+"::::"+xmlpath+"::::"+newValue);
				
				st.executeUpdate("UPDATE paper_information SET "+String.valueOf(Field)+" = '"+String.valueOf(newValue)+"' "
	             + "WHERE Link = '"+String.valueOf(nameLink)+"'");
				
			}
            con.close();
            
		 System.out.println("Update");
	}

	
	public Map<String, String> selectAll(String url, String uname, String pw)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT Link,Abstract,Whole_Document FROM paper_information";
	      Map<String, String> linkMap = new HashMap<String, String>();
	         
	      try{
	    	  Connection conn = DriverManager.getConnection(url, uname, pw);
	    	  // create the java statement
	    	  Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  String link = rs.getString("Link");
	    	  String abs = rs.getString("Abstract");
	    	  String body = rs.getString("Whole_Document");
	    	  String doc = abs+" "+body;
		        
	        linkMap.put(String.valueOf(link),String.valueOf(doc));
	        
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}

	
	public void updateModel(String url, String uname, String pw,String key, String data) throws SQLException, IOException
	{
		 String msg="Insertion Successful";
		 Connection con = null;
	     Statement st = null;
	     CodeBank cb = new CodeBank();
	        
	     con = DriverManager.getConnection(url, uname, pw);
         String Field = "Topic_Model";
			
        String conc = "http://farnsworth.papro.org.uk/file/";
        String nameLink = conc+key;
        
        System.out.println(nameLink);
        
        st = con.createStatement();

        st.executeUpdate("UPDATE paper_information SET "+String.valueOf(Field)+" = '"+String.valueOf(data)+"' "
	             + "WHERE Link = '"+String.valueOf(nameLink)+"'");
		
        con.close();
        
        System.out.println("Update");
	}


	public Map<String, String> userAll(String url, String uname, String pw)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT Name,Research_Profile,Research_Groups FROM user_links";
	      Map<String, String> linkMap = new HashMap<String, String>();
	         
	      try{
	    	  Connection conn = DriverManager.getConnection(url, uname, pw);
	    	  // create the java statement
	    	  Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  String name = rs.getString("Name");
	    	  String profile = rs.getString("Research_Profile");
	    	  String group = rs.getString("Research_Groups");
	    	//  String pub = rs.getString("Selected_Publications");
		   
	    	  String user = profile+" "+group+" ";
		        
	        linkMap.put(String.valueOf(name),String.valueOf(user));
	        
	      }
	      st.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}

	
	public void updateFreq(String url, String uname, String pw,String key, String data) throws SQLException, IOException
	{
		 String msg="Insertion Successful";
		 Connection con = null;
	     Statement st = null;
	     CodeBank cb = new CodeBank();
	        
	     con = DriverManager.getConnection(url, uname, pw);
         String Field = "TermFrequency";
			
        String conc = "http://farnsworth.papro.org.uk/file/";
        String nameLink = conc+key;
        
        System.out.println(nameLink);
        
        st = con.createStatement();

        st.executeUpdate("UPDATE paper_information SET "+String.valueOf(Field)+" = '"+String.valueOf(data)+"' "
	             + "WHERE Link = '"+String.valueOf(nameLink)+"'");
		
        con.close();
        
        System.out.println("Update");
	}
	
	public void updateUserModel(String url, String uname, String pw,String key, String data) throws SQLException, IOException
	{
		 String msg="Insertion Successful";
		 Connection con = null;
	     Statement st = null;
	     CodeBank cb = new CodeBank();
	        
	     con = DriverManager.getConnection(url, uname, pw);
         String Field = "New_Model";
			
        String nameLink = key;
        
        System.out.println(nameLink);
        
        st = con.createStatement();

        st.executeUpdate("UPDATE user_links SET "+String.valueOf(Field)+" = '"+String.valueOf(data)+"' "
	             + "WHERE Name = '"+String.valueOf(nameLink)+"'");
		
        con.close();
        
        System.out.println("Update");
	}


	public void updateRec(String url, String uname, String pw,String key, List data) throws SQLException, IOException
	{
		 String msg="Insertion Successful";
		 Connection con = null;
	     Statement st = null;
	     CodeBank cb = new CodeBank();
	        
	     con = DriverManager.getConnection(url, uname, pw);
         String Field = "REC";
			
        String nameLink = key;
        
        System.out.println(nameLink);
        
        st = con.createStatement();

        st.executeUpdate("UPDATE user_links SET "+String.valueOf(Field)+" = '"+String.valueOf(data)+"' "
	             + "WHERE Name = '"+String.valueOf(nameLink)+"'");
		
        con.close();
        
        System.out.println("Update");
	}

	public Map<String,String> selectRec(String url, String uname, String pw)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT Name,Recommendation FROM user_links";
	      Map<String,String> linkMap = new HashMap<String,String>();
	         
	      try{
	    	  Connection conn = DriverManager.getConnection(url, uname, pw);
	    	  // create the java statement
	    	  Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  String link = rs.getString("Recommendation");
	    	  String name = rs.getString("Name");
		        
	        linkMap.put(String.valueOf(name),String.valueOf(link));
	        
	      }
	      st.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}

	public Map<String, String> selectMail(String url, String uname, String pw,String key)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT Original_Url, Title, Abstract FROM paper_information WHERE Link = '"+key+"'";
	      
	      Map<String, String> linkMap = new HashMap<String, String>();
	         
	      try{
			
	    	  
			 Connection conn = DriverManager.getConnection(url, uname, pw);
		     
	      
	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String ourl = rs.getString("Original_Url");
	        String title = rs.getString("Title");
	        String abs = rs.getString("Abstract");
	        
	        String body = "Title : "+title+"\n"+"Abstract : "+abs;
	        linkMap.put(String.valueOf(ourl),String.valueOf(body));
	        
	      }
	      st.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return linkMap;
	}
	
	public String selectEmail(String url, String uname, String pw,String key)
	{
		// our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT Email FROM user_links WHERE Name = '"+key+"'";
	      String email="";
	      
	      try{
	    	  Connection conn = DriverManager.getConnection(url, uname, pw);
	    	  // create the java statement
	    	  Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  String link = rs.getString("Email");
	    	    
	    	  email = String.valueOf(link);
	        
	      }
	      st.close();
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return email;
	}
}