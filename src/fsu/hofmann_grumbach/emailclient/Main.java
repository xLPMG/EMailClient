package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fsu.hofmann_grumbach.emailclient.gui.MainWindow;

public class Main {

	static ReadSocket rS;
	static ReadMailAPI rM;
	
	public static void main(String[] args) {
		MainWindow gui = new MainWindow();
		gui.startx();
		try {
			chooseMethod();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void chooseMethod() throws IOException {
		System.out.println("Read mails using sockets(0) or using Java Mail API(1)?");
		BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
		while(true) {
	        String in = reader.readLine();
	        if(in.equals("0")) {
	        	rS = new ReadSocket();
	        	rS.start();
	        	break;
	        }else if(in.equals("1")) {
	        	rM = new ReadMailAPI();
	        	rM.start();
	        	break;
	        }
	        System.out.println("Wrong input.");
		}
	}

}
