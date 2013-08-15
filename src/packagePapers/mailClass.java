package packagePapers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailClass {

	public mailClass() {
		// TODO Auto-generated constructor stub
	}

	public String sendEmail (String receiverEmail,String content) throws AddressException, MessagingException
	{
	String response = "Email Successfully Sent!";	
	String host = "smtp.gmail.com";
    String from = "bola.oladapo@gmail.com";
    String pass = "0lum@y0w@";
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true"); // added this line
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");

    //String[] to = {"b.f.oladapo@warwick.ac.uk"}; // added this line

    String[] to = {receiverEmail}; // added this line

    
    System.out.println(to.length);
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));

    InternetAddress[] toAddress = new InternetAddress[to.length];

    // To get the array of addresses
    for( int i=0; i < to.length; i++ ) { // changed from a while loop
        toAddress[i] = new InternetAddress(to[i]);
    }
    System.out.println(Message.RecipientType.TO);

    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
    }
    message.setSubject("Testing Dissertation Project - Please Evaluate");
    message.setText(content);
    Transport transport = session.getTransport("smtp");
    transport.connect(host, from, pass);
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
 
    
    return response;
}
}