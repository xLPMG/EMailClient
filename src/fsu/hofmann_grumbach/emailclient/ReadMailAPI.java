package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

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

			if (serverAdress.equals("") || serverPort.equals("") || user.equals("") || pass.equals("")) {
				return;
			}

			Properties properties = new Properties();

			properties.put("mail.pop3.host", serverAdress);
			properties.put("mail.pop3.port", serverPort);

			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			Store store = emailSession.getStore("pop3s");

			store.connect("pop3.uni-jena.de", user, pass);

			Folder inboxFolder = store.getFolder("INBOX");
			inboxFolder.open(Folder.READ_ONLY);

			Message[] messages = inboxFolder.getMessages();
			System.out.println(messages.length+" messages found.");
			System.out.println("Commands: LIST; RETR ALL; RETR <number>; QUIT");
			while (true) {
				System.out.print("Input:");
				String cmd = userInput.readLine();

				if (cmd.toLowerCase().startsWith("list")) {
					for (int i = 0, n = messages.length; i < n; i++) {
						Message message = messages[i];
						System.out.println("#" + i + ", Subject: " + message.getSubject());
					}
				} else if (cmd.toLowerCase().startsWith("retr")) {
					String id = cmd.toLowerCase().replace("retr ", "");
					if (id.matches("[0-9]+")) {

						int i = Integer.parseInt(id);
						Message message = messages[i];
						System.out.println("######################################################");
						System.out.println("Email #" + (i));
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
							System.out.println("######################################################");
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
					}
				} else if (cmd.toLowerCase().startsWith("quit"))
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
				System.out.println("Warning: E-Mail contains image");
			} else if (bodyPart.getContentType().toString().contains("application")) {
				System.out.println("Warning: E-Mail contains additional attachment");
			}
		}
	}

}
