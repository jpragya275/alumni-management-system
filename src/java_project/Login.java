package java_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
	
	Connection conn=null;
	ResultSet rs=null;
	Statement s;
	PreparedStatement ps=null;
	
	int attempt=1;

	 static String user,eroll,uType;
	
	private JPanel contentPane;
	private JTextField t1;
	private JPasswordField p1;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	public Login() {
		
		setTitle("AMS LOGIN PAGE ");
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("JAWAHARLAL NEHRU UNIVERSITY");
		l1.setFont(new Font("Calibri", Font.BOLD, 35));
		l1.setForeground(new Color(255, 102, 0));
		l1.setBounds(82, 30, 512, 45);
		panel.add(l1);
		
		JLabel l2 = new JLabel("ALUMNI MANAGEMENT SYSTEM");
		l2.setForeground(new Color(0, 0, 204));
		l2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		l2.setBounds(157, 86, 356, 27);
		panel.add(l2);
		
		JLabel l3 = new JLabel("Username");
		l3.setForeground(new Color(51, 51, 51));
		l3.setFont(new Font("Andalus", Font.BOLD, 20));
		l3.setBounds(157, 163, 101, 33);
		panel.add(l3);
		
		JLabel l4 = new JLabel("Password");
		l4.setForeground(new Color(51, 51, 51));
		l4.setFont(new Font("Andalus", Font.BOLD, 20));
		l4.setBounds(157, 207, 92, 27);
		panel.add(l4);
		
		t1 = new JTextField();
		t1.setFont(new Font("Arial", Font.PLAIN, 15));
		t1.setBounds(284, 163, 213, 27);
		panel.add(t1);
		t1.setColumns(10);
		
		p1 = new JPasswordField();
		p1.setFont(new Font("Arial", Font.PLAIN, 15));
		p1.setBounds(284, 207, 213, 27);
		panel.add(p1);
		
		
		JButton b1 = new JButton("LOGIN");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String a=t1.getText();
				 String b=String.valueOf(p1.getPassword());
				
				if(attempt<4){
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
						s=(Statement)conn.createStatement();
						String q="select * from signup where username=? and password=? ";
						ps=conn.prepareStatement(q);
						ps.setString(1,a);
						ps.setString(2,b);
						rs=ps.executeQuery();
						
						if(rs.next())
						{
							JOptionPane.showMessageDialog(null,"Username and password matched!");
							user=a;
							uType=rs.getString("user_type");
							eroll=rs.getString(1);
							new Home().setVisible(true);
							setVisible(false);
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Incorrect username or password");
							t1.setText(null);
							p1.setText(null);
							attempt++;
						}
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
				}
				
				else if(attempt==4)
				{
					JOptionPane.showMessageDialog(null,"Access Denied! Maximum attempt limit reached!");
					t1.setText(null);
					p1.setText(null);
					System.exit(0);
				}
				
			}
		});
		b1.setForeground(new Color(0, 0, 153));
		b1.setFont(new Font("Andalus", Font.BOLD, 15));
		b1.setBounds(359, 274, 89, 27);
		panel.add(b1);
		
		JButton b2 = new JButton("SIGNUP");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Signup().setVisible(true);
				setVisible(false);
			}
		});
		b2.setForeground(new Color(0, 0, 102));
		b2.setFont(new Font("Andalus", Font.BOLD, 15));
		b2.setBounds(267, 346, 89, 23);
		panel.add(b2);
		
		JButton b3 = new JButton("FORGOT PASSWORD");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgotPass().setVisible(true);
				setVisible(false);
			}
		});
		b3.setForeground(new Color(0, 0, 102));
		b3.setFont(new Font("Andalus", Font.BOLD, 15));
		b3.setBounds(398, 346, 196, 23);
		panel.add(b3);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
