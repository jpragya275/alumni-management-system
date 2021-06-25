package java_project;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View_msg extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	Connection conn;
	PreparedStatement ps;
	Statement s;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_msg frame = new View_msg();
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
	public View_msg() {
		setTitle("RECEIVED MESSAGES");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 475);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMessages = new JLabel("MESSAGES");
		lblMessages.setForeground(Color.RED);
		lblMessages.setFont(new Font("Andalus", Font.BOLD, 18));
		lblMessages.setBounds(273, 29, 102, 31);
		contentPane.add(lblMessages);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 93, 518, 265);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setForeground(new Color(0, 0, 153));
		table.setBackground(new Color(255, 255, 102));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sender", "Message"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	
		});
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Message().setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(0, 0, 102));
		btnBack.setFont(new Font("Andalus", Font.BOLD, 16));
		btnBack.setBounds(282, 383, 107, 31);
		contentPane.add(btnBack);
		msgList();
		show_msg();
	}
	public ArrayList<Msg> msgList()
	{
		ArrayList<Msg> mList=new ArrayList<>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q="select * from message where receiver=? ";
			//s.setString(1,Login.user);
			ps=conn.prepareStatement(q);
			ps.setString(1,Login.user);
			rs=ps.executeQuery();
			Msg m;
			while(rs.next())
			{
				m=new Msg(rs.getString("sender"),rs.getString("msg"));
				mList.add(m);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return mList;
	}
	public void show_msg()
	{
		ArrayList<Msg> ms= msgList();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		Object[] row =new Object[2];
		for(int i=0 ; i<ms.size(); i++)
		{
			row[0]=ms.get(i).getSender();
			row[1]=ms.get(i).getMsg();
			model.addRow(row);
			
			
		}
		
	}
}
