package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

public class ReadMailAPI {

	public void start() {
		String serverAdress;
		String serverPort;
		String user;
		String pass;

		System.out.println("Read mails using java mail API.");
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

			if (serverAdress.equals("") || !serverPort.matches("[0-9]+") || user.equals("") || pass.equals("")) {
				System.out.println("Error: Wrong input.");
				return;
			}

			Properties properties = new Properties();
			properties.put("mail.pop3.host", serverAdress);
			properties.put("mail.pop3.port", serverPort);

			Session emailSession = Session.getDefaultInstance(properties);
			// use pop3s instead of pop3 to enable ssl/tls encryption
			Store store = emailSession.getStore("pop3s");
			store.connect(serverAdress, user, pass);

			Folder inboxFolder = store.getFolder("INBOX");
			if (inboxFolder == null) {
				throw new Exception("Invalid folder");
			}
			inboxFolder.open(Folder.READ_ONLY);

			Message[] messages = inboxFolder.getMessages();
			System.out.println(messages.length + " messages found.");
			while (true) {
				System.out.print("IN:");
				String cmd = userInput.readLine();

				if (cmd.toLowerCase().equals("list")) {
					for (int i = 0, n = messages.length; i < n; i++) {
						Message message = messages[i];
						System.out.println("#" + i + ", Subject: " + message.getSubject());
					}
				} else if (cmd.toLowerCase().startsWith("retr")) {
					String id = cmd.toLowerCase().replace("retr ", "");
					if (id.matches("[0-9]+")) {

						int i = Integer.parseInt(id);
						Message message = messages[i];
						System.out.println("#########################################################");
						System.out.println("Email #" + (i + 1));
						System.out.println("Subject: " + message.getSubject());
						System.out.println("From: " + message.getFrom()[0]);
						
						if (message.getContent() instanceof Multipart) {
							System.out.println("Content: ");
							writeContent((Multipart) message.getContent());
						} else {
							System.out.println("Content: " + message.getContent().toString());
						}
					} else if (id.equalsIgnoreCase("all")) {
						for (int i = 0, n = messages.length; i < n; i++) {
							Message message = messages[i];
							System.out.println("#########################################################");
							System.out.println("Email #" + (i + 1));
							System.out.println("Subject: " + message.getSubject());
							System.out.println("From: " + message.getFrom()[0]);
							
							if (message.getContent() instanceof Multipart) {
								System.out.println("Content: ");
								writeContent((Multipart) message.getContent());
							} else {
								System.out.println("Content: " + message.getContent().toString());
							}

						}
					} else {
						System.out.println("Error: Wrong syntax.");
					}
				} else if(cmd.equalsIgnoreCase("help")){
					System.out.println("Commands: LIST; RETR ALL; RETR <number>; QUIT");
				} else if (cmd.equalsIgnoreCase("quit"))
					break;
			}

			inboxFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeContent(Multipart part) throws MessagingException, IOException {
		for (int i = 0; i < part.getCount(); i++) {
			BodyPart bodyPart = part.getBodyPart(i);
			Object content = bodyPart.getContent();
			if (content instanceof Multipart) {
				writeContent((Multipart) content);
			} else if (bodyPart.getContentType().toString().contains("text/plain")) {
				System.out.println(content.toString());
			} else if (bodyPart.getContentType().toString().contains("image/jpeg")) {
				System.out.println("Warning: E-Mail contains image.");
			} else if (bodyPart.getContentType().toString().contains("application")) {
				System.out.println("Warning: E-Mail contains additional attachment.");
			}
		}
	}

}
