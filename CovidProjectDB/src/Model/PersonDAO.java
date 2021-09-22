package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import DBOperations.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonDAO {
	
	
	/**
	 * @param name
	 * @param phone
	 * @param email
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertPerson(String name, String phone, String email) throws SQLException, ClassNotFoundException {
		
		String sql = "insert into person(name, phone, email) values('"+name+"', '"+phone+"', '"+email+"');";
		try {
			DBUtil.dbExecuteQuery(sql);
		}catch(SQLException e) {
			System.out.println("Exception occur while inserting the data "+e);
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/**
	 * @param id
	 * @param email
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void updatePerson(int id, String email) throws SQLException, ClassNotFoundException{
		
		String sql = "update person set email = '"+email+"' where id = '"+id+"' ";		
		try {
			
			DBUtil.dbExecuteQuery(sql);
		}catch(SQLException e) {
			System.out.println("Error occured while updating record");
			e.printStackTrace();
			throw e;
		}
			
		
	}
	
	
	/**
	 * @param name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deletePersonById(String name) throws ClassNotFoundException, SQLException{
		
		String sql = "delete from person where name = '"+name+"'";
		
		try {
			DBUtil.dbExecuteQuery(sql);
		}catch(SQLException e) {
			System.out.println("Error occured while deleteing"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ObservableList<Person> getAllRecords() throws SQLException, ClassNotFoundException{
		
		String sql = "select  * from person";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Person> personList = getPersonObjects(rsSet);
			return personList;
		}catch(SQLException e) {
			System.out.println("Error occured while fetching records from DB"+e);
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @param rsSet
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private static ObservableList<Person> getPersonObjects(ResultSet rsSet) throws SQLException, ClassNotFoundException {
		
		try {
			ObservableList<Person> personList = FXCollections.observableArrayList();
			while(rsSet.next()) {
				
				Person person = new Person();
				person.setPersonId(rsSet.getInt("id"));
				person.setPersonName(rsSet.getString("name"));
				person.setPersonPhone(rsSet.getString("phone"));
				person.setPersonEmail(rsSet.getString("email"));
				personList.add(person);
			}
			return personList;
			}catch(SQLException e) {
				System.out.println("Error occured while fetching records from DB"+e);
				e.printStackTrace();
				throw e;
			}
	}
	
	
	/**
	 * @param personId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ObservableList<Person> searchPerson(String personId) throws ClassNotFoundException, SQLException{
		
		String sql = "select * from person where id = '"+personId+"'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Person> list = getPersonObjects(rsSet);
			return list;
			
		}catch(SQLException e) {
			System.out.println("Error occured while searching for record"+e);
			e.printStackTrace();
			throw e;
		}
		
	}

}
