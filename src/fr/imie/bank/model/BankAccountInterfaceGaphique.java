package fr.imie.bank.model;

import java.math.BigDecimal;

public class BankAccountInterfaceGaphique {
	private String number;
	private BigDecimal balance;
	private Person owner;
	
	
	public void BankAccount(String number,BigDecimal bal, Person owner) {
		this.number = number;
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
