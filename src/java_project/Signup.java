package java_project;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField Eno;
	private JTextField email;
	private JTextField address;
	private JTextField phno;
	private JTextField uname;
	private JPasswordField pass;
	private JPasswordField cnfpass;
	private JRadioButton r2;
	private JRadioButton r1;
	private JRadioButton r3;
	private JRadioButton r5;
	private JRadioButton r4;
	private JComboBox mm;
	private JComboBox dd;
	private JComboBox yy;
	
	String utype=null;
	String gender=null;
	String dob="";
	static String en;
	
	Connection conn=null;
	Statement s=null;
	PreparedStatement ps;
	private JComboBox course;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setTitle("NEW USER SIGNUP PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("Registration Form");
		l1.setForeground(new Color(51, 153, 102));
		l1.setFont(new Font("Vani", Font.BOLD, 22));
		l1.setBounds(277, 11, 225, 31);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("Welcome to the registration page. Please take a moment to fill out the form below.");
		l2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		l2.setForeground(new Color(255, 0, 51));
		l2.setBounds(54, 41, 713, 22);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Name :");
		l3.setFont(new Font("Tahoma", Font.BOLD, 15));
		l3.setBounds(54, 86, 57, 14);
		contentPane.add(l3);
		
		JLabel l4 = new JLabel("Course :");
		l4.setFont(new Font("Tahoma", Font.BOLD, 15));
		l4.setBounds(54, 111, 70, 14);
		contentPane.add(l4);
		
		JLabel l5 = new JLabel("Date of Birth :");
		l5.setFont(new Font("Tahoma", Font.BOLD, 15));
		l5.setBounds(54, 186, 118, 14);
		contentPane.add(l5);
		
		JLabel l6 = new JLabel("Email id :");
		l6.setFont(new Font("Tahoma", Font.BOLD, 15));
		l6.setBounds(54, 161, 91, 14);
		contentPane.add(l6);
		
		JLabel l7 = new JLabel("Enrollment No. :");
		l7.setFont(new Font("Tahoma", Font.BOLD, 15));
		l7.setBounds(54, 136, 133, 14);
		contentPane.add(l7);
		
		JLabel l8 = new JLabel("Gender :");
		l8.setFont(new Font("Tahoma", Font.BOLD, 15));
		l8.setBounds(54, 211, 91, 14);
		contentPane.add(l8);
		
		JLabel l9 = new JLabel("Address :");
		l9.setFont(new Font("Tahoma", Font.BOLD, 15));
		l9.setBounds(54, 236, 81, 14);
		contentPane.add(l9);
		
		JLabel l10 = new JLabel("Contact No. :");
		l10.setFont(new Font("Tahoma", Font.BOLD, 15));
		l10.setBounds(54, 261, 106, 14);
		contentPane.add(l10);
		
		JLabel l11 = new JLabel("Year of admission :");
		l11.setFont(new Font("Tahoma", Font.BOLD, 15));
		l11.setBounds(54, 286, 149, 14);
		contentPane.add(l11);
		
		JLabel l12 = new JLabel("Year of Passing :");
		l12.setFont(new Font("Tahoma", Font.BOLD, 15));
		l12.setBounds(426, 282, 126, 22);
		contentPane.add(l12);
		
		JLabel l13 = new JLabel("Select :");
		l13.setFont(new Font("Tahoma", Font.BOLD, 15));
		l13.setBounds(54, 311, 57, 14);
		contentPane.add(l13);
		
		JLabel l14 = new JLabel("Username :");
		l14.setFont(new Font("Tahoma", Font.BOLD, 15));
		l14.setBounds(54, 336, 99, 14);
		contentPane.add(l14);
		
		JLabel l15 = new JLabel("Password :");
		l15.setFont(new Font("Tahoma", Font.BOLD, 15));
		l15.setBounds(54, 361, 91, 14);
		contentPane.add(l15);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfirmPassword.setBounds(418, 363, 149, 14);
		contentPane.add(lblConfirmPassword);
		
		
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		name.setBounds(136, 85, 277, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		Eno = new JTextField();
		Eno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Eno.setBounds(197, 135, 251, 20);
		contentPane.add(Eno);
		Eno.setColumns(10);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		email.setBounds(142, 160, 342, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		String date[]=new String[31];
		for(int i=0 ; i<31 ; i++)
		{
			date[i]=Integer.toString(i+1);
		}
		
		dd = new JComboBox(date);
		dd.setBounds(226, 185, 57, 20);
		contentPane.add(dd);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setForeground(new Color(0, 0, 255));
		lblDate.setBounds(182, 188, 34, 14);
		contentPane.add(lblDate);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setForeground(new Color(0, 0, 255));
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonth.setBounds(319, 188, 46, 14);
		contentPane.add(lblMonth);
		
		String month[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
		
		mm = new JComboBox(month);
		mm.setBounds(385, 185, 57, 20);
		contentPane.add(mm);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setForeground(new Color(0, 0, 255));
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYear.setBounds(489, 190, 46, 14);
		contentPane.add(lblYear);
		
		String year[]=new String[50];
		for(int i=0 ; i<50 ; i++)
		{
			year[i]=Integer.toString(1970+i);
		}
		
		yy = new JComboBox(year);
		yy.setBounds(539, 185, 70, 20);
		contentPane.add(yy);
		
		address = new JTextField();
		address.setFont(new Font("Tahoma", Font.PLAIN, 13));
		address.setBounds(134, 235, 563, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		phno = new JTextField();
		phno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		phno.setBounds(163, 260, 202, 20);
		contentPane.add(phno);
		phno.setColumns(10);
		
		String year1[]=new String[50];
		for(int i=0 ; i<50 ; i++)
		{
			year1[i]=Integer.toString(1980+i);
		}
		
		JComboBox yoj = new JComboBox(year1);
		yoj.setBounds(213, 285, 81, 20);
		contentPane.add(yoj);
		
		String year2[]=new String[50];
		for(int i=0 ; i<50 ; i++)
		{
			year2[i]=Integer.toString(1980+i);
		}
		
		JComboBox yop = new JComboBox(year2);
		yop.setBounds(576, 285, 81, 20);
		contentPane.add(yop);
		
		r1 = new JRadioButton("Male");
		r1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r1.isSelected())
				{
					r2.setSelected(false);
					r3.setSelected(false);
					gender="male";
				}
			}
		});
		r1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		r1.setBounds(154, 208, 70, 22);
		contentPane.add(r1);
		
		r2 = new JRadioButton("Female");
		r2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r2.isSelected())
				{
					r1.setSelected(false);
					r3.setSelected(false);
					gender="female";
				}
			}
		});
		r2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		r2.setBounds(304, 209, 109, 23);
		contentPane.add(r2);
		
		r3 = new JRadioButton("Other");
		r3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r3.isSelected())
				{
					r1.setSelected(false);
					r2.setSelected(false);
					gender="other";
				}
			}
		});
		r3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		r3.setBounds(443, 209, 109, 23);
		contentPane.add(r3);
		
		r4 = new JRadioButton("Alumni");
		r4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r4.isSelected())
				{
					r5.setSelected(false);
					utype="alumni";
				}
			}
		});
		r4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		r4.setBounds(131, 307, 109, 23);
		contentPane.add(r4);
		
		r5 = new JRadioButton("Student");
		r5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r5.isSelected())
				{
					r4.setSelected(false);
					utype="student";
				}
			}
		});
		r5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		r5.setBounds(304, 309, 109, 23);
		contentPane.add(r5);
		
		uname = new JTextField();
		uname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		uname.setBounds(154, 335, 270, 20);
		contentPane.add(uname);
		uname.setColumns(10);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pass.setBounds(155, 360, 225, 20);
		contentPane.add(pass);
		
		cnfpass = new JPasswordField();
		cnfpass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cnfpass.setBounds(577, 360, 211, 20);
		contentPane.add(cnfpass);
		
		
		JButton b1 = new JButton("SUBMIT");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nm=name.getText();
				String crs=course.getSelectedItem().toString();
				en=Eno.getText();
				String ad=address.getText();
				String em=email.getText();
				String pn=phno.getText();
				String y1=yoj.getSelectedItem().toString();
				String y2=yop.getSelectedItem().toString();
				String un=uname.getText();
				String p1=String.valueOf(pass.getPassword());
				String p2=String.valueOf(cnfpass.getPassword());
				dob=dob+mm.getSelectedItem()+"-";
				dob=dob+dd.getSelectedItem()+"-";
				dob+=yy.getSelectedItem();
			
				if(nm.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter name");
				}
				else if(crs.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter course");
				}
				else if(en.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Enrollment no.");
				}
				else if(ad.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Address");
				}
				else if(em.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Email-id");
				}
				else if(pn.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Contact no.");
				}
				else if(y1.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Year of Admission");
				}
				else if(y2.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Year of Passing");
				}
				else if(un.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Username");
				}
				else if(gender.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Select Gender");
				}
				else if(utype.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Select UserType");
				}
				else if(dob.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Select Date of Birth");
				}
				else if(p1.equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter Password");
				}
				else if(p2.equals(""))
				{
					JOptionPane.showMessageDialog(null,"please confirm Password");
				}
				else if(!p1.equals(p2))
				{
					JOptionPane.showMessageDialog(null,"Re-type password!");
				}
				else if(checkUser(un))
				{
					JOptionPane.showMessageDialog(null,"Username already exists!");
				}
				else if(pn.length()!=10)
				{
					JOptionPane.showMessageDialog(null,"Please enter a valid contact no.!");
				}
				else{
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
					s=(Statement)conn.createStatement();
					String q="insert into signup(name,course,EnrollNo,address,email_id,contactNo,"
							+ "year_of_adm,year_of_pass,username,password,user_type,dateOfBirth,gender)"
							+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps=conn.prepareStatement(q);
					ps.setString(1,nm);
					ps.setString(2,crs);
					ps.setString(3,en);
					ps.setString(4,ad);
					ps.setString(5,em);
					ps.setString(6,pn);
					ps.setString(7,y1);
					ps.setString(8,y2);
					ps.setString(9,un);
					ps.setString(10,p1);
					ps.setString(11,utype);
					ps.setString(12,dob);
					ps.setString(13,gender);
					
					if(ps.executeUpdate()>0)
						JOptionPane.showMessageDialog(null,"Details successfully inserted into Database");
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
				if(utype.equals("alumni"))
				{
					new Signup1().setVisible(true);
					setVisible(false);
				}
				else
				{
					new Login().setVisible(true);
					setVisible(false);
				}
				
				}
			 }
		});
		b1.setFont(new Font("Andalus", Font.BOLD, 15));
		b1.setForeground(new Color(0, 0, 153));
		b1.setBounds(359, 421, 109, 23);
		contentPane.add(b1);
		
		String cr[]={"---select---","MCA","B.tech-Computer Science","M.A. Philosophy","M.A. Sociology","M.tech Nano Science",
				"B.A. Chinese","PhD"};
		
		course = new JComboBox(cr);
		course.setFont(new Font("Tahoma", Font.PLAIN, 13));
		course.setBounds(134, 110, 314, 20);
		contentPane.add(course);
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
