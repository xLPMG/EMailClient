package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import fsu.hofmann_grumbach.emailclient.util.Account;
import fsu.hofmann_grumbach.emailclient.util.DataHandler;

public class LoginWindow extends JFrame {

	private DataHandler dH;
	private static final long serialVersionUID = -6355342756346660353L;
	private final int width = 800;
	private final int height = 600;
	private final String fontName = "Arial";
	private Color c_0 = new Color(244, 244, 242);
	private Color c_1 = new Color(232, 232, 232);
	private Color c_2 = new Color(187, 191, 202);
	private Color c_3 = new Color(61, 61, 61);
	private Color c_4 = new Color(5, 5, 5, 204);
	private Color c_transparent = new Color(0, 0, 0, 0);
	private DefaultListModel accListModel;

	public LoginWindow(DataHandler dH) {
		this.dH = dH;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		// center window on screen
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setTitle("Java E-Mail Client");

		JPanel loginPanel = new JPanel();
		loginPanel.setSize(width, height);
		loginPanel.setLayout(null);
		setContentPane(loginPanel);
		JPanel dataPanel = new JPanel();
		dataPanel.setBounds(500, 0, 300, 600);
		dataPanel.setBackground(c_4);
		dataPanel.setLayout(null);
		loginPanel.add(dataPanel);

		JLabel lblTitle = new JLabel("Java E-Mail Client");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 39, 300, 33);
		lblTitle.setForeground(c_1);
		lblTitle.setFont(new Font(fontName, Font.BOLD, 28));
		dataPanel.add(lblTitle);

		JButton btnAddAccount = new JButton("Add a new E-Mail");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAccount();
			}
		});
		btnAddAccount.setBounds(64, 510, 185, 30);
		btnAddAccount.setOpaque(false);
		btnAddAccount.setContentAreaFilled(false);
		btnAddAccount.setBorderPainted(false);
		btnAddAccount.setForeground(c_1);
		btnAddAccount.setFocusPainted(false);
		btnAddAccount.setFont(new Font(fontName, Font.PLAIN, 18));
		btnAddAccount.setBackground(c_transparent);
		dataPanel.add(btnAddAccount);

		JLabel lblCopyright = new JLabel("Grumbach & Hofmann, 2022");
		lblCopyright.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCopyright.setBounds(0, 567, 294, 33);
		lblCopyright.setForeground(c_3);
		dataPanel.add(lblCopyright);

		JLabel lblSelectAccount = new JLabel("Select an account");
		lblSelectAccount.setBounds(64, 113, 173, 26);
		dataPanel.add(lblSelectAccount);
		lblSelectAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAccount.setForeground(c_1);
		lblSelectAccount.setFont(new Font("Arial", Font.PLAIN, 22));

		JPanel listPanel = new JPanel((LayoutManager) new FlowLayout(FlowLayout.LEFT));
		listPanel.setBorder(null);
		listPanel.setBackground(new Color(73, 73, 73));
		listPanel.setBounds(21, 151, 256, 248);
		dataPanel.add(listPanel);

		JList accList = new JList();
		accListModel = new DefaultListModel();
		for (Account acc : dH.getAccountData()) {
			accListModel.addElement(acc.getUsername());
		}
		accList.setModel(accListModel);
		accList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		accList.setForeground(c_1);
		accList.setSelectionForeground(c_4);
		accList.setBackground(c_transparent);
		accList.setSelectionBackground(c_transparent);
		accList.setOpaque(false);
		accList.setFont(new Font(fontName, Font.PLAIN, 16));
		listPanel.add(accList);

		accList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
					int index = list.locationToIndex(evt.getPoint());
					String selectedUsername = (String) accListModel.getElementAt(index);
					for (Account acc : dH.getAccountData()) {
						if (acc.getUsername().equals(selectedUsername)) {
							MainWindow mW = new MainWindow(dH, acc);
						}
					}
				}
			}
		});

		try {
			BackgroundPanel bgImg = new BackgroundPanel(ImageIO.read(new File("res/bg_wide_01_blurred.jpg")),
					BackgroundPanel.SCALED);
			loginPanel.add(bgImg);
			bgImg.setSize(800, 600);
			bgImg.setBackground(new Color(255, 255, 255));
			bgImg.setLayout(null);

			BufferedImage emailIcon = ImageIO.read(new File("res/email.png"));
			JLabel picLabel = new JLabel(new ImageIcon(emailIcon.getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
			picLabel.setBounds(160, 210, 180, 180);
			bgImg.add(picLabel);

			JLabel lblJEMC = new JLabel("JEMC v1.1");
			lblJEMC.setFont(new Font("Optima", Font.BOLD, 28));
			lblJEMC.setHorizontalAlignment(SwingConstants.CENTER);
			lblJEMC.setBounds(160, 200, 180, 29);
			bgImg.add(lblJEMC);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addAccount() {
		NewAccountWindow nAW = new NewAccountWindow(dH);
		nAW.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				updateAccList();
			}
		});
	}

	private void updateAccList() {
		accListModel.clear();
		for (Account acc : dH.getAccountData()) {
			accListModel.addElement(acc.getUsername());
		}
	}
}
