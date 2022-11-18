package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SendSocket {

	public void start() {
		System.out.println("Read mails using sockets.");
		try {
			String localhost = InetAddress.getLocalHost().getHostAddress();
			// read user input using BufferedReader
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Server adress: ");
			String serverAddress = stdin.readLine();
			System.out.print("Port: ");
			String portStr = stdin.readLine();
			System.out.print("Sender e-mail address: ");
			String sender = stdin.readLine();
			System.out.print("Please enter your e-mail password: ");
			String password = stdin.readLine();
			System.out.print("Recipient e-mail address (separated by comma): ");
			String recipient = stdin.readLine();
			boolean encrypt=false;
			System.out.print("Encrypt mail? (0 = no, 1 = yes): ");
			while(true) {
	        String in = stdin.readLine();
	        if(in.equals("0")) {
	        	encrypt=false;
	        	break;
	        }else if(in.equals("1")) {
	        	encrypt=true;
	        	break;
	        }
	        System.out.println("Wrong input.");
			}
			System.out.print("What is the subject of your message? ");
			String subject = stdin.readLine();
			
			System.out.print("You can now type in your message: (Type END in a new line at the end of our message)\n");

			List<String> message = new ArrayList<>();
			while(true) {
				String line = stdin.readLine();
				if(line.equals("END")) {
					break;
				}else {
					message.add(line);
				}
			}

			// check if port is a number and that the adress isnt empty
			if (!portStr.matches("[0-9]+")) {
				System.out.println("Error: Port input is not a number");
				return;
			}
			int serverPort = Integer.parseInt(portStr);

			if (serverAddress.equals("")) {
				System.out.println("Error: Please provide a server adress");
				return;
			}

			Socket servSocket = new Socket(serverAddress, serverPort);
			DataOutputStream os = new DataOutputStream(servSocket.getOutputStream());
			DataInputStream is = new DataInputStream(servSocket.getInputStream());

			PrintWriter sockout = new PrintWriter(os, true);
			BufferedReader sockin = new BufferedReader(new InputStreamReader(is));

			if (servSocket != null && os != null && is != null) {
				sockout.println("HELO " + localhost);
				handleOutput(sockin.readLine(), "HELO");
				sockout.println("AUTH LOGIN");
				handleOutput(sockin.readLine(), "AUTH LOGIN");
				sockout.println(Base64.getEncoder().encodeToString(sender.getBytes()));
				handleOutput(sockin.readLine(), "USERNAME INPUT");
				sockout.println(Base64.getEncoder().encodeToString(password.getBytes()));
				handleOutput(sockin.readLine(), "PASSWORD INPUT");
				sockout.println("MAIL From:" + sender);
				handleOutput(sockin.readLine(), "MAIL FROM");
				sockout.println("RCPT To:" + recipient);
				handleOutput(sockin.readLine(), "RCPT TO");
				sockout.println("DATA");
				handleOutput(sockin.readLine(), "DATA");
				sockout.println("Subject: " + subject);
				for(String line : message) {
					sockout.println(line);
				}
				sockout.println(".");
				handleOutput(sockin.readLine(), "END OF DATA");
				if(encrypt) {
				sockout.println("STARTTLS");
				handleOutput(sockin.readLine(), "STARTTLS");
				}
				sockout.println("QUIT");
				handleOutput(sockin.readLine(), "QUIT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleOutput(String serverResponse, String operation) {
		System.out.println(operation + ": " + serverResponse);
	}
}
