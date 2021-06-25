package java_project;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Message extends JFrame {

	private JPanel contentPane;
	private JTextField recv;
	
	Connection conn=null;
	Statement s=null;
	PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Message frame = new Message();
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
	public Message() {
		setTitle("MESSAGING FRAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 424);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReciever = new JLabel("Reciever :");
		lblReciever.setForeground(new Color(51, 153, 0));
		lblReciever.setFont(new Font("Arial", Font.BOLD, 15));
		lblReciever.setBounds(58, 71, 94, 26);
		contentPane.add(lblReciever);
		
		recv = new JTextField();
		recv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		recv.setBounds(150, 71, 238, 29);
		contentPane.add(recv);
		recv.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message :");
		lblMessage.setForeground(new Color(51, 102, 0));
		lblMessage.setFont(new Font("Arial", Font.BOLD, 15));
		lblMessage.setBounds(58, 133, 84, 29);
		contentPane.add(lblMessage);
		
		JTextArea tmsg = new JTextArea();
		tmsg.setFont(new Font("Monospaced", Font.PLAIN, 13));
		tmsg.setBounds(150, 136, 343, 70);
		contentPane.add(tmsg);
		
		JButton btnSend = new JButton("SEND");
		btnSend.setForeground(new Color(0, 0, 102));
		btnSend.setFont(new Font("Andalus", Font.BOLD, 18));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg=tmsg.getText();
				String rcv=recv.getText();
				if(rcv.equals(""))
					JOptionPane.showMessageDialog(null,"Enter receiver!");
				else if(!checkUser(rcv))
					JOptionPane.showMessageDialog(null,"Invalid receiver!");
				
				else if(msg.equals(""))
					JOptionPane.showMessageDialog(null,"Message field empty!");
				else
				{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
						s=(Statement)conn.createStatement();
						String q="insert into message(sender,receiver,msg)"
								+ "values(?,?,?)";
						ps=conn.prepareStatement(q);
						ps.setString(1,Login.user);
						ps.setString(2,rcv);
						ps.setString(3,msg);
						
						if(ps.executeUpdate()>0)
							JOptionPane.showMessageDialog(null,"Message sent!");
								
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				tmsg.setText(null);
				recv.setText(null);
			}
					
				
				
		});
		btnSend.setBounds(294, 261, 94, 34);
		contentPane.add(btnSend);
		
		JButton btnViewMessages = new JButton("VIEW MESSAGES");
		btnViewMessages.setForeground(new Color(0, 0, 102));
		btnViewMessages.setFont(new Font("Andalus", Font.BOLD, 15));
		btnViewMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new View_msg().setVisible(true);
				setVisible(false);
			}
		});
		btnViewMessages.setBounds(483, 261, 159, 34);
		contentPane.add(btnViewMessages);
		
		JLabel lblWriteMessage = new JLabel("WRITE MESSAGE");
		lblWriteMessage.setForeground(new Color(204, 0, 0));
		lblWriteMessage.setFont(new Font("Aparajita", Font.BOLD, 20));
		lblWriteMessage.setBounds(56, 21, 148, 33);
		contentPane.add(lblWriteMessage);
		
		JButton btnBackToHome = new JButton("BACK TO HOME");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Home().setVisible(true);
				setVisible(false);
			}
		});
		btnBackToHome.setForeground(new Color(0, 0, 102));
		btnBackToHome.setFont(new Font("Andalus", Font.BOLD, 15));
		btnBackToHome.setBounds(42, 262, 162, 34);
		contentPane.add(btnBackToHome);
	}
	public boolean checkUser(String usern)
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			ResultSet rs=s.executeQuery("select username from signup");
			while(rs.next())
			{
				if(rs.getString(1).equals(usern))
				{
					return true;
				}
			}
			
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		return false;
	}
}
