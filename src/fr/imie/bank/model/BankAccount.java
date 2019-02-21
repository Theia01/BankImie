package fr.imie.bank.model;

public class BankAccount {
	private int number;
	private int id;
	
	
	
	public BankAccount(int number) {
		super();
		this.number = number;
	}
	public BankAccount(int number, int id) {
		super();
		this.number = number;
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
