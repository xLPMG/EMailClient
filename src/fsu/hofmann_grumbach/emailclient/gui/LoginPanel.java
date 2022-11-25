package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 4728908292222639026L;

	public LoginPanel() {
		setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Login");
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Comic Sans MS", Font.PLAIN, 32));
		add(title, BorderLayout.NORTH);
		
		setBackground(new Color(44,44,44));
	}

}
