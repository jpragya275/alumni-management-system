package java_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Search_2 extends JFrame {
	private JTextField k1;
	private JTextField k2;
	private JTextField k3;
	private JComboBox c2;
	private JButton b2;
	private JComboBox c3;
	private JTable table;
	private JScrollPane sp;
	
	Connection conn;
	Statement s;
	PreparedStatement ps;
	ResultSet rs;
	int count =0;
	String s1,s2,s3,s4,s5,s6;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_2 frame = new Search_2();
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
	public Search_2() {
		setTitle("SEARCH FRAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 493);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l4 = new JLabel("Search By:");
		l4.setBounds(327, 18, 89, 28);
		contentPane.add(l4);
		
		JButton b1 = new JButton("ADD +");
	
		
		b1.setBounds(611, 18, 89, 23);
		contentPane.add(b1);
		
		String f1[]={"name","course","year_of_adm","year_of_passing","user_type","username"};
		
		JComboBox c1 = new JComboBox(f1);
		c1.setBounds(422, 21, 163, 20);
		contentPane.add(c1);
		
		JLabel l1 = new JLabel("Enter Key :");
		l1.setBounds(19, 26, 72, 14);
		contentPane.add(l1);
		
		k1 = new JTextField();
		k1.setBounds(101, 20, 217, 20);
		contentPane.add(k1);
		k1.setColumns(10);
		
		c2 = new JComboBox(f1);
		c2.setBounds(422, 57, 163, 20);
		c2.setVisible(false);
		contentPane.add(c2);
		
		k2 = new JTextField();
		k2.setColumns(10);
		k2.setBounds(101, 57, 217, 20);
		k2.setVisible(false);
		contentPane.add(k2);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count++;
				k2.setVisible(true);
				c2.setVisible(true);
				b2.setVisible(true);
			}
		});
		
		b2 = new JButton("ADD +");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count++;
				k3.setVisible(true);
				c3.setVisible(true);
			}
		});
		b2.setBounds(611, 56, 89, 23);
		b2.setVisible(false);
		contentPane.add(b2);
		
		c3 = new JComboBox(f1);
		c3.setBounds(422, 88, 163, 20);
		c3.setVisible(false);
		contentPane.add(c3);
		
		k3 = new JTextField();
		k3.setColumns(10);
		k3.setBounds(101, 93, 217, 20);
		k3.setVisible(false);
		contentPane.add(k3);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Andalus", Font.BOLD, 15));
		btnSearch.setBounds(313, 124, 117, 23);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("GO BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Home().setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Dialog", Font.BOLD, 11));
		btnBack.setBounds(624, 122, 105, 28);
		contentPane.add(btnBack);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				if(k1.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Enter something to search");
				else
				{
					if(count==2) {
						 s1=k1.getText();
						 s2=c1.getSelectedItem().toString();
						 s3=k2.getText();
						 s4=c2.getSelectedItem().toString();
						 s5=k3.getText();
						 s6=c3.getSelectedItem().toString();
						
						 
							JOptionPane.showMessageDialog(null,"Fetching search results...");
							ListUsers2(s2,s1,s4,s3,s6,s5);
							show_sresult2(s2,s1,s4,s3,s6,s5);
					}
					else if(count==1) {
						 s1=k1.getText();
						 s2=c1.getSelectedItem().toString();
						 s3=k2.getText();
						 s4=c2.getSelectedItem().toString();
						 
							JOptionPane.showMessageDialog(null,"Fetching search results...");
							ListUsers1(s2,s1,s4,s3);
							show_sresult1(s2,s1,s4,s3);
					}
					else {
					 s1=k1.getText();
					 s2=c1.getSelectedItem().toString();
						JOptionPane.showMessageDialog(null,"Fetching search results...");
						ListUsers(s2,s1);
						show_sresult(s2,s1);
					}
					}
				}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					sp.setVisible(true);
					table.setVisible(true);
					//ListUsers();
					//show_sresult();
				}
				});
		sp = new JScrollPane();
		sp.setBounds(10, 152, 610, 291);
		sp.setVisible(false);
		contentPane.add(sp);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 204));
		table.setBackground(new Color(255, 255, 102));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Course", "Year of Admission", "Year of Passing"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		sp.setViewportView(table);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k1.setText(null);
				k2.setText(null);
				k3.setText(null);
				k2.setVisible(false);
				k3.setVisible(false);
				b2.setVisible(false);
				c2.setVisible(false);
				c3.setVisible(false);
				label.setText(null);
				sp.setVisible(false);
				table.setVisible(false);
				/*new Search().setVisible(true);
				setVisible(false);*/
			}
		});
		btnNewButton.setBounds(647, 170, 82, 23);
		contentPane.add(btnNewButton);
		
		label = new JLabel("");
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(19, 124, 284, 19);
		contentPane.add(label);
		table.setVisible(false);
		
	}
	
	public ArrayList<SearchResultt> ListUsers(String sr1, String sr2)
	{
		ArrayList<SearchResultt> usersList=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q="select name,course,year_of_adm,year_of_pass from signup where " +sr1+" = '"+sr2+"' ";
			ps=conn.prepareStatement(q);
			//ps.setString(1,s2);
			//ps.setString(2,s1);
			rs=ps.executeQuery();
			SearchResultt ser;
			while(rs.next())
			{
				ser = new SearchResultt(
											rs.getString("name"),
											rs.getString("course"),
											rs.getString("year_of_adm"),
											rs.getString("year_of_pass"));
				usersList.add(ser);
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
			
			//ex.printStackTrace();
		}
		//System.out.println("Data fetched from db");
		return usersList;
	}
	public void show_sresult(String ss1, String ss2)
	{
		//System.out.println("inside show_result()");
		ArrayList<SearchResultt> sr= ListUsers(ss1,ss2);
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		if(sr.size()==0)
		{
			label.setText("No such record found!");
		}
		Object[] row =new Object[4];
		for(int i=0 ; i<sr.size(); i++)
		{
			row[0]=sr.get(i).getName();
			row[1]=sr.get(i).getCourse();
			row[2]=sr.get(i).getyoa();
			row[3]=sr.get(i).getyop();
			model.addRow(row);
		
		}
		
	}
	public ArrayList<SearchResultt> ListUsers1(String sr1, String sr2,String sr3, String sr4)
	{
		ArrayList<SearchResultt> usersList=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q="select name,course,year_of_adm,year_of_pass from signup where " +sr1+" = '"+sr2
					+"' and " +sr3+" = '"+sr4+"'";
			ps=conn.prepareStatement(q);
			//ps.setString(1,s2);
			//ps.setString(2,s1);
			rs=ps.executeQuery();
			SearchResultt ser;
			while(rs.next())
			{
				ser = new SearchResultt(
											rs.getString("name"),
											rs.getString("course"),
											rs.getString("year_of_adm"),
											rs.getString("year_of_pass"));
				usersList.add(ser);
				
			}
		}catch(Exception ex){
			//System.out.println("hi line 233");
			
			ex.printStackTrace();
		}
		//System.out.println("Data fetched from db");
		return usersList;
	}
	public void show_sresult1(String ss1, String ss2,String ss3, String ss4)
	{
		//System.out.println("inside show_result()");
		ArrayList<SearchResultt> sr= ListUsers1(ss1,ss2,ss3,ss4);
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		if(sr.size()==0)
		{
			label.setText("No such record found!");
		}
		Object[] row =new Object[4];
		for(int i=0 ; i<sr.size(); i++)
		{
			row[0]=sr.get(i).getName();
			row[1]=sr.get(i).getCourse();
			row[2]=sr.get(i).getyoa();
			row[3]=sr.get(i).getyop();
			model.addRow(row);
		
		}
		
	}
	public ArrayList<SearchResultt> ListUsers2(String sr1, String sr2,String sr3, String sr4, String sr5, String sr6)
	{
		ArrayList<SearchResultt> usersList=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q="select name,course,year_of_adm,year_of_pass from signup where " +sr1+" = '"+sr2
					+"' and " +sr3+" = '"+sr4+"' and " +sr5+" = '"+sr6+"'";
					
			ps=conn.prepareStatement(q);
			//ps.setString(1,s2);
			//ps.setString(2,s1);
			rs=ps.executeQuery();
			SearchResultt ser;
			while(rs.next())
			{
				ser = new SearchResultt(
											rs.getString("name"),
											rs.getString("course"),
											rs.getString("year_of_adm"),
											rs.getString("year_of_pass"));
				usersList.add(ser);
				
			}
		}catch(Exception ex){
			//System.out.println("hi line 233");
			
			ex.printStackTrace();
		}
		//System.out.println("Data fetched from db");
		return usersList;
	}
	public void show_sresult2(String ss1, String ss2, String ss3, String ss4, String ss5, String ss6)
	{
		//System.out.println("inside show_result()");
		ArrayList<SearchResultt> sr= ListUsers2(ss1,ss2,ss3,ss4,ss5,ss6);
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		model.setRowCount(0);
		if(sr.size()==0)
		{
			label.setText("No such record found!");
		}
		Object[] row =new Object[4];
		for(int i=0 ; i<sr.size(); i++)
		{
			row[0]=sr.get(i).getName();
			row[1]=sr.get(i).getCourse();
			row[2]=sr.get(i).getyoa();
			row[3]=sr.get(i).getyop();
			model.addRow(row);
		
		}
		
	}
}