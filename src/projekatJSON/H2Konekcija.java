package projekatJSON;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Konekcija {

	private static Connection conn;
	private static boolean hasData = false;

	private void Konekcija() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:~/BazaStudenti");
			//conn = DriverManager.getConnection("jdbc:h2:file:C:/Users/steva/eclipse-workspace/IspitDerbyDB/bazaPodataka/BazaStudenti.db");
			Inicijalizacija();

		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}

	}

	public ResultSet PrikaziStudente() throws SQLException {
		if (conn == null) {
			Konekcija();
		}

		Statement stat;

		stat = conn.createStatement();
		ResultSet res = stat.executeQuery("SELECT ID, Indeks, Ime, Prezime, Bodovi FROM studenti");
		return res;

	}

	private void Inicijalizacija() throws SQLException {

		if (!hasData) {
			hasData = true;
			Statement stat2 = conn.createStatement();
			ResultSet res = stat2.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
			if( !res.next() ) {
			Statement stat = conn.createStatement();
			stat.execute(
					"CREATE TABLE studenti(ID varchar,Indeks varchar,Ime varchar, Prezime varchar, Bodovi varchar,primary key(ID));");
			System.out.println("Tabela studenti kreirana u bazi podataka...");
			}
		}

	}
	
public void dodajStudenta(String ID, String Indeks, String Ime, String Prezime, String Bodovi) throws ClassNotFoundException, SQLException {
		
		if ( conn == null ) {
			Konekcija();
		}
		
		PreparedStatement prep = conn.prepareStatement("INSERT INTO studenti VALUES(?, ?, ?, ?, ?);"); 
		prep.setString(1, ID);
		prep.setString(2, Indeks);
		prep.setString(3, Ime);
		prep.setString(4, Prezime);
		prep.setString(5, Bodovi);
		prep.execute();
		
	}

}
