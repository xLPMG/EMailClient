package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -6355342756346660353L;
	private LoginPanel loginPanel;

	/**
	 * Launch the application.
	 */
	public void startx() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);
		//center window on screen
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setTitle("Java E-Mail Client");
		loginPanel = new LoginPanel();

		setContentPane(loginPanel);
	}

}
