package projekatJSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;

public class BazaConn {
	
	static Connection connect = null;
	
	public static Connection Connection() {
		try {
			Class.forName("org.h2.Driver");
			Connection connect = DriverManager.getConnection("jdbc:h2:~/BazaStudenti");
			return connect;
		} catch (Exception e){
			e.printStackTrace();
		}
		return connect;
	}
	
	
}