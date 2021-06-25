package java_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JPasswordField;

public class ForgotPass extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	
	Connection conn;
	Statement s;
	PreparedStatement ps,ps1;
	ResultSet rs;
	private JPasswordField t2;
	private JPasswordField t3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPass frame = new ForgotPass();
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
	public ForgotPass() {
		setTitle("CHANGE PASSWORD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your new password here.....");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Andalus", Font.PLAIN, 16));
		lblNewLabel.setBounds(65, 37, 278, 19);
		contentPane.add(lblNewLabel);
		
		JLabel l2 = new JLabel("Username :");
		l2.setForeground(new Color(0, 0, 153));
		l2.setFont(new Font("Arial", Font.BOLD, 16));
		l2.setBounds(65, 82, 104, 25);
		contentPane.add(l2);
		
		JLabel lblNewLabel_2 = new JLabel("Enter new Password :");
		lblNewLabel_2.setForeground(new Color(0, 0, 153));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(65, 118, 191, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Confirm Password :");
		lblNewLabel_3.setForeground(new Color(0, 0, 153));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3.setBounds(65, 151, 167, 25);
		contentPane.add(lblNewLabel_3);
		
		t1 = new JTextField();
		t1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		t1.setBounds(159, 82, 220, 25);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String u=t1.getText();
				String p1=t2.getText();
				String p2=t3.getText();
				if(u.isEmpty() || p1.isEmpty() || p2.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Enter the details!");
				}
				else
				{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
						s=(Statement)conn.createStatement();
						String q="select * from signup where username=?";
						ps=conn.prepareStatement(q);
						ps.setString(1,t1.getText());
						rs=ps.executeQuery();
						if(rs.next())
						{
							if(!p1.equals(p2))
							{
								JOptionPane.showMessageDialog(null,"Re-enter the password");
							}
							else
							{
								String q2="update signup set password=? where username=?";
								ps1=conn.prepareStatement(q2);
								ps1.setString(1,p1);
								ps1.setString(2,u);
								if(ps1.executeUpdate()>0)
									JOptionPane.showMessageDialog(null,"Password changed successfully");
								new Login().setVisible(true);
								setVisible(false);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Invalid username!");
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
			}
		});
		btnSubmit.setForeground(new Color(0, 0, 102));
		btnSubmit.setFont(new Font("Andalus", Font.BOLD, 16));
		btnSubmit.setBounds(282, 236, 114, 41);
		contentPane.add(btnSubmit);
		
		t2 = new JPasswordField();
		t2.setBounds(244, 117, 213, 25);
		contentPane.add(t2);
		
		t3 = new JPasswordField();
		t3.setText("");
		t3.setBounds(226, 155, 231, 25);
		contentPane.add(t3);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Andalus", Font.BOLD, 15));
		btnBack.setBounds(534, 11, 104, 33);
		contentPane.add(btnBack);
	}
}
