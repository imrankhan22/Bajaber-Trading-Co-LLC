
import java.sql.*;

import javax.swing.*;
public class Connector {
	Connection con=null;
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:Employe.sqlite");
			return con;
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}