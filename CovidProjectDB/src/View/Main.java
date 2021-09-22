package View;


import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import Controller.ContactController;
import Controller.PersonController;
import Model.Contact;
import Model.ContactDAO;
import Model.Person;
import Model.PersonDAO;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
	    launch(args);
	  }
	
	 @SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public void start(Stage primaryStage) {
		  try {
	Label heading = new Label("-------RecordPerson-------");
    heading.setStyle("-fx-font: 22 arial;");
    
    TextField name = new TextField();
	
    TextField phone = new TextField();
	
    TextField email = new TextField();
        
    Label name_label = new Label("Name:");
	
	Label phone_label = new Label("Phone:");
	
	Label email_label = new Label("Email:");
	
	Button RecordPerson_button =  new Button("-Record-");
	Button DeletePerson_button =  new Button("-Delete-");
	
	
	RecordPerson_button.setOnAction(e -> {
		try {
			PersonController.insertPerson(name.getText(), phone.getText(), email.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Person Has been added to database successfully");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Registration failed");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Registration failed");
			alert.showAndWait();
		}
		
	});
	
	DeletePerson_button.setOnAction(e -> {
		try {
			PersonController.deletePerson(name.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("DELETE successfully");
			alert.showAndWait();
		}catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Delete failed");
				alert.showAndWait();
			} catch (SQLException e1) {
				e1.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Delete failed");
				alert.showAndWait();
			}
			
		});
	
	Label heading2 = new Label("-------Update/Find-------");
    heading2.setStyle("-fx-font: 22 arial;");
    
	TextField searchId = new TextField();
	
	TextField changeEmail = new TextField();
	 Label person_id = new Label("Person ID:");
		
	Label person_email = new Label("New Email:");
	
	Button UpdatePerson_button =  new Button("-Update-");
	UpdatePerson_button.setOnAction(e -> {
		try {
			PersonController.updatePerson(searchId.getText(), changeEmail.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Update complete");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Update failed");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Update failed");
			alert.showAndWait();
		}
		
	});
	
	
	
	Button SearchPerson_button =  new Button("-Search By ID-");
	SearchPerson_button.setOnAction(e -> {
		try {
			PersonController.searchPerson(searchId.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has been found");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		}
	});
	
	
	Button SearchAllPerson_button =  new Button("-Display All-");
	SearchAllPerson_button.setOnAction(e -> {
		try {
			PersonController.searchAllPerson();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("All Contacts Displayed");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Error displaying all contacts");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Error displaying all contacts");
			alert.showAndWait();
		}
	});
	
	Button Save = new Button("Save to file");
	Save.setOnAction(e -> {
		try {
			ObservableList<Person> personList = PersonDAO.getAllRecords();
			PersonController.SaveAction(personList);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Saved to file");
			alert.showAndWait();
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	});
	
	TableView tableview = PersonController.tableview;
	tableview.getColumns().addAll(PersonController.idCol, PersonController.nameCol,PersonController.phoneCol, PersonController.emailCol);
	tableview.setColumnResizePolicy(tableview.CONSTRAINED_RESIZE_POLICY);
	
	
	HBox s1 = new HBox(10);
	s1.getChildren().addAll(heading);
	s1.setAlignment(Pos.TOP_CENTER);
	
	
	HBox s2 = new HBox(10);
	s2.getChildren().addAll(name_label);
	s2.getChildren().addAll(name);
	s2.setAlignment(Pos.TOP_CENTER);
	
	HBox s3 = new HBox(10);
	s3.getChildren().addAll(phone_label);
	s3.getChildren().addAll(phone);
	s3.setAlignment(Pos.TOP_CENTER);
	
	HBox s4 = new HBox(10);
	s4.getChildren().addAll(email_label);
	s4.getChildren().addAll(email);
	s4.setAlignment(Pos.TOP_CENTER);
	
	
	HBox s6 = new HBox(10);
	s6.getChildren().addAll(RecordPerson_button);
	s6.getChildren().addAll(DeletePerson_button);
	s6.setAlignment(Pos.TOP_CENTER);
	

	HBox s11 = new HBox(10);
	s11.getChildren().addAll(heading2);
	s11.setAlignment(Pos.CENTER);
	
	HBox s5 = new HBox(10);
	s5.getChildren().addAll(person_id);
	s5.getChildren().addAll(searchId);
	s5.setAlignment(Pos.CENTER);
	
	HBox s9 = new HBox(10);
	s9.getChildren().addAll(person_email);
	s9.getChildren().addAll(changeEmail);
	s9.setAlignment(Pos.CENTER);
	
	HBox s13 = new HBox(10);
	s13.setAlignment(Pos.BOTTOM_LEFT);
	s13.getChildren().addAll(Save);
	
	HBox s10 = new HBox(10);
	s10.getChildren().addAll(UpdatePerson_button);
	s10.getChildren().addAll(SearchPerson_button);
	s10.getChildren().addAll(SearchAllPerson_button);
	s10.setAlignment(Pos.CENTER);
	
	VBox s7 = new VBox(20);
	s7.getChildren().addAll(tableview);
	s7.setPrefWidth(600);
	s7.setAlignment(Pos.TOP_CENTER);
	
	VBox s8 = new VBox(10);
	//s8.setPadding(new Insets(0, 20, 20, 20));
	s8.getChildren().addAll(s1,s2,s3,s4,s6,s11,s5,s9,s10,s13);
	
	HBox layout1 = new HBox(20);
	layout1.getChildren().addAll(s8, s7);
	layout1.setAlignment(Pos.TOP_CENTER);
	
	
	Tab tab1 = new Tab();
	tab1.setText("	Add Contact to DataBase	");
	tab1.setContent(layout1);
	
	
	
	
	
	Label heading3 = new Label("-------Record Close Contacts-------");
    heading3.setStyle("-fx-font: 22 arial;");
    
    Label heading4 = new Label("-------Delete/Search-------");
    heading4.setStyle("-fx-font: 22 arial;");
    
    TextField closeID = new TextField();
    
    TextField closeName = new TextField();
	
    TextField person1 = new TextField();
	
	TextField person2 = new TextField();
	
	Label person1_label = new Label("Person1 = ");
		
	Label person2_label = new Label("Person 2 = ");
	
	Label closeid_label = new Label("Close Contact ID = ");
	
	Label searchName_label = new Label("Close Contact Name = ");
	
	
	Label date = new Label("Date Of Contact:");
	DatePicker d = new DatePicker();

	
	Button recordclose = new Button("Record close contact");
	Button Deleteclose =  new Button("Delete");	
	Button searchID = new Button("SearchById");
	
	recordclose.setOnAction(e -> {
		try {
			ContactController.insertContact(person1.getText(), person2.getText(), d.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Close Contact has been recorded");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Registration failed");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Registration failed");
			alert.showAndWait();
		}
		
	});
	
	Deleteclose.setOnAction(e -> {
		try {
			ContactController.deleteContact(closeID.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("DELETE successfully");
			alert.showAndWait();
		}catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Delete failed");
				alert.showAndWait();
			} catch (SQLException e1) {
				e1.printStackTrace();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Delete failed");
				alert.showAndWait();
			}
			
		});
	
	
	searchID.setOnAction(e -> {
		try {
			ContactController.searchContact(closeID.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has been found");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		}
	});
	
	Button searchbyname = new Button("Search By Name");
	searchbyname.setOnAction(e -> {
		try {
			ContactController.searchContactName(closeName.getText());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has been found");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		}
	});
	
	
	
	
	Button SearchAllContact_button =  new Button("Display All");
	SearchAllContact_button.setOnAction(e -> {
		try {
			ContactController.searchAllContact();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("All Contacts Displayed");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Error displaying all contacts");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Error displaying all contacts");
			alert.showAndWait();
		}
	});
	
	
	
	Button SearchDate_button = new Button("Search By Date + Name");
	SearchDate_button.setOnAction(e -> {
		try {
			ContactController.searhByDate(person1.getText(), d.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has been found");
			alert.showAndWait();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		} catch (SQLException e1) {
			e1.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Record has not been found");
			alert.showAndWait();
		}
	});
	
	
	Button Save2 = new Button("Save to file");
	Save2.setOnAction(e -> {
		try {
			ObservableList<Contact> contactList = ContactDAO.getAllContacts();
			ContactController.SaveAction(contactList);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Saved to file");
			alert.showAndWait();
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	});
	
	
	TableView tableview2 = ContactController.tableview2;
	tableview2.getColumns().addAll(ContactController.contactidCol, ContactController.person1Col,ContactController.person2Col, ContactController.dateCol);
	tableview2.setColumnResizePolicy(tableview.CONSTRAINED_RESIZE_POLICY);
	
	HBox r1 = new HBox(10);
	r1.getChildren().addAll(heading3);
	r1.setAlignment(Pos.TOP_CENTER);
	
	
	HBox r2 = new HBox(10);
	r2.getChildren().addAll(person1_label);
	r2.getChildren().addAll(person1);
	r2.setAlignment(Pos.TOP_CENTER);
	
	HBox r3 = new HBox(10);
	r3.getChildren().addAll(person2_label);
	r3.getChildren().addAll(person2);
	r3.setAlignment(Pos.TOP_CENTER);
	
	HBox r4 = new HBox(10);
	r4.getChildren().addAll(date);
	r4.getChildren().addAll(d);
	r4.setAlignment(Pos.TOP_CENTER);
	
	HBox r6 = new HBox(10);
	r6.getChildren().addAll(recordclose);
	r6.getChildren().addAll(SearchDate_button);
	r6.setAlignment(Pos.TOP_CENTER);
	
	HBox r12 = new HBox(10);
	r12.getChildren().addAll(heading4);
	r12.setAlignment(Pos.TOP_CENTER); 
	
	HBox r9 = new HBox(10);
	r9.getChildren().addAll(closeid_label);
	r9.getChildren().addAll(closeID);
	r9.setAlignment(Pos.TOP_CENTER);
	
	HBox r11 = new HBox(10);
	r11.getChildren().addAll(searchName_label);
	r11.getChildren().addAll(closeName);
	r11.setAlignment(Pos.TOP_CENTER);
	
	HBox r13 = new HBox(10);
	r13.setAlignment(Pos.BOTTOM_LEFT);
	r13.getChildren().addAll(Save2);
	
	
	HBox r10= new HBox(10);
	r10.getChildren().addAll(Deleteclose);
	r10.getChildren().addAll(searchID);
	r10.getChildren().addAll(searchbyname);
	r10.getChildren().addAll(SearchAllContact_button);
	r10.setAlignment(Pos.TOP_CENTER);
	
	VBox r7 = new VBox(20);
	r7.getChildren().addAll(tableview2);
	r7.setPrefWidth(400);
	r7.setAlignment(Pos.TOP_CENTER);
	
	VBox r8 = new VBox(10);
	r8.setPadding(new Insets(0, 20, 20, 20));
	r8.getChildren().addAll(r1,r2,r3, r4,r6,r12,r9,r11,r10,r13);
	
	HBox layout2 = new HBox(20);
	layout2.getChildren().addAll(r8, r7);
	layout2.setAlignment(Pos.TOP_CENTER);
	
	
	Tab tab2 = new Tab();
	tab2.setText("	Record Close Contact	");
	tab2.setContent(layout2);
	

	
	
	primaryStage.setTitle("	Covid Close Contact Application	");
		
	TabPane tabPane = new TabPane();
	tabPane.setStyle("-fx-background-color: ALICEBLUE ");
		
		//Adding tabs to the tab pane
	tabPane.getTabs().addAll(tab1, tab2);
	  
	  //Setting anchor pane as the layout
	  AnchorPane pane = new AnchorPane();
	  AnchorPane.setTopAnchor(tabPane, 15.0);
	  AnchorPane.setRightAnchor(tabPane, 15.0);
	  AnchorPane.setBottomAnchor(tabPane, 15.0);
	  AnchorPane.setLeftAnchor(tabPane, 15.0);
	  pane.getChildren().addAll(tabPane);
	  
	  //Setting the stage
	  final Scene scene = new Scene(pane, 1000, 800); 
		primaryStage.setScene(scene);
		primaryStage.show();
		PersonController.initialize();
		ContactController.initialize();
	
		  }catch (Exception e) {
				e.printStackTrace();
			}
		  }

}
