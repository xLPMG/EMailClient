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
			
			
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", serverAdress);
	        properties.put("mail.smtp.port", serverPort);
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        
	        properties.put("mail.smtp.ssl.trust", serverAdress);
	        
	        
	        
	        Session session = Session.getInstance(properties, 
	        	new Authenticator() {
	            	protected PasswordAuthentication getPasswordAuthentication() {
	            		return new PasswordAuthentication(user , pass);
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
	        message.setFrom(new InternetAddress(fromMail));
	        message.addRecipient(
	          Message.RecipientType.TO, new InternetAddress(toMail));
	        message.setSubject(subject);
	        
	        String text="";
			while(true) {
				String line = userInput.readLine();
				if(line.equals(".")) {
					break;
				}else {
					text+=line+"\n";
				}
			}
			message.setText(text);
			
			//Transport.send(message);
			Transport.send(message);
			System.out.println("message sent successfully");  
	        
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

