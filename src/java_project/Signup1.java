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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Signup1 extends JFrame {

	private JPanel contentPane;
	private JTextField qual;
	private JTextField occ;
	private JTextField org;
	private JTextField wexp;
	private JTextField loc;
	private JTextField achv;
	private JTextField othr;
	//public static JTextField tEno;
	
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
					Signup1 frame = new Signup1();
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
	public Signup1() {
		setTitle("ADDITIONAL DETAILS OF ALUMNI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("Please fill out the following details.");
		l1.setForeground(Color.RED);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		l1.setBounds(29, 26, 213, 21);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("Qualification :");
		l2.setForeground(Color.BLUE);
		l2.setFont(new Font("Tahoma", Font.BOLD, 15));
		l2.setBounds(49, 78, 118, 28);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Occupation :");
		l3.setForeground(Color.BLUE);
		l3.setFont(new Font("Tahoma", Font.BOLD, 15));
		l3.setBounds(49, 117, 118, 24);
		contentPane.add(l3);
		
		JLabel l5 = new JLabel("Work Experience :");
		l5.setForeground(Color.BLUE);
		l5.setFont(new Font("Tahoma", Font.BOLD, 15));
		l5.setBounds(49, 191, 161, 26);
		contentPane.add(l5);
		
		JLabel l7 = new JLabel("Achievements  :");
		l7.setForeground(Color.BLUE);
		l7.setFont(new Font("Tahoma", Font.BOLD, 15));
		l7.setBounds(49, 266, 118, 30);
		contentPane.add(l7);
		
		JLabel l6 = new JLabel("Location :");
		l6.setFont(new Font("Tahoma", Font.BOLD, 15));
		l6.setForeground(Color.BLUE);
		l6.setBounds(49, 228, 118, 27);
		contentPane.add(l6);
		
		JLabel l8 = new JLabel("Other Details :");
		l8.setForeground(Color.BLUE);
		l8.setFont(new Font("Tahoma", Font.BOLD, 15));
		l8.setBounds(49, 327, 118, 28);
		contentPane.add(l8);
		
		JLabel l4 = new JLabel("Name of Organization :");
		l4.setForeground(Color.BLUE);
		l4.setFont(new Font("Tahoma", Font.BOLD, 15));
		l4.setBounds(49, 155, 193, 25);
		contentPane.add(l4);
		
		qual = new JTextField();
		qual.setBounds(177, 84, 327, 22);
		contentPane.add(qual);
		qual.setColumns(10);
		
		occ = new JTextField();
		occ.setBounds(169, 121, 335, 21);
		contentPane.add(occ);
		occ.setColumns(10);
		
		org = new JTextField();
		org.setBounds(252, 159, 315, 21);
		contentPane.add(org);
		org.setColumns(10);
		
		wexp = new JTextField();
		wexp.setBounds(220, 196, 231, 21);
		contentPane.add(wexp);
		wexp.setColumns(10);
		
		loc = new JTextField();
		loc.setBounds(148, 235, 267, 21);
		contentPane.add(loc);
		loc.setColumns(10);
		
		achv = new JTextField();
		achv.setBounds(177, 273, 365, 44);
		contentPane.add(achv);
		achv.setColumns(10);
		
		othr = new JTextField();
		othr.setBounds(173, 333, 308, 38);
		contentPane.add(othr);
		othr.setColumns(10);
		
		JButton b1 = new JButton("SUBMIT");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
					s=(Statement)conn.createStatement();
					String q="insert into Alumni(qualification,occupation,name_of_org,work_exp,location,achievements,other_info,EnrollNo)"
							+ "value(?,?,?,?,?,?,?,?)";
					ps=conn.prepareStatement(q);
					ps.setString(8,Signup.en);
					if(qual.getText().isEmpty())
						ps.setString(1,null);
					else
						ps.setString(1,qual.getText());
					if(occ.getText().isEmpty())
						ps.setString(2,null);
					else
						ps.setString(2,occ.getText());
					if(org.getText().isEmpty())
						ps.setString(3,null);
					else
						ps.setString(3,org.getText());
					if(wexp.getText().isEmpty())
						ps.setString(4,null);
					else
						ps.setString(4,wexp.getText());
					if(loc.getText().isEmpty())
						ps.setString(5,null);
					else
						ps.setString(5,loc.getText());
					if(achv.getText().isEmpty())
						ps.setString(6,null);
					else
						ps.setString(6,achv.getText());
					if(othr.getText().isEmpty())
						ps.setString(7,null);
					else
						ps.setString(7,othr.getText());
					if(ps.executeUpdate()>0)
						JOptionPane.showMessageDialog(null,"Details successfully inserted into Database");
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				new Login().setVisible(true);
				setVisible(false);
			}
		});
		b1.setForeground(new Color(0, 0, 153));
		b1.setFont(new Font("Andalus", Font.BOLD, 15));
		b1.setBounds(336, 405, 115, 35);
		contentPane.add(b1);
		
		JLabel lblEnrollmentNo = new JLabel("Enrollment No. :");
		lblEnrollmentNo.setBounds(457, 30, 93, 14);
		contentPane.add(lblEnrollmentNo);
		
		JTextField tEno = new JTextField();
		tEno.setBounds(560, 27, 132, 20);
		tEno.setText(Signup.en);
		contentPane.add(tEno);
		tEno.setColumns(10);
	}
}
