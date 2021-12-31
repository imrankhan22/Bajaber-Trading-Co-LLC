import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;




public class CurrentOrder extends JFrame {

	private JPanel contentPane;
	private JTextField orderno;
	private JTextField datestarted;
	private JTextField invoice;
	private JTextField deposit;
	private JTextField balance;
	private JTextField expectedd;
	private JTextField ncontainer;
	private JTextField commission;
	JTextArea description;
	JTextArea notes;
	String sup="";
	String pay="";
	String bal="";
	String cli,sta;
	JComboBox client;
	JComboBox supplier;
	JComboBox status;
 JComboBox paymentterms;
	

	java.sql.Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllOrders frame = new AllOrders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
void client(){
	try {
		String query="select name from clientinformation";
		PreparedStatement pst=conn.prepareStatement(query);
	    ResultSet rs = pst.executeQuery();
	    while(rs.next()){
	    	  client.addItem(rs.getString("name"));
	    }
	    pst.close();rs.close();
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	
}
public void set(int order){
	PreparedStatement pst11=null;
	 ResultSet rs11=null;

	try {

		String query="select * from allorders where orderno='"+order+"'";
		pst11 = conn.prepareStatement(query);
	     rs11 = pst11.executeQuery();
	     if(rs11.next()){
	    	 orderno.setText(""+order);
	    datestarted.setText(rs11.getString("datestarted"));
	    client.setSelectedItem(rs11.getString("client"));
	    description.setText(rs11.getString("description"));
	    supplier.setSelectedItem(rs11.getString("supplier"));
	    invoice.setText(rs11.getString("invoice_refernce"));
	    status.setSelectedItem(rs11.getString("status"));
	    deposit.setText(rs11.getString("deposit"));
	    paymentterms.setSelectedItem(rs11.getString("payment_terms"));
	    balance.setText(rs11.getString("balance"));
	    expectedd.setText(rs11.getString("exp_date_delivery"));
	    ncontainer.setText(rs11.getString("no_containers"));
	    commission.setText(rs11.getString("commission"));
	    notes.setText(rs11.getString("notes"));
		sup=paymentterms.getSelectedItem().toString();
		pst11.close();
		rs11.close();
	     }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
void supplier(){
	try {
		String query="select cname from supplierinfo";
		PreparedStatement pst=conn.prepareStatement(query);
	    ResultSet rs = pst.executeQuery();
	    while(rs.next()){
	    	  supplier.addItem(rs.getString("cname"));
	    }
	    pst.close();rs.close();
	} catch (Exception e1) {
		e1.printStackTrace();
	}
}
	/**
	 * Create the frame.
	 */

public CurrentOrder(){
	conn=Connector.dbConnector();
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(0, 0, 1300, 786);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
}
	public CurrentOrder(final int order) {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1360, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Update Order Status");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 55));
		lblNewLabel.setBounds(10, 11, 1241, 112);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Order No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 149, 95, 29);
		contentPane.add(lblNewLabel_1);
		
		orderno = new JTextField(order);
		orderno.setBounds(269, 153, 193, 27);
		contentPane.add(orderno);
		orderno.setColumns(10);
		
		JLabel lblDateStarted = new JLabel("Date Started");
		lblDateStarted.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDateStarted.setBounds(10, 200, 122, 29);
		contentPane.add(lblDateStarted);
		
		datestarted = new JTextField();
		datestarted.setColumns(10);
		datestarted.setBounds(269, 204, 193, 27);
		contentPane.add(datestarted);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClient.setBounds(10, 259, 122, 29);
		contentPane.add(lblClient);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescription.setBounds(10, 331, 122, 29);
		contentPane.add(lblDescription);
		
		JLabel lblSupplier = new JLabel("Supplier");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSupplier.setBounds(10, 523, 122, 29);
		contentPane.add(lblSupplier);
		
		JLabel lblInvoiceRefrence = new JLabel("Invoice Refrence");
		lblInvoiceRefrence.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblInvoiceRefrence.setBounds(10, 582, 165, 29);
		contentPane.add(lblInvoiceRefrence);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStatus.setBounds(10, 642, 122, 29);
		contentPane.add(lblStatus);
		
		invoice = new JTextField();
		invoice.setColumns(10);
		invoice.setBounds(269, 586, 193, 27);
		contentPane.add(invoice);
		
		JLabel lblDepositeMade = new JLabel("Deposite(USD)");
		lblDepositeMade.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepositeMade.setBounds(740, 149, 178, 29);
		contentPane.add(lblDepositeMade);
		
		JLabel lblBal = new JLabel("Payment Terms");
		lblBal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBal.setBounds(740, 204, 184, 29);
		contentPane.add(lblBal);
		
		JLabel lblBalance = new JLabel("Balance(USD)");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBalance.setBounds(740, 259, 178, 29);
		contentPane.add(lblBalance);
		
		JLabel lblExpectedDelivery = new JLabel("Expected Delivery");
		lblExpectedDelivery.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblExpectedDelivery.setBounds(740, 318, 193, 29);
		contentPane.add(lblExpectedDelivery);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNotes.setBounds(740, 499, 165, 29);
		contentPane.add(lblNotes);
		
		deposit = new JTextField();
		deposit.setColumns(10);
		deposit.setBounds(1037, 153, 214, 27);
		contentPane.add(deposit);
		
		balance = new JTextField();
		balance.setEditable(false);
		balance.setColumns(10);
		balance.setBounds(1035, 263, 216, 27);
		contentPane.add(balance);
		
		expectedd = new JTextField();
		expectedd.setColumns(10);
		expectedd.setBounds(1037, 322, 214, 27);
		contentPane.add(expectedd);
		
		JLabel lblNoOfContainer = new JLabel("No of Containers");
		lblNoOfContainer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNoOfContainer.setBounds(740, 364, 193, 29);
		contentPane.add(lblNoOfContainer);
		
		ncontainer = new JTextField();
		ncontainer.setColumns(10);
		ncontainer.setBounds(1037, 372, 214, 27);
		contentPane.add(ncontainer);
		
		commission = new JTextField();
		commission.setColumns(10);
		commission.setBounds(1037, 437, 214, 27);
		contentPane.add(commission);
		
		JLabel lblCommissionusd = new JLabel("Commission(USD)");
		lblCommissionusd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCommissionusd.setBounds(740, 433, 193, 29);
		contentPane.add(lblCommissionusd);
		
		JButton btnSave_1 = new JButton("Change");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				cli=client.getSelectedItem().toString();
				sup=supplier.getSelectedItem().toString();
				sta=status.getSelectedItem().toString();
				
				if(pay.equals("Against BL")){
					bal=""+0;
				}
				else{
					bal=balance.getText();
				}
				
				String query="update allorders set orderno='"+orderno.getText()+"',datestarted='"+datestarted.getText()+"',client='"+cli+"',description='"+description.getText()+"',supplier='"+sup+"',invoice_refernce='"+invoice.getText()+"',status='"+sta+"',deposit='"+deposit.getText()+"',payment_terms='"+pay+"',balance='"+bal+"',exp_date_delivery='"+expectedd.getText()+"',no_containers='"+ncontainer.getText()+"',commission='"+commission.getText()+"',notes='"+notes.getText()+"' where orderno='"+orderno.getText()+"' where orderno='"+order+"'";	
			try {
				PreparedStatement pst=conn.prepareStatement(query);	
				pst.execute();			
				pst.close();
				JOptionPane.showMessageDialog(null, "Order has been updated!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
		btnSave_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnSave_1.setBounds(872, 668, 178, 50);
		contentPane.add(btnSave_1);
		
	 supplier = new JComboBox();
		supplier.setBounds(269, 526, 193, 29);
		contentPane.add(supplier);
		 status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"current", "shipped", "complete"}));
		status.setMaximumRowCount(3);
		status.setBounds(269, 644, 193, 27);
		contentPane.add(status);
		
		 paymentterms = new JComboBox();
		paymentterms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String select=(String) paymentterms.getSelectedItem();
                   if(select.equals("Against BL")){
                	   pay=select;
                		balance.setEditable(false);
				}
				if(select.equals("Before Loading")){
					pay=select;
					balance.setEditable(true);
			
				}
			}
		});
		paymentterms.setModel(new DefaultComboBoxModel(new String[] {"Against BL", "Before Loading"}));
		paymentterms.setBounds(1037, 207, 214, 29);
		contentPane.add(paymentterms);
		
		 description = new JTextArea();
		description.setBounds(269, 336, 198, 158);
		contentPane.add(description);
	 notes = new JTextArea();
		notes.setBounds(1037, 488, 214, 146);
		contentPane.add(notes);
		
		client = new JComboBox();
		client.setBounds(269, 262, 193, 29);
		contentPane.add(client);
		client();
		supplier();
		set(order);
	}
}
