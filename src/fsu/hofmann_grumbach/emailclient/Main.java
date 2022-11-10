package fsu.hofmann_grumbach.emailclient;

public class Main {

	public static void main(String[] args) {
		ReadSocket r = new ReadSocket();
		r.start();

		ReadMailAPI rM = new ReadMailAPI();
		 //rM.start();
	}

}
