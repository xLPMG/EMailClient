package fsu.hofmann_grumbach.emailclient;


import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.net.Authenticator;
import javax.mail.*;
import javax.mail.internet.*;


import java.util.Properties;


public class SendMailAPI {
	
	public void start() {

		System.out.println("Sending mails using java mail API");
		String serverAdress;
		String serverPort;
		String user;
		String pass;
		
		try {
			BufferedReader userInput;
			userInput = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Server adress:");
			serverAdress = userInput.readLine();
			System.out.print("Port:");
			serverPort = userInput.readLine();
			System.out.print("USER:");
			user = userInput.readLine();
			System.out.print("PASS:");
			pass = userInput.readLine();
			
			//char[] password=pass.toCharArray();
			
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", "mail.gmx.net");
	        properties.put("mail.smtp.port", 587);
	        //properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.user", "");
	        properties.put("mail.smtp.password", "");
	        
	        properties.put("mail.smtp.ssl.trust", "r");
	        
	        
	        
	        Session session = Session.getInstance(properties, 
	        	new Authenticator() {
	            	protected PasswordAuthentication getPasswordAuthentication() {
	            		return new PasswordAuthentication("", "");
	            }
	        });
	        
	        System.out.print("From:");
			String fromMail = userInput.readLine();
			System.out.print("To:");
			String toMail = userInput.readLine();
			System.out.print("Subject:");
			String subject = userInput.readLine();
			
			System.out.print("Write your message:");
			String msg = userInput.readLine();
			
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("richard_hofmann@gmx.de"));
	        message.setRecipients(
	          Message.RecipientType.TO, InternetAddress.parse("richard_hofmann@gmx.de"));
	        message.setSubject(subject);
	        
	        MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
	        
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			
			message.setContent(multipart);

			//Transport.send(message);
			Transport transport = session.getTransport("smtp");
		     transport.connect(null, "", "");
			
			
			System.out.println("message sent successfully");  
	        
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
/*
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendMailAPI {  
	public void start() {  
  
  String host="mail.gmx.net";  
  final String user="";//change accordingly  
  final String password="";//change accordingly  
    
  String to="";//change accordingly  
  
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true"); 
   props.put("mail.smtp.port", 587);
   props.put("mail.smtp.starttls.enable", "true");
   props.setProperty("mail.smtp.user",user);
   props.setProperty("mail.smtp.password", password);
   props.setProperty("mail.smtp.auth", "true"); 
     
   Session session = Session.getInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("javatpoint");  
     message.setText("This is simple program of sending email using JavaMail API");  
       
    //send the message  
     
     Transport transport = session.getTransport("smtp");
     transport.connect(null, user, password);
     Transport.send(message);  
     System.out.println("mail snet");
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();}  
 }  
}  */

