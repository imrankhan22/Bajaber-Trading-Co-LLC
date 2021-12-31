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

import javax.swing.JTextArea;


public class ClientInformation extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField mobile;
	private JTextField tel;
	private JTextField email;
	private JTextField address;
	private JTextArea textArea;
	java.sql.Connection conn=null;
	private JTextField company;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientInformation frame = new ClientInformation();
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
	public ClientInformation() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Client Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(10, 11, 889, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contact Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(31, 84, 301, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblClientName = new JLabel("Contact Person Name");
		lblClientName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClientName.setBounds(31, 144, 203, 36);
		contentPane.add(lblClientName);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMobile.setBounds(31, 191, 203, 36);
		contentPane.add(lblMobile);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTelephone.setBounds(31, 238, 203, 36);
		contentPane.add(lblTelephone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(31, 285, 203, 36);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddress.setBounds(31, 332, 203, 36);
		contentPane.add(lblAddress);
		
		name = new JTextField();
		name.setBounds(271, 147, 287, 36);
		contentPane.add(name);
		name.setColumns(10);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(271, 194, 287, 36);
		contentPane.add(mobile);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(271, 241, 287, 36);
		contentPane.add(tel);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(271, 288, 287, 36);
		contentPane.add(email);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(271, 335, 287, 36);
		contentPane.add(address);
		
		JLabel lblConsigneeInformation = new JLabel("Consignee Info");
		lblConsigneeInformation.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblConsigneeInformation.setBounds(31, 426, 230, 53);
		contentPane.add(lblConsigneeInformation);
		
		JButton btnNewButton = new JButton("Add Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into clientInformation (name,mobile,telephone,email,address,consignee,company) values (?,?,?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, name.getText());
					pst.setString(2, mobile.getText());
					pst.setString(3, tel.getText());
					pst.setString(4, email.getText());
					pst.setString(5, address.getText());
					pst.setString(6, textArea.getText());

					pst.setString(7, company.getText());
					pst.execute();				
					pst.close();
					JOptionPane.showMessageDialog(null, "Client Information info Saved");
					name.setText("");
					 mobile.setText("");
					 tel.setText("");
					 email.setText("");
					 address.setText("");
					 textArea.setText("");

					company.setText("");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(1021, 146, 167, 62);
		contentPane.add(btnNewButton);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				ShowClient show=new ShowClient();
				show.setVisible(true);
			}
		});
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnShowAll.setBounds(1021, 328, 167, 62);
		contentPane.add(btnShowAll);
		
		 textArea = new JTextArea();
		textArea.setBounds(31, 477, 464, 201);
		contentPane.add(textArea);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCompanyName.setBounds(31, 379, 203, 36);
		contentPane.add(lblCompanyName);
		
		company = new JTextField();
		company.setColumns(10);
		company.setBounds(271, 382, 287, 36);
		contentPane.add(company);
	}
	public void load(String cli){

		 try {
				String query="select * from clientinformation where name='"+cli+"'";
				 PreparedStatement pst = conn.prepareStatement(query);
				 ResultSet rs = pst.executeQuery();
				 if(rs.next()){
					 name.setText(rs.getString("name"));
					   mobile.setText(rs.getString("mobile"));
						tel.setText(rs.getString("telephone"));
						 email.setText(rs.getString("email"));
						address.setText(rs.getString("address"));
						textArea.setText(rs.getString("consignee"));
						company.setText(rs.getString("company"));
				 }
			pst.close();
			 rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ClientInformation(final String cli) {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1300, 786);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Client Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(10, 11, 889, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contact Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(31, 84, 301, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblClientName = new JLabel("Contact Person Name");
		lblClientName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClientName.setBounds(31, 144, 203, 36);
		contentPane.add(lblClientName);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMobile.setBounds(31, 191, 203, 36);
		contentPane.add(lblMobile);
		
		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTelephone.setBounds(31, 238, 203, 36);
		contentPane.add(lblTelephone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(31, 285, 203, 36);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddress.setBounds(31, 332, 203, 36);
		contentPane.add(lblAddress);
		
		name = new JTextField();
		name.setBounds(271, 147, 287, 36);
		contentPane.add(name);
		name.setColumns(10);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(271, 194, 287, 36);
		contentPane.add(mobile);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(271, 241, 287, 36);
		contentPane.add(tel);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(271, 288, 287, 36);
		contentPane.add(email);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(271, 335, 287, 36);
		contentPane.add(address);
		
		JLabel lblConsigneeInformation = new JLabel("Consignee Info");
		lblConsigneeInformation.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblConsigneeInformation.setBounds(31, 471, 230, 53);
		contentPane.add(lblConsigneeInformation);
		
		JButton btnNewButton = new JButton("Update Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
						String query="update clientInformation set name='"+name.getText()+"',mobile='"+mobile.getText()+"',telephone='"+tel.getText()+"',email='"+email.getText()+"',address='"+address.getText()+"',consignee='"+textArea.getText()+"',company='"+company.getText()+"' where name=='"+cli+"'";
						
					
							PreparedStatement pst=conn.prepareStatement(query);	
							pst.execute();			
							pst.close();
							JOptionPane.showMessageDialog(null, "client information updated");
						} catch (Exception e) {
							e.printStackTrace();
						}
					
					name.setText("");
					 mobile.setText("");
					 tel.setText("");
					 email.setText("");
					 address.setText("");
					 textArea.setText("");

					company.setText("");
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnNewButton.setBounds(1117, 149, 167, 62);
		contentPane.add(btnNewButton);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				ShowClient show=new ShowClient();
				show.setVisible(true);
			}
		});
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnShowAll.setBounds(1117, 387, 167, 62);
		contentPane.add(btnShowAll);
		
		 textArea = new JTextArea();
		textArea.setBounds(31, 535, 464, 201);
		contentPane.add(textArea);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCompanyName.setBounds(31, 379, 203, 36);
		contentPane.add(lblCompanyName);
		
		company = new JTextField();
		company.setColumns(10);
		company.setBounds(271, 382, 287, 36);
		contentPane.add(company);
		load(cli);
	}
}
