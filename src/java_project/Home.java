package java_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.socket.SocketServer;
import com.ui.ChatFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setTitle("HOME PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("Welcome to JNU Alumni Management System");
		l1.setForeground(new Color(204, 51, 51));
		l1.setFont(new Font("Times New Roman", Font.BOLD, 28));
		l1.setBounds(107, 11, 558, 46);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel(" My Profile");
		l2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//new Myprofile(Login.user);
				new Myprofile().setVisible(true);
				setVisible(false);
			}
		});
		l2.setFont(new Font("Aparajita", Font.BOLD, 22));
		l2.setForeground(new Color(0, 102, 0));
		l2.setBounds(61, 151, 105, 25);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Search");
		l3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Search_2().setVisible(true);
				setVisible(false);
			}
		});
		l3.setForeground(new Color(0, 102, 0));
		l3.setFont(new Font("Aparajita", Font.BOLD, 22));
		l3.setBounds(61, 187, 68, 25);
		contentPane.add(l3);
		
		JLabel l4 = new JLabel("Message");
		l4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				ChatFrame cf = new ChatFrame();
		        java.awt.EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                cf.setVisible(true);
		                cf.establishConnection();
		            }
		        });
				//setVisible(false);
			}
		});
		l4.setForeground(new Color(0, 102, 0));
		l4.setFont(new Font("Aparajita", Font.BOLD, 22));
		l4.setBounds(61, 223, 68, 25);
		contentPane.add(l4);
		
		JLabel l5 = new JLabel("Events");
		l5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Event().setVisible(true);
				setVisible(false);
			}
		});
		l5.setForeground(new Color(0, 102, 0));
		l5.setFont(new Font("Aparajita", Font.BOLD, 22));
		l5.setBounds(61, 259, 61, 25);
		contentPane.add(l5);
		
		JLabel l6 = new JLabel("Sign Out");
		l6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"Signing out....");
				new Login().setVisible(true);
				setVisible(false);
			}
		});
		l6.setForeground(new Color(0, 102, 0));
		l6.setFont(new Font("Aparajita", Font.BOLD, 22));
		l6.setBounds(61, 295, 68, 25);
		contentPane.add(l6);
		
		JLabel label = new JLabel();
		label.setLabelFor(this);
		label.setBounds(176, 90, 570, 316);
		contentPane.add(label);
		ImageIcon i=new ImageIcon(this.getClass().getResource("/jnupic.jpg"));
		Image img1=i.getImage();
		Image img2=img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon imgs=new ImageIcon(img2);
		label.setIcon(imgs);
	}
}
