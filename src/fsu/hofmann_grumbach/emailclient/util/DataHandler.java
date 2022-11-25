package fsu.hofmann_grumbach.emailclient.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class DataHandler {

	// HashMap<ProgramName, username:password>
	String os = "win";

	private HashMap<String, String> accountDataMap = new HashMap<>();
	private HashMap<String, String> emailDataMap = new HashMap<>();
	private File programFolder;
	private File userData;
	private File emailData;
	private AES aes;
	private String keycode;
	private String generalPassword = "";
	private String hint = "";

	public DataHandler() {
		os = System.getProperty("os.name").toLowerCase();
		init();
		loadData();
	}

	private void init() {
		if (os.contains("win")) {
			programFolder = new File(System.getenv("APPDATA") + "/EMC-GrumHofm");
			if (!programFolder.exists()) {
				programFolder.mkdirs();
			}
			userData = new File(System.getenv("APPDATA") + "/EMC-GrumHofm/userData.dat");
			if (!userData.exists()) {
				try {
					userData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (os.contains("mac")) {
			programFolder = new File(System.getProperty("user.home", "."), "Library/Application Support/" + "EMC-GrumHofm");
			if (!programFolder.exists()) {
				programFolder.mkdirs();
			}
			userData = new File(System.getProperty("user.home", "."), "Library/Application Support/EMC-GrumHofm/userData.dat");
			if (!userData.exists()) {
				try {
					userData.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		aes = new AES();
		try {
			keycode = getKeycode();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getKeycode() throws IOException {
		String salt = "u8IkY=Tnij?S";

		if (os.contains("win")) {
			String command = "wmic csproduct get UUID";
			StringBuffer output = new StringBuffer();

			Process SerNumProcess = Runtime.getRuntime().exec(command);
			BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));

			String line = "";
			while ((line = sNumReader.readLine()) != null) {
				output.append(line + "\n");
			}
			String MachineID = output.toString().substring(output.indexOf("\n"), output.length()).trim();
			try {
				SerNumProcess.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return MachineID + salt;
		} else if (os.contains("mac")) {
			String command = "system_profiler SPHardwareDataType | awk '/UUID/ { print $3; }'";
			StringBuffer output = new StringBuffer();

			Process SerNumProcess = Runtime.getRuntime().exec(command);
			BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));

			String line = "";
			while ((line = sNumReader.readLine()) != null) {
				output.append(line + "\n");
			}

			String MachineID = output.toString().substring(output.indexOf("UUID: "), output.length()).replace("UUID: ",
					"");
			try {
				SerNumProcess.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sNumReader.close();
			return MachineID + salt;
		}
		return salt + salt + salt;

	}

	private void loadData() {
		try {
			accountDataMap.clear();
			BufferedReader br = new BufferedReader(new FileReader(userData.getAbsolutePath()));
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String decodedLine = aes.decode(line, keycode);
					//TODO
//					if (decodedLine.startsWith("pass:")) {
//						generalPassword = decodedLine.split(":")[1];
//					} else if (decodedLine.startsWith("hint:")) {
//						hint = decodedLine.split(":")[1];
//						hint = hint.replace("x3", "");
//					} else {
//						String data[] = decodedLine.split("[,]");
//						dataMap.put(data[0], data[1]);
//					}
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveData() {
		try {
			FileWriter writer = new FileWriter(userData.getAbsolutePath());
			writer.write("");
			for (Entry<String, String> entry : accountDataMap.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				writer.write(aes.encode(key + "," + value, keycode));
				writer.write(System.getProperty("line.separator"));
			}
			//TODO
//			writer.write(aes.encode("pass:" + generalPassword, keycode));
//			writer.write(System.getProperty("line.separator"));
//			writer.write(aes.encode("hint:" + hint + "x3", keycode));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadData();
	}

	public HashMap<String, String> getAccountData() {
		return accountDataMap;
	}

	public void setAccountData(HashMap<String, String> dataMap) {
		this.accountDataMap = dataMap;
		saveData();
	}
	
	public void addAccount() {}
	public void removeAccount() {}
	
	public void addEMail() {}
	public void removeEMail() {}
	

}
