package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;

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
			
			char[] password=pass.toCharArray();
			
			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", serverAdress);
	        properties.put("mail.smtp.port", serverPort);
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        
	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });
	        
	        
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}