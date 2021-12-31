import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import jxl.Workbook;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SupplierInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	java.sql.Connection conn=null;
	private JTextField sector;
	private JTextField country;
	private String text;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierInfo frame = new SupplierInfo();
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
	public SupplierInfo() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1365, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pinformation info=new Pinformation();
				info.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(32, 657, 132, 59);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				int i;
				i=table.getSelectedRow();
				 text= (String) table.getValueAt(i, 0);
			}
		});
		table.setBounds(10, 88, 1329, 560);
		contentPane.add(table);
		
		JLabel lblSupplierInfor = new JLabel("Supplier Info");
		lblSupplierInfor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSupplierInfor.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblSupplierInfor.setBounds(10, 11, 408, 66);
		contentPane.add(lblSupplierInfor);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String select=comboBox.getSelectedItem().toString();
				
				  DefaultTableModel model ;
				  ResultSet rs = null;
				  PreparedStatement pst=null;

                  model = new DefaultTableModel();
                  table.setModel(model);
                  model.addColumn("Company Name");
                  model.addColumn("Sector");
                  model.addColumn("Country");
                  model.addColumn("Address");
                  model.addColumn("Website");
                  model.addColumn("Telephone");
                  model.addColumn("Email");
                  model.addRow(new Object[]{"Company Name", "Sector", "Country", "Address", "Website","Telephone","Email"});
				
				
				
				if(select.equals("country")){
					
					
					try {
						String query="select * from supplierinfo order by country";
					 pst=conn.prepareStatement(query);
					    rs=pst.executeQuery();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				if(select.equals("sector")){
					try {
						String query="select * from supplierinfo order by sector";
						 pst=conn.prepareStatement(query);
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
				    rs.close();pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
				
			}
			
			
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"country", "sector"}));
		comboBox.setBounds(1125, 40, 169, 41);
		contentPane.add(comboBox);
		
			
			JButton btnNewButton_1 = new JButton("Contacts");
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						
						 
						                 DefaultTableModel model ;
						    

						                    model = new DefaultTableModel();
						                    table.setModel(model);
						                    model.addColumn("Name");
						                    model.addColumn("Ext");
						                    model.addColumn("Mobile");
						                    model.addColumn("Email");
						                    model.addColumn("Company");
						                    model.addRow(new Object[]{"Name", "EXT", "Mobile", "Email", "Company"});

						    
						
						String query="select * from contactperson";
						PreparedStatement pst=conn.prepareStatement(query);
						ResultSet rs1=pst.executeQuery();
						//table.setModel(DbUtils.resultSetToTableModel(rs));
						 ResultSetMetaData meta = rs1.getMetaData();
						int numberOfColumns = meta.getColumnCount();
					    while (rs1.next())
					    {
					        Object [] rowData = new Object[numberOfColumns];
					        for (int i = 0; i < rowData.length; ++i)
					        {
					            rowData[i] = rs1.getObject(i+1);
					        }
					        model.addRow(rowData);
					    }
					    rs1.close();
					    pst.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					  
					
					
				}
			});
			btnNewButton_1.setBounds(174, 657, 124, 59);
			contentPane.add(btnNewButton_1);
			
		
			
			
			JButton btnNewButton_3 = new JButton("Directory");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					  DefaultTableModel model ;
					  ResultSet rs = null;
					  PreparedStatement pst=null;
	                  model = new DefaultTableModel();
	                  table.setModel(model);
	                  model.addColumn("Company Name");
	                  model.addColumn("Sector");
	                  model.addColumn("Country");
	                  model.addColumn("Address");
	                  model.addColumn("Website");
	                  model.addColumn("Telephone");
	                  model.addColumn("Email");
	                  model.addRow(new Object[]{"Company Name", "Sector", "Country", "Address", "Website","Telephone","Email"});
					
					
					try {
						
						String query="select * from supplierinfo";
						 pst=conn.prepareStatement(query);
						 rs=pst.executeQuery();
						//table.setModel(DbUtils.resultSetToTableModel(rs));
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
			});
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnNewButton_3.setBounds(319, 657, 193, 59);
			contentPane.add(btnNewButton_3);
			
			JButton btnNewButton_2 = new JButton("Export");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						exportTable(table, new File("SupplierInformation.xls"));

						JOptionPane.showMessageDialog(null, "Supplier Information Exported into Excel file");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnNewButton_2.setBounds(535, 657, 124, 59);
			contentPane.add(btnNewButton_2);
			
			JLabel lblNewLabel = new JLabel("Search by Sector");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setBounds(428, 34, 168, 43);
			contentPane.add(lblNewLabel);
			
			sector = new JTextField();
			sector.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent arg0) {
					
					DefaultTableModel model ;
					  ResultSet rs = null;
					  PreparedStatement pst=null;
	                  model = new DefaultTableModel();
	                  table.setModel(model);
	                  model.addColumn("Company Name");
	                  model.addColumn("Sector");
	                  model.addColumn("Country");
	                  model.addColumn("Address");
	                  model.addColumn("Website");
	                  model.addColumn("Telephone");
	                  model.addColumn("Email");
	                  model.addRow(new Object[]{"Company Name", "Sector", "Country", "Address", "Website","Telephone","Email"});
					
					
					try {
						
						String query="select * from supplierinfo where sector like '%"+sector.getText()+"%'";
						 pst=conn.prepareStatement(query);
						 rs=pst.executeQuery();
						//table.setModel(DbUtils.resultSetToTableModel(rs));
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
			});
			sector.setBounds(605, 44, 151, 32);
			contentPane.add(sector);
			sector.setColumns(10);
			
			JLabel lblSearchByCountry = new JLabel("Search by Country");
			lblSearchByCountry.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblSearchByCountry.setBounds(766, 34, 168, 43);
			contentPane.add(lblSearchByCountry);
			
			country = new JTextField();
			country.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent arg0) {
					DefaultTableModel model ;
					  ResultSet rs = null;
					  PreparedStatement pst=null;
	                  model = new DefaultTableModel();
	                  table.setModel(model);
	                  model.addColumn("Company Name");
	                  model.addColumn("Sector");
	                  model.addColumn("Country");
	                  model.addColumn("Address");
	                  model.addColumn("Website");
	                  model.addColumn("Telephone");
	                  model.addColumn("Email");
	                  model.addRow(new Object[]{"Company Name", "Sector", "Country", "Address", "Website","Telephone","Email"});
					
					
					try {
						
						String query="select * from supplierinfo where country like '%"+country.getText()+"%'";
						 pst=conn.prepareStatement(query);
						 rs=pst.executeQuery();
						//table.setModel(DbUtils.resultSetToTableModel(rs));
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
			});
			country.setColumns(10);
			country.setBounds(944, 44, 151, 32);
			contentPane.add(country);
			
			JButton btnNewButton_4 = new JButton("Delete");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int reply = JOptionPane.showConfirmDialog(null, "Do you really want to Delete it !!","Delete Supplier Info", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						 
					 try {
							String query="Delete from supplierinfo where cname='"+text+"'";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.execute();
							pst.close();
							JOptionPane.showMessageDialog(null, "Supplier Information deleted");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 try {
							String query="Delete from contactperson where comp='"+text+"'";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.execute();
							pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 

						JOptionPane.showMessageDialog(null, "Supplier deleted !!");
					 
					 }
					
					
				}
			});
			btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnNewButton_4.setBounds(787, 657, 124, 59);
			contentPane.add(btnNewButton_4);
			
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Pinformation p =new Pinformation(text);
					p.setVisible(true);
				}
			});
			btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnUpdate.setBounds(971, 657, 124, 59);
			contentPane.add(btnUpdate);
			
			JButton btnDelContact = new JButton("Del Contact");
			btnDelContact.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					 try {
							String query="Delete from contactperson where comp='"+text+"'";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.execute();
							pst.close();

							JOptionPane.showMessageDialog(null, "Contact Deleted !!");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			btnDelContact.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnDelContact.setBounds(1161, 659, 124, 59);
			contentPane.add(btnDelContact);
		
	}
}
