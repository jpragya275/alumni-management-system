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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CreateEvent extends JFrame {

	private JPanel contentPane;
	private JTextField Ename;
	private JTextField Edate;
	private JTextField Eorg;

	Connection conn;
	Statement s;
	ResultSet rs;
	PreparedStatement ps;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEvent frame = new CreateEvent();
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
	public CreateEvent() {
		setTitle("CREATE EVENT FRAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreateANew = new JLabel("Create a new Event ");
		lblCreateANew.setForeground(new Color(204, 0, 0));
		lblCreateANew.setFont(new Font("Aparajita", Font.BOLD, 20));
		lblCreateANew.setBounds(70, 21, 198, 31);
		contentPane.add(lblCreateANew);
		
		JLabel lblEventName = new JLabel("Event Name :");
		lblEventName.setFont(new Font("Arial", Font.BOLD, 15));
		lblEventName.setBounds(70, 89, 109, 31);
		contentPane.add(lblEventName);
		
		Ename = new JTextField();
		Ename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Ename.setBounds(189, 89, 302, 26);
		contentPane.add(Ename);
		Ename.setColumns(10);
		
		JLabel lblEventDetails = new JLabel("Event Details :");
		lblEventDetails.setFont(new Font("Arial", Font.BOLD, 15));
		lblEventDetails.setBounds(70, 145, 109, 26);
		contentPane.add(lblEventDetails);
		
		JTextArea Edetail = new JTextArea();
		Edetail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Edetail.setBounds(189, 147, 421, 93);
		contentPane.add(Edetail);
		
		JLabel lblDateOfEvent = new JLabel("Date of Event :");
		lblDateOfEvent.setFont(new Font("Arial", Font.BOLD, 15));
		lblDateOfEvent.setBounds(70, 272, 118, 26);
		contentPane.add(lblDateOfEvent);
		
		Edate = new JTextField();
		Edate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Edate.setBounds(189, 272, 154, 24);
		contentPane.add(Edate);
		Edate.setColumns(10);
		
		JButton btnCreateEvent = new JButton("CREATE EVENT");
		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String en=Ename.getText();
				String edet=Edetail.getText();
				String edate=Edate.getText();
				String org=Eorg.getText();
				
				if(en.equals(""))
					JOptionPane.showMessageDialog(null,"Event name blank!");
				else if(edet.equals(""))
					JOptionPane.showMessageDialog(null,"Event details blank!");
				else if(edate.equals(""))
					JOptionPane.showMessageDialog(null,"Event date blank!");
				else if(org.equals(""))
					JOptionPane.showMessageDialog(null,"Event organiser blank!");
				else
				{
					try{
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
						s=(Statement)conn.createStatement();
						String q="insert into events(E_name , E_det , E_date ,E_org)"
								+ "values(?,?,?,?)";
						ps=conn.prepareStatement(q);
						ps.setString(1,en);
						ps.setString(2,edet);
						ps.setString(3,edate);
						ps.setString(4,org);
						
						if(ps.executeUpdate()>0)
							JOptionPane.showMessageDialog(null,"Event Created!");
						else
							JOptionPane.showMessageDialog(null,"Event creation failed!");
								
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
			}
				
		});
		btnCreateEvent.setForeground(new Color(0, 0, 102));
		btnCreateEvent.setFont(new Font("Andalus", Font.BOLD, 15));
		btnCreateEvent.setBounds(259, 381, 189, 31);
		contentPane.add(btnCreateEvent);
		
		JLabel lblOrganisedBy = new JLabel("Organised By :");
		lblOrganisedBy.setFont(new Font("Arial", Font.BOLD, 15));
		lblOrganisedBy.setBounds(70, 319, 118, 26);
		contentPane.add(lblOrganisedBy);
		
		Eorg = new JTextField();
		Eorg.setBounds(189, 317, 302, 26);
		contentPane.add(Eorg);
		Eorg.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Event().setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Andalus", Font.BOLD, 15));
		btnBack.setBounds(565, 20, 89, 31);
		contentPane.add(btnBack);
	}
}

