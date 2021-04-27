package projekatJSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import java.sql.*;
import org.apache.derby.iapi.sql.PreparedStatement;

public class DBKonekcija {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:studentiISzJsona;create=true";

	Connection conn;
	

	public  DBKonekcija() {

		try {
			this.conn = DriverManager.getConnection(JDBC_URL);
			if (this.conn != null) {
				System.out.println("Konekcija na bazu uspjesna!");
				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	

	public void KreirajTabelu() {
		
			try {
				
				conn.createStatement().executeQuery("Create TABLE Studenti(ID varchar, Indeks varchar, Ime varchar, Prezime varchar, Bodovi varchar, primary key(ID))");
				Statement stat = conn.createStatement();
				ResultSet res = stat.executeQuery("SELECT ID, Indeks, Ime, Prezime, Bodovi FROM Studenti");
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public ResultSet Print() throws SQLException {
		
		Statement stat = conn.createStatement();
		ResultSet res = stat.executeQuery("SELECT ID, Indeks, Ime, Prezime, Bodovi FROM Studenti");
		return res;

	}

	public void UbaciUTabelu(String id, String indeks, String ime, String prezime, String bodovi) throws SQLException {

		java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO Studenti VALUES(?, ?, ?, ?, ?)");
		ps.setString(1, id);
		ps.setString(2, indeks);
		ps.setString(3, ime);
		ps.setString(4, prezime);
		ps.setString(5, bodovi);
		ps.execute();

	}

}
