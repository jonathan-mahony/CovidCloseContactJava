package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {
	
	private IntegerProperty idProperty;
	
	private StringProperty person1Property;
	
	private StringProperty person2Property;
	
	private StringProperty dateProperty;

	public Contact() {
		this.idProperty = new SimpleIntegerProperty();
		this.person1Property = new SimpleStringProperty();
		this.person2Property = new SimpleStringProperty();
		this.dateProperty = new SimpleStringProperty();
	}

	public int getPerId() {
		return idProperty.get();
	}
	
	public void setPersonId(int id) {
		this.idProperty.set(id);
	}
	
	public IntegerProperty getPersonId() {
		return idProperty;
	}
	
	public String getPer1() {
		return person1Property.get();
	}
	
	public void setPerson1(String person1) {
		this.person1Property.set(person1);
	}
	
	public StringProperty getPerson1() {
		return person1Property;
	}
	
	public String getPer2() {
		return person2Property.get();
	}
	
	public void setPerson2(String person2) {
		this.person2Property.set(person2);
	}
	
	public StringProperty getPerson2() {
		return person2Property;
	}
	
	public String getDa() {
		return dateProperty.get();
	}
	
	public void setDate(String date) {
		this.dateProperty.set(date);
	}
	
	public StringProperty getDate() {
		return dateProperty;
	}
}
