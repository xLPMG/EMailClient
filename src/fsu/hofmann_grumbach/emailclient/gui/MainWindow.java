package fsu.hofmann_grumbach.emailclient.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import fsu.hofmann_grumbach.emailclient.util.DataHandler;

public class MainWindow extends JFrame {

	private DataHandler dH;
	private static final long serialVersionUID = -6355342756346660353L;
	private LoginPanel loginPanel;
	private BackgroundPanel backgroundPanel;
	private final int width = 800;
	private final int height = 600;

	public MainWindow(DataHandler dH) {
		this.dH = dH;
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		// center window on screen
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Java E-Mail Client");

		loginPanel = new LoginPanel(dH);
		getContentPane().add(loginPanel);
	}

}
