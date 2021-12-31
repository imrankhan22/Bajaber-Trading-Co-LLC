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
import java.sql.SQLException;


public class Pinformation extends JFrame {

	private JPanel contentPane;
	private JTextField website;
	private JTextField telephone;
	private JTextField email;
	private JTextField textField;
	private JTextField sector;
	private JTextField country;
	private JTextField address;
	java.sql.Connection conn=null;

	

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
	public void load(String comp){

		 try {
				String query="select * from supplierinfo where cname='"+comp+"'";
				 PreparedStatement pst = conn.prepareStatement(query);
				 ResultSet rs = pst.executeQuery();
				 if(rs.next()){
					 textField.setText(rs.getString("cname"));
					   sector.setText(rs.getString("sector"));
						country.setText(rs.getString("country"));
						 address.setText(rs.getString("address"));
						website.setText(rs.getString("website"));
						telephone.setText(rs.getString("telephone"));
						email.setText(rs.getString("email"));
				 }
			pst.close();
			 rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Pinformation() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1150, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Supplier Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(10, 11, 885, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contact Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(259, 392, 194, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Website");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(259, 435, 130, 29);
		contentPane.add(lblNewLabel_2);
		
		website = new JTextField();
		website.setBounds(443, 439, 275, 29);
		contentPane.add(website);
		website.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("TelePhone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(259, 492, 118, 29);
		contentPane.add(lblNewLabel_3);
		
		telephone = new JTextField();
		telephone.setBounds(443, 496, 275, 29);
		contentPane.add(telephone);
		telephone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(259, 559, 118, 29);
		contentPane.add(lblNewLabel_4);
		
		email = new JTextField();
		email.setBounds(443, 563, 275, 29);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Supplier Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into supplierinfo (cname,sector,country,address,website,telephone,email) values (?,?,?,?,?,?,?)";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, sector.getText());
					pst.setString(3, country.getText());
					pst.setString(4, address.getText());
					pst.setString(5, website.getText());
					pst.setString(6, telephone.getText());
					pst.setString(7, email.getText());
					pst.execute();				
					pst.close();
					
					JOptionPane.showMessageDialog(null, "Supplier info Saved");
					textField.setText("");
					 sector.setText("");
				   country.setText("");
					 address.setText("");
					 website.setText("");
					 telephone.setText("");
					 email.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(259, 612, 262, 55);
		contentPane.add(btnNewButton);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupplierName.setBounds(259, 159, 143, 29);
		contentPane.add(lblSupplierName);
		
		textField = new JTextField();
		textField.setBounds(443, 162, 275, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("ADD Contact");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contact con=new contact();
				con.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(617, 614, 183, 55);
		contentPane.add(btnNewButton_1);
		
		JLabel lblSectory = new JLabel("Sectory");
		lblSectory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSectory.setBounds(258, 212, 130, 29);
		contentPane.add(lblSectory);
		
		JLabel lblCont = new JLabel("country");
		lblCont.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCont.setBounds(259, 252, 130, 29);
		contentPane.add(lblCont);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(259, 315, 130, 29);
		contentPane.add(lblAddress);
		
		sector = new JTextField();
		sector.setColumns(10);
		sector.setBounds(443, 212, 275, 29);
		contentPane.add(sector);
		
		country = new JTextField();
		country.setColumns(10);
		country.setBounds(443, 256, 275, 29);
		contentPane.add(country);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(443, 319, 275, 29);
		contentPane.add(address);
	}
	public Pinformation(final String comp) {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1600, 850);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Supplier Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(10, 11, 885, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contact Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(540, 393, 194, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Website");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(540, 450, 130, 29);
		contentPane.add(lblNewLabel_2);
		
		website = new JTextField();
		website.setBounds(879, 454, 275, 29);
		contentPane.add(website);
		website.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("TelePhone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(540, 520, 118, 29);
		contentPane.add(lblNewLabel_3);
		
		telephone = new JTextField();
		telephone.setBounds(879, 524, 275, 29);
		contentPane.add(telephone);
		telephone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(540, 593, 118, 29);
		contentPane.add(lblNewLabel_4);
		
		email = new JTextField();
		email.setBounds(879, 597, 275, 29);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnNewButton = new JButton("Update Supplier Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="update supplierinfo set cname='"+textField.getText()+"',sector='"+sector.getText()+"',country='"+country.getText()+"',address='"+address.getText()+"',website='"+website.getText()+"',telephone='"+telephone.getText()+"',email='"+email.getText()+"' where cname=='"+comp+"'";
					
				
						PreparedStatement pst=conn.prepareStatement(query);	
						pst.execute();			
						pst.close();
						JOptionPane.showMessageDialog(null, "supplier info updated");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(486, 714, 262, 55);
		contentPane.add(btnNewButton);
		
		JLabel lblSupplierName = new JLabel("Supplier Name");
		lblSupplierName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupplierName.setBounds(545, 94, 143, 29);
		contentPane.add(lblSupplierName);
		
		textField = new JTextField();
		textField.setBounds(879, 93, 275, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
	
		
		JLabel lblSectory = new JLabel("Sectory");
		lblSectory.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSectory.setBounds(540, 182, 130, 29);
		contentPane.add(lblSectory);
		
		JLabel lblCont = new JLabel("country");
		lblCont.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCont.setBounds(540, 261, 130, 29);
		contentPane.add(lblCont);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(540, 345, 130, 29);
		contentPane.add(lblAddress);
		
		sector = new JTextField();
		sector.setColumns(10);
		sector.setBounds(879, 186, 275, 29);
		contentPane.add(sector);
		
		country = new JTextField();
		country.setColumns(10);
		country.setBounds(879, 265, 275, 29);
		contentPane.add(country);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(879, 349, 275, 29);
		contentPane.add(address);
		load(comp);
	}
}
