import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class Import extends JFrame {

	private JPanel contentPane;
	private JTextField path;
	private JTable table;
	private String filepath=null;
	java.sql.Connection conn=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Import frame = new Import();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void supplier(){
		int rows=table.getRowCount();
		 PreparedStatement pst = null;
		 for(int row = 1; row<rows; row++)
		 {
			 String str1=(String)table.getValueAt(row, 0);
			 String str2=(String)table.getValueAt(row, 1);
			 String str3=(String)table.getValueAt(row, 2);
			 String str4=(String)table.getValueAt(row, 3);
			 String str5=(String)table.getValueAt(row, 4);
			 String str6=(String)table.getValueAt(row, 5);
			 String str7=(String)table.getValueAt(row, 6);
			
			 
			 
		try {
			String query="insert into supplierinfo (cname,sector,country,address,website,telephone,email) values (?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(query);
			pst.setString(1, str1);
			pst.setString(2, str2);
			pst.setString(3, str3);
			pst.setString(4, str4);
			pst.setString(5, str5);
			pst.setString(6, str6);
			pst.setString(7, str7);
			
			
			pst.execute();				
					
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);	
		}
	
		
		
		 }
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Supplier Information Imported Sucessfully");	
		 
	
	}
public void client(){
	int rows=table.getRowCount();
	 PreparedStatement pst = null;
	 for(int row = 1; row<rows; row++)
	 {
		 String str1=(String)table.getValueAt(row, 0);
		 String str2=(String)table.getValueAt(row, 1);
		 String str3=(String)table.getValueAt(row, 2);
		 String str4=(String)table.getValueAt(row, 3);
		 String str5=(String)table.getValueAt(row, 4);
		 String str6=(String)table.getValueAt(row, 5);
		 String str7=(String)table.getValueAt(row, 6);
	
		 
		 
	try {
		String query="insert into clientInformation (name,mobile,telephone,email,address,consignee,company) values (?,?,?,?,?,?,?)";
		pst=conn.prepareStatement(query);
		pst.setString(1, str1);
		pst.setString(2, str2);
		pst.setString(3, str3);
		pst.setString(4, str4);
		pst.setString(5, str5);
		pst.setString(6, str6);
		pst.setString(7, str7);
	
		
		pst.execute();				
				
	} catch (Exception e) {

		JOptionPane.showMessageDialog(null, e);	
	}

	
	
	 }
		try {
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Client Imported Sucessfully");	
	 
	}
public void orders(){
	int rows=table.getRowCount();
	 PreparedStatement pst = null;
	 for(int row = 1; row<rows; row++)
	 {
		 String str1=(String)table.getValueAt(row, 0);
		 String str2=(String)table.getValueAt(row, 1);
		 String str3=(String)table.getValueAt(row, 2);
		 String str4=(String)table.getValueAt(row, 3);
		 String str5=(String)table.getValueAt(row, 4);
		 String str6=(String)table.getValueAt(row, 5);
		 String str7=(String)table.getValueAt(row, 6);
		 String str8=(String)table.getValueAt(row, 7);
		 String str9=(String)table.getValueAt(row, 8);
		 String str10=(String)table.getValueAt(row, 9);
		 String str11=(String)table.getValueAt(row, 10);
		 String str12=(String)table.getValueAt(row, 11);
		 String str13=(String)table.getValueAt(row, 12);
		 String str14=(String)table.getValueAt(row, 13);
		 
		 
	try {
		String query="insert into allorders (orderno,datestarted,client,description,supplier,invoice_refernce,status,deposit,payment_terms,balance,exp_date_delivery,no_containers,commission,notes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		pst=conn.prepareStatement(query);
		pst.setString(1, str1);
		pst.setString(2, str2);
		pst.setString(3, str3);
		pst.setString(4, str4);
		pst.setString(5, str5);
		pst.setString(6, str6);
		pst.setString(7, str7);
		pst.setString(8, str8);
		pst.setString(9, str9);
		pst.setString(10, str10);
		pst.setString(11, str11);
		pst.setString(12, str12);
		pst.setString(13, str13);
		pst.setString(14, str14);
		
		pst.execute();				
				
	} catch (Exception e) {

		JOptionPane.showMessageDialog(null, e);	
	}

	
	
	 }
		try {
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Orders Imported Sucessfully");	
	 
	
}

public void table(){
	Vector headers = new Vector();
	Vector data = new Vector();

	File file = new File(filepath);
	try {
	Workbook workbook = Workbook.getWorkbook(file);
	Sheet sheet = workbook.getSheet(0);
	headers.clear();
	for (int i = 0; i < sheet.getColumns(); i++) {
	Cell cell1 = sheet.getCell(i, 0);
	headers.add(cell1.getContents());
	}
	data.clear();
	for (int j = 1; j < sheet.getRows(); j++) {
	Vector d = new Vector();
	for (int i = 0; i < sheet.getColumns(); i++) {
	Cell cell = sheet.getCell(i, j);
	d.add(cell.getContents());
	}
	d.add("\n");
	data.add(d);
	}
	}
	catch (Exception e) {
	e.printStackTrace();
	}
	DefaultTableModel model = new DefaultTableModel(data,headers);
	table.setModel(model);
	
	
	model = new DefaultTableModel(data, headers);
	table.setModel(model);
	
}


	public Import() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1600, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Supplier", "Client", "Orders"}));
		comboBox.setBounds(773, 28, 138, 41);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Import Externa File");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblNewLabel.setBounds(10, 11, 461, 82);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select File Type");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(471, 28, 271, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("File Path");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(935, 19, 155, 51);
		contentPane.add(lblNewLabel_2);
		
		path = new JTextField();
		path.setBounds(1100, 19, 309, 51);
		contentPane.add(path);
		path.setColumns(10);
		
		JButton btnNewButton = new JButton("Import");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String select=comboBox.getSelectedItem().toString();
				String type=null;
				filepath=path.getText();
				
                if(select.equals("Supplier")){
                table();
             	supplier();		
				}
				if(select.equals("Client")){
					table();
					client();
			
				}
				if(select.equals("Orders")){
					table();
					orders();
					
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(1419, 11, 155, 64);
		contentPane.add(btnNewButton);
		
		 table = new JTable();
		table.setBounds(10, 80, 1564, 720);
		contentPane.add(table);
		table.setAutoCreateRowSorter(true);
		
		
		
		
		
		
	}
}
