package fsu.hofmann_grumbach.emailclient.util;

public class Account {
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String serverPOP3;
	private int serverPOP3Port;
	private String serverSMTP;
	private int serverSMTPPort;

	public Account(String username, String password, String email, String name, String surname, String serverPOP3,
			int serverPOP3Port, String serverSMTP, int serverSMTPPort) {
		this.username=username;
		this.password=password;
		this.email=email;
		this.name=name;
		this.surname=surname;
		this.serverPOP3=serverPOP3;
		this.serverPOP3Port=serverPOP3Port;
		this.serverSMTP=serverSMTP;
		this.serverSMTPPort=serverSMTPPort;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getServerPOP3() {
		return serverPOP3;
	}

	public void setServerPOP3(String serverPOP3) {
		this.serverPOP3 = serverPOP3;
	}

	public int getServerPOP3Port() {
		return serverPOP3Port;
	}

	public void setServerPOP3Port(int serverPOP3Port) {
		this.serverPOP3Port = serverPOP3Port;
	}

	public String getServerSMTP() {
		return serverSMTP;
	}

	public void setServerSMTP(String serverSMTP) {
		this.serverSMTP = serverSMTP;
	}

	public int getServerSMTPPort() {
		return serverSMTPPort;
	}

	public void setServerSMTPPort(int serverSMTPPort) {
		this.serverSMTPPort = serverSMTPPort;
	}
	
	public String getData() {
	return password+"###"+email+"###"+name+"###"+surname+"###"+serverPOP3+"###"+serverPOP3Port+"###"+serverSMTP+"###"+serverSMTPPort;
	}
}
