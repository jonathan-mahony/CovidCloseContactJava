package DBOperations;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil {;
	
	private static Connection conn ;	
	final static String URL = "jdbc:mysql://localhost:3306/project";
	final static String USERNAME = "root";
	final static String PASSWORD = "Eastferry12";
	
	 /**
	 * @return
	 * @throws SQLException
	 */
	public static Connection dbConnect() throws SQLException{
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");	           
	        }catch(ClassNotFoundException cnfe){
	            System.err.println("Error: "+cnfe.getMessage());
	        }
	        
	        conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	        return conn;
	    }
	
	/**
	 * @throws SQLException
	 */
	public static void dbDisconnected() throws SQLException{
		try {
			if(conn !=null && !conn.isClosed()) {
				conn.close();
			}
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	//this is for insert/update/delete operation
	/**
	 * @param sqlStmt
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void dbExecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
		Statement stmt = null;
		try {
			dbConnect();
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlStmt);
		}
		catch(SQLException e) {
			System.out.println("Problem occured at dbExecuteQuery operation"+e);
			throw e;
		}
		finally {
			if(stmt != null) {
				stmt.close();
			}
			dbDisconnected();
		}
	}
	
	
	//This if for retrieving records from database
	/**
	 * @param sqlQuery
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet dbExecute(String sqlQuery) throws SQLException, ClassNotFoundException{
		Statement stmt = null;
		ResultSet rs = null;
		CachedRowSet crs= null;
		
		try {
			dbConnect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(rs);
		}
		catch(SQLException e) {
			System.out.println("Error occured in dbExecute operation "+e);
			throw e;
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			dbDisconnected();
		}
		return crs;
	}
	
	
	
}
