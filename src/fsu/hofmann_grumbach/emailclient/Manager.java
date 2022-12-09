package fsu.hofmann_grumbach.emailclient;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fsu.hofmann_grumbach.emailclient.gui.LoginWindow;
import fsu.hofmann_grumbach.emailclient.util.DataHandler;

public class Manager {

	public void init() {
		DataHandler dH = new DataHandler();
		startx(dH);
		
		try {
			chooseMethod();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void chooseMethod() throws IOException {
		System.out.println("Read mails using sockets(0) or using Java Mail API(1)?");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String in = reader.readLine();
			if (in.equals("0")) {
				ReadSocket rS = new ReadSocket();
				rS.start();
				break;
			} else if (in.equals("1")) {
				ReadMailAPI rM = new ReadMailAPI();
				rM.start();
				break;
			}
			System.out.println("Wrong input.");
		}
	}
	
	private void startx(DataHandler dH) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow(dH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
