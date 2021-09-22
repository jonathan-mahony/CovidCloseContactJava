package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import DBOperations.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactDAO {

		
	/**
	 * @param person1
	 * @param person2
	 * @param date
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void insertContact(String person1, String person2, String date) throws SQLException, ClassNotFoundException {
			
			String sql = "insert into closecontact(Person1, Person2, date) values('"+person1+"', '"+person2+"', '"+date+"');";
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
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deleteContactById(String id) throws ClassNotFoundException, SQLException{
			
			String sql = "delete from closecontact where ContactID = '"+id+"'";
			
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
	public static ObservableList<Contact> getAllContacts() throws SQLException, ClassNotFoundException{
			
			String sql = "select  * from closecontact";
			try {
				ResultSet rsSet = DBUtil.dbExecute(sql);
				ObservableList<Contact> contactList = getContactObjects(rsSet);
				return contactList;
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
	private static ObservableList<Contact> getContactObjects(ResultSet rsSet) throws SQLException, ClassNotFoundException {
			
			try {
				ObservableList<Contact> contactList = FXCollections.observableArrayList();
				while(rsSet.next()) {
					
					Contact contact = new Contact();
					contact.setPersonId(rsSet.getInt("ContactID"));
					contact.setPerson1(rsSet.getString("Person1"));
					contact.setPerson2(rsSet.getString("Person2"));
					contact.setDate(rsSet.getString("date"));
					contactList.add(contact);
				}
				return contactList;
				}catch(SQLException e) {
					System.out.println("Error occured while fetching records from DB"+e);
					e.printStackTrace();
					throw e;
				}
		}
	
	/**
	 * @param ContactId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ObservableList<Contact> searchContact(String ContactId) throws ClassNotFoundException, SQLException{
			
			String sql = "select * from closecontact where ContactID = '"+ContactId+"'";
			try {
				ResultSet rsSet = DBUtil.dbExecute(sql);
				ObservableList<Contact> list = getContactObjects(rsSet);
				return list;
				
			}catch(SQLException e) {
				System.out.println("Error occured while searching for record"+e);
				e.printStackTrace();
				throw e;
			}
	}
	
	
	/**
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ObservableList<Contact> searchContactbyname(String name) throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM closecontact WHERE Person1 = '"+name+"' OR Person2 ='"+name+"'";
		try {
			ResultSet rsSet = DBUtil.dbExecute(sql);
			ObservableList<Contact> list = getContactObjects(rsSet);
			return list;
			
		}catch(SQLException e) {
			System.out.println("Error occured while searching for record"+e);
			e.printStackTrace();
			throw e;
		}
	}	
	
	/**
	 * @param name
	 * @param date
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ObservableList<Contact> searchContactbydate(String name, String date) throws ClassNotFoundException, SQLException{
			
			String sql = "SELECT * FROM closecontact WHERE Person1 = '"+name+"' OR Person2 ='"+name+"' AND date >='"+date+"' ";
			try {
				ResultSet rsSet = DBUtil.dbExecute(sql);
				ObservableList<Contact> list = getContactObjects(rsSet);
				return list;
				
			}catch(SQLException e) {
				System.out.println("Error occured while searching for record"+e);
				e.printStackTrace();
				throw e;
			}
	}	
	
}
