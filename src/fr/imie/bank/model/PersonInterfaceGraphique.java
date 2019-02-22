package fr.imie.bank.model;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonInterfaceGraphique {

	private IntegerProperty id;
	private StringProperty firstname;
	private StringProperty lastname;
	private StringProperty email;
	private ObjectProperty<LocalDate> birthday;
	private ArrayList<BankAccount> accounts = new ArrayList<>();

	
	
	public PersonInterfaceGraphique() {}

	public PersonInterfaceGraphique(String firstname, String lastname, String email, LocalDate birthday) {
		super();
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.email = new SimpleStringProperty(email);
		this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
	}


	public PersonInterfaceGraphique(int id, String firstname, String lastname, String email, LocalDate birthday) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.email = new SimpleStringProperty(email);
		this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
	}

	public String getFirstname() {return firstname.get();}
	public void setFirstname(String firstname) {this.firstname.set(firstname);}
	public StringProperty firstnameProperty() {return firstname;} 

	public String getLastname() {return lastname.get();}
	public void setLastname(String lastname) {this.lastname.set(lastname);}
	public StringProperty lastnameProperty() {return lastname;}

	public String getEmail() {return email.get();}
	public void setEmail(String email) {this.email.set(email);}
	public StringProperty emailProperty() {return email;}

	public LocalDate getBirthday() {return birthday.get();}
	public void setBirthday(LocalDate birthday) {this.birthday.set(birthday);}
	public ObjectProperty<LocalDate> birthdayProperty() {return birthday;}

	public ArrayList<BankAccount> getAccounts() {return accounts;}
	public void setAccounts(ArrayList<BankAccount> accounts) {this.accounts = accounts;}
	public void addAccount(BankAccount bacc) {this.getAccounts().add(bacc);}

		public String toString() {
		String s = this.getFirstname() + " " + this.getLastname() + " " + this.getEmail() + " " + this.getBirthday();
		return s;
	}

	public IntegerProperty getId() {
		return id;
	}

	public void setId(int id) {
		this.id.set(id);
	}

}
