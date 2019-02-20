package application;

import java.time.LocalDate;

public class Person {
private String firstname;
private String lastname;
private String email;
private LocalDate birthdate;


public Person(String lastname, String firstname, String email, LocalDate birthday) {
	super();
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

public String toString() {
	String s = this.getFirstname() + " " + this.getLastname() + " " + this.getEmail() + " " + this.getBirthday();
	return s;
}

}
