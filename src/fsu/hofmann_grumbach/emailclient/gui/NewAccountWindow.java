package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fsu.hofmann_grumbach.emailclient.util.DataHandler;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class NewAccountWindow extends JFrame {

	private final int width = 800;
	private final int height = 600;
	private DataHandler dH;
	private final String fontName = "Arial";
	private Color c_0 = new Color(244, 244, 242);
	private Color c_1 = new Color(232, 232, 232);
	private Color c_2 = new Color(187, 191, 202);
	private Color c_3 = new Color(61, 61, 61);
	private Color c_4 = new Color(5, 5, 5, 204);
	private Color c_transparent = new Color(0, 0, 0, 0);
	private JTextField txtAddress;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtInbox;
	private JTextField txtOutbox;
	private JTextField txtInboxPort;
	private JTextField txtOutboxPort;

	public NewAccountWindow(DataHandler dH) {
		this.dH = dH;

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, width, height);
		setLocationRelativeTo(null);
		setTitle("Add a new e-mail account");
		setVisible(true);
		getContentPane().setLayout(null);

		JPanel dataPanel = new JPanel();
		dataPanel.setLocation(100, 100);
		dataPanel.setSize(600, 400);
		dataPanel.setBackground(c_4);
		getContentPane().add(dataPanel, BorderLayout.CENTER);
		dataPanel.setLayout(null);

		JLabel lblSenderInformation = new JLabel("Sender information");
		lblSenderInformation.setBounds(10, 6, 165, 22);
		lblSenderInformation.setForeground(c_1);
		lblSenderInformation.setFont(new Font(fontName, Font.BOLD, 18));
		dataPanel.add(lblSenderInformation);

		JPanel senderInformationPanel = new JPanel();
		senderInformationPanel.setBounds(10, 34, 580, 109);
		senderInformationPanel.setBackground(c_transparent);
		dataPanel.add(senderInformationPanel);
		senderInformationPanel.setLayout(null);

		txtAddress = new JTextField();
		txtAddress.setBounds(197, 8, 377, 26);
		txtAddress.setBackground(c_3);
		txtAddress.setForeground(c_1);
		txtAddress.setFont(new Font(fontName, Font.PLAIN, 13));
		txtAddress.setBorder(null);
		senderInformationPanel.add(txtAddress);
		txtAddress.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(197, 42, 377, 26);
		txtName.setBackground(c_3);
		txtName.setForeground(c_1);
		txtName.setFont(new Font(fontName, Font.PLAIN, 13));
		txtName.setBorder(null);
		senderInformationPanel.add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setBounds(197, 76, 377, 26);
		txtSurname.setBackground(c_3);
		txtSurname.setForeground(c_1);
		txtSurname.setFont(new Font(fontName, Font.PLAIN, 13));
		txtSurname.setBorder(null);
		senderInformationPanel.add(txtSurname);
		txtSurname.setColumns(10);

		JLabel lblAddress = new JLabel("Sender E-Mail address");
		lblAddress.setBounds(5, 5, 159, 33);
		lblAddress.setForeground(c_1);
		lblAddress.setFont(new Font(fontName, Font.PLAIN, 13));
		senderInformationPanel.add(lblAddress);

		JLabel lblSurname = new JLabel("Name");
		lblSurname.setBounds(5, 39, 152, 33);
		lblSurname.setForeground(c_1);
		lblSurname.setFont(new Font(fontName, Font.PLAIN, 13));
		senderInformationPanel.add(lblSurname);

		JLabel lblName = new JLabel("Surname");
		lblName.setBounds(5, 73, 159, 33);
		lblName.setForeground(c_1);
		lblName.setFont(new Font(fontName, Font.PLAIN, 13));
		senderInformationPanel.add(lblName);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 160, 165, 30);
		lblUsername.setForeground(c_1);
		lblUsername.setFont(new Font(fontName, Font.PLAIN, 13));
		dataPanel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 191, 165, 30);
		lblPassword.setForeground(c_1);
		lblPassword.setFont(new Font(fontName, Font.PLAIN, 13));
		dataPanel.add(lblPassword);

		JLabel lblInboxServer = new JLabel("Inbox address");
		lblInboxServer.setBounds(10, 222, 165, 30);
		lblInboxServer.setForeground(c_1);
		lblInboxServer.setFont(new Font(fontName, Font.PLAIN, 13));
		dataPanel.add(lblInboxServer);

		JLabel lblOutbox = new JLabel("Outbox address");
		lblOutbox.setBounds(10, 253, 165, 30);
		lblOutbox.setForeground(c_1);
		lblOutbox.setFont(new Font(fontName, Font.PLAIN, 13));
		dataPanel.add(lblOutbox);

		txtUsername = new JTextField();
		txtUsername.setBounds(207, 161, 383, 26);
		txtUsername.setBackground(c_3);
		txtUsername.setForeground(c_1);
		txtUsername.setFont(new Font(fontName, Font.PLAIN, 13));
		txtUsername.setBorder(null);
		dataPanel.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(207, 192, 383, 26);
		txtPassword.setBackground(c_3);
		txtPassword.setForeground(c_1);
		txtPassword.setFont(new Font(fontName, Font.PLAIN, 13));
		txtPassword.setBorder(null);
		dataPanel.add(txtPassword);

		txtInbox = new JTextField();
		txtInbox.setBounds(207, 223, 282, 26);
		txtInbox.setBackground(c_3);
		txtInbox.setForeground(c_1);
		txtInbox.setFont(new Font(fontName, Font.PLAIN, 13));
		txtInbox.setBorder(null);
		dataPanel.add(txtInbox);
		txtInbox.setColumns(10);

		txtOutbox = new JTextField();
		txtOutbox.setBounds(207, 254, 282, 26);
		txtOutbox.setBackground(c_3);
		txtOutbox.setForeground(c_1);
		txtOutbox.setFont(new Font(fontName, Font.PLAIN, 13));
		txtOutbox.setBorder(null);
		dataPanel.add(txtOutbox);
		txtOutbox.setColumns(10);

		JLabel lblSeparator = new JLabel(":");
		lblSeparator.setBounds(501, 228, 15, 16);
		lblSeparator.setForeground(c_1);
		lblSeparator.setFont(new Font(fontName, Font.PLAIN, 18));
		dataPanel.add(lblSeparator);

		JLabel lblSeparator_1 = new JLabel(":");
		lblSeparator_1.setBounds(501, 259, 15, 16);
		lblSeparator_1.setForeground(c_1);
		lblSeparator_1.setFont(new Font(fontName, Font.PLAIN, 18));
		dataPanel.add(lblSeparator_1);

		txtInboxPort = new JTextField();
		txtInboxPort.setBounds(517, 223, 73, 26);
		txtInboxPort.setBackground(c_3);
		txtInboxPort.setForeground(c_1);
		txtInboxPort.setFont(new Font(fontName, Font.PLAIN, 13));
		txtInboxPort.setBorder(null);
		dataPanel.add(txtInboxPort);
		txtInboxPort.setColumns(10);

		txtOutboxPort = new JTextField();
		txtOutboxPort.setBounds(517, 254, 73, 26);
		txtOutboxPort.setBackground(c_3);
		txtOutboxPort.setForeground(c_1);
		txtOutboxPort.setFont(new Font(fontName, Font.PLAIN, 13));
		txtOutboxPort.setBorder(null);
		dataPanel.add(txtOutboxPort);
		txtOutboxPort.setColumns(10);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAccount();
				close();
			}
		});
		btnConfirm.setBounds(10, 365, 285, 30);
		btnConfirm.setForeground(c_1);
		btnConfirm.setBackground(c_3);
		btnConfirm.setFont(new Font(fontName, Font.PLAIN, 13));
		btnConfirm.setBorderPainted(false);
		btnConfirm.setFocusPainted(false);
		dataPanel.add(btnConfirm);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnCancel.setBounds(306, 365, 285, 30);
		btnCancel.setForeground(c_1);
		btnCancel.setBackground(c_3);
		btnCancel.setFont(new Font(fontName, Font.PLAIN, 13));
		btnCancel.setBorderPainted(false);
		btnConfirm.setFocusPainted(false);
		dataPanel.add(btnCancel);

		try {
			BackgroundPanel bgImg = new BackgroundPanel(ImageIO.read(new File("res/bg_wide_01_blurred.jpg")),
					BackgroundPanel.SCALED);
			getContentPane().add(bgImg);
			bgImg.setSize(800, 600);
			bgImg.setBackground(new Color(255, 255, 255));
			bgImg.setLayout(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addAccount() {
		String username = txtUsername.getText();
		String email = txtAddress.getText();
		String password = txtPassword.getText();
		String name = txtName.getText();
		String surname = txtSurname.getText();
		String inboxAddress = txtInbox.getText();
		String outboxAddress = txtOutbox.getText();
		String inboxPort = txtInboxPort.getText();
		String outboxPort = txtInboxPort.getText();

		if (!username.equals("") && !email.equals("") && !password.equals("") && !name.equals("") && !surname.equals("")
				&& !inboxAddress.equals("") && !outboxAddress.equals("") && inboxPort.matches("[0-9]+")
				&& outboxPort.matches("[0-9]+")) {
			
			dH.addAccount(username, email, password, name, surname, inboxAddress, Integer.parseInt(inboxPort),
					outboxAddress, Integer.parseInt(outboxPort));
		}
	}
	
	private void close() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
