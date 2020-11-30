package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para conex�o com o banco de dados
 */
public class ConnectionSingleton {
	private static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static final String HOST = "192.168.99.100";
	private static final String DB = "lojaOnline";
	private static final String USER = "SA";
	private static final String PASS = "P4ssw0rd";

	private static ConnectionSingleton instance = null;
	private Connection con;

	/**
	 * Fun��o que retorna a instancia da conex�o
	 */
	public static ConnectionSingleton getInstance() {
		if (instance == null) {
			instance = new ConnectionSingleton();
		}
		return instance;
	}

	/**
	 * Construtor conectando com a biblioteca
	 */
	private ConnectionSingleton() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fun��o que retorna a conex�o
	 */
	public Connection getConnection() {
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection(String.format(
						"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s;", HOST, DB, USER, PASS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
