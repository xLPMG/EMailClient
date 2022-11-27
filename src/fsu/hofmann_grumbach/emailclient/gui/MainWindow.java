package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fsu.hofmann_grumbach.emailclient.mail.MailReceiver;
import fsu.hofmann_grumbach.emailclient.util.Account;
import fsu.hofmann_grumbach.emailclient.util.DataHandler;

public class MainWindow extends JFrame {

	private DataHandler dH;
	private Account account;
	private MailReceiver mR;
	private final int width = 800;
	private final int height = 600;
	private final String fontName = "Arial";
	private Color c_0 = new Color(244, 244, 242);
	private Color c_1 = new Color(232, 232, 232);
	private Color c_2 = new Color(187, 191, 202);
	private Color c_3 = new Color(61, 61, 61);
	private Color c_4 = new Color(5, 5, 5, 204);
	private Color c_transparent = new Color(0, 0, 0, 0);
	private JTable mailTable;
	private DefaultTableModel mailTableModel;

	public MainWindow(DataHandler dH, Account activeAccount) {
		this.dH = dH;
		account = activeAccount;
		mR = new MailReceiver(dH);

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, width, height);
		// center window on screen
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(c_4);
		setTitle("Java E-Mail Client - " + account.getUsername());
		setVisible(true);

		JPanel menuBar = new JPanel();
		menuBar.setBounds(0, 0, 800, 40);
		menuBar.setBackground(new Color(43, 43, 43));
		getContentPane().add(menuBar);
		menuBar.setLayout(null);

		JButton btnRefreshInbox = new JButton("Refresh inbox");
		btnRefreshInbox.setFont(new Font(fontName, Font.PLAIN, 13));
		btnRefreshInbox.setContentAreaFilled(false);
		btnRefreshInbox.setBorderPainted(false);
		btnRefreshInbox.setForeground(c_1);
		btnRefreshInbox.setBackground(c_transparent);
		btnRefreshInbox.setFocusPainted(false);
		btnRefreshInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRefreshInbox.setBounds(6, 6, 124, 31);
		menuBar.add(btnRefreshInbox);

		JButton btnNewMail = new JButton("New mail");
		btnNewMail.setFont(new Font(fontName, Font.PLAIN, 13));
		btnNewMail.setContentAreaFilled(false);
		btnNewMail.setBorderPainted(false);
		btnNewMail.setForeground(c_1);
		btnNewMail.setBackground(c_transparent);
		btnNewMail.setFocusPainted(false);
		btnNewMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewMail.setBounds(142, 6, 119, 31);
		menuBar.add(btnNewMail);

		JPanel inboxPanel = new JPanel();
		inboxPanel.setBounds(0, 41, 800, 478);
		inboxPanel.setBackground(c_transparent);
		getContentPane().add(inboxPanel);
		inboxPanel.setLayout(null);

		JScrollPane inboxScrollPane = new JScrollPane();
		inboxScrollPane.setLocation(20, 0);
		inboxScrollPane.setSize(760, 400);
		mailTableModel = new DefaultTableModel();
		mailTable = new JTable(mailTableModel);
		mailTable.setBounds(20, 35, 760, 400);
		try {
			updateTable(mailTableModel);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		inboxScrollPane.setViewportView(mailTable);
		inboxPanel.add(inboxScrollPane);

	}

	private void updateTable(DefaultTableModel mailTableModel) throws MessagingException {
		mailTableModel.addColumn("Subject");
		mailTableModel.addColumn("Read");
		mailTableModel.addColumn("From");
		mailTableModel.addColumn("To");
		mailTableModel.addColumn("Date sent");
		setTitle("Java E-Mail Client - " + account.getUsername() + " - LOADING MAILS");
		mR.receiveMails(account.getInbox(), account.getInboxPort() + "", account.getUsername(), account.getPassword());
		dH.loadMails();
		setTitle("Java E-Mail Client - " + account.getUsername());
		for (Message m : dH.getEmailList()) {
			Address[] froms = m.getFrom();
			Address[] tos = m.getAllRecipients();
			String from = froms == null ? null : ((InternetAddress) froms[0]).getAddress();
			String recipients = "";
			if (tos != null) {
				for (Address to : tos) {
					recipients += tos == null ? null : ((InternetAddress) to).getAddress();
				}
			} else {
				recipients = "???";
			}

			mailTableModel.addRow(new Object[] { m.getSubject(), "no", from, recipients, m.getSentDate() });

		}

	}
}
