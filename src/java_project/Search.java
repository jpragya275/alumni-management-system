package java_project;

import java.awt.BorderLayout;
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
import java.awt.Color;

public class Search extends JFrame {
	private JTextField k1;
	private JTable table;
	private JScrollPane sp;
	
	Connection conn;
	Statement s;
	PreparedStatement ps;
	ResultSet rs;

	String s1,s2;
	private JLabel lblSearchResults;
	private JComboBox c1;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
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
	public Search() {
		setTitle("SEARCH FRAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 493);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String f1[]={"name","course","year_of_adm","year_of_passing","user_type","username"};
		
		JLabel l1 = new JLabel("Enter Key :");
		l1.setBounds(19, 26, 72, 14);
		contentPane.add(l1);
		
		k1 = new JTextField();
		k1.setBounds(101, 20, 217, 20);
		contentPane.add(k1);
		k1.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		
		btnSearch.setFont(new Font("Andalus", Font.BOLD, 15));
		btnSearch.setBounds(587, 21, 117, 23);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(k1.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Enter something to search");
				else
				{
					
					 s1=k1.getText();
					 s2=c1.getSelectedItem().toString();
					
					sp.setVisible(true);
					table.setVisible(true);
					lblSearchResults.setVisible(true);
					ListUsers();
					show_sresult();
					
				}
				
			}
		});
		sp = new JScrollPane();
		sp.setBounds(29, 110, 654, 296);
		sp.setVisible(false);
		contentPane.add(sp);
		
		table = new JTable();
		table.setBackground(new Color(204, 255, 204));
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
		
		lblSearchResults = new JLabel("Search Results");
		lblSearchResults.setForeground(Color.MAGENTA);
		lblSearchResults.setFont(new Font("Arial", Font.BOLD, 15));
		lblSearchResults.setBounds(19, 75, 170, 28);
		lblSearchResults.setVisible(false);
		contentPane.add(lblSearchResults);
		
		c1 = new JComboBox(f1);
		c1.setBounds(364, 20, 170, 20);
		contentPane.add(c1);
		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(199, 75, 416, 22);
		contentPane.add(label);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Home().setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Andalus", Font.BOLD, 15));
		btnBack.setBounds(559, 417, 117, 26);
		contentPane.add(btnBack);
		table.setVisible(false);
		
	}
	public ArrayList<SearchResultt> ListUsers()
	{
		ArrayList<SearchResultt> usersList=new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q=null;
			
				 q="select name,course,year_of_adm,year_of_pass from signup "
						+ "where "+s2+"= '"+s1+"'";
						//+ "name=? or username =? or course=? or year_of_adm=? or year_of_pass=? or user_type=? ";
			
			
			ps=conn.prepareStatement(q);
			/*ps.setString(1,s1);
			ps.setString(2,s1);
			ps.setString(3,s1);
			ps.setString(4,s1);
			ps.setString(5,s1);
			ps.setString(6,s1);*/
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
		}
		
		return usersList;
	}
	public void show_sresult()
	{
		
		ArrayList<SearchResultt> sr= ListUsers();
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
