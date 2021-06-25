package java_project;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Event extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
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
					Event frame = new Event();
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
	public Event() {
		setTitle("EVENTS FRAME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEventsAndNotifications = new JLabel("EVENTS AND NOTIFICATIONS");
		lblEventsAndNotifications.setForeground(new Color(153, 0, 102));
		lblEventsAndNotifications.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblEventsAndNotifications.setBounds(233, 11, 241, 28);
		contentPane.add(lblEventsAndNotifications);
		
		JLabel lblUpcomingEvents = new JLabel("UPCOMING EVENTS");
		lblUpcomingEvents.setForeground(new Color(102, 153, 0));
		lblUpcomingEvents.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		lblUpcomingEvents.setBounds(262, 50, 218, 28);
		contentPane.add(lblUpcomingEvents);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 89, 674, 291);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 204));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"S.no.", "Event Name", "Event Details", "Date of Event", "Organiser"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAddEvent = new JButton("ADD EVENT +");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreateEvent().setVisible(true);
				setVisible(false);
			}
		});
		btnAddEvent.setForeground(new Color(0, 0, 102));
		btnAddEvent.setFont(new Font("Andalus", Font.BOLD, 15));
		btnAddEvent.setBounds(482, 401, 174, 28);
		contentPane.add(btnAddEvent);
		
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
		EList();
		show_event();
	}
	public ArrayList<Events> EList()
	{
		ArrayList<Events> eList=new ArrayList<>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javaprjct","root","root");
			s=(Statement)conn.createStatement();
			String q="select * from events limit 10 ";
			ps=conn.prepareStatement(q);
			//ps.setString(1,Login.user);
			rs=ps.executeQuery();
			Events e;
			while(rs.next())
			{
				e=new Events(rs.getString("E_name"),rs.getString("E_det"),rs.getString("E_date"),rs.getString("E_org"));
				eList.add(e);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return eList;
	}
	public void show_event()
	{
		ArrayList<Events> ev= EList();
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		Object[] row =new Object[5];
		int sn=1;
		for(int i=0 ; i<ev.size(); i++)
		{
			row[0]=sn;
			row[1]=ev.get(i).getEname();
			row[2]=ev.get(i).getEdet();
			row[3]=ev.get(i).getEdate();
			row[4]=ev.get(i).getEorg();
			model.addRow(row);
			sn++;
		}
		
	}
}
