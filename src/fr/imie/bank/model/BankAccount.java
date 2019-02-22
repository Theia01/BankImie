package fr.imie.bank.model;

import java.math.BigDecimal;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

public class BankAccount {
	private StringProperty number;
	private ObjectProperty<BigDecimal> balance;
	private PersonInterfaceGraphique owner;
	
	
	public BankAccount(String number,BigDecimal bal, Person owner) {
		super();
		this.number = new SimpleStringProperty(number);
		this.setOwner(owner);
		this.balance = bal;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
	
}
