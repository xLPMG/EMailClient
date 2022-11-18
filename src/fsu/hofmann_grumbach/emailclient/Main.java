package fsu.hofmann_grumbach.emailclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static ReadSocket rS;
	static ReadMailAPI rM;
	
	public static void main(String[] args) {
		try {
			chooseMethod();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void chooseMethod() throws IOException {
		System.out.println("Read mails (0) or send mails (1)?");

		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while(true) {
	        String in = reader.readLine();
	        
	        if(in.equals("0")) {
	    		System.out.println("Read mails using sockets(0) or using Java Mail API(1)?");
	    		in = reader.readLine();
	    		if(in.equals("0")){
		        	rS = new ReadSocket();
		        	rS.start();
		        	break;
	    		}else if(in.equals("1")) {
		        	rM = new ReadMailAPI();
		        	rM.start();
		        	break;
		        }
	        }else if(in.equals("1")) {
	    		System.out.println("Send mails using sockets(0) or using Java Mail API(1)");
	    		in = reader.readLine();
	 	    		if(in.equals("0")){
	 		        	
	 		        	break;
	 	    		}else if(in.equals("1")) {
	 		        	//sM = new SendMailAPI();
	 		        	//sm.start();
	 		        	break;
	 		        }
	 	        
	        }
	        System.out.println("Wrong input.");
		}
	}

}
