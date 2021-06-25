package java_project;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
/*
 * make jtable, perform action listener, see batch for batchmates, add filter perform filter
 * */
 
public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField textField;
	private JTextField txtYourBatchmates;
	private JTable rtable;

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
					SearchFrame frame = new SearchFrame();
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
	public SearchFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 491);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		t1 = new JTextField();
		t1.setText("Enter Keywords...");
		t1.setForeground(Color.BLACK);
		t1.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		t1.setBounds(10, 21, 315, 25);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton searchButton = new JButton("");
		searchButton.setBounds(442, 21, 36, 35);
		contentPane.add(searchButton);
		
		JButton btnNewButton_1 = new JButton("Add filters");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNewButton_1.setBounds(488, 21, 89, 35);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setText("Enter Keywords...");
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		textField.setColumns(10);
		textField.setBounds(10, 55, 315, 25);
		contentPane.add(textField);
		
		String filter[]= {"Name","Batch","Course","Location","Qualification","Organisation"};
		//getContentPane().setLayout(null);
		JComboBox comboBox = new JComboBox(filter);
		comboBox.setBounds(335, 21, 97, 24);
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		//comboBox.setToolTipText(filter);
		comboBox.setBackground(Color.WHITE);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(filter);
		comboBox_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(335, 55, 97, 24);
		contentPane.add(comboBox_1);
		
		txtYourBatchmates = new JTextField();
		txtYourBatchmates.setText("Your Batchmates");
		txtYourBatchmates.setForeground(Color.GRAY);
		txtYourBatchmates.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		txtYourBatchmates.setBounds(10, 115, 152, 20);
		contentPane.add(txtYourBatchmates);
		txtYourBatchmates.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Home().setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Andalus", Font.BOLD, 15));
		btnBack.setBounds(57, 403, 89, 24);
		contentPane.add(btnBack);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 683, 282);
		contentPane.add(scrollPane);
		
		rtable = new JTable();
		rtable.setBackground(new Color(255, 255, 204));
		rtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Batch", "Course",  "Location" //"Working At",
			}
		) {
			Class[] columnTypes = new Class[] {
					String.class, Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(rtable);
		ListUsers();
		show_sresult();
	}
			public ArrayList<SearchResultt> ListUsers()
		{
			ArrayList<SearchResultt> usersList=new ArrayList<>();
			try {
				String y=t1.getText();
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
				s=(Statement)conn.createStatement();
				String query = "select name, course, year_of_adm , year_of_pass  from signup where year_of_adm=?";
				ps = conn.prepareStatement(query);
				ps.setString(1,y);
				rs = ps.executeQuery();
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
				System.out.println(ex.getMessage());
			}
			return usersList;
		}
		public void show_sresult()
		{
			ArrayList<SearchResultt> sr= ListUsers();
			DefaultTableModel model=(DefaultTableModel)rtable.getModel();
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


	/*	btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String s1 = txtEnterKeywords.getText(); 
					String combo = (String)comboBox.getSelectedItem();
					if(s1.length()>0) {
							String s2 = "select name,centreCode from student where" + combo + "= '"+ s1 +"' ";
						
						//Conn c = new Conn();
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jnu","root","3hdsoW@!"); //127//.0.0.1:3306
							Statement s = con.prepareStatement(s2);
							ResultSet rs1 = s.executeQuery(s2);
							if(rs1.next()) {
								System.out.println("Action Performed and "+s1);
								lblNewLabel.setText(rs1.getString(1));
								lblNewLabel_2.setText(rs1.getString(2));
							}
							else {
								System.out.println("error in get string");
							}
							} else {
								System.out.println("string length 0");
						}
						} catch(Exception ex) {
							System.out.println("review query");
						}
			}
		});*/
	
			
