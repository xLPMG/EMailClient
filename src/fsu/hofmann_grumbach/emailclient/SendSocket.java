package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class SendSocket {

	String serverAdress = "";
	int serverPort;
	String sender="";
	String recipient="";
	String message="";
	Socket socket = null;
	InetAddress localhost;

	public void start() {
		System.out.println("Read mails using sockets.");
		try {
			localhost = InetAddress.getLocalHost();
			// read user input using BufferedReader
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Server adress:");
			serverAdress = stdin.readLine();
			System.out.print("Port:");
			String portStr = stdin.readLine();
			System.out.print("Sender e-mail address:");
			sender = stdin.readLine();
			System.out.print("Recipient e-mail address (separated by comma):");
			recipient = stdin.readLine();
			
			
			// check if port is a number and that the adress isnt empty
			if (!portStr.matches("[0-9]+")) {
				System.out.println("Error: Port input is not a number");
				return;
			}
			serverPort = Integer.parseInt(portStr);

			if (serverAdress.equals("")) {
				System.out.println("Error: Please provide a server adress");
				return;
			}

			socket = new Socket(serverAdress, serverPort);
			InputStream is = socket.getInputStream();
			BufferedReader sockin = new BufferedReader(new InputStreamReader(is));

			OutputStream os = socket.getOutputStream();
			PrintWriter sockout = new PrintWriter(os, true);
			
			String initialID = sockin.readLine();
		      System.out.println(initialID);
		      System.out.println("EHLO " + localhost.getHostName());
		      sockout.println("EHLO " + localhost.getHostName());
		      String welcome = sockin.readLine();
		      System.out.println(welcome);
		      System.out.println("MAIL From:<" + sender + ">");
		      sockout.println("MAIL From:<" + sender + ">");
		      String senderOK = sockin.readLine();
		      System.out.println(senderOK);
		      System.out.println("RCPT TO:<" + recipient + ">");
		      sockout.println("RCPT TO:<" + recipient + ">");
		      String recipientOK = sockin.readLine();
		      System.out.println(recipientOK);
		      System.out.println("DATA");
		      sockout.println("DATA");
		      sockout.println("du hurensohn");
		      System.out.println(".");
		      sockout.println(".");
		      String acceptedOK = sockin.readLine();
		      System.out.println(acceptedOK);
		      System.out.println("QUIT");
		      sockout.println("QUIT");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(BufferedReader in, BufferedWriter out, String s) {
		try {
			out.write(s + "\n");
			out.flush();
			System.out.println(s);
			s = in.readLine();
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send(BufferedWriter out, String s) {
		try {
			out.write(s + "\n");
			out.flush();
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
