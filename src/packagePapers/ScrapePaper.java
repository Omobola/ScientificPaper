package packagePapers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;

import com.jcraft.jsch.JSchException;

public class ScrapePaper {

	public ScrapePaper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SQLException 
	 * @throws JSchException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException, JSchException {
		// TODO Auto-generated method stub

		/*CodeBank cb = new CodeBank();
		List<String> address = new ArrayList<String>();
		String directoryPath = "C:\\Users\\Omobola\\Desktop";*/
		
		
		/* Download JSON files from partridge API 
		address = cb.createAddress();
		
		for(int cnt = 0; cnt<address.size(); cnt++)
		{
			System.out.println(address.get(cnt).toString());
		}
		
		String msg = cb.saveJSON(address, directoryPath);
		System.out.println(msg);
		*/
		
		//cb.extractData();
		
		CodeBank cb = new CodeBank();
		
		/*String url = "jdbc:mysql://mysql5.dcs.warwick.ac.uk:3306/cspmaidb";
		//String url = "jdbc:mysql://joshua.dcs.warwick.ac.uk:3306/cspmaidb";
	 
		String uname = "cspmaiweb";
		String pw = "yT9B3rfxli";*/
		

		
		
		//System.out.println(cc.PaperData(url, uname, pw, "Bolanle", "Data Mining", "Text Mining", "bola.oladapo@dcs.warwick.ac.uk","None for now","ggggg","jjjj"));
		
		String userlinks = "http://www2.warwick.ac.uk/fac/sci/dcs/people";
		
		String path = "http://farnsworth.papro.org.uk/api/papers/";
		
		String url = "jdbc:mysql://localhost:3307/ScientificPaper";
		String uname = "root";
		String pw = "bolanle";
		
		ConnectionClass cc = new ConnectionClass();
		CodeBank cd = new CodeBank();
		
		cc.connectToDatabase(url,uname,pw);
		
		/*String strSshUser = "cspmaiweb";                          // SSH loging username
        String strSshPassword = "1Adefowope";                 // SSH login password
        String strSshHost = "joshua.dcs.warwick.ac.uk";               // hostname or ip or SSH server
        int nSshPort = 22;                                  // remote SSH host port number
        String strRemoteHost = "mysql5.dcs.warwick.ac.uk";            // hostname or ip of your database server
        int nLocalPort = 3307;                                 // local port number use to bind SSH tunnel
        int nRemotePort = 3306;                                // remote port number of your database 
        String strDbUser = "test";                   // database loging username
        String strDbPassword = "yT9B3rfxli";                  // database login password

		
		//cc.portForwarding(strSshUser,strSshPassword,strSshHost,nSshPort,strRemoteHost,nLocalPort,nRemotePort );
        
       //cc.connectToDatabase(url, uname, pw);
        
       // cc.forwarding();
		*/
	}
}
