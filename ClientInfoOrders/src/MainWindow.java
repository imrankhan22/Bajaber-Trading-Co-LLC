import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTable orders;
	java.sql.Connection conn=null;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						MainWindow frame = new MainWindow();
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
	void load(){
		
		  DefaultTableModel model ;
		  ResultSet rs = null;
		  PreparedStatement pst = null;
	    model = new DefaultTableModel();
	    orders.setModel(model);
	    model.addColumn("OrderNO");
	    model.addColumn("Date Started");
	    model.addColumn("Client");
	    model.addColumn("Description");
	    model.addColumn("Supplier");
	    model.addColumn("Invoice Reference");
	    model.addColumn("Status");
	    model.addColumn("Deposit");
	 
	    model.addColumn("Payment Terms");
	    model.addColumn("Balance(USD)");
	    model.addColumn("Delivery Date");
	    model.addColumn("No Containers");
	    model.addColumn("Commission(USD)");

	    model.addColumn("Notes");
		
	   
	    model.addRow(new Object[]{"OrderNo", "Date Started", "Client", "Description", "Supplier","Invoice Reference","Status","Deposit","Payment Terms","Balance(USD)","Delivery Date","No Containers","Commission(USD)","Notes"});
		

			try {
				String query="select * from allorders where status='current'";
				 pst=conn.prepareStatement(query);
			    rs=pst.executeQuery();
			   
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		
		
		
		 ResultSetMetaData meta;
		try {
			meta = rs.getMetaData();
			int numberOfColumns = meta.getColumnCount();
		    while (rs.next())
		    {
		        Object [] rowData = new Object[numberOfColumns];
		        for (int i = 0; i < rowData.length; ++i)
		        {
		            rowData[i] = rs.getObject(i+1);
		        }
		        model.addRow(rowData);
		    }
		    rs.close();
		     pst.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	public MainWindow() {

		conn=Connector.dbConnector();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1360, 765);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPersonalInformation = new JButton("Supplier Information");
		btnPersonalInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SupplierInfo info=new SupplierInfo();
				info.setVisible(true);
			}
		});
		btnPersonalInformation.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPersonalInformation.setBounds(10, 107, 204, 50);
		contentPane.add(btnPersonalInformation);
		
		JButton btnNewButton = new JButton("Client Information");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowClient frame = new ShowClient();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(237, 107, 179, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("All Orders");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ShowAllOrders client=new ShowAllOrders();
				client.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(448, 107, 179, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Current Orders");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ShowAll client=new ShowAll();
				client.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(670, 107, 179, 50);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Shipped Orders");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Shippedorders frame = new Shippedorders();
				frame.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBounds(919, 107, 161, 50);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Complete Orders");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CompleteOrders com=new CompleteOrders();
				com.setVisible(true);
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBounds(1143, 107, 173, 50);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("BAJABER GENERAL TRADING");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 60));
		lblNewLabel.setBounds(10, 11, 1058, 85);
		contentPane.add(lblNewLabel);
		
		orders = new JTable();
		orders.setBounds(1, 182, 1333, 533);
		contentPane.add(orders);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Import frame = new Import();
				frame.setVisible(true);
			}
		});
		btnImport.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnImport.setBounds(1195, 30, 121, 50);
		contentPane.add(btnImport);
		load();
	}
}
