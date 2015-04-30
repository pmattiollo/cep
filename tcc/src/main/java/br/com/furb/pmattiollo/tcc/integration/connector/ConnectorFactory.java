package br.com.furb.pmattiollo.tcc.integration.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorFactory {

	private String url;
	private String user = "root";
	private String passwd = "admin";
	private String host = "localhost";
	private String port = "3306";
	private String base = "sonar";
	
	private ConnectorFactory() {}
	
	private static ConnectorFactory INSTANCE;
	
	public static ConnectorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectorFactory();
		}
		return INSTANCE;
	}
	
	public synchronized Connection getConnection() throws SQLException {		
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		loadParameters();
		
		return DriverManager.getConnection(url, user, passwd);
	}
	
	public synchronized Connection getConnection(String url, String user, String passwd) throws SQLException {		
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		loadParameters();
		
		return DriverManager.getConnection(url, user, passwd);
	}
	
	private void loadParameters() {
		url =  getUrl(host, port, base); 
	}
	
	private String getUrl(String host, String port, String base) {
		StringBuilder sb = new StringBuilder();
		sb.append("jdbc:mysql://");
		sb.append(host).append(":");
		sb.append(port).append("/"); 
		sb.append(base == null ? "" : base.trim());
		return sb.toString();
	}
}