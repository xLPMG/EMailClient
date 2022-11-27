package fsu.hofmann_grumbach.emailclient;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fsu.hofmann_grumbach.emailclient.gui.MainWindow;
import fsu.hofmann_grumbach.emailclient.util.DataHandler;

public class Manager {

	public void init() {
		DataHandler dH = new DataHandler();
		dH.addAccount("lpmg", "lpmg@web.de", "pass", "luca", "phil", "pop3", 0, "smtp", 0);
		startx(dH);
		
		try {
			chooseMethod();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void chooseMethod() throws IOException {
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
	
	public void startx(DataHandler dH) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(dH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
