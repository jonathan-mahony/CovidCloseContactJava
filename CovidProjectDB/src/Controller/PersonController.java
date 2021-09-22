package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Person;
import Model.PersonDAO;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonController {
	
	/**
	 * @param name
	 * @param phone
	 * @param email
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void insertPerson(String name, String phone, String email) throws ClassNotFoundException, SQLException{
		PersonDAO.insertPerson(name, phone, email);
		ObservableList<Person> personList = PersonDAO.getAllRecords();
		populateTable(personList);
	}

	
	/**
	 * @param id
	 * @param email
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void updatePerson(String id, String email) throws ClassNotFoundException, SQLException{
		try {
			PersonDAO.updatePerson(Integer.parseInt(id), email);
			ObservableList<Person> personList = PersonDAO.getAllRecords();
			populateTable(personList);
		}catch(SQLException e) {
			System.out.println("Error occured while updating"+e);
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * @param name
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void deletePerson(String name) throws ClassNotFoundException, SQLException{
		try {
		PersonDAO.deletePersonById(name);
		ObservableList<Person> personList = PersonDAO.getAllRecords();
		populateTable(personList);
	}catch(SQLException e) {
		System.out.println("Error occured while deleting"+e);
		e.printStackTrace();
		throw e;
	}
		
	}
	@SuppressWarnings("rawtypes")
	public static TableView tableview = new TableView<>();
    public static TableColumn<Person, Integer> idCol = new TableColumn<>("Id");
	public static TableColumn<Person, String> nameCol = new TableColumn<>("Name");
	public static TableColumn<Person, String> phoneCol = new TableColumn<>("Phone");
	public static TableColumn<Person, String> emailCol= new TableColumn<>("Email");
	
	
	/**
	 * @throws Exception
	 */
	public static void initialize() throws Exception{
		//tableview.getColumns().addAll(idCol, nameCol, phoneCol, emailCol);
		idCol.setCellValueFactory(cellData -> cellData.getValue().getPersonId().asObject());
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getPersonName());
		phoneCol.setCellValueFactory(cellData -> cellData.getValue().getPersonPhone());
		emailCol.setCellValueFactory(cellData -> cellData.getValue().getPersonEmail());
		ObservableList<Person> personList = PersonDAO.getAllRecords();
		populateTable(personList);
	}
	
	/**
	 * @param personList
	 */
	@SuppressWarnings("unchecked")
	public static void populateTable(ObservableList<Person> personList) {
		tableview.setItems(personList);
	}
	
	
	/**
	 * @param Id
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void searchPerson(String Id) throws SQLException, ClassNotFoundException{
		ObservableList<Person> list = PersonDAO.searchPerson(Id);
		populateTable(list);
	}
	
	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void searchAllPerson() throws SQLException, ClassNotFoundException{
		ObservableList<Person> personList = PersonDAO.getAllRecords();
		populateTable(personList);
	}
	
	
	
	public static void SaveAction(ObservableList<Person> personList)throws SQLException, ClassNotFoundException {
	
		try {
			FileOutputStream f = new FileOutputStream(new File("SavePersonList.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(new ArrayList<Person>(personList));
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
