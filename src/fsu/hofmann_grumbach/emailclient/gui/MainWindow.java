package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.Color;

import javax.swing.JFrame;

import fsu.hofmann_grumbach.emailclient.util.Account;
import fsu.hofmann_grumbach.emailclient.util.DataHandler;

public class MainWindow extends JFrame {

	private DataHandler dH;
	private final int width = 800;
	private final int height = 600;
	private final String fontName = "Arial";
	private Color c_0 = new Color(244, 244, 242);
	private Color c_1 = new Color(232, 232, 232);
	private Color c_2 = new Color(187, 191, 202);
	private Color c_3 = new Color(61, 61, 61);
	private Color c_4 = new Color(5, 5, 5, 204);
	private Color c_transparent = new Color(0, 0, 0, 0);
	
	public MainWindow(DataHandler dH, Account activeAccount) {
		this.dH = dH;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, width, height);
		// center window on screen
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setTitle("Java E-Mail Client - "+activeAccount.getUsername());
		setVisible(true);
	}
	
}
