import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class CompleteOrders extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton btnExport;
	java.sql.Connection conn=null;
	private JTextField company;
	private JLabel label;
	private JTextField client;
	private JLabel label_1;
	private JButton button;
	private JButton btnUpdate;
	private String text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompleteOrders frame = new CompleteOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	void load(){
		
		  DefaultTableModel model ;
		  ResultSet rs = null;

	    model = new DefaultTableModel();
	    table.setModel(model);
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
				String query="select * from allorders where status='complete'";
				PreparedStatement pst=conn.prepareStatement(query);
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	}
	public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);
        for(int i=0; i < model.getColumnCount(); i++) {
    out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
    for(int j=0; j < model.getColumnCount(); j++) {
        out.write(model.getValueAt(i,j).toString()+"\t");
        }
        out.write("\n");
    }

    out.close();
}

	/**
	 * Create the frame.
	 */
	public CompleteOrders() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 77, 1324, 569);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				int i;
				i=table.getSelectedRow();
				 text= (String) table.getValueAt(i, 0);
			}
		});
		contentPane.add(table);
		
		lblNewLabel = new JLabel("Completed Orders");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(10, 0, 527, 66);
		contentPane.add(lblNewLabel);
		
		btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					exportTable(table, new File("CompletedOrders.xls"));

					JOptionPane.showMessageDialog(null, "Completed Orders Exported into Excel File");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnExport.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnExport.setBounds(228, 664, 163, 54);
		contentPane.add(btnExport);
		
		company = new JTextField();
		company.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel model ;
				  ResultSet rs = null;
				  PreparedStatement pst=null;

			    model = new DefaultTableModel();
			    table.setModel(model);
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
						String query="select * from allorders where status='complete' and supplier like '%"+company.getText()+"%'";
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
				    pst.close();rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		company.setColumns(10);
		company.setBounds(1129, 32, 151, 32);
		contentPane.add(company);
		
		label = new JLabel("Search by Company");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(905, 23, 196, 43);
		contentPane.add(label);
		
		client = new JTextField();
		client.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel model ;
				  ResultSet rs = null;
				  PreparedStatement pst=null;

			    model = new DefaultTableModel();
			    table.setModel(model);
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
						String query="select * from allorders where status='complete' and client like '%"+client.getText()+"%'";
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
				    pst.close();rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		client.setColumns(10);
		client.setBounds(712, 29, 151, 32);
		contentPane.add(client);
		
		label_1 = new JLabel("Search by Client");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(534, 21, 168, 43);
		contentPane.add(label_1);
		
		button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 29));
		button.setBounds(685, 664, 163, 54);
		contentPane.add(button);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int order = Integer.parseInt(text);
				CurrentOrder or=new CurrentOrder(order);
			      or.setVisible(true);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnUpdate.setBounds(1095, 664, 163, 54);
		contentPane.add(btnUpdate);
		load();
	}

}
