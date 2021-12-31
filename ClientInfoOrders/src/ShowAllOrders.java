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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ShowAllOrders extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	java.sql.Connection conn=null;
	private JButton btnNewButton;
	private JButton btnRefresh;
	private JTextField company;
	private JLabel lblSearchByCompany;
	private JTextField client;
	private JLabel lblSearchByClient;
	private JButton btnUpdate;
	private String text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowAllOrders frame = new ShowAllOrders();
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
	    model.addColumn("Deposit");
	    model.addColumn("Status");
	    model.addColumn("Payment Terms");
	    model.addColumn("Balance(USD)");
	    model.addColumn("Delivery Date");
	    model.addColumn("No Containers");
	    model.addColumn("Commission(USD)");

	    model.addColumn("Notes");
		
	   
	    model.addRow(new Object[]{"OrderNo", "Date Started", "Client", "Description", "Supplier","Invoice Reference","Status","Deposit","Payment Terms","Balance(USD)","Delivery Date","No Containers","Commission(USD)","Notes"});
		

			try {
				String query="select * from allorders";
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
	public ShowAllOrders() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 77, 1324, 576);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				int i;
				i=table.getSelectedRow();
				 text= (String) table.getValueAt(i, 0);
			}
		});
		contentPane.add(table);
		
		lblNewLabel = new JLabel("All Orders");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(10, 0, 347, 66);
		contentPane.add(lblNewLabel);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				AllOrders client=new AllOrders();
				client.setVisible(true);
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnAddNew.setBounds(134, 664, 192, 54);
		contentPane.add(btnAddNew);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				
				
				
				
				String select=comboBox.getSelectedItem().toString();
				
				  DefaultTableModel model = null ;
				  ResultSet rs = null;
				  model = new DefaultTableModel();
				    table.setModel(model);
				    model.addColumn("OrderNO");
				    model.addColumn("Date Started");
				    model.addColumn("Client");
				    model.addColumn("Description");
				    model.addColumn("Supplier");
				    model.addColumn("Invoice Reference");
				    model.addColumn("Deposit");
				    model.addColumn("Payment Terms");
				    model.addColumn("Balance(USD)");
				    model.addColumn("Delivery Date");
				    model.addColumn("No Containers");
				    model.addColumn("Commission(USD)");

				    model.addColumn("Notes");
					
				   
				    model.addRow(new Object[]{"OrderNo", "Date Started", "Client", "Description", "Supplier","Invoice Reference","Deposit","Payment Terms","Balance(USD)","Delivery Date","No Containers","Commission(USD)","Notes"});
					
				
                  if(select.equals("client")){
					
					
					try {
						String query="select * from allorders order by client";
						PreparedStatement pst=conn.prepareStatement(query);
					    rs=pst.executeQuery();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if(select.equals("supplier")){
					try {
						String query="select * from allorders order by supplier";
						PreparedStatement pst=conn.prepareStatement(query);
						 rs=pst.executeQuery();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"client", "supplier"}));
		comboBox.setBounds(1128, 16, 169, 41);
		contentPane.add(comboBox);
		
		btnNewButton = new JButton("Export");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					exportTable(table, new File("AllOrders.xls"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnNewButton.setBounds(350, 665, 192, 53);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you really want to Delete it !!","Delete order", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					 try {
							String query="Delete from allorders where orderno='"+text+"'";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.execute();
							pst.close();
							JOptionPane.showMessageDialog(null, "Order  deleted");
							
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBounds(645, 659, 124, 59);
		contentPane.add(button);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRefresh.setBounds(1027, 666, 124, 59);
		contentPane.add(btnRefresh);
		
		company = new JTextField();
		company.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
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
			    model.addColumn("Deposit");
			    model.addColumn("Status");
			    model.addColumn("Payment Terms");
			    model.addColumn("Balance(USD)");
			    model.addColumn("Delivery Date");
			    model.addColumn("No Containers");
			    model.addColumn("Commission(USD)");

			    model.addColumn("Notes");
				
			   
			    model.addRow(new Object[]{"OrderNo", "Date Started", "Client", "Description", "Supplier","Invoice Reference","Status","Deposit","Payment Terms","Balance(USD)","Delivery Date","No Containers","Commission(USD)","Notes"});
				

					try {
						String query="select * from allorders where supplier like '%"+company.getText()+"%'";
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
		});
		company.setColumns(10);
		company.setBounds(909, 20, 151, 32);
		contentPane.add(company);
		
		lblSearchByCompany = new JLabel("Search by Company");
		lblSearchByCompany.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchByCompany.setBounds(676, 12, 196, 43);
		contentPane.add(lblSearchByCompany);
		
		client = new JTextField();
		client.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
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
			    model.addColumn("Deposit");
			    model.addColumn("Status");
			    model.addColumn("Payment Terms");
			    model.addColumn("Balance(USD)");
			    model.addColumn("Delivery Date");
			    model.addColumn("No Containers");
			    model.addColumn("Commission(USD)");

			    model.addColumn("Notes");
				
			   
			    model.addRow(new Object[]{"OrderNo", "Date Started", "Client", "Description", "Supplier","Invoice Reference","Status","Deposit","Payment Terms","Balance(USD)","Delivery Date","No Containers","Commission(USD)","Notes"});
				

					try {
						String query="select * from allorders where client like '%"+client.getText()+"%'";
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
		});
		client.setColumns(10);
		client.setBounds(515, 20, 151, 32);
		contentPane.add(client);
		
		lblSearchByClient = new JLabel("Search by Client");
		lblSearchByClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchByClient.setBounds(337, 12, 168, 43);
		contentPane.add(lblSearchByClient);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int order = Integer.parseInt(text);
				CurrentOrder or=new CurrentOrder(order);
			      or.setVisible(true);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(856, 659, 124, 59);
		contentPane.add(btnUpdate);
		load();
	}
}

