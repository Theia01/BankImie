package fr.imie.bank.model;
import java.util.Date;

public class Person {
private String firstname;
private String lastname;
private String email;
private Date birthdate;
private int id;

public Person(String lastname, String firstname, String email, Date birthday) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.birthdate = birthday;
}

public Person(int id, String lastname, String firstname, String email, Date birthday) {
	super();
	this.id = id;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.birthdate = birthday;
}

public Person() {
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
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
public Date getBirthday() {
	return birthdate;
}
public void setBirthdate(Date birthday) {
	this.birthdate = birthday;
}

public String toString() {
	String s = this.getFirstname() + " " + this.getLastname() + " " + this.getEmail() + " " + this.getBirthday();
	return s;
}

}
