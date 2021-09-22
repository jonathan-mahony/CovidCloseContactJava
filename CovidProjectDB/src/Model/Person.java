package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

	
	/**
	 * 
	 */
	private IntegerProperty idProperty;
	
	private StringProperty nameProperty;
	
	private StringProperty phoneProperty;
	
	private StringProperty emailProperty;

	public Person() {
		this.idProperty = new SimpleIntegerProperty();
		this.nameProperty = new SimpleStringProperty();
		this.phoneProperty = new SimpleStringProperty();
		this.emailProperty = new SimpleStringProperty();
	}

	/**
	 * @return
	 */
	public int getPerId() {
		return idProperty.get();
	}
	
	/**
	 * @param id
	 */
	public void setPersonId(int id) {
		this.idProperty.set(id);
	}
	
	/**
	 * @return
	 */
	public IntegerProperty getPersonId() {
		return idProperty;
	}
	
	/**
	 * @return
	 */
	public String getPerName() {
		return nameProperty.get();
	}
	
	/**
	 * @param name
	 */
	public void setPersonName(String name) {
		this.nameProperty.set(name);
	}
	
	/**
	 * @return
	 */
	public StringProperty getPersonName() {
		return nameProperty;
	}
	
	/**
	 * @return
	 */
	public String getPerPhone() {
		return phoneProperty.get();
	}
	
	/**
	 * @param phone
	 */
	public void setPersonPhone(String phone) {
		this.phoneProperty.set(phone);
	}
	
	/**
	 * @return
	 */
	public StringProperty getPersonPhone() {
		return phoneProperty;
	}
	
	/**
	 * @return
	 */
	public String getPerEmail() {
		return emailProperty.get();
	}
	
	/**
	 * @param email
	 */
	public void setPersonEmail(String email) {
		this.emailProperty.set(email);
	}
	
	/**
	 * @return
	 */
	public StringProperty getPersonEmail() {
		return emailProperty;
	}
	
}
