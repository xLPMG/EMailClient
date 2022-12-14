package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ReadSocket {

	String serverAddress = "";
	int serverPort;
	SSLSocket client = null;

	public void start() {

		System.out.println("Read mails using sockets.");

		try {
			// read user input using BufferedReader
			BufferedReader stdin;
			stdin = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Server adress:");
			serverAddress = stdin.readLine();
			System.out.print("Port:");
			String portStr = stdin.readLine();
			// check if port is a number and that the adress isnt empty
			if (!portStr.matches("[0-9]+")) {
				System.out.println("Error: Port input is not a number");
				return;
			}
			serverPort = Integer.parseInt(portStr);

			if (serverAddress.equals("")) {
				System.out.println("Error: Please provide a server adress");
				return;
			}

			// initiate new client with specified adress and port
			SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			client = (SSLSocket) factory.createSocket(serverAddress, serverPort);

			// define input- and output stream for client-server connection
			InputStream is = client.getInputStream();
			BufferedReader sockin = new BufferedReader(new InputStreamReader(is));

			OutputStream os = client.getOutputStream();
			PrintWriter sockout = new PrintWriter(os, true);

			// accept user commands until user quits:
			while (true) {
				System.out.print("IN:");
				String userInput = stdin.readLine();
				sockout.println(userInput);

				String serverOutput = sockin.readLine();
				System.out.println("OUT:" + serverOutput);

				// commands retr and list require a multi-line output
				if ((userInput.toLowerCase().startsWith("retr") || userInput.equalsIgnoreCase("list"))
						&& serverOutput.charAt(0) == '+')
					while (true) {
						serverOutput = sockin.readLine();
						if (serverOutput != null && serverOutput.length() == 1)
							if (serverOutput.charAt(0) == '.')
								break;
						System.out.println(serverOutput);
					}

				// quit loop
				if (userInput.equalsIgnoreCase("quit")) {
					System.out.println("Disconnected");
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
	}
}
