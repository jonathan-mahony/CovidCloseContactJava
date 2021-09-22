package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Contact;
import Model.ContactDAO;
import Model.Person;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * @author jonathan
 * 
 *
 */

public class ContactController {
	
	/**
	 * @param person1
	 * @param person2
	 * @param date
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertContact(String person1, String person2, String date) throws ClassNotFoundException, SQLException{
		ContactDAO.insertContact(person1, person2, date);
		ObservableList<Contact> contactList = ContactDAO.getAllContacts();
		populateTable(contactList);
	}
	
	/**
	 * @param id
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deleteContact(String id) throws ClassNotFoundException, SQLException{
		try {
		ContactDAO.deleteContactById(id);
		ObservableList<Contact> contactList = ContactDAO.getAllContacts();
		populateTable(contactList);
	}catch(SQLException e) {
		System.out.println("Error occured while deleting"+e);
		e.printStackTrace();
		throw e;
		}
	}

	@SuppressWarnings("rawtypes")
	public static TableView tableview2 = new TableView<>();
	public static TableColumn<Contact, Integer> contactidCol = new TableColumn<>("ContactId");
	public static TableColumn<Contact, String> person1Col = new TableColumn<>("Person1");
	public static TableColumn<Contact, String> person2Col = new TableColumn<>("Person2");
	public static TableColumn<Contact, String> dateCol= new TableColumn<>("Date");
	
	
	/**
	 * @throws Exception
	 */
	public static void initialize() throws Exception{
		//tableview.getColumns().addAll(idCol, nameCol, phoneCol, emailCol);
		contactidCol.setCellValueFactory(cellData -> cellData.getValue().getPersonId().asObject());
		person1Col.setCellValueFactory(cellData -> cellData.getValue().getPerson1());
		person2Col.setCellValueFactory(cellData -> cellData.getValue().getPerson2());
		dateCol.setCellValueFactory(cellData -> cellData.getValue().getDate());
		ObservableList<Contact> contactList = ContactDAO.getAllContacts();
		populateTable(contactList);
	}
	
	/**
	 * @param personList
	 */
	@SuppressWarnings("unchecked")
	public static void populateTable(ObservableList<Contact> personList) {
		tableview2.setItems(personList);
	}
	
	
	/**
	 * @param Id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void searchContact(String Id) throws SQLException, ClassNotFoundException{
		ObservableList<Contact> list = ContactDAO.searchContact(Id);
		populateTable(list);
	}
	/**
	 * @param name
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void searchContactName(String name) throws SQLException, ClassNotFoundException{
		ObservableList<Contact> list = ContactDAO.searchContactbyname(name);
		populateTable(list);
	}
	
	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void searchAllContact() throws SQLException, ClassNotFoundException{
		ObservableList<Contact> contactList = ContactDAO.getAllContacts();
		populateTable(contactList);
	}
	
	/**
	 * @param name
	 * @param date
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void searhByDate(String name, String date) throws SQLException, ClassNotFoundException{
		ObservableList<Contact> contactList = ContactDAO.searchContactbydate(name, date);
		populateTable(contactList);
	}
	
	
	/**
	 * @param contactList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void SaveAction(ObservableList<Contact> contactList)throws SQLException, ClassNotFoundException {
		
		try {
			FileOutputStream f = new FileOutputStream(new File("SaveContactList.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(new ArrayList<Contact>(contactList));
			o.close();
			f.close();
			System.out.println("SAVE succesful");
		}catch(FileNotFoundException ex) {
			System.err.println("Save: File nopt found.");
			
		}catch(IOException ex) {
			System.err.println("Save: Error initializing stream.");
			ex.printStackTrace(); 
		}
		
	}
	
	
}
