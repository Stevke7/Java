package projekatJSON;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;
import java.sql.*;
import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class JSONProjekat {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:studentiIzJsona;create=true";

	private JFrame frame;
	private JTextArea txtrIzlazta;

	private ArrayList<StudentiJson> studentijson;
	private JTable table;

	H2Konekcija h2konekcija = new H2Konekcija();
	ResultSet rezultat;

	/**
	 * Launch the application.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JSONProjekat window = new JSONProjekat();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;

	/**
	 * Create the application.
	 */
	public JSONProjekat() {
		connection = BazaConn.Connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnCitajJson = new JButton("Citaj JSON");
		btnCitajJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				citajJSON();

			}
		});
		btnCitajJson.setBounds(304, 12, 107, 23);
		frame.getContentPane().add(btnCitajJson);

		JButton btnPrikazKonzola = new JButton("Ispis Konzola");
		btnPrikazKonzola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrikazKonzola();
			}
		});
		btnPrikazKonzola.setBounds(304, 46, 107, 23);
		frame.getContentPane().add(btnPrikazKonzola);

		JButton btnPrikaziBazu = new JButton("Prikazi DB");
		btnPrikaziBazu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrikazBaze();
			}
		});
		btnPrikaziBazu.setBounds(304, 83, 107, 23);
		frame.getContentPane().add(btnPrikaziBazu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 287, 239);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

	protected void PrikazBaze() {

		try {

			String sql = "SELECT * FROM studenti";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void PrikazKonzola() {
		try {
			rezultat = h2konekcija.PrikaziStudente();
			while (rezultat.next()) {
				System.out.println(
						rezultat.getString("ID") + " " + rezultat.getString("Indeks") + " " + rezultat.getString("Ime")
								+ " " + rezultat.getString("Prezime") + " " + rezultat.getString("Bodovi"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void citajJSON() {
		// incijalizacija citaca
		BufferedReader reader = null;

		try {
			// KOVERZIJA JSON-a U JAVA OBJEKAT
			reader = new BufferedReader(new FileReader("Studenti.json"));// Inicijalizacija citaca Json fajla
			Gson gson = new Gson(); // Inicijalizacija Gson-a
			StudentiJson[] studentijson = gson.fromJson(reader, StudentiJson[].class); // Konverzija JSON-a u Java
																						// objekat

			System.out.println("Broj ucitanih studenata je: " + studentijson.length);

			H2Konekcija h2konekcija = new H2Konekcija();

			// KONVERZIJA OBJEKTA U STRING
			if (studentijson != null) {

				for (StudentiJson s : studentijson) {
					String st = ("ID: " + s.getID() + " Indeks: " + s.getIndeks() + " Ime:" + s.getИме() + " Prezime:"
							+ s.getPrezime() + " Bodovi:" + s.getBodovi());
					String idSt = s.getID();
					String indeksSt = s.getIndeks();
					String imeSt = s.getИме();
					String prezimeSt = s.getPrezime();
					String bodoviSt = s.getBodovi();

					h2konekcija.dodajStudenta(idSt, indeksSt, imeSt, prezimeSt, bodoviSt);
					
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

}
