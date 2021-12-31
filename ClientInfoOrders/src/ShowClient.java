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
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ShowClient extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	java.sql.Connection conn=null;
	private JButton btnNewButton_1;
	private JLabel lblSearchByName;
	private JTextField textField;
	private JButton button;
	private JButton button_1;
	private JButton btnRefresh;
	private JTextField textField_1;
private String text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowClient frame = new ShowClient();
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
		  PreparedStatement pst=null;

	    model = new DefaultTableModel();
	    table.setModel(model);
	    model.addColumn("Name");
	    model.addColumn("Mobile");
	    model.addColumn("Telephone");
	    model.addColumn("Email");
	    model.addColumn("Address");
	    model.addColumn("Consignee Information");
	    model.addColumn("Company");
	   
	    model.addRow(new Object[]{"Name", "Mobile", "Telephone", "Email", "Address","Consignee Information","Company"});
		

			try {
				String query="select * from clientinformation";
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
		    rs.close();pst.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
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
	public ShowClient() {
		conn=Connector.dbConnector();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ClientInformation frame = new ClientInformation();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnNewButton.setBounds(79, 647, 200, 75);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				int i;
				i=table.getSelectedRow();
				 text= (String) table.getValueAt(i, 0);
			}
		});
		table.setBounds(10, 106, 1324, 530);
		contentPane.add(table);
		
		lblNewLabel = new JLabel("All Clients Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(10, 0, 614, 66);
		contentPane.add(lblNewLabel);
		
		btnNewButton_1 = new JButton("Export");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					exportTable(table, new File("ClientInformation.xls"));

					JOptionPane.showMessageDialog(null, "Client Information Exported to Excel file");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		btnNewButton_1.setBounds(323, 647, 200, 75);
		contentPane.add(btnNewButton_1);
		
		lblSearchByName = new JLabel("Search by name");
		lblSearchByName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchByName.setBounds(634, 25, 161, 43);
		contentPane.add(lblSearchByName);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				
				 DefaultTableModel model ;
				  ResultSet rs = null;
				  PreparedStatement pst=null;

			    model = new DefaultTableModel();
			    table.setModel(model);
			    model.addColumn("Name");
			    model.addColumn("Mobile");
			    model.addColumn("Telephone");
			    model.addColumn("Email");
			    model.addColumn("Address");
			    model.addColumn("Consignee Information");
			    model.addColumn("Company");
			   
			    model.addRow(new Object[]{"Name", "Mobile", "Telephone", "Email", "Address","Consignee Information","Company"});
				

					try {
						String query="select * from clientinformation where name like '%"+textField.getText()+"%'";
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
				    rs.close();pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		textField.setColumns(10);
		textField.setBounds(812, 33, 151, 32);
		contentPane.add(textField);
		
		button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientInformation p =new ClientInformation(text.toString());
				p.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBounds(731, 659, 124, 59);
		contentPane.add(button);
		
		button_1 = new JButton("Delete");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Do you really want to Delete it !!","Delete Client", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					
				try {
						String query="Delete from clientinformation where name='"+text+"'";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();
						pst.close();
						JOptionPane.showMessageDialog(null, "Client Information deleted");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					 }
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_1.setBounds(562, 659, 124, 59);
		contentPane.add(button_1);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				load();
				
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRefresh.setBounds(903, 659, 124, 59);
		contentPane.add(btnRefresh);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				
				DefaultTableModel model ;
				  ResultSet rs = null;
				  PreparedStatement pst=null;

			    model = new DefaultTableModel();
			    table.setModel(model);
			    model.addColumn("Name");
			    model.addColumn("Mobile");
			    model.addColumn("Telephone");
			    model.addColumn("Email");
			    model.addColumn("Address");
			    model.addColumn("Consignee Information");
			    model.addColumn("Company");
			   
			    model.addRow(new Object[]{"Name", "Mobile", "Telephone", "Email", "Address","Consignee Information","Company"});
				

					try {
						String query="select * from clientinformation where company like '%"+textField_1.getText()+"%'";
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
				    rs.close();pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(1173, 33, 151, 32);
		contentPane.add(textField_1);
		
		JLabel lblSearchByCompany = new JLabel("Search by Company");
		lblSearchByCompany.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearchByCompany.setBounds(973, 25, 190, 43);
		contentPane.add(lblSearchByCompany);
		load();
	}
}
