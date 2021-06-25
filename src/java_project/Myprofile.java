package java_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Myprofile extends JFrame {

	private JPanel contentPane;
	private JTextField tyoj;
	private JTextField tyop;
	private JTextField tcourse;
	private JTextField tqual;
	private JTextField tocc;
	private JTextField torg;
	private JTextField twexp;
	private JTextField tloc;
	private JTextField temail;
	private JTextField tname;
	
	Connection conn=null;
	ResultSet rs1,rs2;
	Statement s;
	PreparedStatement ps3,ps4,ps1,ps2;
	 String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
	 private JTextArea tachv;
	 private JTextArea tothr;
	 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Myprofile frame = new Myprofile();
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
	public Myprofile(String u)
	{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q1="select name,course,year_of_adm,year_of_pass,email_id,EnrollNo from signup where username=?";
			ps1=conn.prepareStatement(q1);
			ps1.setString(1,u);
			rs1=ps1.executeQuery();
			
			while(rs1.next())
			{	
				 s1=rs1.getString(1);
				 s2=rs1.getString(2);
				 s3=rs1.getString(3);
				 s4=rs1.getString(4);
				 s5=rs1.getString(5);
				 s6=rs1.getString(6);
			}
			
			String q2="select qualification,occupation,name_of_org,work_exp,location,achievements,other_info from alumni where EnrollNo=?";
			ps2=conn.prepareStatement(q2);
			ps2.setString(1,s6);
			rs2=ps2.executeQuery();
			
			while(rs2.next())
			{
				s7=rs2.getString(1);
				s8=rs2.getString(2);
				s9=rs2.getString(3);
				s10=rs2.getString(4);
				s11=rs2.getString(5);
				s12=rs2.getString(6);
				s13=rs2.getString(7);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Myprofile() {
		this(Login.user);
		setBackground(new Color(255, 255, 255));
		setTitle("USER PROFILE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l1 = new JLabel("");
		l1.setForeground(new Color(204, 0, 0));
		l1.setFont(new Font("Aharoni", Font.PLAIN, 16));
		l1.setBounds(35, 45, 77, 24);
		l1.setText("Name :");
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("Course :");
		l2.setForeground(new Color(204, 0, 0));
		l2.setFont(new Font("Aharoni", Font.PLAIN, 16));
		l2.setBounds(385, 45, 63, 24);
		contentPane.add(l2);
		
		JLabel lblYearOfAdmission = new JLabel("Year of Admission :");
		lblYearOfAdmission.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblYearOfAdmission.setForeground(new Color(204, 0, 0));
		lblYearOfAdmission.setBounds(35, 80, 155, 28);
		contentPane.add(lblYearOfAdmission);
		
		JLabel lblYearOfPassing = new JLabel("Year of Passing :");
		lblYearOfPassing.setForeground(new Color(204, 0, 0));
		lblYearOfPassing.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblYearOfPassing.setBounds(352, 81, 141, 27);
		contentPane.add(lblYearOfPassing);
		
		JLabel lblQualification = new JLabel("Qualification :");
		lblQualification.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblQualification.setForeground(new Color(204, 0, 0));
		lblQualification.setBounds(37, 119, 141, 24);
		contentPane.add(lblQualification);
		
		JLabel lblNewLabel = new JLabel("Occupation :");
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblNewLabel.setBounds(362, 119, 129, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNameOfOrganization = new JLabel("Name of Organization :");
		lblNameOfOrganization.setForeground(new Color(204, 0, 0));
		lblNameOfOrganization.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblNameOfOrganization.setBounds(36, 162, 199, 19);
		contentPane.add(lblNameOfOrganization);
		
		JLabel lblWorkExperience = new JLabel("Work Experience :");
		lblWorkExperience.setForeground(new Color(204, 0, 0));
		lblWorkExperience.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblWorkExperience.setBounds(35, 202, 168, 19);
		contentPane.add(lblWorkExperience);
		
		JLabel lblAchievements = new JLabel("Achievements :");
		lblAchievements.setForeground(new Color(204, 0, 0));
		lblAchievements.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblAchievements.setBounds(35, 279, 134, 19);
		contentPane.add(lblAchievements);
		
		tname = new JTextField();
		tname.setBackground(new Color(255, 255, 255));
		tname.setForeground(new Color(0, 0, 153));
		tname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tname.setBounds(104, 47, 245, 22);
		tname.setText(s1);
		tname.setEditable(false);
		contentPane.add(tname);
		tname.setColumns(10);
		
		tyoj = new JTextField();
		tyoj.setBackground(new Color(255, 255, 255));
		tyoj.setForeground(new Color(0, 0, 153));
		tyoj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tyoj.setBounds(200, 85, 86, 23);
		tyoj.setText(s3);
		tyoj.setEditable(false);
		contentPane.add(tyoj);
		tyoj.setColumns(10);
		
		tyop = new JTextField();
		tyop.setBackground(new Color(255, 255, 255));
		tyop.setForeground(new Color(0, 0, 153));
		tyop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tyop.setBounds(492, 85, 86, 23);
		tyop.setText(s4);
		tyop.setEditable(false);
		contentPane.add(tyop);
		tyop.setColumns(10);
		
		tcourse = new JTextField();
		tcourse.setBackground(new Color(255, 255, 255));
		tcourse.setForeground(new Color(0, 0, 153));
		tcourse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tcourse.setBounds(462, 45, 264, 24);
		tcourse.setText(s2);
		tcourse.setEditable(false);
		contentPane.add(tcourse);
		tcourse.setColumns(10);
		
		tqual = new JTextField();
		tqual.setBackground(new Color(255, 255, 255));
		tqual.setForeground(new Color(0, 0, 153));
		tqual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tqual.setBounds(159, 119, 193, 24);
		tqual.setText(s7);
		tqual.setEditable(false);
		contentPane.add(tqual);
		tqual.setColumns(10);
		
		tocc = new JTextField();
		tocc.setBackground(new Color(255, 255, 255));
		tocc.setForeground(new Color(0, 0, 153));
		tocc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tocc.setBounds(471, 119, 255, 24);
		tocc.setText(s8);
		tocc.setEditable(false);
		contentPane.add(tocc);
		tocc.setColumns(10);
		
		torg = new JTextField();
		torg.setBackground(new Color(255, 255, 255));
		torg.setForeground(new Color(0, 0, 153));
		torg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		torg.setBounds(229, 161, 373, 24);
		torg.setText(s9);
		torg.setEditable(false);
		contentPane.add(torg);
		torg.setColumns(10);
		
		twexp = new JTextField();
		twexp.setBackground(new Color(255, 255, 255));
		twexp.setForeground(new Color(0, 0, 153));
		twexp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		twexp.setBounds(186, 201, 183, 24);
		twexp.setText(s10);
		twexp.setEditable(false);
		contentPane.add(twexp);
		twexp.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location :");
		lblLocation.setForeground(new Color(204, 0, 0));
		lblLocation.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblLocation.setBounds(416, 202, 86, 18);
		contentPane.add(lblLocation);
		
		tloc = new JTextField();
		tloc.setBackground(new Color(255, 255, 255));
		tloc.setForeground(new Color(0, 0, 153));
		tloc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tloc.setBounds(512, 201, 214, 24);
		tloc.setText(s11);
		tloc.setEditable(false);
		contentPane.add(tloc);
		tloc.setColumns(10);
		
		JLabel lblEmailId = new JLabel("E-mail id :");
		lblEmailId.setForeground(new Color(204, 0, 0));
		lblEmailId.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblEmailId.setBounds(35, 247, 86, 19);
		contentPane.add(lblEmailId);
		
		temail = new JTextField();
		temail.setBackground(new Color(255, 255, 255));
		temail.setForeground(new Color(0, 0, 153));
		temail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		temail.setBounds(131, 244, 304, 24);
		temail.setText(s5);
		temail.setEditable(false);
		contentPane.add(temail);
		temail.setColumns(10);
		
		JLabel lblOtherDetails = new JLabel("Other details :");
		lblOtherDetails.setForeground(new Color(204, 0, 0));
		lblOtherDetails.setFont(new Font("Aharoni", Font.PLAIN, 16));
		lblOtherDetails.setBounds(35, 342, 119, 24);
		contentPane.add(lblOtherDetails);
		
		JButton b_update = new JButton("UPDATE");
		b_update.setEnabled(false);
		if(Login.uType.equals("alumni"))
			b_update.setEnabled(true);
		b_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
					s=(Statement)conn.createStatement();
					String q="update signup "
							+ "set email_id=?"
							+ "where username=?";
					ps3=conn.prepareStatement(q);
					ps3.setString(1,temail.getText());
					ps3.setString(2,Login.user);
					ps3.executeUpdate();
					
					String q1="update alumni "
							+ "set qualification=?,occupation=?,name_of_org=?,work_exp=?,location=?,achievements=?,other_info=?"
							+ "where EnrollNo=?";
					ps4=conn.prepareStatement(q1);
					ps4.setString(1,tqual.getText());
					ps4.setString(2,tocc.getText());
					ps4.setString(3,torg.getText());
					ps4.setString(4,twexp.getText());
					ps4.setString(5,tloc.getText());
					ps4.setString(6,tachv.getText());
					ps4.setString(7,tothr.getText());
					ps4.setString(8,Login.eroll);
					if(ps4.executeUpdate()>0 || ps3.executeUpdate()>0)
						JOptionPane.showMessageDialog(null,"Details updated successfully!");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				new Home().setVisible(true);
				setVisible(false);
				
			}
		});
		b_update.setForeground(new Color(0, 0, 102));
		b_update.setFont(new Font("Andalus", Font.BOLD, 16));
		b_update.setBounds(580, 413, 119, 28);
		contentPane.add(b_update);
		
		JButton b_back = new JButton("BACK");
		b_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Home().setVisible(true);
				setVisible(false);
			}
		});
		b_back.setForeground(new Color(0, 0, 102));
		b_back.setFont(new Font("Andalus", Font.BOLD, 16));
		b_back.setBounds(35, 418, 89, 23);
		contentPane.add(b_back);
		
		tachv = new JTextArea();
		tachv.setBackground(new Color(255, 255, 255));
		tachv.setForeground(new Color(0, 0, 153));
		tachv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tachv.setText(s12);
		tachv.setEditable(false);
		tachv.setBounds(159, 276, 316, 57);
		contentPane.add(tachv);
		
		tothr = new JTextArea();
		tothr.setForeground(new Color(0, 0, 153));
		tothr.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tothr.setBackground(new Color(255, 255, 255));
		tothr.setBounds(164, 342, 310, 46);
		tothr.setText(s13);
		tothr.setEditable(false);
		contentPane.add(tothr);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tqual.setEditable(true);
				tocc.setEditable(true);
				tloc.setEditable(true);
				torg.setEditable(true);
				twexp.setEditable(true);
				tachv.setEditable(true);
				tothr.setEditable(true);
				temail.setEditable(true);
			}
		});
		btnEdit.setForeground(new Color(0, 0, 102));
		btnEdit.setFont(new Font("Andalus", Font.BOLD, 16));
		btnEdit.setBounds(294, 413, 102, 28);
		contentPane.add(btnEdit);
		
		}
}

