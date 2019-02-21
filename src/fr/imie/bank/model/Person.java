package fr.imie.bank.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Person {
private int id;
private String firstname;
private String lastname;
private String email;
private LocalDate birthdate;
private ArrayList<BankAccount> accounts = new ArrayList<>();

public Person(String lastname, String firstname, String email, LocalDate birthday) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.birthdate = birthday;
}
à
public Person(int id,String lastname, String firstname, String email, LocalDate birthday) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.birthdate = birthday;
}


public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public LocalDate getBirthday() {
	return birthdate;
}
public void setBirthdate(LocalDate birthday) {
	this.birthdate = birthday;
}

public ArrayList<BankAccount> getAccounts() {
	return accounts;
}

public void setAccounts(ArrayList<BankAccount> accounts) {
	this.accounts = accounts;
}

public void addAccount(BankAccount bacc) {
	this.getAccounts().add(bacc);
}

	public String toString() {
	String s = this.getFirstname() + " " + this.getLastname() + " " + this.getEmail() + " " + this.getBirthday();
	return s;
}

public ArrayList<BankAccount> getAccounts() {
	return accounts;
}



public void setAccounts(ArrayList<BankAccount> accounts) {
	this.accounts = accounts;
}

public void addAccount(BankAccount bacc) {
	this.getAccounts().add(bacc);
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

}
