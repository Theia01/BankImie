package fr.imie.bank.model;

import java.math.BigDecimal;

public class BankAccount {
	private int number;
	private BigDecimal balance;
	private Person owner;
	
	
	public BankAccount(int number,BigDecimal bal, Person owner) {
		super();
		this.number = number;
		this.setOwner(owner);
		this.balance = bal;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
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
