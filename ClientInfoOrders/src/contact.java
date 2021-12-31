import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;


public class contact extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField ext;
	private JTextField mobile;
	private JTextField email1;
	java.sql.Connection conn=null;
	JComboBox comboBox;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pinformation frame = new Pinformation();
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
	void client(){
		try {
			String query="select cname from supplierinfo";
			PreparedStatement pst=conn.prepareStatement(query);
		    ResultSet rs = pst.executeQuery();
		    while(rs.next()){
		    	  comboBox.addItem(rs.getString("cname"));
		    }
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public contact() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 795, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Supplier Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(10, 11, 885, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblContactPerson = new JLabel("Contact Persons");
		lblContactPerson.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContactPerson.setBounds(144, 92, 194, 22);
		contentPane.add(lblContactPerson);
		
		JLabel label1 = new JLabel("Name");
		label1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label1.setBounds(140, 190, 130, 29);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("EXT.");
		label2.setFont(new Font("Tahoma", Font.BOLD, 18));
		label2.setBounds(140, 230, 130, 29);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("Mobile");
		label3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label3.setBounds(140, 270, 130, 29);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("Email");
		label4.setFont(new Font("Tahoma", Font.BOLD, 18));
		label4.setBounds(140, 310, 130, 29);
		contentPane.add(label4);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(432, 193, 275, 29);
		contentPane.add(name);
		
		ext = new JTextField();
		ext.setColumns(10);
		ext.setBounds(432, 233, 275, 29);
		contentPane.add(ext);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(432, 273, 275, 29);
		contentPane.add(mobile);
		
		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(432, 313, 275, 29);
		contentPane.add(email1);
		
		JButton btnNewButton_1 = new JButton("ADD Contact");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into contactperson (name,ext,mobile,email,comp) values (?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
				
					pst.setString(1, name.getText());
					pst.setString(2, ext.getText());
					pst.setString(3, mobile.getText());
					pst.setString(4, email1.getText());
					pst.setString(5, comboBox.getSelectedItem().toString());
					
					pst.execute();				
					pst.close();
					name.setText("");
					ext.setText("");
					mobile.setText("");
					email1.setText("");
				
					JOptionPane.showMessageDialog(null, "Contact Saved");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(319, 402, 183, 55);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCompanyName.setBounds(140, 140, 184, 29);
		contentPane.add(lblCompanyName);
		
		comboBox = new JComboBox();
		comboBox.setBounds(432, 140, 275, 35);
		contentPane.add(comboBox);
		client();
	}
}
