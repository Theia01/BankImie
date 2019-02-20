package application;

import java.time.LocalDate;
import java.util.ArrayList;

import application.Person;

public class Repertoire {
	public ArrayList<Person> contacts = new ArrayList<>();

	public Repertoire() {
		super();
	}
	
	public void addContact(Person p) {
		this.contacts.add(p);
	}
	
	public void listContact() {
		for(int i=0;i<contacts.size();i++) {
			System.out.println(this.contacts.get(i).toString());
		}
	}
	
	public void modContact(int i, String fname, String lname, String email, LocalDate d) {
		this.contacts.get(i).setFirstname(fname);
		this.contacts.get(i).setLastname(lname);
		this.contacts.get(i).setEmail(email);
		this.contacts.get(i).setBirthdate(d);
	}
	
	public void deleteContact(int i) {
		this.contacts.remove(i);
	}
	
	
}
